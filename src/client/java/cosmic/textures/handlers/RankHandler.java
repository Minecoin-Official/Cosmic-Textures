package cosmic.textures.handlers;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class RankHandler {

    public static Identifier resolve(NbtCompound nbt) {
        byte rankTier = nbt.getByte("rankTier").orElse((byte) 0);

        return switch (rankTier) {
            case 2 -> Identifier.of("cosmic-textures", "misc/rank_2");
            case 3 -> Identifier.of("cosmic-textures", "misc/rank_3");
            // Can add celestial here
            // case 4 -> Identifier.of("cosmic-textures", "CELESTIAL");
            default -> null;
        };
    }
}
