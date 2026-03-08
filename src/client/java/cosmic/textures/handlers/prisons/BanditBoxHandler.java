package cosmic.textures.handlers.prisons;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class BanditBoxHandler {

    public static Identifier resolve(NbtCompound publicBukkitValues) {
        return switch (publicBukkitValues.getString("cosmicprisons:bandit_box_key_tier").orElse("")) {
            case "SIMPLE" -> Identifier.of("cosmic-textures", "prisons/bandit-boxes/simple_key");
            case "UNCOMMON" -> Identifier.of("cosmic-textures", "prisons/bandit-boxes/uncommon_key");
            case "ELITE" -> Identifier.of("cosmic-textures", "prisons/bandit-boxes/elite_key");
            case "ULTIMATE" -> Identifier.of("cosmic-textures", "prisons/bandit-boxes/ultimate_key");
            case "LEGENDARY" -> Identifier.of("cosmic-textures", "prisons/bandit-boxes/legendary_key");
            case "GODLY" -> Identifier.of("cosmic-textures", "prisons/bandit-boxes/godly_key");
            default -> null;
        };
    }
}
