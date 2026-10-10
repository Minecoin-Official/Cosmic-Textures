package cosmic.textures.handlers;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class CatalystExtractorHandler {

    public static Identifier resolve(NbtCompound nbt) {

        String tier = nbt.getString("tier").orElse("");

        return switch (tier) {
                case "Stone" -> Identifier.of("cosmic-textures", "catalyst_extractors/stone_catalyst_extractor");
                case "Iron" -> Identifier.of("cosmic-textures", "catalyst_extractors/iron_catalyst_extractor");
                case "Diamond" -> Identifier.of("cosmic-textures", "catalyst_extractors/diamond_catalyst_extractor");
                case "Nether" -> Identifier.of("cosmic-textures", "catalyst_extractors/nether_catalyst_extractor");
                default -> null;
        };
    };
}
