package io.bluestaggo.tweakedadventure.mixin;

import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(DamageSource.class)
public abstract class DamageSourceMixin {
	@ModifyConstant(
		method = "<init>",
		constant = @Constant(
			floatValue = 0.3f
		)
	)
	private float reduceExhaustion(float constant) {
		return 0.1f;
	}
}
