package io.bluestaggo.tweakedadventure.mixin.worldgen;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import io.bluestaggo.tweakedadventure.TweakedAdventureConfig;
import io.bluestaggo.tweakedadventure.worldgen.TweakedAdventureBiomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.layer.Layer;
import net.minecraft.world.biome.layer.RiverMixLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(RiverMixLayer.class)
public abstract class RiverMixLayerMixin extends Layer {
	public RiverMixLayerMixin(long seed) {
		super(seed);
	}

	@ModifyExpressionValue(
		method = "nextValues",
		at = @At(
			value = "FIELD",
			target = "Lnet/minecraft/world/biome/Biome;id:I",
			ordinal = 1
		)
	)
	private int moreFrozenRiverBiomes(int icePlains, @Local(ordinal = 4) int i, @Local(ordinal = 0) int[] baseBiomes) {
		int baseBiome = baseBiomes[i];
		if (baseBiome == Biome.ICE_MOUNTAINS.id
			|| TweakedAdventureConfig.getInstance().taigaType() != TweakedAdventureConfig.TaigaType.SNOWLESS && (
				baseBiome == TweakedAdventureBiomes.SNOWY_TAIGA.id
				|| baseBiome == TweakedAdventureBiomes.SNOWY_TAIGA_HILLS.id
			)) {
			return baseBiome;
		}
		return icePlains;
	}
}
