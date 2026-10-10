package cosmic.textures.handlers.prisons;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class BanditBoxHandler {

    public static Identifier resolve(NbtCompound publicBukkitValues) {

        if (!publicBukkitValues.getString("cosmicprisons:bandit_box_key_tier").orElse("").isEmpty()) {
            return switch (publicBukkitValues.getString("cosmicprisons:bandit_box_key_tier").orElse("")) {
                case "SIMPLE" -> Identifier.of("cosmic-textures", "prisons/bandit-boxes/simple_key");
                case "SIMPLE_PLUS" -> Identifier.of("cosmic-textures", "prisons/bandit-boxes/simple_plus_key");
                case "ELITE" -> Identifier.of("cosmic-textures", "prisons/bandit-boxes/elite_key");
                case "ELITE_PLUS" -> Identifier.of("cosmic-textures", "prisons/bandit-boxes/elite_plus_key");
                case "LEGENDARY" -> Identifier.of("cosmic-textures", "prisons/bandit-boxes/legendary_key");
                case "LEGENDARY_PLUS" -> Identifier.of("cosmic-textures", "prisons/bandit-boxes/legendary_plus_key");
                case "GODLY" -> Identifier.of("cosmic-textures", "prisons/bandit-boxes/godly_key");
                case "GODLY_PLUS" -> Identifier.of("cosmic-textures", "prisons/bandit-boxes/godly_plus_key");
                default -> null;
            };
        }

        if (!publicBukkitValues.getString("cosmicprisons:bandit_box_tier").orElse("").isEmpty()) {
            return switch (publicBukkitValues.getString("cosmicprisons:bandit_box_tier").orElse("")) {
                case "SIMPLE" -> Identifier.of("cosmic-textures", "prisons/bandit-boxes/simple_bandit_box");
                case "SIMPLE_PLUS" -> Identifier.of("cosmic-textures", "prisons/bandit-boxes/simple_bandit_box_plus");
                case "ELITE" -> Identifier.of("cosmic-textures", "prisons/bandit-boxes/elite_bandit_box");
                case "ELITE_PLUS" -> Identifier.of("cosmic-textures", "prisons/bandit-boxes/elite_bandit_box_plus");
                case "LEGENDARY" -> Identifier.of("cosmic-textures", "prisons/bandit-boxes/legendary_bandit_box");
                case "LEGENDARY_PLUS" -> Identifier.of("cosmic-textures", "prisons/bandit-boxes/legendary_bandit_box_plus");
                case "GODLY" -> Identifier.of("cosmic-textures", "prisons/bandit-boxes/godly_bandit_box");
                case "GODLY_PLUS" -> Identifier.of("cosmic-textures", "prisons/bandit-boxes/godly_bandit_box_plus");
                default -> null;
            };
        }

        return null;

    }
}
