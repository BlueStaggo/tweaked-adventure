package io.bluestaggo.tweakedadventure.mixin.entity;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import io.bluestaggo.tweakedadventure.TweakedAdventureConfig;
import net.minecraft.entity.living.effect.StatusEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(StatusEffect.class)
public abstract class StatusEffectMixin {
	@ModifyExpressionValue(
		method = "apply",
		at = @At(
			value = "CONSTANT",
			args = "floatValue=0.025"
		)
	)
	private float reduceHungerExhaustion(float original) {
		if (!TweakedAdventureConfig.getInstance().modernExhaustion()) {
			return original;
		}
		return 0.005f;
	}
}
