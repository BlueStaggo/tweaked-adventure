package io.bluestaggo.tweakedadventure.mixin.worldgen;

import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@SuppressWarnings("UnusedReturnValue")
@Mixin(Biome.class)
public interface BiomeInvoker {
	@Invoker("setName") Biome invokeSetName(String name);
	@Invoker("setBaseColor") Biome invokeSetBaseColor(int color);
	@Invoker("setTemperatureAndDownfall") Biome invokeSetTemperatureAndDownfall(float temperature, float downfall);
	@Invoker("setHeight") Biome invokeSetHeight(float baseHeight, float heightVariation);
	@Invoker("disableRain") Biome invokeDisableRain();
}
