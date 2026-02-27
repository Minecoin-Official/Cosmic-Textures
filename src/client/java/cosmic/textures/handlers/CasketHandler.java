package cosmic.textures.handlers;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

import java.util.Objects;

public class CasketHandler {

    public static Identifier resolve(ItemStack stack) {
        NbtCompound nbt = Objects.requireNonNull(stack.get(DataComponentTypes.CUSTOM_DATA)).copyNbt();
        String chestKeyLootbox = nbt.getString("chestKeyLootbox").orElse("");
        String chestKeyName = nbt.getString("chestKeyName").orElse("");

        if (chestKeyLootbox.contains("abyssal")) return switch (chestKeyName) {
            case "basic" -> Identifier.of("cosmic-textures", "keys/casket_basic");
            case "celestial" -> Identifier.of("cosmic-textures", "keys/casket_celes");
            default -> null;
        };
        return null;
    }
}
