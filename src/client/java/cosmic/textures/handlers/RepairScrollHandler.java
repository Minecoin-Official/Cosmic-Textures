package cosmic.textures.handlers;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

import java.util.Objects;

public class RepairScrollHandler {

    public static Identifier resolve(ItemStack stack) {

        NbtCompound nbt = Objects.requireNonNull(stack.get(DataComponentTypes.CUSTOM_DATA)).copyNbt();

        if (stack.getCustomName() == null) return null;

        String name = stack.getCustomName().getString();
        if (name == null) return null;

        if (name.contains("Stone") || name.contains("Chain") || name.contains("Basic"))
            return switch ((int) (double) nbt.getDouble("repairIncr").orElse(0.0)) {
                case 100 -> Identifier.of("cosmic-textures", "repair-scrolls/stone_repair_100");
                case 75 -> Identifier.of("cosmic-textures", "repair-scrolls/stone_repair_75");
                case 50 -> Identifier.of("cosmic-textures", "repair-scrolls/stone_repair_50");
                case 25 -> Identifier.of("cosmic-textures", "repair-scrolls/stone_repair_25");
                default -> null;
            };

        if (name.contains("Elite") || name.contains("Iron"))
            return switch ((int) (double) nbt.getDouble("repairIncr").orElse(0.0)) {
                case 100 -> Identifier.of("cosmic-textures", "repair-scrolls/iron_repair_100");
                case 75 -> Identifier.of("cosmic-textures", "repair-scrolls/iron_repair_75");
                case 50 -> Identifier.of("cosmic-textures", "repair-scrolls/iron_repair_50");
                case 25 -> Identifier.of("cosmic-textures", "repair-scrolls/iron_repair_25");
                default -> null;
            };

        if (name.contains("Legendary") || name.contains("Diamond"))
            return switch ((int) (double) nbt.getDouble("repairIncr").orElse(0.0)) {
                case 100 -> Identifier.of("cosmic-textures", "repair-scrolls/diamond_repair_100");
                case 75 -> Identifier.of("cosmic-textures", "repair-scrolls/diamond_repair_75");
                case 50 -> Identifier.of("cosmic-textures", "repair-scrolls/diamond_repair_50");
                case 25 -> Identifier.of("cosmic-textures", "repair-scrolls/diamond_repair_25");
                default -> null;
            };

        if (name.contains("Godly") || name.contains("Nether"))
            return switch ((int) (double) nbt.getDouble("repairIncr").orElse(0.0)) {
                case 100 -> Identifier.of("cosmic-textures", "repair-scrolls/nether_repair_100");
                case 75 -> Identifier.of("cosmic-textures", "repair-scrolls/nether_repair_75");
                case 50 -> Identifier.of("cosmic-textures", "repair-scrolls/nether_repair_50");
                case 25 -> Identifier.of("cosmic-textures", "repair-scrolls/nether_repair_25");
                default -> null;
            };

        return null;
    }
}
