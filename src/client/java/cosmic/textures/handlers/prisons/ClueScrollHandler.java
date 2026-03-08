package cosmic.textures.handlers.prisons;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class ClueScrollHandler {

    public static Identifier resolve(NbtCompound publicBukkitValues) {

        String clueScrollData = publicBukkitValues.getString("cosmicprisons:clue_scroll_data").orElse("");
        if (clueScrollData.contains("SIMPLE")) return Identifier.of("cosmic-textures", "prisons/clue-scrolls/simple_clue");
        if (clueScrollData.contains("UNCOMMON")) return Identifier.of("cosmic-textures", "prisons/clue-scrolls/uncommon_clue");
        if (clueScrollData.contains("ELITE")) return Identifier.of("cosmic-textures", "prisons/clue-scrolls/elite_clue");
        if (clueScrollData.contains("ULTIMATE")) return Identifier.of("cosmic-textures", "prisons/clue-scrolls/ultimate_clue");
        if (clueScrollData.contains("LEGENDARY")) return Identifier.of("cosmic-textures", "prisons/clue-scrolls/legendary_clue");
        if (clueScrollData.contains("GODLY")) return Identifier.of("cosmic-textures", "prisons/clue-scrolls/godly_clue");
        return null;
    }
}
