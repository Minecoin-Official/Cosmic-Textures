package cosmic.textures.handlers;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class BeeHandler {

    public static Identifier resolve(NbtCompound nbt) {
        return switch (nbt.getString("bee_tier").orElse("")) {
            case "NORMAL" -> Identifier.of("cosmic-textures", "bees/bee_basic");
            case "ELITE" -> Identifier.of("cosmic-textures", "bees/bee_elite");
            case "LEGENDARY" -> Identifier.of("cosmic-textures", "bees/bee_legendary");
            case "GODLY" -> Identifier.of("cosmic-textures", "bees/bee_godly");
            case "HEROIC" -> Identifier.of("cosmic-textures", "bees/bee_heroic");
            case "MYTHIC" -> Identifier.of("cosmic-textures", "bees/bee_mythic");
            default -> null;
        };
    }
}
