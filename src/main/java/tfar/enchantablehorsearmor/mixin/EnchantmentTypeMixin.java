package tfar.enchantablehorsearmor.mixin;

import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = {"net.minecraft.enchantment.EnchantmentType$1",//ARMOR
				"net.minecraft.enchantment.EnchantmentType$2",//ARMOR_FEET
				"net.minecraft.enchantment.EnchantmentType$3",//ARMOR_LEGS
				"net.minecraft.enchantment.EnchantmentType$4",//ARMOR_CHEST
				"net.minecraft.enchantment.EnchantmentType$5",//ARMOR_HEAD
})
public class EnchantmentTypeMixin {

	@Inject(method = "canEnchantItem(Lnet/minecraft/item/Item;)Z",at = @At("RETURN"),cancellable = true)
	private void enchantHorseArmor(Item itemIn, CallbackInfoReturnable<Boolean> cir){
		if (cir.getReturnValue())return;
		if (itemIn instanceof HorseArmorItem)cir.setReturnValue(true);
	}
}
