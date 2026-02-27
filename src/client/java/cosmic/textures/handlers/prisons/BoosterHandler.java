package cosmic.textures.handlers.prisons;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class BoosterHandler {

    public static Identifier resolve(NbtCompound publicBukkitValues) {
        return switch (publicBukkitValues.getString("cosmicprisons:booster_type").orElse("")) {
            case "xp" -> Identifier.of("cosmic-textures", "prisons/boosters/xp_booster");
            case "energy" -> Identifier.of("cosmic-textures", "prisons/boosters/energy_booster");
            default -> null;
        };
    }
}
