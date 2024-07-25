package io.bluestaggo.tweakedadventure.mixin.worldgen;

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

	@ModifyConstant(
		method = "<init>",
		constant = @Constant(
			intValue = 14745456
		)
	)
	private int restoreWaterColor(int constant) {
		if (TweakedAdventureConfig.getInstance().murkySwamps()) {
			return constant;
		}
		return 0xFFFFFF;
	}

	@Inject(
		method = "getGrassColor",
		at = @At("HEAD"),
		cancellable = true
	)
	private void getPureGrassColor(WorldView world, int x, int y, int z, CallbackInfoReturnable<Integer> cir) {
		if (!TweakedAdventureConfig.getInstance().murkySwamps()) {
			cir.setReturnValue(super.getGrassColor(world, x, y, z));
		}
	}

	@Inject(
		method = "getFoliageColor",
		at = @At("HEAD"),
		cancellable = true
	)
	private void getPureFoliageColor(WorldView world, int x, int y, int z, CallbackInfoReturnable<Integer> cir) {
		if (!TweakedAdventureConfig.getInstance().murkySwamps()) {
			cir.setReturnValue(super.getFoliageColor(world, x, y, z));
		}
	}
}
