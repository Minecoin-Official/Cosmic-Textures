package cosmic.textures.handlers.prisons;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class PrisonsMysteryChestHandler {

    public static Identifier resolve(ItemStack stack) {

        NbtComponent customData = stack.get(DataComponentTypes.CUSTOM_DATA);
        if (customData == null) return null;
        NbtCompound nbt = customData.copyNbt();
        NbtCompound publicBukkitValues = nbt.getCompoundOrEmpty("PublicBukkitValues");
        String mysteryChestID = publicBukkitValues.getString("cosmicprisons:mysterychestid").orElse("");

        if (mysteryChestID.contains("random") && mysteryChestID.contains("enchantbook"))
            return switch (mysteryChestID) {
                case "special_randomsimpleenchantbook" -> Identifier.of("cosmic-textures", "prisons/books/simple_book");
                case "special_randomuncommonenchantbook" -> Identifier.of("cosmic-textures", "prisons/books/uncommon_book");
                case "special_randomeliteenchantbook" -> Identifier.of("cosmic-textures", "prisons/books/elite_book");
                case "special_randomultimateenchantbook" -> Identifier.of("cosmic-textures", "prisons/books/ultimate_book");
                case "special_randomlegendaryenchantbook" -> Identifier.of("cosmic-textures", "prisons/books/legendary_book");
                default -> null;
            };
        return null;
    }
}
