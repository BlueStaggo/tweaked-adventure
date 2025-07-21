package io.bluestaggo.tweakedadventure.mixin.worldgen;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import io.bluestaggo.tweakedadventure.TweakedAdventureConfig;
import io.bluestaggo.tweakedadventure.worldgen.TweakedAdventureBiomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.layer.AddDeepOceanLayer;
import net.minecraft.world.biome.layer.Layer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(AddDeepOceanLayer.class)
public abstract class AddDeepOceanLayerMixin extends Layer {
	public AddDeepOceanLayerMixin(long seed) {
		super(seed);
	}

	@ModifyExpressionValue(
		method = "nextValues",
		at = @At(
			value = "FIELD",
			target = "Lnet/minecraft/world/biome/Biome;FOREST:Lnet/minecraft/world/biome/Biome;",
			ordinal = 1
		)
	)
	private Biome handleForestInPlains(Biome original) {
		if (TweakedAdventureConfig.getInstance().forestsInPlains()) {
			return original;
		}
		return Biome.PLAINS;
	}

	@ModifyVariable(
		method = "nextValues",
		ordinal = 7,
		at = @At(
			value = "STORE",
			ordinal = 0
		)
	)
	private int addExtraHillVariants(int value) {
		if (value == TweakedAdventureBiomes.SNOWY_TAIGA.id) {
			value = TweakedAdventureBiomes.SNOWY_TAIGA_HILLS.id;
		}
		return value;
	}
}
