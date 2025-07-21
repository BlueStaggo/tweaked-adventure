package io.bluestaggo.tweakedadventure.mixin.worldgen;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import io.bluestaggo.tweakedadventure.TweakedAdventureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.VeinFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(VeinFeature.class)
public abstract class VeinFeatureMixin extends Feature {
	@ModifyExpressionValue(
		method = "place",
		at = @At(
			value = "CONSTANT",
			args = "intValue=2"
		)
	)
	private int revert4BlockShift(int original) {
		if (TweakedAdventureConfig.getInstance().higherOres()) {
			original -= 4;
		}
		return original;
	}
}
