package io.bluestaggo.tweakedadventure.worldgen;

import io.bluestaggo.tweakedadventure.TweakedAdventureConfig;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DesertBiome;
import net.minecraft.world.biome.ForestBiome;
import net.minecraft.world.biome.TaigaBiome;

public class TweakedAdventureBiomes {
	private static int nextID;

	public static final Biome DESERT_HILLS;
	public static final Biome FOREST_HILLS;
	public static final Biome TAIGA_HILLS;

	static {
		BiomeBuilder biomeBuilder = new BiomeBuilder();
		if (TweakedAdventureConfig.getInstance().hillBiomes()) {
			biomeBuilder.biome = DESERT_HILLS = new DesertBiome(getNextID());
			biomeBuilder.color(13786898).name("DesertHills").disableRain().climate(2.0f, 0.0f).height(0.3f, 0.8f);
			biomeBuilder.biome = FOREST_HILLS = new ForestBiome(getNextID());
			biomeBuilder.color(2250012).name("ForestHills").climate(0.7f, 0.8f).height(0.3f, 0.7f);
			biomeBuilder.biome = TAIGA_HILLS = new TaigaBiome(getNextID());
			biomeBuilder.color(5159473).name("TaigaHills").climate(0.2f, 0.8f).height(0.3f, 0.8f);
		} else {
			DESERT_HILLS = Biome.DESERT;
			FOREST_HILLS = Biome.FOREST;
			TAIGA_HILLS = Biome.TAIGA;
		}
	}

	private static int getNextID() {
		while (nextID < Biome.BY_ID.length && Biome.BY_ID[nextID] != null) {
			nextID++;
		}

		if (nextID >= Biome.BY_ID.length) {
			throw new RuntimeException("Ran out of biome IDs!");
		}

		return nextID;
	}
}
