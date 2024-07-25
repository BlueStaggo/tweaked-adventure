package io.bluestaggo.tweakedadventure.mixin;

import io.bluestaggo.tweakedadventure.TweakedAdventureConfig;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(World.class)
public abstract class WorldMixin {
	@ModifyConstant(
		method = "tick",
		constant = @Constant(
			longValue = 400L
		)
	)
	private long spawnAnimalsFrequently(long constant) {
		if (TweakedAdventureConfig.getInstance().frequentAnimalRespawning()) {
			return 1L;
		}
		return constant;
	}
}
