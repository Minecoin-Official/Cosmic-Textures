package cosmic.textures.handlers.prisons;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class ClueScrollHandler {

    public static Identifier resolve(NbtCompound publicBukkitValues) {

        String clueScrollData = publicBukkitValues.getString("cosmicprisons:clue_scroll_data").orElse("");
        String mysteryScrollTier = publicBukkitValues.getString("cosmicprisons:mystery_tier").orElse("");
        if (clueScrollData.contains("SIMPLE") || mysteryScrollTier.equals("SIMPLE")) return Identifier.of("cosmic-textures", "prisons/clue-scrolls/simple_clue");
        if (clueScrollData.contains("UNCOMMON") || mysteryScrollTier.equals("UNCOMMON")) return Identifier.of("cosmic-textures", "prisons/clue-scrolls/uncommon_clue");
        if (clueScrollData.contains("ELITE") || mysteryScrollTier.equals("ELITE")) return Identifier.of("cosmic-textures", "prisons/clue-scrolls/elite_clue");
        if (clueScrollData.contains("ULTIMATE") || mysteryScrollTier.equals("ULTIMATE")) return Identifier.of("cosmic-textures", "prisons/clue-scrolls/ultimate_clue");
        if (clueScrollData.contains("LEGENDARY") || mysteryScrollTier.equals("LEGENDARY")) return Identifier.of("cosmic-textures", "prisons/clue-scrolls/legendary_clue");
        if (clueScrollData.contains("GODLY") || mysteryScrollTier.equals("GODLY")) return Identifier.of("cosmic-textures", "prisons/clue-scrolls/godly_clue");
        return null;
    }
}
