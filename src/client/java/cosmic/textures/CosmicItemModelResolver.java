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
        HANDLERS.put("facility_access_card", FacilityCardHandler::resolve);
        HANDLERS.put("skit_redeem", SkitHandler::resolve);
        HANDLERS.put("lucky_block", SkitHandler::resolve);
        HANDLERS.put("mythic_envoy", FlareHandler::resolve);
        HANDLERS.put("scavenger_envoy", FlareHandler::resolve);
        HANDLERS.put("armorset_catalyst", CatalystHandler::resolve);
        HANDLERS.put("catalyst_extractor", CatalystExtractorHandler::resolve);
        HANDLERS.put("player_event_mult_booster", PersonalBoosterHandler::resolve);
        HANDLERS.put("vault_key", VaultKeyHandler::resolve);
        HANDLERS.put("fishing_bait_block_fragment", BaitHandler::resolve);
        HANDLERS.put("island_buddy", IslandBuddyHandler::resolve);
        HANDLERS.put("extraction_key_card", ExtractionKeycardHandler::resolve);
    }

    public static Identifier resolve(ItemStack stack) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return null;
//        if (client.getCurrentServerEntry() == null) return null;

        // Sky Logic

        // Spawners (No custom data)
        if (stack.isOf(Items.SPAWNER) && stack.getCustomName() != null) {
            return SpawnerHandler.resolve(stack);
        }

        if (stack.contains(DataComponentTypes.CUSTOM_DATA)) {
            NbtComponent customData = stack.get(DataComponentTypes.CUSTOM_DATA);
            if (customData == null) return null;
            NbtCompound nbt = customData.copyNbt();

            String cosmicItemType = nbt.getString("cosmicItem").orElse("");
            String persistentItemType = nbt.getString("persistentItem").orElse("");
            String mysteryChest = nbt.getString("mysteryChest").orElse("");

            Function<NbtCompound, Identifier> handler = null;

            if (!cosmicItemType.isEmpty()) handler = HANDLERS.get(cosmicItemType);
            if (handler == null && !persistentItemType.isEmpty())
                handler = HANDLERS.get(persistentItemType);

            if (handler != null) return handler.apply(nbt);

            // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            // Other miscellaneous item textures ⬇️

            // Custom Recipes
            if (cosmicItemType.equals("custom_recipe") || cosmicItemType.equals("custom_recipe_page"))
                return CustomRecipeHandler.resolve(stack);

            // Custom Recipe Crafts
            if (!nbt.getString("cosmicRecipeItem").orElse("").isEmpty()) return CustomCraftsHandler.resolve(stack);

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

            if (cosmicItemType.equals("mystery_chest") && mysteryChest.contains("special_random_teir_1_bot_upgrade"))
                return Identifier.of("cosmic-textures", "bot-upgrades/random_bot_upgrade_1");
            if (cosmicItemType.equals("mystery_chest") && mysteryChest.contains("special_random_teir_2_bot_upgrade"))
                return Identifier.of("cosmic-textures", "bot-upgrades/random_bot_upgrade_2");
            if (cosmicItemType.equals("mystery_chest") && mysteryChest.contains("special_random_teir_3_bot_upgrade"))
                return Identifier.of("cosmic-textures", "bot-upgrades/random_bot_upgrade_3");
            if (cosmicItemType.equals("mystery_chest") && mysteryChest.contains("special_random_bot_upgrade"))
                return Identifier.of("cosmic-textures", "bot-upgrades/random_bot_upgrade");

            // Gems
            if (cosmicItemType.equals("random_enchant_gem") ||
                    cosmicItemType.equals("enchant_gem") ||
                    cosmicItemType.equals("random_extended_enchant_gem") ||
                    mysteryChest.contains("special_random_mythic_gem") ||
                    mysteryChest.contains("special_mystery_boss_gem") ||
                    mysteryChest.contains("special_random_iron_gem_") ||
                    (mysteryChest.contains("_boss_") && mysteryChest.contains("_gem_")))
                return GemHandler.resolve(stack);

            // Custom Crafts
            if (cosmicItemType.equals("honey_bottle")) return CustomCraftsHandler.resolve(stack);

            if (!nbt.getString("nodeFragment").orElse("").isEmpty()) {
                return NodeFragmentHandler.resolve(nbt.getString("nodeFragment").orElse(""));
            }

            // Realm portals and realm portal shards
            if (cosmicItemType.equals("realm_portal_shard") || cosmicItemType.equals("realm_portal"))
                return RealmPortalHandler.resolve(stack);

            // Admin tools
            if (stack.getCustomName() != null && stack.getCustomName().getString().contains("Quantum Sabre ("))
                return Identifier.of("cosmic-textures", "admin-tools/quantum_sabre");
            if (stack.getCustomName() != null && stack.getCustomName().getString().contains("Chrono Scythe ("))
                return Identifier.of("cosmic-textures", "admin-tools/chrono_scythe");
            if (stack.getCustomName() != null && stack.getCustomName().getString().contains("Obsidian Drill ("))
                return Identifier.of("cosmic-textures", "admin-tools/obsidian_drill");
            if (stack.getCustomName() != null && stack.getCustomName().getString().contains("Maelstrom Incarnate ("))
                return Identifier.of("cosmic-textures", "admin-tools/maelstrom_incarnate");

            // Linked Chest
            if (cosmicItemType.equals("ender_chest"))
                return Identifier.of("cosmic-textures", "misc-island/linked_chest");

            // Collection Chest
            if (cosmicItemType.equals("collection_chest"))
                return Identifier.of("cosmic-textures", "misc-island/collection_chest");

            // Builder's Wand
            if (cosmicItemType.equals("builders_wand")) {
                if (nbt.getInt("maxSize").orElse(0).equals(250)) return Identifier.of("cosmic-textures", "misc-island/builders_wand_250");
                if (nbt.getInt("maxSize").orElse(0).equals(1000)) return Identifier.of("cosmic-textures", "misc-island/builders_wand_1000");
            }

            // Battery Baton
            if (cosmicItemType.equals("battery_baton"))
                return Identifier.of("cosmic-textures", "misc-island/battery_baton");

            // Hopper Dropper
            if (cosmicItemType.equals("hopper_dropper"))
                return Identifier.of("cosmic-textures", "misc-island/hopper_dropper");

            // Easter Basket
            if (cosmicItemType.equals("easter_basket"))
                return Identifier.of("cosmic-textures", "lootboxes/easter_basket");

            // Repair Scrolls
            if (cosmicItemType.equals("repair_scroll")) return RepairScrollHandler.resolve(stack);

            // Caskets
            if (cosmicItemType.equals("mystery_chest") && mysteryChest.contains("casket"))
                return CasketHandler.resolve(stack);

            // Casket Keys
            if (nbt.getString("chestKeyLootbox").orElse("").contains("casket")) return CasketHandler.resolve(stack);

            // Fuel
            if (!nbt.getString("auto_bot_fuel").orElse("").isEmpty()) return FuelHandler.resolve(nbt);

            // Fish
            if (!nbt.getString("fishable").orElse("").isEmpty()) return FishHandler.resolve(nbt);

            // Random Personal Booster
            if (cosmicItemType.equals("mystery_chest") && mysteryChest.equals("special_random_booster"))
                return Identifier.of("cosmic-textures", "boosters/random_personal_booster");

            // Enchantment Books
            if (cosmicItemType.equals("customEnchBook") || cosmicItemType.equals("randomGearBook"))
                return EnchantBookHandler.resolve(stack);
            // Island treasure
            if (cosmicItemType.equals("skill_treasure_chest") || persistentItemType.equals("skill_treasure_chest"))
                return Identifier.of("cosmic-textures", "misc-island/treasure_chest");

            // Cosmetic redeem
            if (cosmicItemType.equals("cosmetic_redeem"))
                return Identifier.of("cosmic-textures", "misc/cosmetic_redeem");

            // Science satchel
            if (cosmicItemType.equals("gold_satchel") && nbt.getByte("activated").orElse((byte) 0).equals((byte) 1))
                return Identifier.of("cosmic-textures", "misc/science_satchel_enabled");

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

            // Item flip credit
            if (cosmicItemType.equals("item_flip_credit"))
                return Identifier.of("cosmic-textures", "misc/item_flip_credit");

            // Rank Generator
            if (cosmicItemType.equals("mystery_chest") && mysteryChest.contains("rank_generator"))
                return Identifier.of("cosmic-textures", "misc/rank_generator");

            // Weekly lootboxes
//            if (cosmicItemType.equals("mystery_chest") && stack.isOf(Items.BEACON) && stack.getCustomName() != null
//                    && Objects.requireNonNull(stack.getCustomName()).getString().contains("Lootbox"))
//                return Identifier.of("cosmic-textures", "misc/cosmic_crate");

            // Science Amplifiers
            if (cosmicItemType.equals("science_amplifier")) {
                if (nbt.getInt("level").orElse(0).equals(2))
                    return Identifier.of("cosmic-textures", "misc-island/science_amp2");
                if (nbt.getInt("level").orElse(0).equals(3))
                    return Identifier.of("cosmic-textures", "misc-island/science_amp3");
            }

            // Forbidden Tome + Tome Page
            if (cosmicItemType.equals("forbidden_tome_page"))
                return Identifier.of("cosmic-textures", "misc-adventure/forbidden_tome_page");
            if (cosmicItemType.equals("forbidden_tome"))
                return Identifier.of("cosmic-textures", "misc-adventure/forbidden_tome");

            // Bee Larvae
            if (cosmicItemType.equals("bee_larvae")) return Identifier.of("cosmic-textures", "bees/bee_larvae");

            // Cosmic Crate
            if (cosmicItemType.equals("cosmic_crate")) return Identifier.of("cosmic-textures", "misc/cosmic_crate");

            // Spooky Serum
            if (cosmicItemType.equals("bloodbag2026_serum")) return switch (nbt.getString("serumType").orElse("")) {
                case "TIER_1" -> Identifier.of("cosmic-textures", "lootboxes/spooky_serum_1");
                case "TIER_2" -> Identifier.of("cosmic-textures", "lootboxes/spooky_serum_2");
                case "TIER_3" -> Identifier.of("cosmic-textures", "lootboxes/spooky_serum_3");
                case "TIER_4" -> Identifier.of("cosmic-textures", "lootboxes/spooky_serum_4");
                case "TIER_5" -> Identifier.of("cosmic-textures", "lootboxes/spooky_serum_5");
                default -> Identifier.of("cosmic-textures", "lootboxes/spooky_serum");
            };

            // Mystery Chests
            switch (mysteryChest) {
                // Weekly Lootbox
//                case "special_weekly_lootbox_static" -> {
//                    return Identifier.of("cosmic-textures", "misc/cosmic_crate");}
                // Boss Gem Lootbox
                case "special_mystery_boss_gem_static" -> {
                    return Identifier.of("cosmic-textures", "gems/iron_gem_boss");}
                // Random Rare Candy
                case "special_random_rare_candy" -> {
                    return Identifier.of("cosmic-textures", "candy/rare");}
                // Randomization Scrolls
                case "special_random_randomization_scroll_stone" -> {
                    return Identifier.of("cosmic-textures", "randomization-scrolls/random_stone");}
                case "special_random_randomization_scroll_iron" -> {
                    return Identifier.of("cosmic-textures", "randomization-scrolls/random_iron");}
                case "special_random_randomization_scroll_diamond" -> {
                    return Identifier.of("cosmic-textures", "randomization-scrolls/random_diamond");}
                case "special_random_randomization_scroll_nether" -> {
                    return Identifier.of("cosmic-textures", "randomization-scrolls/random_nether");}
                // Seasonal Lootboxes
                case "special_fireworks_cache_static", "special_fireworks_cache" -> {
                    return Identifier.of("cosmic-textures", "lootboxes/fireworks_cache");}
                case "special_fireworks_cache_3x_static" , "special_fireworks_cache_3x" -> {
                    return Identifier.of("cosmic-textures", "lootboxes/fireworks_cache_3x");}
                case "special_cupids_cache_static", "special_cupids_cache" -> {
                    return Identifier.of("cosmic-textures", "lootboxes/cupid_cache");}
                case "special_cupids_cache_3x_static", "special_cupids_cache_3x" -> {
                    return Identifier.of("cosmic-textures", "lootboxes/cupid_cache_3x");}
                case "special_chocolate_droppings_static", "special_chocolate_droppings" -> {
                    return Identifier.of("cosmic-textures", "lootboxes/chocolate_droppings");}
                case "special_chocolate_droppings_3x_static", "special_chocolate_droppings_3x" -> {
                    return Identifier.of("cosmic-textures", "lootboxes/chocolate_droppings_3x");}
                case "special_random_bloodbag_serum_2026" -> {
                    return Identifier.of("cosmic-textures", "lootboxes/spooky_serum");}
            }

            // Random socket expanders
            if (mysteryChest.contains("random_socket_expander")) {
                // Random tech socket
                if (mysteryChest.contains("technology")) {
                    if (mysteryChest.contains("chain") || mysteryChest.contains("stone")) return Identifier.of("cosmic-textures", "sockets/tech_random_stone");
                    if (mysteryChest.contains("iron")) return Identifier.of("cosmic-textures", "sockets/tech_random_iron");
                    if (mysteryChest.contains("diamond")) return Identifier.of("cosmic-textures", "sockets/tech_random_diamond");
                    if (mysteryChest.contains("nether")) return Identifier.of("cosmic-textures", "sockets/tech_random_nether");
                }

                // Non-tech random sockets
                if (mysteryChest.contains("chain") || mysteryChest.contains("stone")) return Identifier.of("cosmic-textures", "sockets/random_stone");
                if (mysteryChest.contains("iron")) return Identifier.of("cosmic-textures", "sockets/random_iron");
                if (mysteryChest.contains("diamond")) return Identifier.of("cosmic-textures", "sockets/random_diamond");
            }

            // Random mega module
            if (mysteryChest.contains("mega_bot_module"))
                return Identifier.of("cosmic-textures", "bot-upgrades/random_module");
            // Admin tool box
            if (mysteryChest.contains("special_random_admin_tool_box")) {
                if (mysteryChest.contains("stone"))
                    return Identifier.of("cosmic-textures", "admin-tools/admin_tool_box_stone");
                if (mysteryChest.contains("iron"))
                    return Identifier.of("cosmic-textures", "admin-tools/admin_tool_box_iron");
                if (mysteryChest.contains("diamond"))
                    return Identifier.of("cosmic-textures", "admin-tools/admin_tool_box_diamond");
                if (mysteryChest.contains("nether"))
                    return Identifier.of("cosmic-textures", "admin-tools/admin_tool_box_nether");
                else return Identifier.of("cosmic-textures", "admin-tools/admin_tool_box_unknown");
            }

            // Auction House Revealed Book Categories
            if (client.currentScreen != null && stack.getCustomName() != null) {
                String currentScreenTitle = client.currentScreen.getTitle().getString();
                String itemName = stack.getCustomName().getString();

                if (currentScreenTitle.equals("Auction Categories") && (itemName.equals("Stone Weapon Enchantments") ||
                        itemName.equals("Stone Tool Enchantments") || itemName.equals("Chainmail Armor Enchantments")) && stack.isOf(Items.ENCHANTED_BOOK))
                    return Identifier.of("cosmic-textures", "books/stone_revealed");
                if (currentScreenTitle.equals("Auction Categories") && (itemName.equals("Iron Weapon Enchantments") ||
                        itemName.equals("Iron Tool Enchantments") || itemName.equals("Iron Armor Enchantments")) && stack.isOf(Items.ENCHANTED_BOOK))
                    return Identifier.of("cosmic-textures", "books/iron_revealed");
                if (currentScreenTitle.equals("Auction Categories") && (itemName.equals("Diamond Weapon Enchantments") ||
                        itemName.equals("Diamond Tool Enchantments") || itemName.equals("Diamond Armor Enchantments")) && stack.isOf(Items.ENCHANTED_BOOK))
                    return Identifier.of("cosmic-textures", "books/diamond_revealed");
                if (currentScreenTitle.equals("Auction Categories") && (itemName.equals("Nether Weapon Enchantments") ||
                        itemName.equals("Nether Tool Enchantments") || itemName.equals("Nether Armor Enchantments")) && stack.isOf(Items.ENCHANTED_BOOK))
                    return Identifier.of("cosmic-textures", "books/nether_revealed");
            }
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
            if (stack.getCustomName() != null && Objects.requireNonNull(stack.getCustomName()).getString().contains("Secret Dust") && stack.isOf(Items.GUNPOWDER)) {
                if (Objects.requireNonNull(stack.getCustomName()).getString().contains("Simple")) return Identifier.of("cosmic-textures", "prisons/dust/simple_secret_dust");
                if (Objects.requireNonNull(stack.getCustomName()).getString().contains("Uncommon")) return Identifier.of("cosmic-textures", "prisons/dust/uncommon_secret_dust");
                if (Objects.requireNonNull(stack.getCustomName()).getString().contains("Elite")) return Identifier.of("cosmic-textures", "prisons/dust/elite_secret_dust");
                if (Objects.requireNonNull(stack.getCustomName()).getString().contains("Ultimate")) return Identifier.of("cosmic-textures", "prisons/dust/ultimate_secret_dust");
                if (Objects.requireNonNull(stack.getCustomName()).getString().contains("Legendary")) return Identifier.of("cosmic-textures", "prisons/dust/legendary_secret_dust");
            }

            // Market Textures
            if (client.currentScreen != null && stack.getCustomName() != null) {
                String currentScreenTitle = client.currentScreen.getTitle().getString();
                String itemName = stack.getCustomName().getString();

                // Dust
                if (currentScreenTitle.contains("Pickaxe Dust - Select Tier")) {
                    if (itemName.contains("Simple") && stack.isOf(Items.SUGAR))
                        return Identifier.of("cosmic-textures", "prisons/dust/simple_dust");
                    if (itemName.contains("Uncommon") && stack.isOf(Items.SUGAR))
                        return Identifier.of("cosmic-textures", "prisons/dust/uncommon_dust");
                    if (itemName.contains("Elite") && stack.isOf(Items.SUGAR))
                        return Identifier.of("cosmic-textures", "prisons/dust/elite_dust");
                    if (itemName.contains("Ultimate") && stack.isOf(Items.SUGAR))
                        return Identifier.of("cosmic-textures", "prisons/dust/ultimate_dust");
                    if (itemName.contains("Legendary") && stack.isOf(Items.SUGAR))
                        return Identifier.of("cosmic-textures", "prisons/dust/legendary_dust");
                    if (itemName.contains("Godly") && stack.isOf(Items.SUGAR))
                        return Identifier.of("cosmic-textures", "prisons/dust/godly_dust");
                }

                // Charge Orbs
                if (currentScreenTitle.contains("Charge Orbs - Listings")) {
                    if (itemName.equals("1% Charge Orb") && stack.isOf(Items.MAGMA_CREAM))
                        return Identifier.of("cosmic-textures", "prisons/charge-orbs/charge_orb_1");
                    if (itemName.equals("2% Charge Orb") && stack.isOf(Items.MAGMA_CREAM))
                        return Identifier.of("cosmic-textures", "prisons/charge-orbs/charge_orb_2");
                    if (itemName.equals("3% Charge Orb") && stack.isOf(Items.MAGMA_CREAM))
                        return Identifier.of("cosmic-textures", "prisons/charge-orbs/charge_orb_3");
                    if (itemName.equals("4% Charge Orb") && stack.isOf(Items.MAGMA_CREAM))
                        return Identifier.of("cosmic-textures", "prisons/charge-orbs/charge_orb_4");
                    if (itemName.equals("5% Charge Orb") && stack.isOf(Items.MAGMA_CREAM))
                        return Identifier.of("cosmic-textures", "prisons/charge-orbs/charge_orb_5");
                    if (itemName.equals("6% Charge Orb") && stack.isOf(Items.MAGMA_CREAM))
                        return Identifier.of("cosmic-textures", "prisons/charge-orbs/charge_orb_6");
                    if (itemName.equals("7% Charge Orb") && stack.isOf(Items.MAGMA_CREAM))
                        return Identifier.of("cosmic-textures", "prisons/charge-orbs/charge_orb_7");
                    if (itemName.equals("8% Charge Orb") && stack.isOf(Items.MAGMA_CREAM))
                        return Identifier.of("cosmic-textures", "prisons/charge-orbs/charge_orb_8");
                    if (itemName.equals("9% Charge Orb") && stack.isOf(Items.MAGMA_CREAM))
                        return Identifier.of("cosmic-textures", "prisons/charge-orbs/charge_orb_9");
                    if (itemName.equals("10% Charge Orb") && stack.isOf(Items.MAGMA_CREAM))
                        return Identifier.of("cosmic-textures", "prisons/charge-orbs/charge_orb_10");
                    if (itemName.equals("11% Charge Orb") && stack.isOf(Items.MAGMA_CREAM))
                        return Identifier.of("cosmic-textures", "prisons/charge-orbs/charge_orb_11");
                    if (itemName.equals("12% Charge Orb") && stack.isOf(Items.MAGMA_CREAM))
                        return Identifier.of("cosmic-textures", "prisons/charge-orbs/charge_orb_12");
                    if (itemName.equals("13% Charge Orb") && stack.isOf(Items.MAGMA_CREAM))
                        return Identifier.of("cosmic-textures", "prisons/charge-orbs/charge_orb_13");
                    if (itemName.equals("14% Charge Orb") && stack.isOf(Items.MAGMA_CREAM))
                        return Identifier.of("cosmic-textures", "prisons/charge-orbs/charge_orb_14");
                    if (itemName.equals("15% Charge Orb") && stack.isOf(Items.MAGMA_CREAM))
                        return Identifier.of("cosmic-textures", "prisons/charge-orbs/charge_orb_15");
                    if (itemName.equals("16% Charge Orb") && stack.isOf(Items.MAGMA_CREAM))
                        return Identifier.of("cosmic-textures", "prisons/charge-orbs/charge_orb_16");
                    if (itemName.equals("17% Charge Orb") && stack.isOf(Items.MAGMA_CREAM))
                        return Identifier.of("cosmic-textures", "prisons/charge-orbs/charge_orb_17");
                    if (itemName.equals("18% Charge Orb") && stack.isOf(Items.MAGMA_CREAM))
                        return Identifier.of("cosmic-textures", "prisons/charge-orbs/charge_orb_18");
                    if (itemName.equals("19% Charge Orb") && stack.isOf(Items.MAGMA_CREAM))
                        return Identifier.of("cosmic-textures", "prisons/charge-orbs/charge_orb_19");
                    if (itemName.equals("20% Charge Orb") && stack.isOf(Items.MAGMA_CREAM))
                        return Identifier.of("cosmic-textures", "prisons/charge-orbs/charge_orb_20");
                }
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
                    case "eraser" -> Identifier.of("cosmic-textures", "prisons/enchant-items/eraser");
                    case "bandit_box_key", "bandit_box" -> BanditBoxHandler.resolve(publicBukkitValues);
                    case "clue_scroll", "mystery_clue_scroll" -> ClueScrollHandler.resolve(publicBukkitValues);
                    case "pickaxe_prestige_token" -> PrestigeTokenHandler.resolve(publicBukkitValues);
                    case "xp_bottle" -> xpBottleHandler.resolve(publicBukkitValues);
                    case "randomization_scroll" -> PrisonsRandomizationScrollHandler.resolve(publicBukkitValues);
                    default -> null;
                };
            }
        }

        return null;
    }
}