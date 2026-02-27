package cosmic.textures.handlers;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class MarauderKeyHandler {

    public static Identifier resolve(NbtCompound nbt) {
        String gameType = nbt.getString("gameType").orElse("");
        String tier = nbt.getString("tier").orElse("");

        // Only return a texture if BOTH criteria match
        return switch (gameType) {
            case "abandoned_ruins" -> switch (tier) {
                case "HEROIC" -> Identifier.of("cosmic-textures", "keys/heroic_chain_key");
                case "MYTHIC" -> Identifier.of("cosmic-textures", "keys/mythic_chain_key");
                default -> null;
            };

            case "lost_wasteland" -> switch (tier) {
                case "HEROIC" -> Identifier.of("cosmic-textures", "keys/heroic_iron_key");
                case "MYTHIC" -> Identifier.of("cosmic-textures", "keys/mythic_iron_key");
                default -> null;
            };

            case "demonic_realm" -> switch (tier) {
                case "HEROIC" -> Identifier.of("cosmic-textures", "keys/heroic_diamond_key");
                case "MYTHIC" -> Identifier.of("cosmic-textures", "keys/mythic_diamond_key");
                default -> null;
            };

            case "abyss" -> switch (tier) {
                case "HEROIC" -> Identifier.of("cosmic-textures", "keys/heroic_abyss_key");
                case "MYTHIC" -> Identifier.of("cosmic-textures", "keys/mythic_abyss_key");
                default -> null;
            };

            default -> null;
        };
    }
}
