package cosmic.textures.handlers;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class FacilityCardHandler {

    public static Identifier resolve(NbtCompound nbt) {
        return switch (nbt.getString("gameType").orElse("")) {
            case "abandoned_ruins_facility" -> Identifier.of("cosmic-textures", "misc-adventure/facility_1");
            case "lost_wasteland_facility" -> Identifier.of("cosmic-textures", "misc-adventure/facility_2");
            case "demonic_realm_facility" -> Identifier.of("cosmic-textures", "misc-adventure/facility_3");
            default -> null;
        };
    }
}
