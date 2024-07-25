package io.bluestaggo.tweakedadventure.mixin.worldgen;

import com.llamalad7.mixinextras.sugar.Local;
import io.bluestaggo.tweakedadventure.TweakedAdventureConfig;
import net.minecraft.world.biome.layer.AddIslandLayer;
import net.minecraft.world.biome.layer.Layer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AddIslandLayer.class)
public abstract class AddIslandLayerMixin extends Layer {
	private AddIslandLayerMixin(long seed) {
		super(seed);
	}

	@Redirect(
		method = "nextValues",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/biome/layer/AddIslandLayer;nextInt(I)I",
			ordinal = 1
		)
	)
	private int noPlainsOceans(AddIslandLayer instance, int bound,
							   @Local(ordinal = 10) int neighbor0,
							   @Local(ordinal = 11) int neighbor1,
							   @Local(ordinal = 12) int neighbor2,
							   @Local(ordinal = 13) int neighbor3) {
		if (TweakedAdventureConfig.getInstance().oceansInPlains()
				|| neighbor0 == 0 || neighbor1 == 0 || neighbor2 == 0 || neighbor3 == 0) {
			return this.nextInt(bound);
		}
		return 0;
	}
}
