package cosmic.textures.handlers;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class AdventureMapHandler {

    public static Identifier resolve(NbtCompound nbt) {

        String cosmicItemType = nbt.getString("cosmicItem").orElse("");

        if (cosmicItemType.isEmpty()) return null;

        return switch (cosmicItemType) {

            case "map_fragment" -> {
                String fragmentType = nbt.getString("fragmentType").orElse("");
                yield switch (fragmentType) {
                    case "lost_wasteland" -> Identifier.of("cosmic-textures", "maps/lost_wasteland_frag");
                    case "demonic_realm" -> Identifier.of("cosmic-textures", "maps/demonic_realm_frag");
                    case "the_abyss" -> Identifier.of("cosmic-textures", "maps/abyss_frag");
                    default -> null;
                };
            }

            case "advenMap" -> {
                String adventureMap = nbt.getString("adventureMap").orElse("");
                yield switch (adventureMap) {
                    case "lost_wasteland" -> Identifier.of("cosmic-textures", "maps/lost_wasteland_map");
                    case "demonic_realm" -> Identifier.of("cosmic-textures", "maps/demonic_realm_map");
                    case "the_abyss" -> Identifier.of("cosmic-textures", "maps/abyss_map");
                    default -> null;
                };
            }

            default -> null;
        };
    }
}
