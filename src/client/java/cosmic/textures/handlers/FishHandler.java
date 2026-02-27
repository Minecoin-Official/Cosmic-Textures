package cosmic.textures.handlers;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class FishHandler {

    public static Identifier resolve(NbtCompound nbt) {
        return switch (nbt.getString("fishable").orElse("")) {
            case "COD" -> Identifier.of("cosmic-textures", "fish/cod");
            case "SALMON" -> Identifier.of("cosmic-textures", "fish/salmon");
            case "PUFFERFISH" -> Identifier.of("cosmic-textures", "fish/pufferfish");
            case "FROG" -> Identifier.of("cosmic-textures", "fish/frog");
            case "AXOLOTL" -> Identifier.of("cosmic-textures", "fish/axolotl");
            case "SQUID" -> Identifier.of("cosmic-textures", "fish/squid");
            case "DOLPHIN" -> Identifier.of("cosmic-textures", "fish/dolphin");
            case "TURTLE" -> Identifier.of("cosmic-textures", "fish/turtle");
            case "GLOW_SQUID" -> Identifier.of("cosmic-textures", "fish/glow_squid");
            case "GUARDIAN" -> Identifier.of("cosmic-textures", "fish/guardian");
            default -> null;
        };
    }
}
