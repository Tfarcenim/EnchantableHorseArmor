package tfar.enchantablehorsearmor;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.vertex.VertexBuilderUtils;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;

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
}
