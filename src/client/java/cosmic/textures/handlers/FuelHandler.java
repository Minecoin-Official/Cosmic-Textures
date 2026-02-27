package cosmic.textures.handlers;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class FuelHandler {

    public static Identifier resolve(NbtCompound nbt) {
        return switch (nbt.getString("auto_bot_fuel").orElse("")) {
            case "BASE" -> Identifier.of("cosmic-textures", "fuel/fuel");
            case "COAL" -> Identifier.of("cosmic-textures", "fuel/coal_fuel");
            case "IRON" -> Identifier.of("cosmic-textures", "fuel/iron_fuel");
            case "COPPER_IRON" -> Identifier.of("cosmic-textures", "fuel/copper_fuel");
            case "OBSIDIAN" -> Identifier.of("cosmic-textures", "fuel/obsidian_fuel");
            case "NETHER" -> Identifier.of("cosmic-textures", "fuel/nether_fuel");
            default -> null;
        };
    }
}
