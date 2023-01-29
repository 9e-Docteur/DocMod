package fr.ninedocteur.docmod.mixin;

import fr.ninedocteur.docmod.common.block.DMOreBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemMixin {

    @Inject(at = @At("HEAD"), method = "isFireproof()Z", cancellable = true)
    private void isFireproof(CallbackInfoReturnable<Boolean> cir) {
        if ((Item)(Object) this instanceof BlockItem blockItem){
            if (blockItem.getBlock() instanceof DMOreBlock){
                cir.setReturnValue(true);
            }
        }
    }
}
