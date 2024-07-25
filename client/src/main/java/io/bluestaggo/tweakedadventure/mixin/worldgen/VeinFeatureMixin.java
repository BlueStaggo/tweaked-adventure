package io.bluestaggo.tweakedadventure.mixin.worldgen;

import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.VeinFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(VeinFeature.class)
public abstract class VeinFeatureMixin extends Feature {
	@ModifyConstant(
		method = "place",
		constant = @Constant(
			intValue = 2
		)
	)
	private int revert4BlockShift(int constant) {
		return -2;
	}
}
