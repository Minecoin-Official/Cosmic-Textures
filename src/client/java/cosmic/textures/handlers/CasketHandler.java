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
        String mysteryChest = nbt.getString("mysteryChest").orElse("");

        // Caskets
        if (mysteryChest.contains("casket")) return switch (mysteryChest) {
            case "casket_abandoned_ruins_static" -> Identifier.of("cosmic-textures", "caskets/chain_casket_locked");
            case "casket_plagued_static" -> Identifier.of("cosmic-textures", "caskets/iron_casket_locked");
            case "casket_demonic_static" -> Identifier.of("cosmic-textures", "caskets/diamond_casket_locked");
            case "casket_abyssal_static" -> Identifier.of("cosmic-textures", "caskets/nether_casket_locked");
            default -> null;
        };

        // Keys
        return switch (chestKeyLootbox) {
            case "casket_abandoned_ruins_static" -> switch (chestKeyName) {
                case "basic" -> Identifier.of("cosmic-textures", "caskets/stone_key_basic");
                case "elite" -> Identifier.of("cosmic-textures", "caskets/stone_key_elite");
                case "godly" -> Identifier.of("cosmic-textures", "caskets/stone_key_godly");
                case "mythic" -> Identifier.of("cosmic-textures", "caskets/stone_key_mythic");
                case "celestial" -> Identifier.of("cosmic-textures", "caskets/stone_key_celestial");
                default -> null;
            };

            case "casket_lost_wasteland_static" -> switch (chestKeyName) {
                case "basic" -> Identifier.of("cosmic-textures", "caskets/iron_key_basic");
                case "elite" -> Identifier.of("cosmic-textures", "caskets/iron_key_elite");
                case "godly" -> Identifier.of("cosmic-textures", "caskets/iron_key_godly");
                case "mythic" -> Identifier.of("cosmic-textures", "caskets/iron_key_mythic");
                case "celestial" -> Identifier.of("cosmic-textures", "caskets/iron_key_celestial");
                default -> null;
            };

            case "casket_demonic_realm_static" -> switch (chestKeyName) {
                case "basic" -> Identifier.of("cosmic-textures", "caskets/diamond_key_basic");
                case "elite" -> Identifier.of("cosmic-textures", "caskets/diamond_key_elite");
                case "godly" -> Identifier.of("cosmic-textures", "caskets/diamond_key_godly");
                case "mythic" -> Identifier.of("cosmic-textures", "caskets/diamond_key_mythic");
                case "celestial" -> Identifier.of("cosmic-textures", "caskets/diamond_key_celestial");
                default -> null;
            };

            case "casket_abyssal_static" -> switch (chestKeyName) {
                case "basic" -> Identifier.of("cosmic-textures", "caskets/nether_key_basic");
                case "elite" -> Identifier.of("cosmic-textures", "caskets/nether_key_elite");
                case "godly" -> Identifier.of("cosmic-textures", "caskets/nether_key_godly");
                case "mythic" -> Identifier.of("cosmic-textures", "caskets/nether_key_mythic");
                case "celestial" -> Identifier.of("cosmic-textures", "caskets/nether_key_celestial");
                default -> null;
            };
            default -> null;
        };
    }
}
