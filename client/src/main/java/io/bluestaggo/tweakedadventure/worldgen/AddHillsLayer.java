package io.bluestaggo.tweakedadventure.worldgen;

import io.bluestaggo.tweakedadventure.TweakedAdventureConfig;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.IntArrays;
import net.minecraft.world.biome.layer.AddDeepOceanLayer;
import net.minecraft.world.biome.layer.Layer;

public class AddHillsLayer extends AddDeepOceanLayer {
	public AddHillsLayer(long seed, Layer parent) {
		super(seed, parent);
	}

	@Override
	public int[] nextValues(int x, int z, int width, int length) {
		int[] input = this.parent.nextValues(x - 1, z - 1, width + 2, length + 2);
		int[] output = IntArrays.get(width * length);

		for (int zz = 0; zz < length; ++zz) {
			for (int xx = 0; xx < width; ++xx) {
				this.setChunkSeed(xx + x, zz + z);
				int inputBiome = input[xx + 1 + (zz + 1) * (width + 2)];
				if (this.nextInt(3) == 0) {
					int outputBiome = inputBiome;
					if (inputBiome == Biome.DESERT.id) {
						outputBiome = TweakedAdventureBiomes.DESERT_HILLS.id;
					} else if (inputBiome == Biome.FOREST.id) {
						outputBiome = TweakedAdventureBiomes.FOREST_HILLS.id;
					} else if (inputBiome == Biome.TAIGA.id) {
						outputBiome = TweakedAdventureBiomes.TAIGA_HILLS.id;
					} else if (inputBiome == TweakedAdventureBiomes.SNOWY_TAIGA.id) {
						outputBiome = TweakedAdventureBiomes.SNOWY_TAIGA_HILLS.id;
					} else if (inputBiome == Biome.ICE_PLAINS.id) {
						outputBiome = Biome.ICE_MOUNTAINS.id;
					} else if (inputBiome == Biome.PLAINS.id && TweakedAdventureConfig.getInstance().forestsInPlains()) {
						outputBiome = Biome.FOREST.id;
					}

					if (outputBiome != inputBiome) {
						int neighbor0 = input[xx + 1 + (zz + 1 - 1) * (width + 2)];
						int neighbor1 = input[xx + 1 + 1 + (zz + 1) * (width + 2)];
						int neighbor2 = input[xx + 1 - 1 + (zz + 1) * (width + 2)];
						int neighbor3 = input[xx + 1 + (zz + 1 + 1) * (width + 2)];
						if (neighbor0 == inputBiome && neighbor1 == inputBiome && neighbor2 == inputBiome && neighbor3 == inputBiome) {
							output[xx + zz * width] = outputBiome;
						} else {
							output[xx + zz * width] = inputBiome;
						}
					} else {
						output[xx + zz * width] = inputBiome;
					}
				} else {
					output[xx + zz * width] = inputBiome;
				}
			}
		}

		return output;
	}
}
