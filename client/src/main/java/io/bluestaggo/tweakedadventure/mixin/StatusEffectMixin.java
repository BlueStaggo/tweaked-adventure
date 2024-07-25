package io.bluestaggo.tweakedadventure.mixin;

import net.minecraft.entity.living.effect.StatusEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(StatusEffect.class)
public abstract class StatusEffectMixin {
	@ModifyConstant(
		method = "apply",
		constant = @Constant(
			floatValue = 0.025f
		)
	)
	private float reduceHungerExhaustion(float constant) {
		return 0.005f;
	}
}
