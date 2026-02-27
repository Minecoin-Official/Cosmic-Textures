package cosmic.textures.mixin.client;

import cosmic.textures.CosmicItemModelResolver;
import net.minecraft.client.item.ItemModelManager;
import net.minecraft.component.ComponentType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ItemModelManager.class)
public class ItemModelManagerMixin {

    @Redirect(
            method = "update",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;get(Lnet/minecraft/component/ComponentType;)Ljava/lang/Object;"
            )
    )
    private @Nullable Object cosmicTextures$overrideModel(
            ItemStack stack,
            ComponentType<Identifier> type
    ) {
        Identifier model = CosmicItemModelResolver.resolve(stack);
        if (model != null) {
            return model;
        }
        return stack.get(type);
    }
}