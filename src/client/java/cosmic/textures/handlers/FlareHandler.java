package cosmic.textures.handlers;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class FlareHandler {

    public static Identifier resolve(NbtCompound nbt) {

        String cosmicItemType = nbt.getString("cosmicItem").orElse("");
        String advType = nbt.getString("advType").orElse("");
        if (cosmicItemType.equals("scavenger_envoy")) return switch (advType) {
            case "abandoned_ruins" -> Identifier.of("cosmic-textures", "airdrop-flares/chain_flare");
            case "lost_wasteland" -> Identifier.of("cosmic-textures", "airdrop-flares/iron_flare");
            case "demonic_realm" -> Identifier.of("cosmic-textures", "airdrop-flares/diamond_flare");
            default -> null;
        };

        if (cosmicItemType.equals("mythic_envoy")) return switch (advType) {
            case "abandoned_ruins" -> Identifier.of("cosmic-textures", "airdrop-flares/chain_flare_mythic");
            case "lost_wasteland" -> Identifier.of("cosmic-textures", "airdrop-flares/iron_flare_mythic");
            case "demonic_realm" -> Identifier.of("cosmic-textures", "airdrop-flares/diamond_flare_mythic");
            default -> null;
        };
        return null;
    }
}
