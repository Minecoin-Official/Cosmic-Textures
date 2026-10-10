package cosmic.textures.handlers;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class ChunkBoosterHandler {

    public static Identifier resolve(NbtCompound nbt) {
        return switch (nbt.getString("boostType").orElse("")) {
            case "ISLAND_EXP" -> Identifier.of("cosmic-textures", "chunk-boosters/exp_booster");
            case "SPAWNER_SHARD_CHANCE" -> Identifier.of("cosmic-textures", "chunk-boosters/shard_booster");
            case "SKILL_BOOST" -> Identifier.of("cosmic-textures", "chunk-boosters/skill_booster");
            case "TROPHY_POINT_GAIN" -> Identifier.of("cosmic-textures", "chunk-boosters/trophy_booster");

            case "AUTO_MINER_RESOURCE_INCREASE" -> Identifier.of("cosmic-textures", "chunk-boosters/miner_booster");
            case "AUTO_FISHER_RESOURCE_INCREASE" -> Identifier.of("cosmic-textures", "chunk-boosters/fisher_booster");
            case "AUTO_HARVESTER_RESOURCE_INCREASE" -> Identifier.of("cosmic-textures", "chunk-boosters/harvester_booster");
            case "AUTO_SLAYER_RESOURCE_INCREASE" -> Identifier.of("cosmic-textures", "chunk-boosters/slayer_booster");
            default -> Identifier.of("cosmic-textures", "chunk-boosters/unknown_booster");
        };
    }
}
