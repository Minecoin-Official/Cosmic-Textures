package cosmic.textures.handlers;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class VaultKeyHandler {

    public static Identifier resolve(NbtCompound nbt) {

        String gameType = nbt.getString("gameType").orElse("");
        String lootTier = nbt.getString("lootTier").orElse("");

        return switch (gameType) {
            case "abandoned_ruins" -> switch (lootTier) {
                case "NORMAL" -> Identifier.of("cosmic-textures", "vault_keys/chain_basic_vault_key");
                case "ELITE" -> Identifier.of("cosmic-textures", "vault_keys/chain_elite_vault_key");
                case "LEGENDARY" -> Identifier.of("cosmic-textures", "vault_keys/chain_legendary_vault_key");
                case "GODLY" -> Identifier.of("cosmic-textures", "vault_keys/chain_godly_vault_key");
                case "HEROIC" -> Identifier.of("cosmic-textures", "vault_keys/chain_heroic_vault_key");
                case "MYTHIC" -> Identifier.of("cosmic-textures", "vault_keys/chain_mythic_vault_key");
                default -> null;
            };
            case "lost_wasteland" -> switch (lootTier) {
                case "NORMAL" -> Identifier.of("cosmic-textures", "vault_keys/iron_basic_vault_key");
                case "ELITE" -> Identifier.of("cosmic-textures", "vault_keys/iron_elite_vault_key");
                case "LEGENDARY" -> Identifier.of("cosmic-textures", "vault_keys/iron_legendary_vault_key");
                case "GODLY" -> Identifier.of("cosmic-textures", "vault_keys/iron_godly_vault_key");
                case "HEROIC" -> Identifier.of("cosmic-textures", "vault_keys/iron_heroic_vault_key");
                case "MYTHIC" -> Identifier.of("cosmic-textures", "vault_keys/iron_mythic_vault_key");
                default -> null;
            };
            case "demonic_realm" -> switch (lootTier) {
                case "NORMAL" -> Identifier.of("cosmic-textures", "vault_keys/diamond_basic_vault_key");
                case "ELITE" -> Identifier.of("cosmic-textures", "vault_keys/diamond_elite_vault_key");
                case "LEGENDARY" -> Identifier.of("cosmic-textures", "vault_keys/diamond_legendary_vault_key");
                case "GODLY" -> Identifier.of("cosmic-textures", "vault_keys/diamond_godly_vault_key");
                case "HEROIC" -> Identifier.of("cosmic-textures", "vault_keys/diamond_heroic_vault_key");
                case "MYTHIC" -> Identifier.of("cosmic-textures", "vault_keys/diamond_mythic_vault_key");
                default -> null;
            };
            case "the_abyss" -> switch (lootTier) {
                case "NORMAL" -> Identifier.of("cosmic-textures", "vault_keys/nether_basic_vault_key");
                case "ELITE" -> Identifier.of("cosmic-textures", "vault_keys/nether_elite_vault_key");
                case "LEGENDARY" -> Identifier.of("cosmic-textures", "vault_keys/nether_legendary_vault_key");
                case "GODLY" -> Identifier.of("cosmic-textures", "vault_keys/nether_godly_vault_key");
                case "HEROIC" -> Identifier.of("cosmic-textures", "vault_keys/nether_heroic_vault_key");
                case "MYTHIC" -> Identifier.of("cosmic-textures", "vault_keys/nether_mythic_vault_key");
                default -> null;
            };
            default -> null;
        };
    }
}
