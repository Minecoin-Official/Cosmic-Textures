package cosmic.textures.handlers;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

import java.util.Objects;

public class BotUpgradeHandler {

    public static Identifier resolve(ItemStack stack) {

        NbtCompound nbt = Objects.requireNonNull(stack.get(DataComponentTypes.CUSTOM_DATA)).copyNbt();
        String upgrade = nbt.getString("upgrade").orElse("");
        int tier = nbt.getInt("tier").orElse(0);
        String cosmicItemType = nbt.getString("cosmicItem").orElse("");
        String persistentItemType = nbt.getString("persistentItem").orElse("");

        if (cosmicItemType.equals("auto_bot_upgrade") || persistentItemType.equals("auto_bot_upgrade")) {
            return switch (upgrade) {
                case "SLAYER" -> switch (tier) {
                    case 1 -> Identifier.of("cosmic-textures", "bot-upgrades/slayer1");
                    case 2 -> Identifier.of("cosmic-textures", "bot-upgrades/slayer2");
                    case 3 -> Identifier.of("cosmic-textures", "bot-upgrades/slayer3");
                    default -> null;
                };

                case "MINER" -> switch (tier) {
                    case 1 -> Identifier.of("cosmic-textures", "bot-upgrades/miner1");
                    case 2 -> Identifier.of("cosmic-textures", "bot-upgrades/miner2");
                    case 3 -> Identifier.of("cosmic-textures", "bot-upgrades/miner3");
                    default -> null;
                };

                case "FISHER" -> switch (tier) {
                    case 1 -> Identifier.of("cosmic-textures", "bot-upgrades/fishing1");
                    case 2 -> Identifier.of("cosmic-textures", "bot-upgrades/fishing2");
                    case 3 -> Identifier.of("cosmic-textures", "bot-upgrades/fishing3");
                    default -> null;
                };

                case "HARVESTER" -> switch (tier) {
                    case 1 -> Identifier.of("cosmic-textures", "bot-upgrades/harvester1");
                    case 2 -> Identifier.of("cosmic-textures", "bot-upgrades/harvester2");
                    case 3 -> Identifier.of("cosmic-textures", "bot-upgrades/harvester3");
                    default -> null;
                };

                case "BUNDLER" -> switch (tier) {
                    case 1 -> Identifier.of("cosmic-textures", "bot-upgrades/bundler1");
                    case 2 -> Identifier.of("cosmic-textures", "bot-upgrades/bundler2");
                    case 3 -> Identifier.of("cosmic-textures", "bot-upgrades/bundler3");
                    default -> null;
                };

                case "CRAFTER" -> switch (tier) {
                    case 1 -> Identifier.of("cosmic-textures", "bot-upgrades/crafter1");
                    case 2 -> Identifier.of("cosmic-textures", "bot-upgrades/crafter2");
                    case 3 -> Identifier.of("cosmic-textures", "bot-upgrades/crafter3");
                    default -> null;
                };

                case "FURNACE" -> switch (tier) {
                    case 1 -> Identifier.of("cosmic-textures", "bot-upgrades/smelter1");
                    case 2 -> Identifier.of("cosmic-textures", "bot-upgrades/smelter2");
                    case 3 -> Identifier.of("cosmic-textures", "bot-upgrades/smelter3");
                    default -> null;
                };

                case "SELLER" -> switch (tier) {
                    case 1 -> Identifier.of("cosmic-textures", "bot-upgrades/seller1");
                    case 2 -> Identifier.of("cosmic-textures", "bot-upgrades/seller2");
                    case 3 -> Identifier.of("cosmic-textures", "bot-upgrades/seller3");
                    default -> null;
                };

                case "RECYCLER" -> switch (tier) {
                    case 1 -> Identifier.of("cosmic-textures", "bot-upgrades/recycler1");
                    case 2 -> Identifier.of("cosmic-textures", "bot-upgrades/recycler2");
                    case 3 -> Identifier.of("cosmic-textures", "bot-upgrades/recycler3");
                    default -> null;
                };

                default -> null;
            };
        }

        if (cosmicItemType.equals("auto_bot_upgrade_module") || persistentItemType.equals("auto_bot_upgrade_module")) {
            return switch (upgrade) {
                case "SMELTER_MODULE" -> Identifier.of("cosmic-textures", "bot-upgrades/smelter_module");
                case "MINER_MODULE" -> Identifier.of("cosmic-textures", "bot-upgrades/miner_module");
                case "CRAFTER_MODULE" -> Identifier.of("cosmic-textures", "bot-upgrades/crafter_module");
                case "SLAYER_MODULE" -> Identifier.of("cosmic-textures", "bot-upgrades/slayer_module");
                case "SLAYER_MODULE_LOOT" -> Identifier.of("cosmic-textures", "bot-upgrades/slayer_module_loot");
                case "HARVESTER_MODULE" -> Identifier.of("cosmic-textures", "bot-upgrades/harvester_module");
                case "HARVEST_MODULE_AREA" -> Identifier.of("cosmic-textures", "bot-upgrades/harvester_module_area");
                default -> Identifier.of("cosmic-textures", "bot-upgrades/module_unknown");
            };
        }
        return null;
    }
}
