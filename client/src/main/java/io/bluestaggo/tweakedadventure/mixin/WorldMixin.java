package io.bluestaggo.tweakedadventure.mixin;

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
		return 1L;
	}
}
