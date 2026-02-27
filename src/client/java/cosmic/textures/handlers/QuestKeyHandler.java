package cosmic.textures.handlers;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class QuestKeyHandler {

    public static Identifier resolve(NbtCompound nbt) {
        String rawKey = nbt.getString("island_quest_key_adventure").orElse("").toUpperCase();
        String tier = nbt.getString("island_quest_key_tier").orElse("").toUpperCase();

        // Only return a texture if both criteria match
        if (rawKey.contains("ABANDONED_RUINS")) {
            return switch (tier) {
                case "HEROIC" -> Identifier.of("cosmic-textures", "keys/heroic_quest_key_stone");
                case "MYTHIC" -> Identifier.of("cosmic-textures", "keys/mythic_quest_key_stone");
                default -> null;
            };
        }

        if (rawKey.contains("LOST_WASTELAND")) {
            return switch (tier) {
                case "HEROIC" -> Identifier.of("cosmic-textures", "keys/heroic_quest_key_iron");
                case "MYTHIC" -> Identifier.of("cosmic-textures", "keys/mythic_quest_key_iron");
                default -> null;
            };
        }

        if (rawKey.contains("DEMONIC_REALM")) {
            return switch (tier) {
                case "HEROIC" -> Identifier.of("cosmic-textures", "keys/heroic_quest_key_diamond");
                case "MYTHIC" -> Identifier.of("cosmic-textures", "keys/mythic_quest_key_diamond");
                default -> null;
            };
        }

        if (rawKey.contains("ABYSS")) {
            return switch (tier) {
                case "HEROIC" -> Identifier.of("cosmic-textures", "keys/heroic_quest_key_nether");
                case "MYTHIC" -> Identifier.of("cosmic-textures", "keys/mythic_quest_key_nether");
                default -> null;
            };
        }

        // No matches
        return null;
    }
}
