package tfar.enchantablehorsearmor;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.vertex.VertexBuilderUtils;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(EnchantableHorseArmor.MODID)
public class EnchantableHorseArmor {

	public static final String MODID = "enchantablehorsearmor";

	public static IVertexBuilder renderHorseArmorGlintHook(IVertexBuilder old, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, HorseEntity entitylivingbaseIn,
																												 float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		ItemStack stack = entitylivingbaseIn.func_213803_dV();
		HorseArmorItem horseArmorItem = (HorseArmorItem) stack.getItem();
		boolean glint = stack.hasEffect();
		if (glint) {
			ResourceLocation texture = horseArmorItem.func_219976_d();
			return VertexBuilderUtils.newDelegate(
							bufferIn.getBuffer(RenderType.getEntityGlint()),
							bufferIn.getBuffer(RenderType.getEntityCutoutNoCull(texture))
			);
		}
		return old;
	}

	public static void checkHorseHook(Enchantment enchantmentIn, LivingEntity entityIn, CallbackInfoReturnable<Integer> cir) {
		if (entityIn instanceof HorseEntity) {
			ItemStack armor = ((HorseEntity) entityIn).func_213803_dV();
			if (armor.getItem() instanceof HorseArmorItem) {
				int level = getEnchantmentLevel(enchantmentIn, armor);
				cir.setReturnValue(level);
			}
		}
	}
}
