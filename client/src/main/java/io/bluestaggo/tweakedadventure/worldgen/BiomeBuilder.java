package io.bluestaggo.tweakedadventure.worldgen;

import io.bluestaggo.tweakedadventure.mixin.worldgen.BiomeInvoker;
import net.minecraft.world.biome.Biome;

class BiomeBuilder {
	public Biome biome;

	public BiomeBuilder name(String name) {
		((BiomeInvoker) this.biome).invokeSetName(name);
		return this;
	}

	public BiomeBuilder color(int color) {
		((BiomeInvoker) this.biome).invokeSetBaseColor(color);
		return this;
	}

	public BiomeBuilder climate(float t, float d) {
		((BiomeInvoker) this.biome).invokeSetTemperatureAndDownfall(t, d);
		return this;
	}

	public BiomeBuilder height(float b, float v) {
		((BiomeInvoker) this.biome).invokeSetHeight(b, v);
		return this;
	}

	public BiomeBuilder disableRain() {
		((BiomeInvoker) this.biome).invokeDisableRain();
		return this;
	}

	public BiomeBuilder enableSnow() {
		((BiomeInvoker) this.biome).setSnowy(true);
		return this;
	}
}
