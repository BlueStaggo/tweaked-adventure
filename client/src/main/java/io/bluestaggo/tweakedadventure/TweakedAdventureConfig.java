package io.bluestaggo.tweakedadventure;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import net.minecraft.client.Minecraft;

import java.io.*;

public class TweakedAdventureConfig {
	private static TweakedAdventureConfig instance = new TweakedAdventureConfig();

	private boolean forestsInPlains = false;
	private boolean hillBiomes = true;
	private TaigaType taigaType = TaigaType.SNOWY_AND_SLOWLESS;
	private boolean releaseArmor = true;
	private boolean releaseCrits = true;
	private boolean releaseKnockback = true;
	private boolean releaseMeleeDamage = true;
	private boolean animalDespawning = true;
	private boolean frequentAnimalRespawning = true;
	private boolean dropPanes = true;
	private boolean growableSwampTrees = true;
	private boolean murkySwamps = true;
	private ExperienceBarType experienceBarType = ExperienceBarType.NONE;

	public boolean forestsInPlains() {
		return forestsInPlains;
	}

	public boolean hillBiomes() {
		return hillBiomes;
	}

	public TaigaType taigaType() {
		return taigaType;
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

	public boolean murkySwamps() {
		return murkySwamps;
	}

	public boolean growableSwampTrees() {
		return growableSwampTrees;
	}

	public ExperienceBarType experienceBarType() {
		return experienceBarType;
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

	public enum TaigaType {
		@SerializedName("snowless") SNOWLESS,
		@SerializedName("snowy") SNOWY,
		@SerializedName("snowy_and_snowless") SNOWY_AND_SLOWLESS
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
