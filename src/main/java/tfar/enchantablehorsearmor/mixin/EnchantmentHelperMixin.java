package tfar.enchantablehorsearmor.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {

	@Inject(method = "getMaxEnchantmentLevel",at = @At("HEAD"),cancellable = true)
	private static void checkHorse(Enchantment enchantmentIn, LivingEntity entityIn, CallbackInfoReturnable<Integer> cir){
		if (entityIn instanceof HorseEntity) {
			ItemStack armor = ((HorseEntity) entityIn).func_213803_dV();
			if (armor.getItem() instanceof HorseArmorItem) {
				int level = getEnchantmentLevel(enchantmentIn, armor);
				if (level > 0)cir.setReturnValue(level);
			}
		}
	}
}
