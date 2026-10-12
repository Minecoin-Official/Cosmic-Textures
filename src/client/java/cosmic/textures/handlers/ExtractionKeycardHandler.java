package cosmic.textures.handlers;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class ExtractionKeycardHandler {

    public static Identifier resolve(NbtCompound nbt) {
        return switch (nbt.getString("keyColor").orElse("")) {
            case "RED" -> Identifier.of("cosmic-textures", "extraction_keycards/lab_keycard");
            case "GREEN" -> Identifier.of("cosmic-textures", "extraction_keycards/grove_keycard");
            case "BLUE" -> Identifier.of("cosmic-textures", "extraction_keycards/sewers_keycard");
            default -> null;
        };
    }
}
