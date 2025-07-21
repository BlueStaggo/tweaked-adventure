package io.bluestaggo.tweakedadventure.mixin.worldgen;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import io.bluestaggo.tweakedadventure.TweakedAdventureConfig;
import net.minecraft.world.WorldView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SwampBiome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SwampBiome.class)
public abstract class SwampBiomeMixin extends Biome {
	private SwampBiomeMixin(int id) {
		super(id);
	}

	@ModifyExpressionValue(
		method = "<init>",
		at = @At(
			value = "CONSTANT",
			args = "intValue=14745518"
		)
	)
	private int restoreWaterColor(int original) {
		if (TweakedAdventureConfig.getInstance().murkySwamps()) {
			return original;
		}
		return 0xFFFFFF;
	}

	@Inject(
		method = "getGrassColor",
		at = @At("HEAD"),
		cancellable = true
	)
	private void getPureGrassColor(CallbackInfoReturnable<Integer> cir) {
		if (!TweakedAdventureConfig.getInstance().murkySwamps()) {
			cir.setReturnValue(super.getGrassColor());
		}
	}

	@Inject(
		method = "getFoliageColor",
		at = @At("HEAD"),
		cancellable = true
	)
	private void getPureFoliageColor(CallbackInfoReturnable<Integer> cir) {
		if (!TweakedAdventureConfig.getInstance().murkySwamps()) {
			cir.setReturnValue(super.getFoliageColor());
		}
	}
}
