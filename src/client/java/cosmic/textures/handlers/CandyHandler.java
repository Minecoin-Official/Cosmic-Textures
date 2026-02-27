package cosmic.textures.handlers;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class CandyHandler {

    public static Identifier resolve(NbtCompound nbt) {
        return switch (nbt.getInt("maxLevel").orElse(0)) {
            case 2 -> Identifier.of("cosmic-textures", "candy/simple");
            case 4 -> Identifier.of("cosmic-textures", "candy/elite");
            case 6 -> Identifier.of("cosmic-textures", "candy/legendary");
            case 8 -> Identifier.of("cosmic-textures", "candy/godly");
            case 9 -> Identifier.of("cosmic-textures", "candy/heroic");
            case 10 -> Identifier.of("cosmic-textures", "candy/mythic");
            default -> null;
        };
    }
}
