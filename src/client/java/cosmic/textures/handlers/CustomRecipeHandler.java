package cosmic.textures.handlers;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CustomRecipeHandler {

    // Maps the color of the recipe-type name to its tier suffix.
    private static final Map<Formatting, String> TIER_BY_COLOR = Map.of(
            Formatting.DARK_GREEN, "chain",
            Formatting.GOLD, "iron",
            Formatting.YELLOW, "diamond",
            Formatting.RED, "nether"
    );

    public static Identifier resolve(ItemStack stack) {

        NbtCompound nbt = Objects.requireNonNull(stack.get(DataComponentTypes.CUSTOM_DATA)).copyNbt();

        // Recipe Page
        if (nbt.getString("cosmicItem").orElse("").equals("custom_recipe_page")) {
            return switch (nbt.getString("custom_recipe").orElse("")) {
                case "coal_fuel" -> Identifier.of("cosmic-textures", "recipes/coal_fuel_frag");
                case "iron_fuel" -> Identifier.of("cosmic-textures", "recipes/iron_fuel_frag");
                case "copper_fuel" -> Identifier.of("cosmic-textures", "recipes/copper_fuel_frag");
                case "obsidian_fuel", "OBSIDIAN_FUEL" -> Identifier.of("cosmic-textures", "recipes/obsidian_fuel_frag");
                case "nether_fuel" -> Identifier.of("cosmic-textures", "recipes/nether_fuel_frag");
                case "cheap_bread" -> Identifier.of("cosmic-textures", "recipes/cheap_bread_page");

                default -> Identifier.of("cosmic-textures", "recipes/" + withTier("unknown_frag", stack));
            };
        }

        // Full Recipe
        return switch (nbt.getString("custom_recipe").orElse("")) {
            case "coal_fuel" -> Identifier.of("cosmic-textures", "recipes/coal_fuel_recipe");
            case "iron_fuel" -> Identifier.of("cosmic-textures", "recipes/iron_fuel_recipe");
            case "copper_fuel" -> Identifier.of("cosmic-textures", "recipes/copper_fuel_recipe");
            case "obsidian_fuel" -> Identifier.of("cosmic-textures", "recipes/obsidian_fuel_recipe");
            case "nether_fuel" -> Identifier.of("cosmic-textures", "recipes/nether_fuel_recipe");
            case "cheap_bread" -> Identifier.of("cosmic-textures", "recipes/cheap_bread_full");
            default -> Identifier.of("cosmic-textures", "recipes/" + withTier("unknown_map", stack));
        };

    }

    /**
     * Appends "_<tier>" to baseName if a tier can be robustly determined from
     * the item's custom name, otherwise returns baseName unchanged.
     */
    private static String withTier(String baseName, ItemStack stack) {
        String tier = resolveTierSuffix(stack);
        return tier != null ? baseName + "_" + tier : baseName;
    }

    /**
     * Find the "(" and ")" boundary siblings and only reads the color of whatever is between them
     */
    private static String resolveTierSuffix(ItemStack stack) {
        Text customName = stack.getCustomName();
        if (customName == null) {
            return null;
        }

        List<Text> siblings = customName.getSiblings();

        int openIdx = -1;
        int closeIdx = -1;

        for (int i = 0; i < siblings.size(); i++) {
            String segment = siblings.get(i).getString();
            if (openIdx == -1 && segment.contains("(")) {
                openIdx = i;
            } else if (openIdx != -1 && segment.contains(")")) {
                closeIdx = i;
                break;
            }
        }

        // Need a "(" followed later by a ")" with at least one sibling between them.
        if (openIdx == -1 || closeIdx == -1 || closeIdx - openIdx < 2) {
            return null;
        }

        TextColor nameColor = null;
        for (int i = openIdx + 1; i < closeIdx; i++) {
            TextColor color = siblings.get(i).getStyle().getColor();
            if (color == null) {
                return null; // uncolored segment inside the name - don't guess
            }
            if (nameColor == null) {
                nameColor = color;
            } else if (nameColor.getRgb() != color.getRgb()) {
                return null; // mixed colors inside the name - ambiguous
            }
        }

        return tierFromColor(nameColor);
    }

    private static String tierFromColor(TextColor color) {
        for (Map.Entry<Formatting, String> entry : TIER_BY_COLOR.entrySet()) {
            Integer rgb = entry.getKey().getColorValue();
            if (rgb != null && rgb == color.getRgb()) {
                return entry.getValue();
            }
        }
        return null;
    }
}