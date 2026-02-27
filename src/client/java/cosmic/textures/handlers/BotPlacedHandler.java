package cosmic.textures.handlers;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class BotPlacedHandler {

    public static Identifier resolve(NbtCompound nbt) {
        return switch (nbt.getCompoundOrEmpty("cosmicData").toString()) {
            case "{itemSkinType:\"BOT_MINER_ON\"}" -> Identifier.of("cosmic-textures", "bots/stone_bot_off");
            case "{itemSkinType:\"BOT_MINER_OFF\"}" -> Identifier.of("cosmic-textures", "bots/stone_bot_on");
            case "{itemSkinType:\"BOT_HARVESTER_ON\"}" -> Identifier.of("cosmic-textures", "bots/cobblestone_bot_off");
            case "{itemSkinType:\"BOT_HARVESTER_OFF\"}" -> Identifier.of("cosmic-textures", "bots/cobblestone_bot_on");
            case "{itemSkinType:\"BOT_FURNACE_ON\"}" -> Identifier.of("cosmic-textures", "bots/furnace_bot_off");
            case "{itemSkinType:\"BOT_FURNACE_OFF\"}" -> Identifier.of("cosmic-textures", "bots/furnace_bot_on");
            case "{itemSkinType:\"BOT_SLAYER_ON\"}" -> Identifier.of("cosmic-textures", "bots/slayer_bot_off");
            case "{itemSkinType:\"BOT_SLAYER_OFF\"}" -> Identifier.of("cosmic-textures", "bots/slayer_bot_on");
            case "{itemSkinType:\"BOT_CHEST_OFF\"}" -> Identifier.of("cosmic-textures", "bots/chest_bot_off");
            case "{itemSkinType:\"BOT_CHEST_ON\"}" -> Identifier.of("cosmic-textures", "bots/chest_bot_on");
            case "{itemSkinType:\"BOT_RECYCLER_ON\"}" -> Identifier.of("cosmic-textures", "bots/recycler_bot_off");
            case "{itemSkinType:\"BOT_RECYCLER_OFF\"}" -> Identifier.of("cosmic-textures", "bots/recycler_bot_on");
            case "{itemSkinType:\"BOT_FISHING_ON\"}" -> Identifier.of("cosmic-textures", "bots/fishing_bot_off");
            case "{itemSkinType:\"BOT_FISHING_OFF\"}" -> Identifier.of("cosmic-textures", "bots/fishing_bot_on");
            case "{itemSkinType:\"BOT_CRAFTER_ON\"}" -> Identifier.of("cosmic-textures", "bots/crafting_bot_off");
            case "{itemSkinType:\"BOT_CRAFTER_OFF\"}" -> Identifier.of("cosmic-textures", "bots/crafting_bot_on");
            case "{itemSkinType:\"BOT_BUNDLER_ON\"}" -> Identifier.of("cosmic-textures", "bots/compressor_bot_off");
            case "{itemSkinType:\"BOT_BUNDLER_OFF\"}" -> Identifier.of("cosmic-textures", "bots/compressor_bot_on");
            default -> null;
        };
    }
}
