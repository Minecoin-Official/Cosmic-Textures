package cosmic.textures.handlers;

import net.minecraft.util.Identifier;

public class NodeFragmentHandler {

    public static Identifier resolve(String fragmentType) {
        return switch (fragmentType) {
            case "LOG" -> Identifier.of("cosmic-textures", "fragments/log");
            case "COBBLESTONE" -> Identifier.of("cosmic-textures", "fragments/cobblestone");
            case "COAL_ORE" -> Identifier.of("cosmic-textures", "fragments/coal_ore");
            case "IRON_ORE" -> Identifier.of("cosmic-textures", "fragments/iron_ore");
            case "COPPER_ORE" -> Identifier.of("cosmic-textures", "fragments/copper_ore");
            case "LAPIS_ORE" -> Identifier.of("cosmic-textures", "fragments/lapis_ore");
            case "DIAMOND_ORE" -> Identifier.of("cosmic-textures", "fragments/diamond_ore");
            case "GOLD_ORE" -> Identifier.of("cosmic-textures", "fragments/gold_ore");
            case "EMERALD_ORE" -> Identifier.of("cosmic-textures", "fragments/emerald_ore");
            case "OBSIDIAN" -> Identifier.of("cosmic-textures", "fragments/obsidian_ore");
            case "NETHER_QUARTZ" -> Identifier.of("cosmic-textures", "fragments/nether_quartz_ore");
            case "PRISMARINE" -> Identifier.of("cosmic-textures", "fragments/prismarine_ore");
            default -> null;
        };
    }
}
