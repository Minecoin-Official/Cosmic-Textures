package cosmic.textures.handlers;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class DustHandler {

    public static Identifier resolve(NbtCompound nbt) {
        return switch (nbt.getInt("tier").orElse(0)) {
            case 2 -> switch (nbt.getInt("chanceIncrease").orElse(0)) {
                case 1 -> Identifier.of("cosmic-textures", "dust/stone_dust_1");
                case 2 -> Identifier.of("cosmic-textures", "dust/stone_dust_2");
                case 3 -> Identifier.of("cosmic-textures", "dust/stone_dust_3");
                case 4 -> Identifier.of("cosmic-textures", "dust/stone_dust_4");
                case 5 -> Identifier.of("cosmic-textures", "dust/stone_dust_5");
                default -> Identifier.of("cosmic-textures", "dust/z_stone_dust");
            };

            case 4 -> switch (nbt.getInt("chanceIncrease").orElse(0)) {
                case 1 -> Identifier.of("cosmic-textures", "dust/iron_dust_1");
                case 2 -> Identifier.of("cosmic-textures", "dust/iron_dust_2");
                case 3 -> Identifier.of("cosmic-textures", "dust/iron_dust_3");
                case 4 -> Identifier.of("cosmic-textures", "dust/iron_dust_4");
                case 5 -> Identifier.of("cosmic-textures", "dust/iron_dust_5");
                default -> Identifier.of("cosmic-textures", "dust/z_iron_dust");
            };

            case 5 -> switch (nbt.getInt("chanceIncrease").orElse(0)) {
                case 1 -> Identifier.of("cosmic-textures", "dust/diamond_dust_1");
                case 2 -> Identifier.of("cosmic-textures", "dust/diamond_dust_2");
                case 3 -> Identifier.of("cosmic-textures", "dust/diamond_dust_3");
                case 4 -> Identifier.of("cosmic-textures", "dust/diamond_dust_4");
                case 5 -> Identifier.of("cosmic-textures", "dust/diamond_dust_5");
                default -> Identifier.of("cosmic-textures", "dust/z_diamond_dust");
            };

            case 6 -> switch (nbt.getInt("chanceIncrease").orElse(0)) {
                case 1 -> Identifier.of("cosmic-textures", "dust/nether_dust_1");
                case 2 -> Identifier.of("cosmic-textures", "dust/nether_dust_2");
                case 3 -> Identifier.of("cosmic-textures", "dust/nether_dust_3");
                case 4 -> Identifier.of("cosmic-textures", "dust/nether_dust_4");
                case 5 -> Identifier.of("cosmic-textures", "dust/nether_dust_5");
                default -> Identifier.of("cosmic-textures", "dust/z_nether_dust");
            };

            default -> null;
        };
    }
}
