package cosmic.textures.handlers.prisons;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class ChargeOrbHandler {

    public static Identifier resolve(NbtCompound publicBukkitValues) {
        return switch (publicBukkitValues.getInt("cosmicprisons:charge_orb_percent").orElse(0)) {
            case 1 -> Identifier.of("cosmic-textures", "prisons/charge-orbs/charge_orb_1");
            case 2 -> Identifier.of("cosmic-textures", "prisons/charge-orbs/charge_orb_2");
            case 3 -> Identifier.of("cosmic-textures", "prisons/charge-orbs/charge_orb_3");
            case 4 -> Identifier.of("cosmic-textures", "prisons/charge-orbs/charge_orb_4");
            case 5 -> Identifier.of("cosmic-textures", "prisons/charge-orbs/charge_orb_5");
            case 6 -> Identifier.of("cosmic-textures", "prisons/charge-orbs/charge_orb_6");
            case 7 -> Identifier.of("cosmic-textures", "prisons/charge-orbs/charge_orb_7");
            case 8 -> Identifier.of("cosmic-textures", "prisons/charge-orbs/charge_orb_8");
            case 9 -> Identifier.of("cosmic-textures", "prisons/charge-orbs/charge_orb_9");
            case 10 -> Identifier.of("cosmic-textures", "prisons/charge-orbs/charge_orb_10");
            case 11 -> Identifier.of("cosmic-textures", "prisons/charge-orbs/charge_orb_11");
            case 12 -> Identifier.of("cosmic-textures", "prisons/charge-orbs/charge_orb_12");
            case 13 -> Identifier.of("cosmic-textures", "prisons/charge-orbs/charge_orb_13");
            case 14 -> Identifier.of("cosmic-textures", "prisons/charge-orbs/charge_orb_14");
            case 15 -> Identifier.of("cosmic-textures", "prisons/charge-orbs/charge_orb_15");
            case 16 -> Identifier.of("cosmic-textures", "prisons/charge-orbs/charge_orb_16");
            case 17 -> Identifier.of("cosmic-textures", "prisons/charge-orbs/charge_orb_17");
            case 18 -> Identifier.of("cosmic-textures", "prisons/charge-orbs/charge_orb_18");
            case 19 -> Identifier.of("cosmic-textures", "prisons/charge-orbs/charge_orb_19");
            case 20 -> Identifier.of("cosmic-textures", "prisons/charge-orbs/charge_orb_20");
            default -> null;
        };
    }
}
