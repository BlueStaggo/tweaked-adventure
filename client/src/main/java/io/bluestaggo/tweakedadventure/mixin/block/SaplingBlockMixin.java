package io.bluestaggo.tweakedadventure.mixin.block;

import com.llamalad7.mixinextras.sugar.Local;
import io.bluestaggo.tweakedadventure.TweakedAdventureConfig;
import net.minecraft.block.PlantBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.SwampTreeFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Random;

@Mixin(SaplingBlock.class)
public class SaplingBlockMixin extends PlantBlock {
	private SaplingBlockMixin(int id, int sprite) {
		super(id, sprite);
	}

	@Redirect(
		method = "grow",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/gen/feature/Feature;place(Lnet/minecraft/world/World;Ljava/util/Random;III)Z"
		)
	)
	private boolean growSwamp(Feature feature, World world, Random random, int x, int y, int z,
							  @Local(ordinal = 3) int type) {
		if (type == 0 && TweakedAdventureConfig.getInstance().growableSwampTrees()
				&& world.getBiomeSource().getBiome(x, z) == Biome.SWAMPLAND) {
			feature = new SwampTreeFeature();
		}
		return feature.place(world, random, x, y, z);
	}
}
