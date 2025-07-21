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
	public static final Biome SNOWY_TAIGA;
	public static final Biome SNOWY_TAIGA_HILLS;

	static {
		BiomeBuilder biomeBuilder = new BiomeBuilder();
		TweakedAdventureConfig.BiomeHeightType biomeHeightType = TweakedAdventureConfig.getInstance().biomeHeightType();

		biomeBuilder.biome = Biome.OCEAN;
		biomeHeightType.applyOceanHeight(biomeBuilder);
		biomeBuilder.biome = Biome.FROZEN_OCEAN;
		biomeHeightType.applyOceanHeight(biomeBuilder);
		biomeBuilder.biome = Biome.EXTREME_HILLS;
		biomeHeightType.applyExtremeHillsHeight(biomeBuilder);
		biomeBuilder.biome = Biome.ICE_MOUNTAINS;
		biomeHeightType.applyIceMountainsHeight(biomeBuilder);

		switch (TweakedAdventureConfig.getInstance().taigaType()) {
			case SNOWY:
				SNOWY_TAIGA = Biome.TAIGA;
				break;

			case SNOWY_AND_SLOWLESS:
				biomeBuilder.biome = SNOWY_TAIGA = new TaigaBiome(getNextID());
				biomeBuilder.color(0x31554A).name("Snowy Taiga").climate(0.05f, 0.8f).height(0.1f, 0.4f).enableSnow();
				biomeBuilder.biome = Biome.TAIGA;
				biomeBuilder.climate(0.3f, 0.8f).disableSnow();
				break;

			default:
				biomeBuilder.biome = Biome.TAIGA;
				biomeBuilder.climate(0.3f, 0.8f).disableSnow();
				SNOWY_TAIGA = Biome.TAIGA;
				break;
		}

		if (TweakedAdventureConfig.getInstance().hillBiomes()) {
			biomeBuilder.biome = DESERT_HILLS = Biome.DESERT_HILLS;
			biomeHeightType.applyHillsHeight(biomeBuilder);
			biomeBuilder.biome = FOREST_HILLS = Biome.FOREST_HILLS;
			biomeHeightType.applyForestHillsHeight(biomeBuilder);
			biomeBuilder.biome = TAIGA_HILLS = Biome.TAIGA_HILLS;
			biomeHeightType.applyHillsHeight(biomeBuilder);

			switch (TweakedAdventureConfig.getInstance().taigaType()) {
				case SNOWY:
					SNOWY_TAIGA_HILLS = Biome.TAIGA_HILLS;
					break;

				case SNOWY_AND_SLOWLESS:
					biomeBuilder.biome = SNOWY_TAIGA_HILLS = new TaigaBiome(getNextID());
					biomeBuilder.color(5159473).name("Snowy TaigaHills").climate(0.05f, 0.8f).enableSnow();
					biomeHeightType.applyHillsHeight(biomeBuilder);
					biomeBuilder.biome = Biome.TAIGA_HILLS;
					biomeBuilder.climate(0.3f, 0.8f).disableSnow();
					break;

				default:
					biomeBuilder.biome = Biome.TAIGA_HILLS;
					biomeBuilder.climate(0.3f, 0.8f).disableSnow();
					SNOWY_TAIGA_HILLS = Biome.TAIGA_HILLS;
					break;
			}
		} else {
			DESERT_HILLS = Biome.DESERT;
			FOREST_HILLS = Biome.FOREST;
			TAIGA_HILLS = Biome.TAIGA;
			SNOWY_TAIGA_HILLS = SNOWY_TAIGA;
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
