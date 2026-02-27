package cosmic.textures.handlers;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.Objects;

public class RealmPortalHandler {

    public static Identifier resolve(ItemStack stack) {
        if (stack.getCustomName() == null) return null;

        String name = stack.getCustomName().getString();
        if (name == null) return null;

        if (name.contains("Fishing Lake")) {
            if (Objects.requireNonNull(stack.get(DataComponentTypes.CUSTOM_DATA)).copyNbt().getString("cosmicItem").orElse("").equals("realm_portal")) return Identifier.of("cosmic-textures", "realms/fishing_portal");
            return Identifier.of("cosmic-textures", "realms/fishing_shard");
        }
        if (name.contains("Dwarf Mine")) {
            if (Objects.requireNonNull(stack.get(DataComponentTypes.CUSTOM_DATA)).copyNbt().getString("cosmicItem").orElse("").equals("realm_portal")) return Identifier.of("cosmic-textures", "realms/dwarf_portal");
            return Identifier.of("cosmic-textures", "realms/dwarf_shard");
        }
        if (name.contains("Enchanted Forest")) {
            if (Objects.requireNonNull(stack.get(DataComponentTypes.CUSTOM_DATA)).copyNbt().getString("cosmicItem").orElse("").equals("realm_portal")) return Identifier.of("cosmic-textures", "realms/fairy_portal");
            return Identifier.of("cosmic-textures", "realms/fairy_shard");
        }

        return null;
    }
}
