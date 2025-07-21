package io.bluestaggo.tweakedadventure.mixin.entity;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import io.bluestaggo.tweakedadventure.TweakedAdventureConfig;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(DamageSource.class)
public abstract class DamageSourceMixin {
	@ModifyExpressionValue(
		method = "<init>",
		at = @At(
			value = "CONSTANT",
			args = "floatValue=0.3"
		)
	)
	private float reduceExhaustion(float original) {
		if (!TweakedAdventureConfig.getInstance().modernExhaustion()) {
			return original;
		}
		return 0.1f;
	}
}
