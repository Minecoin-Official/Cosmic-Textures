package cosmic.textures.handlers;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class BotItemHandler {

    public static Identifier resolve(ItemStack stack) {
        if (stack.getCustomName() == null) return null;

        String name = stack.getCustomName().getString();
        if (name == null) return null;

        if (name.contains("Tutorial")) {
            if (name.contains("Miner") || name.contains("Mining")) return Identifier.of("cosmic-textures", "bots/tutorial_stone_bot_off");
            if (name.contains("Harvester") || name.contains("Harvesting")) return Identifier.of("cosmic-textures", "bots/tutorial_cobblestone_bot_off");
        }

        if (name.contains("Harvester") || name.contains("Harvesting")) {
            if (name.contains("Baby")) return Identifier.of("cosmic-textures", "bots/baby_cobblestone_bot_off");
            return Identifier.of("cosmic-textures", "bots/cobblestone_bot_off");
        }
        if (name.contains("Crafter") || name.contains("Crafting")) {
            if (name.contains("Baby")) return Identifier.of("cosmic-textures", "bots/baby_crafting_bot_on");
            return Identifier.of("cosmic-textures", "bots/crafting_bot_off");
        }
        if (name.contains("Fisher") || name.contains("Fishing")) {
            if (name.contains("Baby")) return Identifier.of("cosmic-textures", "bots/baby_fishing_bot_on");
            return Identifier.of("cosmic-textures", "bots/fishing_bot_off");
        }
        if (name.contains("Miner") || name.contains("Mining")) {
            if (name.contains("Baby")) return Identifier.of("cosmic-textures", "bots/baby_stone_bot_off");
            return Identifier.of("cosmic-textures", "bots/stone_bot_off");
        }
        if (name.contains("Smelter") || name.contains("Smelting")) {
            if (name.contains("Baby")) return Identifier.of("cosmic-textures", "bots/baby_furnace_bot_on");
            return Identifier.of("cosmic-textures", "bots/furnace_bot_off");
        }
        if (name.contains("Slayer") || name.contains("Slaying")) {
            if (name.contains("Baby")) return Identifier.of("cosmic-textures", "bots/baby_slayer_bot_on");
            return Identifier.of("cosmic-textures", "bots/slayer_bot_off");
        }
        if (name.contains("Sell") || name.contains("Seller")) return Identifier.of("cosmic-textures", "bots/chest_bot_on");
        if (name.contains("Bundle") || name.contains("Bundler")) return Identifier.of("cosmic-textures", "bots/compressor_bot_off");
        if (name.contains("Recycle") || name.contains("Recycler")) return Identifier.of("cosmic-textures", "bots/recycler_bot_off");

        return null;
    }
}
