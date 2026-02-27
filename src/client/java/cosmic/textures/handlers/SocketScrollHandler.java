package cosmic.textures.handlers;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class SocketScrollHandler {

    public static Identifier resolve(NbtCompound nbt) {
        String tier = nbt.getString("tier").orElse("");
        Integer socketMax = nbt.getInt("socketMax").orElse(0);

        return switch (tier) {
            case "Stone" -> switch (socketMax) {
                case 1 -> Identifier.of("cosmic-textures", "socket-scrolls/stone_socket_scroll1");
                case 2 -> Identifier.of("cosmic-textures", "socket-scrolls/stone_socket_scroll2");
                case 3 -> Identifier.of("cosmic-textures", "socket-scrolls/stone_socket_scroll3");
                default -> null;
            };

            case "Iron" -> switch (socketMax) {
                case 1 -> Identifier.of("cosmic-textures", "socket-scrolls/iron_socket_scroll1");
                case 2 -> Identifier.of("cosmic-textures", "socket-scrolls/iron_socket_scroll2");
                case 3 -> Identifier.of("cosmic-textures", "socket-scrolls/iron_socket_scroll3");
                default -> null;
            };

            case "Diamond" -> switch (socketMax) {
                case 1 -> Identifier.of("cosmic-textures", "socket-scrolls/diamond_socket_scroll1");
                case 2 -> Identifier.of("cosmic-textures", "socket-scrolls/diamond_socket_scroll2");
                case 3 -> Identifier.of("cosmic-textures", "socket-scrolls/diamond_socket_scroll3");
                default -> null;
            };

            case "Nether" -> switch (socketMax) {
                case 1 -> Identifier.of("cosmic-textures", "socket-scrolls/nether_socket_scroll1");
                case 2 -> Identifier.of("cosmic-textures", "socket-scrolls/nether_socket_scroll2");
                case 3 -> Identifier.of("cosmic-textures", "socket-scrolls/nether_socket_scroll3");
                default -> null;
            };

            default -> null;
        };
    }
}
