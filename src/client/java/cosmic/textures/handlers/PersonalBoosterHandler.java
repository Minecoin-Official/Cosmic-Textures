package cosmic.textures.handlers;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class PersonalBoosterHandler {

    public static Identifier resolve(NbtCompound nbt) {

        String eventMult = nbt.getString("eventMult").orElse("");

        return switch (eventMult) {
            case "ADVENTURE_EXP" -> Identifier.of("cosmic-textures", "boosters/adventure_exp_booster");
            case "ISLAND_QUEST_PROGRESS" -> Identifier.of("cosmic-textures", "boosters/island_quest_progress_booster");
            case "MARAUDER_BAR_PROGRESS" -> Identifier.of("cosmic-textures", "boosters/marauder_bar_progress_booster");
            case "HITLIST_PROGRESS" -> Identifier.of("cosmic-textures", "boosters/hitlist_progress_booster");
            case "EXP" -> Identifier.of("cosmic-textures", "boosters/exp_booster");

            default -> null;
        };

    }
}
