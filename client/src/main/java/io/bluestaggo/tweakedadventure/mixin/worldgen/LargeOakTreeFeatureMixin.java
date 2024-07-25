package io.bluestaggo.tweakedadventure.mixin.worldgen;

import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.LargeOakTreeFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LargeOakTreeFeature.class)
public abstract class LargeOakTreeFeatureMixin extends Feature {
	@Redirect(
		method = "place",
		at = @At(
			value = "FIELD",
			target = "Lnet/minecraft/world/gen/feature/LargeOakTreeFeature;height:I",
			ordinal = 0
		)
	)
	private int removeHeightCheck(LargeOakTreeFeature instance) {
		return 0;
	}
}
