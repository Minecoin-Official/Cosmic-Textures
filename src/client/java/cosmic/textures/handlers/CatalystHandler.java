package cosmic.textures.handlers;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class CatalystHandler {

    public static Identifier resolve(NbtCompound nbt) {

        String tier = nbt.getString("tier").orElse("");
        String armorSetType = nbt.getString("armorSetType").orElse("");

        return switch (tier) {
            case "STONE" -> switch (armorSetType) {
                case "dwarf" -> Identifier.of("cosmic-textures", "catalysts/chain_dwarf");
                case "fairy" -> Identifier.of("cosmic-textures", "catalysts/chain_fairy");
                case "fishing" -> Identifier.of("cosmic-textures", "catalysts/chain_fisherman");
                case "koth" -> Identifier.of("cosmic-textures", "catalysts/chain_koth");
                case "mythic" -> Identifier.of("cosmic-textures", "catalysts/chain_mythic");
                case "boss" -> Identifier.of("cosmic-textures", "catalysts/chain_boss");
                case "stalker" -> Identifier.of("cosmic-textures", "catalysts/chain_stalker");
                case "bard" -> Identifier.of("cosmic-textures", "catalysts/chain_bard");
                case "mirage" -> Identifier.of("cosmic-textures", "catalysts/chain_mirage");
                case "intimidation" -> Identifier.of("cosmic-textures", "catalysts/chain_intimidation");
                default -> null;
            };
            case "IRON" -> switch (armorSetType) {
                case "dwarf" -> Identifier.of("cosmic-textures", "catalysts/iron_dwarf");
                case "fairy" -> Identifier.of("cosmic-textures", "catalysts/iron_fairy");
                case "fishing" -> Identifier.of("cosmic-textures", "catalysts/iron_fisherman");
                case "koth" -> Identifier.of("cosmic-textures", "catalysts/iron_koth");
                case "mythic" -> Identifier.of("cosmic-textures", "catalysts/iron_mythic");
                case "boss" -> Identifier.of("cosmic-textures", "catalysts/iron_boss");
                case "stalker" -> Identifier.of("cosmic-textures", "catalysts/iron_stalker");
                case "bard" -> Identifier.of("cosmic-textures", "catalysts/iron_bard");
                case "mirage" -> Identifier.of("cosmic-textures", "catalysts/iron_mirage");
                case "intimidation" -> Identifier.of("cosmic-textures", "catalysts/iron_intimidation");
                default -> null;
            };
            case "DIAMOND" -> switch (armorSetType) {
                case "dwarf" -> Identifier.of("cosmic-textures", "catalysts/diamond_dwarf");
                case "fairy" -> Identifier.of("cosmic-textures", "catalysts/diamond_fairy");
                case "fishing" -> Identifier.of("cosmic-textures", "catalysts/diamond_fisherman");
                case "koth" -> Identifier.of("cosmic-textures", "catalysts/diamond_koth");
                case "mythic" -> Identifier.of("cosmic-textures", "catalysts/diamond_mythic");
                case "boss" -> Identifier.of("cosmic-textures", "catalysts/diamond_boss");
                case "stalker" -> Identifier.of("cosmic-textures", "catalysts/diamond_stalker");
                case "bard" -> Identifier.of("cosmic-textures", "catalysts/diamond_bard");
                case "mirage" -> Identifier.of("cosmic-textures", "catalysts/diamond_mirage");
                case "intimidation" -> Identifier.of("cosmic-textures", "catalysts/diamond_intimidation");
                default -> null;
            };
            case "NETHER" -> switch (armorSetType) {
                case "dwarf" -> Identifier.of("cosmic-textures", "catalysts/nether_dwarf");
                case "fairy" -> Identifier.of("cosmic-textures", "catalysts/nether_fairy");
                case "fishing" -> Identifier.of("cosmic-textures", "catalysts/nether_fisherman");
                case "koth" -> Identifier.of("cosmic-textures", "catalysts/nether_koth");
                case "mythic" -> Identifier.of("cosmic-textures", "catalysts/nether_mythic");
                case "boss" -> Identifier.of("cosmic-textures", "catalysts/nether_boss");
                case "stalker" -> Identifier.of("cosmic-textures", "catalysts/nether_stalker");
                case "bard" -> Identifier.of("cosmic-textures", "catalysts/nether_bard");
                case "mirage" -> Identifier.of("cosmic-textures", "catalysts/nether_mirage");
                case "intimidation" -> Identifier.of("cosmic-textures", "catalysts/nether_intimidation");
                default -> null;
            };
            default -> null;
        };

    }
}
