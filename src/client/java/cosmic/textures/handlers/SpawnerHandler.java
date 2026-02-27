package cosmic.textures.handlers;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class SpawnerHandler {

    public static Identifier resolve(ItemStack stack) {
        if (stack.getCustomName() == null) return null;

        String name = stack.getCustomName().getString();
        if (name == null) return null;

        if (name.contains("Rabbit")) return Identifier.of("cosmic-textures", "spawners/rabbit");
        if (name.contains("Chicken")) return Identifier.of("cosmic-textures", "spawners/chicken");
        if (name.contains("Cow")) return Identifier.of("cosmic-textures", "spawners/cow");
        if (name.contains("Spider")) return Identifier.of("cosmic-textures", "spawners/spider");
        if (name.contains("Zombie")) return Identifier.of("cosmic-textures", "spawners/zombie");
        if (name.contains("Skeleton")) return Identifier.of("cosmic-textures", "spawners/skeleton");
        if (name.contains("Witch")) return Identifier.of("cosmic-textures", "spawners/witch");
        if (name.contains("Pig")) return Identifier.of("cosmic-textures", "spawners/pig_zombie");
        if (name.contains("Blaze")) return Identifier.of("cosmic-textures", "spawners/blaze");
        if (name.contains("Ghast")) return Identifier.of("cosmic-textures", "spawners/ghast");
        if (name.contains("Enderman")) return Identifier.of("cosmic-textures", "spawners/enderman");

        return null;
    }
}
