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

        if (!nbt.getString("cosmicRecipeItem").orElse("").isEmpty()) {
            return switch (nbt.getString("cosmicRecipeItem").orElse("")) {
                // Fuel
                case "fuel" -> Identifier.of("cosmic-textures", "fuel/fuel");
                case "coal_fuel" -> Identifier.of("cosmic-textures", "fuel/coal_fuel");
                case "iron_fuel" -> Identifier.of("cosmic-textures", "fuel/iron_fuel");
                case "copper_fuel" -> Identifier.of("cosmic-textures", "fuel/copper_fuel");
                case "obsidian_fuel" -> Identifier.of("cosmic-textures", "fuel/obsidian_fuel");
                case "nether_fuel" -> Identifier.of("cosmic-textures", "fuel/nether_fuel");

                // Chain Recipes
                case "cheap_bread" -> Identifier.of("cosmic-textures", "custom-crafts/cheap_bread");
                case "fishers_pot_pie" -> Identifier.of("cosmic-textures", "custom-crafts/fishers_pot_pie");
                case "buckwheat_trail_crunch" -> Identifier.of("cosmic-textures", "custom-crafts/buckwheat_trail_crunch");
                case "coalfire_chicken_loaf" -> Identifier.of("cosmic-textures", "custom-crafts/coalfire_chicken_loaf");
                case "scribes_salmon_roll" -> Identifier.of("cosmic-textures", "custom-crafts/scribes_salmon_roll");
                case "berry_beef_tart" -> Identifier.of("cosmic-textures", "custom-crafts/berry_beef_tart");
                case "stonebaked_spinefin" -> Identifier.of("cosmic-textures", "custom-crafts/stonebaked_spinefin");
                case "basalt_hide_wrap" -> Identifier.of("cosmic-textures", "custom-crafts/basalt_hide_wrap");
                case "spider_carrot_goulash" -> Identifier.of("cosmic-textures", "custom-crafts/spider-carrot_goulash");
                case "pitcher_cream_custard" -> Identifier.of("cosmic-textures", "custom-crafts/pitcher_cream_custard");
                case "axolotl_silk_roll" -> Identifier.of("cosmic-textures", "custom-crafts/axolotl_silk_roll");

                default -> null;
            };
        }

        return null;
    }
}
