package cosmic.textures.handlers;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class SpawnerShardHandler {

    public static Identifier resolve(NbtCompound nbt) {
        return switch (nbt.getString("shardType").orElse("")) {
            case "RABBIT" -> Identifier.of("cosmic-textures", "fragments/rabbit_shard");
            case "CHICKEN" -> Identifier.of("cosmic-textures", "fragments/chicken_shard");
            case "COW" -> Identifier.of("cosmic-textures", "fragments/cow_shard");
            case "SPIDER" -> Identifier.of("cosmic-textures", "fragments/spider_shard");
            case "ZOMBIE" -> Identifier.of("cosmic-textures", "fragments/zombie_frag");
            case "SKELETON" -> Identifier.of("cosmic-textures", "fragments/skeleton_shard");
            case "WITCH" -> Identifier.of("cosmic-textures", "fragments/witch_frag");
            case "ZOMBIFIED_PIGLIN" -> Identifier.of("cosmic-textures", "fragments/pigzombie_shard");
            case "BLAZE" -> Identifier.of("cosmic-textures", "fragments/blaze_shard");
            case "GHAST" -> Identifier.of("cosmic-textures", "fragments/ghast_shard");
            case "ENDERMAN" -> Identifier.of("cosmic-textures", "fragments/enderman_shard");
            default -> null;
        };
    }
}
