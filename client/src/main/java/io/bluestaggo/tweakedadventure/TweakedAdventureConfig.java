package io.bluestaggo.tweakedadventure;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import io.bluestaggo.tweakedadventure.worldgen.BiomeBuilder;
import net.minecraft.client.Minecraft;

import java.io.*;

public class TweakedAdventureConfig {
	private static TweakedAdventureConfig instance = new TweakedAdventureConfig();

	private boolean modernExhaustion = true;
	private boolean lowerXpRequirement = true;
	private boolean higherOres = true;
	private boolean cheaperRecipes = true;
	private boolean moreStackables = true;
	private boolean nightmares = false;
	private boolean forestsInPlains = false;
	private boolean hillBiomes = true;
	private boolean oceansInPlains = false;
	private boolean releaseLandScale = false;
	private boolean releaseArmor = true;
	private boolean releaseCrits = true;
	private boolean releaseKnockback = true;
	private boolean releaseMeleeDamage = true;
	private boolean animalDespawning = true;
	private boolean frequentAnimalRespawning = true;
	private boolean dropPanes = true;
	private boolean pigmenDropPorkchops = true;
	private boolean growableSwampTrees = true;
	private ExperienceBarType experienceBarType = ExperienceBarType.NONE;
	private BiomeHeightType biomeHeightType = BiomeHeightType.HYBRID;
	private EndermanGriefingLevel endermanGriefingLevel = EndermanGriefingLevel.RESTRICTED;

	public boolean modernExhaustion() {
		return modernExhaustion;
	}

	public boolean lowerXpRequirement() {
		return lowerXpRequirement;
	}

	public boolean higherOres() {
		return higherOres;
	}

	public boolean cheaperRecipes() {
		return cheaperRecipes;
	}

	public boolean moreStackables() {
		return moreStackables;
	}

	public boolean nightmares() {
		return nightmares;
	}

	public boolean forestsInPlains() {
		return forestsInPlains;
	}

	public boolean hillBiomes() {
		return hillBiomes;
	}

	public boolean oceansInPlains() {
		return oceansInPlains;
	}

	public boolean releaseLandScale() {
		return releaseLandScale;
	}

	public boolean releaseArmor() {
		return releaseArmor;
	}

	public boolean releaseCrits() {
		return releaseCrits;
	}

	public boolean releaseKnockback() {
		return releaseKnockback;
	}

	public boolean releaseMeleeDamage() {
		return releaseMeleeDamage;
	}

	public boolean animalDespawning() {
		return animalDespawning;
	}

	public boolean frequentAnimalRespawning() {
		return frequentAnimalRespawning;
	}

	public boolean dropPanes() {
		return dropPanes;
	}

	public boolean pigmenDropPorkchops() {
		return pigmenDropPorkchops;
	}

	public boolean growableSwampTrees() {
		return growableSwampTrees;
	}

	public ExperienceBarType experienceBarType() {
		return experienceBarType;
	}

	public BiomeHeightType biomeHeightType() {
		return biomeHeightType;
	}

	public EndermanGriefingLevel endermanGriefingLevel() {
		return endermanGriefingLevel;
	}

	public static TweakedAdventureConfig getInstance() {
		return instance;
	}

	private TweakedAdventureConfig() {
	}

	public enum ExperienceBarType {
		@SerializedName("none") NONE,
		@SerializedName("barOnly") BAR_ONLY,
		@SerializedName("barAndSkillPoints") BAR_AND_SKILL_POINTS,
		@SerializedName("barAndLevels") BAR_AND_LEVELS
	}

	public enum BiomeHeightType {
		@SerializedName("b1.8") BETA_1_8,
		@SerializedName("r1.1") RELEASE_1_1,
		@SerializedName("r1.3") RELEASE_1_3,
		@SerializedName("hybrid") HYBRID;

		public void applyOceanHeight(BiomeBuilder biomeBuilder) {
			switch (this) {
				case BETA_1_8:
					biomeBuilder.height(-1.0f, 0.5f);
				case RELEASE_1_1:
				case RELEASE_1_3:
				case HYBRID:
					biomeBuilder.height(-1.0f, 0.4f);
			}
		}

		public void applyExtremeHillsHeight(BiomeBuilder biomeBuilder) {
			switch (this) {
				case BETA_1_8:
				case HYBRID:
					biomeBuilder.height(0.2f, 1.8f);
				case RELEASE_1_1:
					biomeBuilder.height(0.2f, 1.3f);
				case RELEASE_1_3:
					biomeBuilder.height(0.3f, 1.5f);
			}
		}

		public void applyIceMountainsHeight(BiomeBuilder biomeBuilder) {
			switch (this) {
				case BETA_1_8:
				case HYBRID:
					biomeBuilder.height(0.2f, 1.8f);
				case RELEASE_1_1:
					biomeBuilder.height(0.2f, 1.2f);
				case RELEASE_1_3:
					biomeBuilder.height(0.3f, 1.3f);
			}
		}

		public void applyHillsHeight(BiomeBuilder biomeBuilder) {
			switch (this) {
				case BETA_1_8:
				case HYBRID:
				case RELEASE_1_3:
					biomeBuilder.height(0.3f, 0.8f);
				case RELEASE_1_1:
					biomeBuilder.height(0.2f, 0.7f);
			}
		}

		public void applyForestHillsHeight(BiomeBuilder biomeBuilder) {
			switch (this) {
				case BETA_1_8:
				case HYBRID:
				case RELEASE_1_3:
					biomeBuilder.height(0.3f, 0.7f);
				case RELEASE_1_1:
					biomeBuilder.height(0.2f, 0.6f);
			}
		}
	}

	public enum EndermanGriefingLevel {
		@SerializedName("disabled") DISABLED,
		@SerializedName("restricted") RESTRICTED,
		@SerializedName("full") FULL
	}

	static {
		Gson gson = new GsonBuilder()
			.setPrettyPrinting()
			.create();
		File configFile = new File(Minecraft.getRunDirectory(), "config/tweaked-adventure.json");
		if (configFile.exists()) {
			try {
				JsonReader jsonReader = new JsonReader(new FileReader(configFile.getAbsolutePath()));
				instance = gson.fromJson(jsonReader, TweakedAdventureConfig.class);
				jsonReader.close();
			} catch (IOException e) {
				System.err.println("Failed to read Tweaked Adventure config!");
				e.printStackTrace();
			}
		}

		configFile.getParentFile().mkdirs();
		if (!configFile.isDirectory()) {
			try {
				FileWriter fileWriter = new FileWriter(configFile.getAbsolutePath());
				fileWriter.write(gson.toJson(instance));
				fileWriter.close();
			} catch (IOException e) {
				System.err.println("Failed to write Tweaked Adventure config!");
				e.printStackTrace();
			}
		}
	}
}
