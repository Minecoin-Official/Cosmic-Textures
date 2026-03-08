package cosmic.textures.handlers;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class CustomRecipeHandler {

    public static Identifier resolve(NbtCompound nbt) {

        // Recipe Page
        if (nbt.getString("cosmicItem").orElse("").equals("custom_recipe_page")) {
            return switch (nbt.getString("custom_recipe").orElse("")) {
                case "coal_fuel" -> Identifier.of("cosmic-textures", "recipes/coal_fuel_frag");
                case "iron_fuel" -> Identifier.of("cosmic-textures", "recipes/iron_fuel_frag");
                case "copper_fuel" -> Identifier.of("cosmic-textures", "recipes/copper_fuel_frag");
                case "OBSIDIAN_FUEL" -> Identifier.of("cosmic-textures", "recipes/obsidian_fuel_frag");
                case "nether_fuel" -> Identifier.of("cosmic-textures", "recipes/nether_fuel_frag");
                case "cheap_bread" -> Identifier.of("cosmic-textures", "recipes/cheap_bread_page");
                default -> Identifier.of("cosmic-textures", "recipes/unknown_frag");
            };
        }

        // Full Recipe
        return switch (nbt.getString("custom_recipe").orElse("")) {
            case "coal_fuel" -> Identifier.of("cosmic-textures", "recipes/coal_fuel_recipe");
            case "iron_fuel" -> Identifier.of("cosmic-textures", "recipes/iron_fuel_recipe");
            case "copper_fuel" -> Identifier.of("cosmic-textures", "recipes/copper_fuel_recipe");
            case "OBSIDIAN_FUEL" -> Identifier.of("cosmic-textures", "recipes/obsidian_fuel_recipe");
            case "nether_fuel" -> Identifier.of("cosmic-textures", "recipes/nether_fuel_recipe");
            case "cheap_bread" -> Identifier.of("cosmic-textures", "recipes/cheap_bread_full");
            default -> Identifier.of("cosmic-textures", "recipes/unknown_map");
        };

    }
}
