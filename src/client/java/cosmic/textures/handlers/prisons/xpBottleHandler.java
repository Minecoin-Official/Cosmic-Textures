package cosmic.textures.handlers.prisons;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class xpBottleHandler {

    public static Identifier resolve(NbtCompound publicBukkitValues) {

        String xpTier = publicBukkitValues.getString("cosmicprisons:xp_tier").orElse("");
        return switch (xpTier) {
            case "SIMPLE" -> Identifier.of("cosmic-textures", "prisons/xp-bottles/simple_xp_bottle");
            case "UNCOMMON" -> Identifier.of("cosmic-textures", "prisons/xp-bottles/uncommon_xp_bottle");
            case "ELITE" -> Identifier.of("cosmic-textures", "prisons/xp-bottles/elite_xp_bottle");
            case "ULTIMATE" -> Identifier.of("cosmic-textures", "prisons/xp-bottles/ultimate_xp_bottle");
            case "LEGENDARY" -> Identifier.of("cosmic-textures", "prisons/xp-bottles/legendary_xp_bottle");
            case "GODLY" -> Identifier.of("cosmic-textures", "prisons/xp-bottles/godly_xp_bottle");
            default -> null;
        };
    }
}
