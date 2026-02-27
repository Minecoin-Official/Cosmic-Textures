package cosmic.textures.handlers.prisons;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class PrisonsShardHandler {

    public static Identifier resolve(NbtCompound publicBukkitValues) {
        return switch (publicBukkitValues.getString("cosmicprisons:shard_tier").orElse("")) {
            case "simple" -> Identifier.of("cosmic-textures", "prisons/shards/simple_shard");
            case "uncommon" -> Identifier.of("cosmic-textures", "prisons/shards/uncommon_shard");
            case "elite" -> Identifier.of("cosmic-textures", "prisons/shards/elite_shard");
            case "ultimate" -> Identifier.of("cosmic-textures", "prisons/shards/ultimate_shard");
            case "legendary" -> Identifier.of("cosmic-textures", "prisons/shards/legendary_shard");
            case "godly" -> Identifier.of("cosmic-textures", "prisons/shards/godly_shard");
            default -> null;
        };
    }
}
