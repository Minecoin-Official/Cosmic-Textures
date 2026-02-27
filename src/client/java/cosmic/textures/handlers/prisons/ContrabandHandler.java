package cosmic.textures.handlers.prisons;

import net.minecraft.util.Identifier;

public class ContrabandHandler {

    public static Identifier resolve(String customItemID) {
        return switch (customItemID) {
            case "contraband_simple" -> Identifier.of("cosmic-textures", "prisons/contrabands/simple_contraband");
            case "contraband_uncommon" -> Identifier.of("cosmic-textures", "prisons/contrabands/uncommon_contraband");
            case "contraband_elite" -> Identifier.of("cosmic-textures", "prisons/contrabands/elite_contraband");
            case "contraband_ultimate" -> Identifier.of("cosmic-textures", "prisons/contrabands/ultimate_contraband");
            case "contraband_legendary" -> Identifier.of("cosmic-textures", "prisons/contrabands/legendary_contraband");
            case "contraband_godly" -> Identifier.of("cosmic-textures", "prisons/contrabands/godly_contraband");
            default -> null;
        };
    }
}
