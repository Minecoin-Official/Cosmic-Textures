package cosmic.textures;

import cosmic.textures.handlers.*;
import cosmic.textures.handlers.prisons.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public class CosmicItemModelResolver {

    private static final Map<String, Function<NbtCompound, Identifier>> HANDLERS = new HashMap<>();

    static {
        HANDLERS.put("spawner_shard", SpawnerShardHandler::resolve);
        HANDLERS.put("rare_candy", CandyHandler::resolve);
        HANDLERS.put("player_rank", RankHandler::resolve);
        HANDLERS.put("enchantment_dust", DustHandler::resolve);
        HANDLERS.put("socket_expander", SocketHandler::resolve);
        HANDLERS.put("marauder_key", MarauderKeyHandler::resolve);
        HANDLERS.put("island_quest_key", QuestKeyHandler::resolve);
        HANDLERS.put("randomization_scroll", RandomizationScrollHandler::resolve);
        HANDLERS.put("whitescroll", WhiteScrollHandler::resolve);
        HANDLERS.put("map_fragment", AdventureMapHandler::resolve);
        HANDLERS.put("advenMap", AdventureMapHandler::resolve);
        HANDLERS.put("bee_worker", BeeHandler::resolve);
        HANDLERS.put("farming_recipe_page", FarmingRecipeHandler::resolve);
        HANDLERS.put("farming_recipe", FarmingRecipeHandler::resolve);
        HANDLERS.put("socket_scroll", SocketScrollHandler::resolve);
        HANDLERS.put("island_booster_module", ChunkBoosterHandler::resolve);
        HANDLERS.put("black_scroll", BlackScrollHandler::resolve);
        HANDLERS.put("custom_recipe", CustomRecipeHandler::resolve);
        HANDLERS.put("custom_recipe_page", CustomRecipeHandler::resolve);
    }

    public static Identifier resolve(ItemStack stack) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return null;
        if (client.getCurrentServerEntry() == null) return null;

        // Sky Logic
        if (stack.contains(DataComponentTypes.CUSTOM_DATA)) {
            NbtComponent customData = stack.get(DataComponentTypes.CUSTOM_DATA);
            if (customData == null) return null;
            NbtCompound nbt = customData.copyNbt();

            String cosmicItemType = nbt.getString("cosmicItem").orElse("");
            String persistentItemType = nbt.getString("persistentItem").orElse("");

            Function<NbtCompound, Identifier> handler = null;

            if (!cosmicItemType.isEmpty()) handler = HANDLERS.get(cosmicItemType);
            if (handler == null && !persistentItemType.isEmpty())
                handler = HANDLERS.get(persistentItemType);

            if (handler != null) return handler.apply(nbt);

            // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            // Other miscellaneous item textures ⬇️

            // Auto Bots (placed)
            if ((stack.isOf(Items.DISPENSER)
                    || stack.isOf(Items.DROPPER))
                    && nbt.getCompoundOrEmpty("cosmicData").toString().contains("BOT"))
                return BotPlacedHandler.resolve(nbt);

            // Auto Bots (held item)
            if (stack.isOf(Items.ARMOR_STAND)
                    && stack.getCustomName() != null
                    && stack.getCustomName().getString().contains("Auto")) {

                Identifier botModel = BotItemHandler.resolve(stack);
                if (botModel != null) {
                    return botModel;
                }
            }

            // Auto Bot Upgrades
            if (persistentItemType.contains("auto_bot_upgrade") ||
                    cosmicItemType.contains("auto_bot_upgrade"))
                return BotUpgradeHandler.resolve(stack);
            // Gems
            if (cosmicItemType.equals("random_enchant_gem") ||
                    cosmicItemType.equals("enchant_gem") ||
                    cosmicItemType.equals("random_extended_enchant_gem") ||
                    nbt.getString("mysteryChest").orElse("").contains("special_random_mythic_gem"))
                return GemHandler.resolve(stack);

            // Custom Crafts
            if (cosmicItemType.equals("honey_bottle")) return CustomCraftsHandler.resolve(stack);

            // Spawners
            if (stack.isOf(Items.SPAWNER)
            ) return SpawnerHandler.resolve(stack);

            if (!nbt.getString("nodeFragment").orElse("").isEmpty()) {
                return NodeFragmentHandler.resolve(nbt.getString("nodeFragment").orElse(""));
            }

            // Realm portals and realm portal shards
            if (cosmicItemType.equals("realm_portal_shard") || cosmicItemType.equals("realm_portal")) return RealmPortalHandler.resolve(stack);

            // Admin tools
            if (stack.getCustomName() != null && stack.getCustomName().getString().contains("Quantum Sabre ("))
                return Identifier.of("cosmic-textures", "admin-tools/quantum_sabre");
            if (stack.getCustomName() != null && stack.getCustomName().getString().contains("Chrono Scythe ("))
                return Identifier.of("cosmic-textures", "admin-tools/chrono_scythe");
            if (stack.getCustomName() != null && stack.getCustomName().getString().contains("Obsidian Drill ("))
                return Identifier.of("cosmic-textures", "admin-tools/obsidian_drill");

            // Repair Scrolls
            if (cosmicItemType.equals("repair_scroll")) return RepairScrollHandler.resolve(stack);

            // Casket Keys
            if (nbt.getString("chestKeyLootbox").orElse("").contains("casket")) return CasketHandler.resolve(stack);

            // Fuel
            if (!nbt.getString("auto_bot_fuel").orElse("").isEmpty()) return FuelHandler.resolve(nbt);

            // Fish
            if (!nbt.getString("fishable").orElse("").isEmpty()) return FishHandler.resolve(nbt);

            // Enchantment Books
            if (cosmicItemType.equals("customEnchBook") || cosmicItemType.equals("randomGearBook")) return EnchantBookHandler.resolve(stack);
            // Island treasure
            if (cosmicItemType.equals("skill_treasure_chest") || persistentItemType.equals("skill_treasure_chest"))
                return Identifier.of("cosmic-textures", "misc-island/treasure_chest");

            // Money Note
            if (cosmicItemType.equals("money_note") || persistentItemType.equals("money_note"))
                return Identifier.of("cosmic-textures", "misc/money_note");

            // Cosmic Coins
            if (cosmicItemType.equals("coin_note") || persistentItemType.equals("coin_note"))
                return Identifier.of("cosmic-textures", "misc/cosmic_coin_note");

            // Slot ticket
            if (cosmicItemType.equals("slot_bot_ticket") || persistentItemType.equals("slot_bot_ticket"))
                return Identifier.of("cosmic-textures", "misc/slot_bot");

            // Slot scrap
            if (cosmicItemType.equals("slot_bot_ticket_scrap") || persistentItemType.equals("slot_bot_ticket_scrap"))
                return Identifier.of("cosmic-textures", "misc/slot_scrap");

            // "Weekly Lootbox" item
            if (nbt.getString("mysteryChest").orElse("").equals("special_weekly_lootbox_static"))
                return Identifier.of("cosmic-textures", "misc/cosmic_crate");

            // Weekly lootboxes
            if (cosmicItemType.equals("mystery_chest") && stack.isOf(Items.BEACON) && stack.getCustomName() != null
                    && Objects.requireNonNull(stack.getCustomName()).getString().contains("Lootbox"))
                return Identifier.of("cosmic-textures", "misc/cosmic_crate");

            // Random Rare Candy
            if (nbt.getString("mysteryChest").orElse("").equals("special_random_rare_candy"))
                return Identifier.of("cosmic-textures", "candy/rare");
        }

        // Prisons Items
        if (stack.contains(DataComponentTypes.CUSTOM_DATA)) {
            NbtComponent customData = stack.get(DataComponentTypes.CUSTOM_DATA);
            if (customData == null) return null;

            NbtCompound nbt = customData.copyNbt();
            NbtCompound publicBukkitValues = nbt.getCompoundOrEmpty("PublicBukkitValues");
            String customItemID = publicBukkitValues.getString("cosmicprisons:custom_item_id").orElse("");

            // Contrabands
            if (customItemID.contains("contraband_"))
                return ContrabandHandler.resolve(customItemID);

            // TEMPORARY LOGIC FOR ITEMS THAT I COULDN'T ACCESS IN-GAME YET:

            if (stack.getCustomName() != null && Objects.requireNonNull(stack.getCustomName()).getString().contains("GP Booster") && stack.isOf(Items.AMETHYST_SHARD))
                return Identifier.of("cosmic-textures", "prisons/boosters/gp_booster");
            if (stack.getCustomName() != null && Objects.requireNonNull(stack.getCustomName()).getString().contains("Cosmic Coin") && stack.isOf(Items.PAPER))
                return Identifier.of("cosmic-textures", "prisons/misc/cosmic_coins");
            if (stack.getCustomName() != null && Objects.requireNonNull(stack.getCustomName()).getString().contains("Eraser") && stack.isOf(Items.PINK_DYE))
                return Identifier.of("cosmic-textures", "prisons/enchant-items/eraser");
            if (stack.getCustomName() != null && Objects.requireNonNull(stack.getCustomName()).getString().contains("Secret Dust") && stack.isOf(Items.GUNPOWDER)) {
                if (Objects.requireNonNull(stack.getCustomName()).getString().contains("Simple")) return Identifier.of("cosmic-textures", "prisons/dust/simple_secret_dust");
                if (Objects.requireNonNull(stack.getCustomName()).getString().contains("Uncommon")) return Identifier.of("cosmic-textures", "prisons/dust/uncommon_secret_dust");
                if (Objects.requireNonNull(stack.getCustomName()).getString().contains("Elite")) return Identifier.of("cosmic-textures", "prisons/dust/elite_secret_dust");
                if (Objects.requireNonNull(stack.getCustomName()).getString().contains("Ultimate")) return Identifier.of("cosmic-textures", "prisons/dust/ultimate_secret_dust");
                if (Objects.requireNonNull(stack.getCustomName()).getString().contains("Legendary")) return Identifier.of("cosmic-textures", "prisons/dust/legendary_secret_dust");
            }

            // Assorted Prisons Items:
            if (!customItemID.isEmpty()) {
                return switch (customItemID) {
                    case "absorber" -> Identifier.of("cosmic-textures", "prisons/enchant-items/absorber");
                    case "white_scroll" -> Identifier.of("cosmic-textures", "prisons/enchant-items/white_scroll");
                    case "gang_point_note" -> Identifier.of("cosmic-textures", "prisons/misc/gang_points");
                    case "cosmic_energy" -> Identifier.of("cosmic-textures", "prisons/misc/cosmic_energy");
                    case "money_note" -> Identifier.of("cosmic-textures", "prisons/misc/money_note");
                    case "charge_orb" -> ChargeOrbHandler.resolve(publicBukkitValues);
                    case "booster" -> BoosterHandler.resolve(publicBukkitValues);
                    case "gear_enchant_page" -> PageHandler.resolve(publicBukkitValues);
                    case "pickaxe_enchant_dust" -> PrisonsDustHandler.resolve(publicBukkitValues);
                    case "mystery_enchant_book", "gear_enchant_book" -> PrisonsEnchantBookHandler.resolve(stack);
                    case "mystery_chest" -> PrisonsMysteryChestHandler.resolve(stack);
                    case "shard" -> PrisonsShardHandler.resolve(publicBukkitValues);
                    case "cosmic_crate" -> Identifier.of("cosmic-textures", "misc/cosmic_crate");
                    case "black_scroll" -> Identifier.of("cosmic-textures", "prisons/enchant-items/black_scroll");
                    case "gen_breaker" -> Identifier.of("cosmic-textures", "prisons/misc/gen_breaker");
                    case "slot_bot_ticket" -> Identifier.of("cosmic-textures", "misc/slot_bot");
                    default -> null;
                };
            }
        }

        return null;
    }
}