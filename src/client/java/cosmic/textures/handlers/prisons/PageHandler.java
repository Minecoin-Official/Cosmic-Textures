package cosmic.textures.handlers.prisons;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class PageHandler {

    public static Identifier resolve(NbtCompound publicBukkitValues) {
        return switch (publicBukkitValues.getString("cosmicprisons:page_tier").orElse("")) {
            case "SIMPLE" -> Identifier.of("cosmic-textures", "prisons/pages/simple_page");
            case "UNCOMMON" -> Identifier.of("cosmic-textures", "prisons/pages/uncommon_page");
            case "ELITE" -> Identifier.of("cosmic-textures", "prisons/pages/elite_page");
            case "ULTIMATE" -> Identifier.of("cosmic-textures", "prisons/pages/ultimate_page");
            case "LEGENDARY" -> Identifier.of("cosmic-textures", "prisons/pages/legendary_page");
            case "GODLY" -> Identifier.of("cosmic-textures", "prisons/pages/godly_page");
            default -> null;
        };
    }
}
