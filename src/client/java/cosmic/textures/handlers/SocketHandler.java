package cosmic.textures.handlers;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class SocketHandler {

    public static Identifier resolve(NbtCompound nbt) {
        String itemTier = nbt.getString("itemTier").orElse("");
        String enchantTier = nbt.getString("enchantTier").orElse("");
        Integer maxSocket = nbt.getInt("enchantTier").orElse(0);

        return switch (enchantTier) {
            case "BASIC" -> switch (itemTier) {
                case "STONE" -> Identifier.of("cosmic-textures", "sockets/basic1");
                case "IRON" -> Identifier.of("cosmic-textures", "sockets/basic2");
                case "DIAMOND" -> Identifier.of("cosmic-textures", "sockets/basic3");
                default -> Identifier.of("cosmic-textures", "sockets/basic0");
            };

            case "ELITE" -> switch (itemTier) {
                case "STONE" -> Identifier.of("cosmic-textures", "sockets/elite1");
                case "IRON" -> Identifier.of("cosmic-textures", "sockets/elite2");
                case "DIAMOND" -> Identifier.of("cosmic-textures", "sockets/elite3");
                default -> Identifier.of("cosmic-textures", "sockets/elite0");
            };

            case "LEGENDARY" -> switch (itemTier) {
                case "STONE" -> Identifier.of("cosmic-textures", "sockets/legendary1");
                case "IRON" -> Identifier.of("cosmic-textures", "sockets/legendary2");
                case "DIAMOND" -> Identifier.of("cosmic-textures", "sockets/legendary3");
                default -> Identifier.of("cosmic-textures", "sockets/legendary0");
            };

            case "GODLY" -> switch (itemTier) {
                case "STONE" -> Identifier.of("cosmic-textures", "sockets/godly1");
                case "IRON" -> Identifier.of("cosmic-textures", "sockets/godly2");
                case "DIAMOND" -> Identifier.of("cosmic-textures", "sockets/godly3");
                default -> Identifier.of("cosmic-textures", "sockets/godly0");
            };

            case "HEROIC" -> switch (itemTier) {
                case "STONE" -> Identifier.of("cosmic-textures", "sockets/heroic1");
                case "IRON" -> Identifier.of("cosmic-textures", "sockets/heroic2");
                case "DIAMOND" -> Identifier.of("cosmic-textures", "sockets/heroic3");
                default -> Identifier.of("cosmic-textures", "sockets/heroic0");
            };

            case "MYTHIC" -> switch (itemTier) {
                case "STONE" -> Identifier.of("cosmic-textures", "sockets/mythic1");
                case "IRON" -> Identifier.of("cosmic-textures", "sockets/mythic2");
                case "DIAMOND" -> Identifier.of("cosmic-textures", "sockets/mythic3");
                default -> Identifier.of("cosmic-textures", "sockets/mythic0");
            };

            case "NETHER" -> switch (maxSocket) {
                case 1 -> Identifier.of("cosmic-textures", "sockets/nether1");
                case 2 -> Identifier.of("cosmic-textures", "sockets/nether2");
                case 3 -> Identifier.of("cosmic-textures", "sockets/nether3");
                case 4 -> Identifier.of("cosmic-textures", "sockets/nether4");
                case 5 -> Identifier.of("cosmic-textures", "sockets/nether5");
                case 6 -> Identifier.of("cosmic-textures", "sockets/nether6");
                case 7 -> Identifier.of("cosmic-textures", "sockets/nether7");
                case 8 -> Identifier.of("cosmic-textures", "sockets/nether8");
                case 9 -> Identifier.of("cosmic-textures", "sockets/nether9");
                case 10 -> Identifier.of("cosmic-textures", "sockets/nether10");
                default -> null;
            };

            default -> null;
        };
    }
}
