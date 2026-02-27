package cosmic.textures.handlers.prisons;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class PrisonsDustHandler {

    public static Identifier resolve(NbtCompound publicBukkitValues) {
        return switch (publicBukkitValues.getString("cosmicprisons:pickaxe_dust_tier").orElse("")) {
            case "SIMPLE" -> Identifier.of("cosmic-textures", "prisons/dust/simple_dust");
            case "UNCOMMON" -> Identifier.of("cosmic-textures", "prisons/dust/uncommon_dust");
            case "ELITE" -> Identifier.of("cosmic-textures", "prisons/dust/elite_dust");
            case "ULTIMATE" -> Identifier.of("cosmic-textures", "prisons/dust/ultimate_dust");
            case "LEGENDARY" -> Identifier.of("cosmic-textures", "prisons/dust/legendary_dust");
            case "GODLY" -> Identifier.of("cosmic-textures", "prisons/dust/godly_dust");
            default -> null;
        };
    }
}
