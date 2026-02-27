package cosmic.textures.handlers;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class BlackScrollHandler {

    public static Identifier resolve(NbtCompound nbt) {
        return switch (nbt.getString("tier").orElse("")) {
            case "Stone" -> Identifier.of("cosmic-textures", "black-scrolls/stone_black_scroll");
            case "Iron" -> Identifier.of("cosmic-textures", "black-scrolls/iron_black_scroll");
            case "Diamond" -> Identifier.of("cosmic-textures", "black-scrolls/diamond_black_scroll");
            case "Nether" -> Identifier.of("cosmic-textures", "black-scrolls/nether_black_scroll");
            default -> null;
        };
    }
}
