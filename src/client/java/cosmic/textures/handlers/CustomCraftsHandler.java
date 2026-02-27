package cosmic.textures.handlers;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

import java.util.Objects;

public class CustomCraftsHandler {
    public static Identifier resolve(ItemStack stack) {

        NbtCompound nbt = Objects.requireNonNull(stack.get(DataComponentTypes.CUSTOM_DATA)).copyNbt();
        String cosmicItemType = nbt.getString("cosmicItem").orElse("");

        if (cosmicItemType.equals("honey_bottle")) {
            return switch (nbt.getString("pollenType").orElse("")) {
                case "TIER_1" -> Identifier.of("cosmic-textures", "custom-crafts/buckwheat_bottle");
                case "TIER_2" -> Identifier.of("cosmic-textures", "custom-crafts/wildflower_bottle");
                case "TIER_3" -> Identifier.of("cosmic-textures", "custom-crafts/highlands_bottle");
                case "TIER_4" -> Identifier.of("cosmic-textures", "custom-crafts/sidr_bottle");
                default -> null;
            };
        }
        return null;
    }
}
