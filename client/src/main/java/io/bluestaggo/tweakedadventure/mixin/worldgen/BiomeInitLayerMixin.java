package io.bluestaggo.tweakedadventure.mixin.worldgen;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import io.bluestaggo.tweakedadventure.TweakedAdventureConfig;
import io.bluestaggo.tweakedadventure.worldgen.TweakedAdventureBiomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.layer.BiomeInitLayer;
import net.minecraft.world.biome.layer.Layer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BiomeInitLayer.class)
public abstract class BiomeInitLayerMixin extends Layer {
	public BiomeInitLayerMixin(long seed) {
		super(seed);
	}

	@ModifyExpressionValue(
		method = "nextValues",
		at = @At(
			value = "FIELD",
			target = "Lnet/minecraft/world/biome/Biome;ICE_PLAINS:Lnet/minecraft/world/biome/Biome;"
		)
	)
	private Biome addSnowyTaiga(Biome original) {
		if (TweakedAdventureConfig.getInstance().taigaType() != TweakedAdventureConfig.TaigaType.SNOWLESS
				&& this.nextInt(TweakedAdventureConfig.getInstance().snowyTaigaInPlainsChance()) == 0) {
			return TweakedAdventureBiomes.SNOWY_TAIGA;
		}
		return original;
	}
}
