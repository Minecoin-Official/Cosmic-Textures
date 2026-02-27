package cosmic.textures.handlers;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class ChunkBoosterHandler {

    public static Identifier resolve(NbtCompound nbt) {
        return switch (nbt.getString("boostType").orElse("")) {
            case "ISLAND_EXP" -> Identifier.of("cosmic-textures", "chunk-boosters/exp_booster");
            case "SPAWNER_SHARD_CHANCE" -> Identifier.of("cosmic-textures", "chunk-boosters/spawner_booster");
            case "SKILL_BOOST" -> Identifier.of("cosmic-textures", "chunk-boosters/skill_booster");
            default -> Identifier.of("cosmic-textures", "chunk-boosters/unknown_booster");
        };
    }
}
