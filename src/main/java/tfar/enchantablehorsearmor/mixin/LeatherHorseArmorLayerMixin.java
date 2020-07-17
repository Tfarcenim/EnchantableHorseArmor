package tfar.enchantablehorsearmor.mixin;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.layers.LeatherHorseArmorLayer;
import net.minecraft.entity.passive.horse.HorseEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import tfar.enchantablehorsearmor.EnchantableHorseArmor;

@Mixin(LeatherHorseArmorLayer.class)
public class LeatherHorseArmorLayerMixin {

	@ModifyVariable(method = "render",at = @At(value = "INVOKE_ASSIGN",target = "Lnet/minecraft/client/renderer/IRenderTypeBuffer;getBuffer(Lnet/minecraft/client/renderer/RenderType;)Lcom/mojang/blaze3d/vertex/IVertexBuilder;"))
	private IVertexBuilder renderHorseArmorGlint(IVertexBuilder old, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, HorseEntity entitylivingbaseIn,
																							 float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		return EnchantableHorseArmor.renderHorseArmorGlintHook(old, matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
	}
}
