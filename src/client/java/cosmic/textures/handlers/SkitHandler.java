package cosmic.textures.handlers;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class SkitHandler {

    public static Identifier resolve(NbtCompound nbt) {

        String cosmicItemType = nbt.getString("cosmicItem").orElse("");
        String skitRedeem = nbt.getString("skitRedeem").orElse("");
        String skitType = nbt.getString("sKitType").orElse("");

        return switch (cosmicItemType) {
            case "skit_redeem" -> switch (skitRedeem) {
                case "DEAD_HOLLOW" -> Identifier.of("cosmic-textures", "skits/deadhollow_monarch_gem");
                case "IRONBOUND_SHADE" -> Identifier.of("cosmic-textures", "skits/ironbound_shade_gem");
                case "BLOOD_WROUGHT" -> Identifier.of("cosmic-textures", "skits/bloodwrought_wraith_gem");
                default -> null;
            };
            case "lucky_block" -> switch (skitType) {
                case "DEAD_HOLLOW" -> Identifier.of("cosmic-textures", "skits/deadhollow_monarch_block");
                case "IRONBOUND_SHADE" -> Identifier.of("cosmic-textures", "skits/ironbound_shade_block");
                case "BLOOD_WROUGHT" -> Identifier.of("cosmic-textures", "skits/bloodwrought_wraith_block");
                default -> null;
            };
            default -> null;
        };
    }
}
