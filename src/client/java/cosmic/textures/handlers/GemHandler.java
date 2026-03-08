package cosmic.textures.handlers;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.regex.Pattern;
import java.util.Objects;

public class GemHandler {

    private static final Pattern ROMAN_NUMERAL = Pattern.compile("^(?i)(M{0,4}(CM|CD|D?C{0,3})"
            + "(XC|XL|L?X{0,3})(IX|IV|V?I{0,3}))$");

    public static Identifier resolve(ItemStack stack) {
        NbtCompound nbt = Objects.requireNonNull(stack.get(DataComponentTypes.CUSTOM_DATA)).copyNbt();
        String rarity = nbt.getString("tierRange").orElse("");
        byte randomTier = nbt.getByte("tier").orElse((byte) 0);
        byte openedTier = nbt.getCompoundOrEmpty("gems").getByte("tier").orElse((byte) 0);
        String cosmicItemType = nbt.getString("cosmicItem").orElse("");

        // Random Mythic Gems
        if (nbt.getString("mysteryChest").orElse("").contains("special_random_mythic_gem") && !nbt.getString("mysteryChest").orElse("").equals("special_random_mythic_gem_stone_diamond_static")) {
            if (nbt.getString("mysteryChest").orElse("").contains("stone")) return Identifier.of("cosmic-textures", "gems/stone_gem_boss");
            if (nbt.getString("mysteryChest").orElse("").contains("iron")) return Identifier.of("cosmic-textures", "gems/iron_gem_boss");
            if (nbt.getString("mysteryChest").orElse("").contains("diamond")) return Identifier.of("cosmic-textures", "gems/diamond_gem_boss");
            if (nbt.getString("mysteryChest").orElse("").contains("nether")) return Identifier.of("cosmic-textures", "gems/nether_gem_boss");
        }

        // Revealed Max/+1 Gems
        for (Text sibling : Objects.requireNonNull(stack.getCustomName()).getSiblings()) {
            if (ROMAN_NUMERAL.matcher(sibling.getString().trim()).matches() && sibling.getStyle().isBold()) {
                if (sibling.getStyle().isUnderlined()) {
                    return switch (openedTier) {
                        case 2 -> Identifier.of("cosmic-textures", "gems/stone_gem_plus1");
                        case 4 -> Identifier.of("cosmic-textures", "gems/iron_gem_plus1");
                        case 5 -> Identifier.of("cosmic-textures", "gems/diamond_gem_plus1");
                        case 6 -> Identifier.of("cosmic-textures", "gems/nether_gem_plus1");
                        default -> null;
                    };
                }
                return switch (openedTier) {
                    case 2 -> Identifier.of("cosmic-textures", "gems/stone_gem_max");
                    case 4 -> Identifier.of("cosmic-textures", "gems/iron_gem_max");
                    case 5 -> Identifier.of("cosmic-textures", "gems/diamond_gem_max");
                    case 6 -> Identifier.of("cosmic-textures", "gems/nether_gem_max");
                    default -> null;
                };
            }
        }

        // Unrevealed +1 Gem
        if (cosmicItemType.equals("random_extended_enchant_gem")) {
            return switch (randomTier) {
                case 2 -> Identifier.of("cosmic-textures", "gems/stone_gem_random_plus1");
                case 4 -> Identifier.of("cosmic-textures", "gems/iron_gem_random_plus1");
                case 5 -> Identifier.of("cosmic-textures", "gems/diamond_gem_random_plus1");
                case 6 -> Identifier.of("cosmic-textures", "gems/nether_gem_random_plus1");
                default -> null;
            };
        }

        // Opened gem (not random)
        if (cosmicItemType.equals("enchant_gem")) {
            return switch (openedTier) {
                case 2 -> Identifier.of("cosmic-textures", "gems/stone_gem");
                case 4 -> Identifier.of("cosmic-textures", "gems/iron_gem");
                case 5 -> Identifier.of("cosmic-textures", "gems/diamond_gem");
                case 6 -> Identifier.of("cosmic-textures", "gems/nether_gem");
                default -> null;
            };
        }

        return switch (randomTier) {
            case 2 -> switch (rarity) {
                case "MINOR" -> Identifier.of("cosmic-textures", "gems/stone_gem_minor");
                case "MAJOR" -> Identifier.of("cosmic-textures", "gems/stone_gem_major");
                case "PERFECT" -> Identifier.of("cosmic-textures", "gems/stone_gem_perfect");
                default -> null;
            };

            case 4 -> switch (rarity) {
                case "MINOR" -> Identifier.of("cosmic-textures", "gems/iron_gem_minor");
                case "MAJOR" -> Identifier.of("cosmic-textures", "gems/iron_gem_major");
                case "PERFECT" -> Identifier.of("cosmic-textures", "gems/iron_gem_perfect");
                default -> null;
            };

            case 5 -> switch (rarity) {
                case "MINOR" -> Identifier.of("cosmic-textures", "gems/diamond_gem_minor");
                case "MAJOR" -> Identifier.of("cosmic-textures", "gems/diamond_gem_major");
                case "PERFECT" -> Identifier.of("cosmic-textures", "gems/diamond_gem_perfect");
                default -> null;
            };

            case 6 -> switch (rarity) {
                case "MINOR" -> Identifier.of("cosmic-textures", "gems/nether_gem_minor");
                case "MAJOR" -> Identifier.of("cosmic-textures", "gems/nether_gem_major");
                case "PERFECT" -> Identifier.of("cosmic-textures", "gems/nether_gem_perfect");
                default -> null;
            };

            default -> null;
        };
    }
}
