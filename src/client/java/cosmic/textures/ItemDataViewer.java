package cosmic.textures;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.ScreenKeyboardEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.input.KeyInput;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

import java.util.Map;
import java.util.TreeMap;

/**
 * Lets the player hold a keybind and left-click an item in any inventory/GUI to
 * dump its relevant custom NBT data into chat instead of performing the click.
 * Each printed line is clickable to copy its value; "View more" expands the
 * full customData tree.
 */
public class ItemDataViewer {

    /** Keybind categories are their own registered objects as of 1.21.9+; create it once and reuse it. */
    private static final KeyBinding.Category CATEGORY = KeyBinding.Category.create(
            Identifier.of("cosmic-textures", "general")
    );

    /** Unbound by default; the player configures it in the vanilla Controls menu. */
    private static final KeyBinding INSPECT_KEY = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.cosmic-textures.inspect_item",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_UNKNOWN,
            CATEGORY
    ));

    private static final String VIEW_MORE_COMMAND = "/cosmictextures viewallcustomitemdata";

    /**
     * KeyBinding#isPressed() only reflects real key state while no Screen is open - vanilla
     * stops polling it the moment a container/inventory screen has keyboard focus. Since this
     * mod's whole job happens *inside* such a screen, we track the physical held-state ourselves
     * via per-screen keyboard events instead of relying on isPressed().
     */
    private static volatile boolean keyHeldInScreen = false;

    /** The customData of the last inspected item, cached for the "View more" command. */
    private static NbtCompound lastCustomData = null;

    private ItemDataViewer() {
    }

    /** Call once from your ClientModInitializer#onInitializeClient(). */
    public static void init() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) ->
                dispatcher.register(ClientCommandManager.literal("cosmictextures")
                        .then(ClientCommandManager.literal("viewallcustomitemdata")
                                .executes(context -> {
                                    printFullCustomData();
                                    return 1;
                                }))));

        ScreenEvents.AFTER_INIT.register((client, screen, scaledWidth, scaledHeight) -> {
            // Seed with the real physical state in case the key was already held before this
            // screen opened (isPressed() is still accurate right up until the screen exists).
            keyHeldInScreen = INSPECT_KEY.isPressed();

            ScreenKeyboardEvents.afterKeyPress(screen).register((Screen screen1, KeyInput input) -> {
                if (INSPECT_KEY.matchesKey(input)) {
                    keyHeldInScreen = true;
                }
            });
            ScreenKeyboardEvents.afterKeyRelease(screen).register((Screen screen1, KeyInput input) -> {
                if (INSPECT_KEY.matchesKey(input)) {
                    keyHeldInScreen = false;
                }
            });
        });
    }

    /**
     * Called from the ClientPlayerInteractionManager#clickSlot mixin.
     * Returns true if the click was intercepted (and should be cancelled).
     */
    public static boolean handleSlotClick(int slotId, int button, SlotActionType actionType) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return false;
        if (INSPECT_KEY.isUnbound() || !isInspectKeyHeld()) return false;
        if (slotId < 0) return false;
        if (actionType != SlotActionType.PICKUP || button != 0) return false;

        ScreenHandler handler = client.player.currentScreenHandler;
        if (handler == null) return false;

        ItemStack stack;
        try {
            stack = handler.getSlot(slotId).getStack();
        } catch (IndexOutOfBoundsException e) {
            return false;
        }

        printItemData(stack);
        return true;
    }

    /** True if the inspect key is currently held, using our own tracked state (see keyHeldInScreen). */
    private static boolean isInspectKeyHeld() {
        return keyHeldInScreen || INSPECT_KEY.isPressed();
    }

    private static void printItemData(ItemStack stack) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        if (stack.isEmpty()) {
            client.player.sendMessage(Text.literal("[Item Viewer] Empty slot.").formatted(Formatting.DARK_GRAY), false);
            return;
        }

        client.player.sendMessage(Text.literal("=== Item Data ===").formatted(Formatting.GOLD), false);

        String itemName = stack.getName().getString();
        client.player.sendMessage(copyableLine("Item Name", itemName), false);

        NbtComponent customDataComponent = stack.contains(DataComponentTypes.CUSTOM_DATA)
                ? stack.get(DataComponentTypes.CUSTOM_DATA)
                : null;

        if (customDataComponent == null) {
            lastCustomData = null;
            client.player.sendMessage(Text.literal("(No customData on this item)").formatted(Formatting.DARK_GRAY), false);
            return;
        }

        NbtCompound nbt = customDataComponent.copyNbt();
        lastCustomData = nbt;

        String cosmicItemType = nbt.getString("cosmicItem").orElse("");
        String mysteryChest = nbt.getString("mysteryChest").orElse("");
        String customItemID = nbt.getCompoundOrEmpty("PublicBukkitValues")
                .getString("cosmicprisons:custom_item_id").orElse("");

        if (!customItemID.isEmpty()) {
            client.player.sendMessage(copyableLine("customItemID", customItemID), false);
        }
        if (!cosmicItemType.isEmpty()) {
            client.player.sendMessage(copyableLine("cosmicItemType", cosmicItemType), false);
        }
        if (!mysteryChest.isEmpty()) {
            client.player.sendMessage(copyableLine("mysteryChest", mysteryChest), false);
        }

        client.player.sendMessage(viewMoreLine(), false);
    }

    private static void printFullCustomData() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        if (lastCustomData == null) {
            client.player.sendMessage(
                    Text.literal("[Item Viewer] No item data available - inspect an item first.")
                            .formatted(Formatting.GRAY), false);
            return;
        }

        Map<String, String> flattened = new TreeMap<>();
        flatten("", lastCustomData, flattened);

        client.player.sendMessage(Text.literal("=== Full customData ===").formatted(Formatting.GOLD), false);

        if (flattened.isEmpty()) {
            client.player.sendMessage(Text.literal("(empty)").formatted(Formatting.DARK_GRAY), false);
            return;
        }

        for (Map.Entry<String, String> entry : flattened.entrySet()) {
            client.player.sendMessage(copyableLine(entry.getKey(), entry.getValue()), false);
        }
    }

    /** Recursively walks a compound/list, producing dot/index-notation paths to every leaf value. */
    private static void flatten(String prefix, NbtElement element, Map<String, String> out) {
        if (element instanceof NbtCompound compound) {
            for (Map.Entry<String, NbtElement> entry : compound.entrySet()) {
                String path = prefix.isEmpty() ? entry.getKey() : prefix + "." + entry.getKey();
                flatten(path, entry.getValue(), out);
            }
        } else if (element instanceof NbtList list) {
            for (int i = 0; i < list.size(); i++) {
                flatten(prefix + "[" + i + "]", list.get(i), out);
            }
        } else if (element != null) {
            out.put(prefix, element.toString());
        }
    }

    /** Builds a "label: value" chat line where clicking anywhere on it copies value to the clipboard. */
    private static MutableText copyableLine(String label, String value) {
        MutableText line = Text.literal(label + ": ").formatted(Formatting.GRAY)
                .append(Text.literal(value).formatted(Formatting.AQUA));

        return line.styled(style -> style
                .withClickEvent(new ClickEvent.CopyToClipboard(value))
                .withHoverEvent(new HoverEvent.ShowText(Text.literal("Click to copy").formatted(Formatting.DARK_GRAY)))
                .withUnderline(true));
    }

    private static MutableText viewMoreLine() {
        MutableText line = Text.literal("View more").formatted(Formatting.YELLOW, Formatting.ITALIC);

        return line.styled(style -> style
                .withClickEvent(new ClickEvent.RunCommand(VIEW_MORE_COMMAND))
                .withHoverEvent(new HoverEvent.ShowText(Text.literal("Click to view all customData").formatted(Formatting.DARK_GRAY)))
                .withUnderline(true));
    }
}