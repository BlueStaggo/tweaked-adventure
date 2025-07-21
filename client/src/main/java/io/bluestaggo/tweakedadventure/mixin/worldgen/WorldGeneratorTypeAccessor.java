package io.bluestaggo.tweakedadventure.mixin.worldgen;

import net.minecraft.world.gen.WorldGeneratorType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(WorldGeneratorType.class)
public interface WorldGeneratorTypeAccessor {
	@Invoker WorldGeneratorType invokeSetVisible(boolean visible);
}
