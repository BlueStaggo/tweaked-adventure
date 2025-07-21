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
		TweakedAdventureConfig.BiomeHeightType biomeHeightType = TweakedAdventureConfig.getInstance().biomeHeightType();

		biomeBuilder.biome = Biome.OCEAN;
		biomeHeightType.applyOceanHeight(biomeBuilder);
		biomeBuilder.biome = Biome.EXTREME_HILLS;
		biomeHeightType.applyExtremeHillsHeight(biomeBuilder);

		if (TweakedAdventureConfig.getInstance().hillBiomes()) {
			biomeBuilder.biome = DESERT_HILLS = new DesertBiome(getNextID());
			biomeBuilder.color(13786898).name("DesertHills").disableRain().climate(Biome.DESERT);
			biomeHeightType.applyHillsHeight(biomeBuilder);
			biomeBuilder.biome = FOREST_HILLS = new ForestBiome(getNextID());
			biomeBuilder.color(2250012).name("ForestHills").climate(Biome.FOREST);
			biomeHeightType.applyForestHillsHeight(biomeBuilder);
			biomeBuilder.biome = TAIGA_HILLS = new TaigaBiome(getNextID());
			biomeBuilder.color(5159473).name("TaigaHills").climate(Biome.TAIGA);
			biomeHeightType.applyHillsHeight(biomeBuilder);
		} else {
			DESERT_HILLS = Biome.DESERT;
			FOREST_HILLS = Biome.FOREST;
			TAIGA_HILLS = Biome.TAIGA;
		}
	}

	private static int getNextID() {
		return getNextID(-1);
	}

	private static int getNextID(int borrowId) {
		if (borrowId >= 0 && Biome.BY_ID[borrowId] != null) {
			return borrowId;
		}

		while (nextID < Biome.BY_ID.length && Biome.BY_ID[nextID] != null) {
			nextID++;
		}

		if (nextID >= Biome.BY_ID.length) {
			throw new RuntimeException("Ran out of biome IDs!");
		}

		return nextID;
	}
}
