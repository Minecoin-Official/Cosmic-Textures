package cosmic.textures.handlers;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class RandomizationScrollHandler {

    public static Identifier resolve(NbtCompound nbt) {
        String tier = nbt.getString("tier").orElse("");
        String enchantTier = nbt.getString("enchantTier").orElse("");

        return switch (tier) {
            case "STONE" -> switch (enchantTier) {
                case "BASIC" -> Identifier.of("cosmic-textures", "randomization-scrolls/basic_stone");
                case "ELITE" -> Identifier.of("cosmic-textures", "randomization-scrolls/elite_stone");
                case "LEGENDARY" -> Identifier.of("cosmic-textures", "randomization-scrolls/legendary_stone");
                case "GODLY" -> Identifier.of("cosmic-textures", "randomization-scrolls/godly_stone");
                case "HEROIC" -> Identifier.of("cosmic-textures", "randomization-scrolls/heroic_stone");
                case "MYTHIC" -> Identifier.of("cosmic-textures", "randomization-scrolls/mythic_stone");
                default -> null;
            };

            case "IRON" -> switch (enchantTier) {
                case "BASIC" -> Identifier.of("cosmic-textures", "randomization-scrolls/basic_iron");
                case "ELITE" -> Identifier.of("cosmic-textures", "randomization-scrolls/elite_iron");
                case "LEGENDARY" -> Identifier.of("cosmic-textures", "randomization-scrolls/legendary_iron");
                case "GODLY" -> Identifier.of("cosmic-textures", "randomization-scrolls/godly_iron");
                case "HEROIC" -> Identifier.of("cosmic-textures", "randomization-scrolls/heroic_iron");
                case "MYTHIC" -> Identifier.of("cosmic-textures", "randomization-scrolls/mythic_iron");
                default -> null;
            };

            case "DIAMOND" -> switch (enchantTier) {
                case "BASIC" -> Identifier.of("cosmic-textures", "randomization-scrolls/basic_diamond");
                case "ELITE" -> Identifier.of("cosmic-textures", "randomization-scrolls/elite_diamond");
                case "LEGENDARY" -> Identifier.of("cosmic-textures", "randomization-scrolls/legendary_diamond");
                case "GODLY" -> Identifier.of("cosmic-textures", "randomization-scrolls/godly_diamond");
                case "HEROIC" -> Identifier.of("cosmic-textures", "randomization-scrolls/heroic_diamond");
                case "MYTHIC" -> Identifier.of("cosmic-textures", "randomization-scrolls/mythic_diamond");
                default -> null;
            };

            case "NETHER" -> switch (enchantTier) {
                case "BASIC" -> Identifier.of("cosmic-textures", "randomization-scrolls/basic_nether");
                case "ELITE" -> Identifier.of("cosmic-textures", "randomization-scrolls/elite_nether");
                case "LEGENDARY" -> Identifier.of("cosmic-textures", "randomization-scrolls/legendary_nether");
                case "GODLY" -> Identifier.of("cosmic-textures", "randomization-scrolls/godly_nether");
                case "HEROIC" -> Identifier.of("cosmic-textures", "randomization-scrolls/heroic_nether");
                case "MYTHIC" -> Identifier.of("cosmic-textures", "randomization-scrolls/mythic_nether");
                default -> null;
            };

            default -> null;
        };
    }
}
