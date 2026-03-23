package cosmic.textures.handlers.prisons;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class PrisonsRandomizationScrollHandler {

    public static Identifier resolve(NbtCompound publicBukkitValues) {

        Integer scrollTier = publicBukkitValues.getInt("cosmicprisons:randomization_scroll_tier").orElse(0);
        return switch (scrollTier) {
            case 0 -> Identifier.of("cosmic-textures", "prisons/randomization-scrolls/simple_randomization_scroll");
            case 1 -> Identifier.of("cosmic-textures", "prisons/randomization-scrolls/uncommon_randomization_scroll");
            case 2 -> Identifier.of("cosmic-textures", "prisons/randomization-scrolls/elite_randomization_scroll");
            case 3 -> Identifier.of("cosmic-textures", "prisons/randomization-scrolls/ultimate_randomization_scroll");
            case 4 -> Identifier.of("cosmic-textures", "prisons/randomization-scrolls/legendary_randomization_scroll");
            case 5 -> Identifier.of("cosmic-textures", "prisons/randomization-scrolls/godly_randomization_scroll");
            default -> null;
        };
    }
}
