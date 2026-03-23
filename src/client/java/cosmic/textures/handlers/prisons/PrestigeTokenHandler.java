package cosmic.textures.handlers.prisons;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class PrestigeTokenHandler {

    public static Identifier resolve(NbtCompound publicBukkitValues) {

        Integer prestigeTokenLevel = publicBukkitValues.getInt("cosmicprisons:prestige_token_level").orElse(0);
        return switch (prestigeTokenLevel) {
            case 1 -> Identifier.of("cosmic-textures", "prisons/pickaxe-prestige-tokens/prestige-token-1");
            case 2 -> Identifier.of("cosmic-textures", "prisons/pickaxe-prestige-tokens/prestige-token-2");
            case 3 -> Identifier.of("cosmic-textures", "prisons/pickaxe-prestige-tokens/prestige-token-3");
            case 4 -> Identifier.of("cosmic-textures", "prisons/pickaxe-prestige-tokens/prestige-token-4");
            case 5 -> Identifier.of("cosmic-textures", "prisons/pickaxe-prestige-tokens/prestige-token-5");
            case 6 -> Identifier.of("cosmic-textures", "prisons/pickaxe-prestige-tokens/prestige-token-6");
            case 7 -> Identifier.of("cosmic-textures", "prisons/pickaxe-prestige-tokens/prestige-token-7");
            case 8 -> Identifier.of("cosmic-textures", "prisons/pickaxe-prestige-tokens/prestige-token-8");
            case 9 -> Identifier.of("cosmic-textures", "prisons/pickaxe-prestige-tokens/prestige-token-9");
            case 10 -> Identifier.of("cosmic-textures", "prisons/pickaxe-prestige-tokens/prestige-token-10");
            default -> Identifier.of("cosmic-textures", "prisons/pickaxe-prestige-tokens/prestige-token");
        };
    }
}
