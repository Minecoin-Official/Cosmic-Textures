package cosmic.textures.handlers;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class BaitHandler {

    public static Identifier resolve(NbtCompound nbt) {
        return switch (nbt.getString("fishType").orElse("")) {
            case "COD" -> Identifier.of("cosmic-textures", "bait/cod_bait_fragment");
            case "SALMON" -> Identifier.of("cosmic-textures", "bait/salmon_bait_fragment");
            case "PUFFERFISH" -> Identifier.of("cosmic-textures", "bait/pufferfish_bait_fragment");
            case "FROG" -> Identifier.of("cosmic-textures", "bait/frog_bait_fragment");
            case "AXOLOTL" -> Identifier.of("cosmic-textures", "bait/axolotl_bait_fragment");
            case "SQUID" -> Identifier.of("cosmic-textures", "bait/squid_bait_fragment");
            case "DOLPHIN" -> Identifier.of("cosmic-textures", "bait/dolphin_bait_fragment");
            case "TURTLE" -> Identifier.of("cosmic-textures", "bait/turtle_bait_fragment");
            case "GLOW_SQUID" -> Identifier.of("cosmic-textures", "bait/glow_squid_bait_fragment");
            case "GUARDIAN" -> Identifier.of("cosmic-textures", "bait/guardian_bait_fragment");
            default -> null;
        };
    }
}
