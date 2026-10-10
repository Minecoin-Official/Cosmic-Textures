package cosmic.textures.handlers;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class IslandBuddyHandler {

    public static Identifier resolve(NbtCompound nbt) {
        return switch (nbt.getString("buddy").orElse("")) {
            case "FASTER_TOOL_PROGRESSION" -> Identifier.of("cosmic-textures", "island_buddies/blaire");
            case "INCREASE_PLAYER_EXP" -> Identifier.of("cosmic-textures", "island_buddies/milo");
            case "INCREASE_ISLAND_EXP" -> Identifier.of("cosmic-textures", "island_buddies/owen");
            default -> null;
        };
    }
}
