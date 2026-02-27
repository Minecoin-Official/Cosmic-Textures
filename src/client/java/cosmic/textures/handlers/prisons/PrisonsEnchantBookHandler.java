package cosmic.textures.handlers.prisons;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.component.type.LoreComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class PrisonsEnchantBookHandler {

    public static Identifier resolve(ItemStack stack) {

        NbtComponent customData = stack.get(DataComponentTypes.CUSTOM_DATA);
        if (customData == null) return null;
        NbtCompound nbt = customData.copyNbt();
        NbtCompound publicBukkitValues = nbt.getCompoundOrEmpty("PublicBukkitValues");
        String customItemID = publicBukkitValues.getString("cosmicprisons:custom_item_id").orElse("");

        if (customItemID.equals("mystery_enchant_book")) return switch (publicBukkitValues.getString("cosmicprisons:mystery_tier").orElse("")) {
            case "SIMPLE" -> Identifier.of("cosmic-textures", "prisons/books/simple_book");
            case "UNCOMMON" -> Identifier.of("cosmic-textures", "prisons/books/uncommon_book");
            case "ELITE" -> Identifier.of("cosmic-textures", "prisons/books/elite_book");
            case "ULTIMATE" -> Identifier.of("cosmic-textures", "prisons/books/ultimate_book");
            case "LEGENDARY" -> Identifier.of("cosmic-textures", "prisons/books/legendary_book");
            case "GODLY" -> Identifier.of("cosmic-textures", "prisons/books/godly_book");
            default -> null;
        };

        if (customItemID.equals("gear_enchant_book")) {

            int currentLevel = publicBukkitValues.getInt("cosmicprisons:gear_enchant_level").orElse(0);
            int maxLevel = extractMaxLevelFromLore(stack);

            boolean isMaxLevel = maxLevel > 0 && currentLevel >= maxLevel;

            if (isMaxLevel) {
                return switch (publicBukkitValues.getString("cosmicprisons:gear_enchant_tier").orElse("")) {
                    case "SIMPLE" -> Identifier.of("cosmic-textures", "prisons/books/simple_revealed_book_max");
                    case "UNCOMMON" -> Identifier.of("cosmic-textures", "prisons/books/uncommon_revealed_book_max");
                    case "ELITE" -> Identifier.of("cosmic-textures", "prisons/books/elite_revealed_book_max");
                    case "ULTIMATE" -> Identifier.of("cosmic-textures", "prisons/books/ultimate_revealed_book_max");
                    case "LEGENDARY" -> Identifier.of("cosmic-textures", "prisons/books/legendary_revealed_book_max");
                    case "GODLY" -> Identifier.of("cosmic-textures", "prisons/books/godly_revealed_book_max");
                    default -> null;
                };
            }

            return switch (publicBukkitValues.getString("cosmicprisons:gear_enchant_tier").orElse("")) {
                case "SIMPLE" -> Identifier.of("cosmic-textures", "prisons/books/simple_revealed_book");
                case "UNCOMMON" -> Identifier.of("cosmic-textures", "prisons/books/uncommon_revealed_book");
                case "ELITE" -> Identifier.of("cosmic-textures", "prisons/books/elite_revealed_book");
                case "ULTIMATE" -> Identifier.of("cosmic-textures", "prisons/books/ultimate_revealed_book");
                case "LEGENDARY" -> Identifier.of("cosmic-textures", "prisons/books/legendary_revealed_book");
                case "GODLY" -> Identifier.of("cosmic-textures", "prisons/books/godly_revealed_book");
                default -> null;
            };
        }

        return null;
    }

    private static int extractMaxLevelFromLore(ItemStack stack) {
        LoreComponent lore = stack.get(DataComponentTypes.LORE);
        if (lore == null) return -1;

        for (Text line : lore.lines()) {
            String raw = line.getString();
            if (raw.startsWith("Maximum Level")) {
                String roman = raw.replace("Maximum Level", "").trim();
                return romanToInt(roman);
            }
        }

        return -1;
    }

    private static int romanToInt(String roman) {
        if (roman == null || roman.isEmpty()) return -1;

        int total = 0;
        int prev = 0;

        for (int i = roman.length() - 1; i >= 0; i--) {
            int value = romanCharValue(roman.charAt(i));

            if (value < 0) return -1;

            if (value < prev) {
                total -= value;
            } else {
                total += value;
                prev = value;
            }
        }

        return total;
    }

    private static int romanCharValue(char c) {
        return switch (c) {
            case 'I' -> 1;
            case 'V' -> 5;
            case 'X' -> 10;
            case 'L' -> 50;
            case 'C' -> 100;
            case 'D' -> 500;
            case 'M' -> 1000;
            default -> -1;
        };
    }

}
