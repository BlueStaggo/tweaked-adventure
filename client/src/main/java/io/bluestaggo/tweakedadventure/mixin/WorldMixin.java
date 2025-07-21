package io.bluestaggo.tweakedadventure.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import io.bluestaggo.tweakedadventure.TweakedAdventureConfig;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(World.class)
public abstract class WorldMixin {
	@ModifyExpressionValue(
		method = "tick",
		at = @At(
			value = "CONSTANT",
			args = "longValue=400"
		)
	)
	private long spawnAnimalsFrequently(long constant) {
		if (TweakedAdventureConfig.getInstance().frequentAnimalRespawning()) {
			return 1L;
		}
		return constant;
	}

	@Inject(
		method = "canSnowFall",
		at = @At(
			value = "RETURN",
			ordinal = 2
		),
		cancellable = true
	)
	private void fixSnowCheck(int x, int y, int z, CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(false);
	}
}
