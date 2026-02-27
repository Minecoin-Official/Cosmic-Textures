package cosmic.textures.handlers;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class WhiteScrollHandler {

    public static Identifier resolve(NbtCompound nbt) {
        return switch (nbt.getString("tier").orElse("")) {
            case "STONE" -> Identifier.of("cosmic-textures", "white-scrolls/stone_white_scroll");
            case "IRON" -> Identifier.of("cosmic-textures", "white-scrolls/iron_white_scroll");
            case "DIAMOND" -> Identifier.of("cosmic-textures", "white-scrolls/diamond_white_scroll");
            case "NETHER" -> Identifier.of("cosmic-textures", "white-scrolls/nether_white_scroll");
            case "RANDOM" -> Identifier.of("cosmic-textures", "white-scrolls/random_white_scroll");
            default -> null;
        };
    }

}
