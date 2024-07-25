package io.bluestaggo.tweakedadventure.mixin;

import io.bluestaggo.tweakedadventure.TweakedAdventureConfig;
import net.minecraft.world.NaturalSpawner;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Random;

@Mixin(NaturalSpawner.class)
public abstract class NaturalSpawnerMixin {
	@Inject(
		method = "populateChunk",
		at = @At("HEAD"),
		cancellable = true
	)
	private static void blockPopulateChunk(World world, Biome biome, int chunkCenterX, int chunkCenterZ, int rangeX, int rangeZ, Random random, CallbackInfo ci) {
		if (TweakedAdventureConfig.getInstance().frequentAnimalRespawning()) {
			ci.cancel();
		}
	}
}
