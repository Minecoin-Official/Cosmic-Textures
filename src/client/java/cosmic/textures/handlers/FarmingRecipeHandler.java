package cosmic.textures.handlers;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class FarmingRecipeHandler {

    public static Identifier resolve(NbtCompound nbt) {

        String cosmicItemType = nbt.getString("cosmicItem").orElse("");

        if (cosmicItemType.isEmpty()) return null;

        return switch (cosmicItemType) {

            case "farming_recipe_page" -> {
                String pageType = nbt.getString("farming_recipe").orElse("");
                yield switch (pageType) {
                    case "CARROTS" -> Identifier.of("cosmic-textures", "recipes/carrot_frag");
                    case "SUGAR_CANE" -> Identifier.of("cosmic-textures", "recipes/sugar_cane_frag");
                    case "TORCHFLOWER" -> Identifier.of("cosmic-textures", "recipes/torchflower_frag");
                    case "BAMBOO" -> Identifier.of("cosmic-textures", "recipes/bamboo_frag");
                    case "MELONS" -> Identifier.of("cosmic-textures", "recipes/melon_frag");
                    case "BEET_ROOT" -> Identifier.of("cosmic-textures", "recipes/beetroot_frag");
                    case "TWISTING_VINE" -> Identifier.of("cosmic-textures", "recipes/twisting_vines_frag");
                    case "PUMPKINS" -> Identifier.of("cosmic-textures", "recipes/pumpkin_frag");
                    case "NETHER_WART" -> Identifier.of("cosmic-textures", "recipes/nether_wart_frag");
                    case "COCOA_BEANS" -> Identifier.of("cosmic-textures", "recipes/cocoa_frag");
                    case "WEEPING_VINES" -> Identifier.of("cosmic-textures", "recipes/weeping_vines_frag");
                    case "CHORUS_FRUIT" -> Identifier.of("cosmic-textures", "recipes/chorus_frag");
                    default -> null;
                };
            }

            case "farming_recipe" -> {
                String recipeType = nbt.getString("farmRecipe").orElse("");
                yield switch (recipeType) {
                    case "CARROTS" -> Identifier.of("cosmic-textures", "recipes/carrot_recipe");
                    case "SUGAR_CANE" -> Identifier.of("cosmic-textures", "recipes/sugar_cane_recipe");
                    case "TORCHFLOWER" -> Identifier.of("cosmic-textures", "recipes/torchflower_recipe");
                    case "BAMBOO" -> Identifier.of("cosmic-textures", "recipes/bamboo_recipe");
                    case "MELONS" -> Identifier.of("cosmic-textures", "recipes/melon_recipe");
                    case "BEET_ROOT" -> Identifier.of("cosmic-textures", "recipes/beetroot_recipe");
                    case "TWISTING_VINE" -> Identifier.of("cosmic-textures", "recipes/twisting_vines_recipe");
                    case "PUMPKINS" -> Identifier.of("cosmic-textures", "recipes/pumpkin_recipe");
                    case "NETHER_WART" -> Identifier.of("cosmic-textures", "recipes/nether_wart_recipe");
                    case "COCOA_BEANS" -> Identifier.of("cosmic-textures", "recipes/cocoa_recipe");
                    case "WEEPING_VINES" -> Identifier.of("cosmic-textures", "recipes/weeping_vines_recipe");
                    case "CHORUS_FRUIT" -> Identifier.of("cosmic-textures", "recipes/chorus_recipe");
                    default -> null;
                };
            }

            default -> null;
        };
    }
}
