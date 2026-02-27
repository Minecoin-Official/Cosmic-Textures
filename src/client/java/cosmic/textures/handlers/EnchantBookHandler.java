package cosmic.textures.handlers;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

import java.util.Objects;

public class EnchantBookHandler {

    public static Identifier resolve(ItemStack stack) {
        if (stack.getCustomName() == null) return null;

        String name = stack.getCustomName().getString();
        if (name == null || name.isEmpty()) return null;

        NbtCompound nbt = Objects.requireNonNull(stack.get(DataComponentTypes.CUSTOM_DATA)).copyNbt();

        if (nbt.getString("cosmicItem").orElse("").equals("randomGearBook")) {
            if (nbt.getString("enchBook").orElse("").contains("STONE") || nbt.getString("enchBook").orElse("").contains("CHAIN"))
                return Identifier.of("cosmic-textures", "books/stone_armor_ench_book");
            if (nbt.getString("enchBook").orElse("").contains("IRON"))
                return Identifier.of("cosmic-textures", "books/iron_armor_ench_book");
            if (nbt.getString("enchBook").orElse("").contains("DIAMOND"))
                return Identifier.of("cosmic-textures", "books/diamond_armor_ench_book");
            if (nbt.getString("enchBook").orElse("").contains("NETHER"))
                return Identifier.of("cosmic-textures", "books/nether_armor_ench_book");
        }

        String bookTier = nbt.getString("enchType").orElse("");

        boolean highTier = isHighTier(name);
        boolean superHighTier = isSuperHighTier(name);

        String suffix = "";
        if (superHighTier) {
            suffix = "_super_high_tier";
        } else if (highTier) {
            suffix = "_high_tier";
        }

        if (bookTier.contains("CHAIN") || bookTier.contains("STONE"))
            return Identifier.of("cosmic-textures", "books/stone_revealed" + suffix);

        if (bookTier.contains("IRON"))
            return Identifier.of("cosmic-textures", "books/iron_revealed" + suffix);

        if (bookTier.contains("DIAMOND"))
            return Identifier.of("cosmic-textures", "books/diamond_revealed" + suffix);

        if (bookTier.contains("NETHER"))
            return Identifier.of("cosmic-textures", "books/nether_revealed" + suffix);

        return null;
    }

    private static boolean isHighTier(String name) {
        return name.contains("Protection IV")
                || name.contains("Projectile Protection IV")
                || name.contains("Fire Protection IV")
                || name.contains("Unbreaking III")
                || name.contains("Efficiency V")
                || name.contains("Smite V")
                || name.contains("Sharpness V")
                || name.contains("Looting III");
    }

    private static boolean isSuperHighTier(String name) {
        return name.matches(".*Protection (V|VI|VII|VIII|IX|X).*")
                || name.matches(".*Projectile Protection (V|VI|VII|VIII|IX|X).*")
                || name.matches(".*Fire Protection (V|VI|VII|VIII|IX|X).*")
                || name.matches(".*Unbreaking (IV|V|VI|VII|VIII|IX|X).*")
                || name.matches(".*Efficiency (VI|VII|VIII|IX|X).*")
                || name.matches(".*Smite (VI|VII|VIII|IX|X).*")
                || name.matches(".*Sharpness (VI|VII|VIII|IX|X).*")
                || name.matches(".*Looting (IV|V|VI|VII|VIII|IX|X).*");
    }
}
