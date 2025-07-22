package io.bluestaggo.tweakedadventure.mixin.worldgen;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.bluestaggo.tweakedadventure.TAUtil;
import io.bluestaggo.tweakedadventure.TweakedAdventureConfig;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkSource;
import net.minecraft.world.gen.chunk.OverworldChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(OverworldChunkGenerator.class)
public abstract class OverworldChunkGeneratorMixin implements ChunkSource {
	@WrapOperation(
		method = "populateChunk",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/World;canFreeze(III)Z"
		)
	)
	public boolean populateIceUnderneath(World world, int x, int y, int z, Operation<Boolean> original) {
		int originalY = y;

		if (TweakedAdventureConfig.getInstance().snowUnderLeaves()) {
			int block;
			boolean newLayer = false;
			while (((block = world.getBlock(x, y, z)) == Block.LEAVES.id
					|| block == 0
					|| TweakedAdventureConfig.getInstance().snowReplacesPlants()
						&& TAUtil.canBlockBeReplacedBySnow(block, true))
					&& y > 0) {
				y--;
				newLayer = true;
			}

			if (newLayer && original.call(world, x, y, z)) {
				world.setBlock(x, y, z, Block.ICE.id);
			}
		}

		return original.call(world, x, originalY, z);
	}

	@WrapOperation(
		method = "populateChunk",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/World;canSnowFall(III)Z"
		)
	)
	public boolean populateSnowUnderneath(World world, int x, int y, int z, Operation<Boolean> original) {
		int originalY = y;

		if (TweakedAdventureConfig.getInstance().snowUnderLeaves()) {
			int block;
			boolean newLayer = false;
			while (((block = world.getBlock(x, y - 1, z)) == Block.LEAVES.id
					|| block == 0
					|| TweakedAdventureConfig.getInstance().snowReplacesPlants()
						&& TAUtil.canBlockBeReplacedBySnow(block, true))
					&& y > 0) {
				y--;
				newLayer = true;
			}

			if (newLayer) {
				int prevBlock = world.getBlock(x, y, z);
				int prevMeta = 0;

				if (TAUtil.canBlockBeReplacedBySnow(prevBlock, false)) {
					prevMeta = world.getBlockMetadata(x, y, z);
					world.setBlockQuietly(x, y, z, 0);
				}

				if (original.call(world, x, y, z)) {
					world.setBlock(x, y, z, Block.SNOW_LAYER.id);
				} else {
					world.setBlockWithMetadataQuietly(x, y, z, prevBlock, prevMeta);
				}
			}
		}

		int prevBlock = world.getBlock(x, originalY, z);
		int prevMeta = 0;

		if (TAUtil.canBlockBeReplacedBySnow(prevBlock, false)) {
			prevMeta = world.getBlockMetadata(x, originalY, z);
			world.setBlockQuietly(x, originalY, z, 0);
		}

		boolean placeSnow = original.call(world, x, originalY, z);
		if (!placeSnow) {
			world.setBlockWithMetadataQuietly(x, originalY, z, prevBlock, prevMeta);
		}
		return placeSnow;
	}
}
