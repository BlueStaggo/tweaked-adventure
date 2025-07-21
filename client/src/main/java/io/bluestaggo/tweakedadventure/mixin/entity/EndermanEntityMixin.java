package io.bluestaggo.tweakedadventure.mixin.entity;

import io.bluestaggo.tweakedadventure.TweakedAdventureConfig;
import net.minecraft.block.Block;
import net.minecraft.entity.living.mob.hostile.EndermanEntity;
import net.minecraft.entity.living.mob.hostile.HostileEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

@Mixin(EndermanEntity.class)
public abstract class EndermanEntityMixin extends HostileEntity {
	@Shadow
	private static boolean[] HOLDABLE_BLOCKS;

	public EndermanEntityMixin(World world) {
		super(world);
	}

	@Inject(
		method = "<clinit>",
		at = @At("TAIL")
	)
	private static void overrideHeldBlocks(CallbackInfo ci) {
		switch (TweakedAdventureConfig.getInstance().endermanGriefingLevel()) {
			case DISABLED:
				Arrays.fill(HOLDABLE_BLOCKS, false);
				break;
			case RESTRICTED:
				Arrays.fill(HOLDABLE_BLOCKS, false);
				HOLDABLE_BLOCKS[Block.GRASS.id] = true;
				HOLDABLE_BLOCKS[Block.DIRT.id] = true;
				HOLDABLE_BLOCKS[Block.SAND.id] = true;
				HOLDABLE_BLOCKS[Block.GRAVEL.id] = true;
				HOLDABLE_BLOCKS[Block.YELLOW_FLOWER.id] = true;
				HOLDABLE_BLOCKS[Block.RED_FLOWER.id] = true;
				HOLDABLE_BLOCKS[Block.BROWN_MUSHROOM.id] = true;
				HOLDABLE_BLOCKS[Block.RED_MUSHROOM.id] = true;
				HOLDABLE_BLOCKS[Block.TNT.id] = true;
				HOLDABLE_BLOCKS[Block.CACTUS.id] = true;
				HOLDABLE_BLOCKS[Block.CLAY.id] = true;
				HOLDABLE_BLOCKS[Block.PUMPKIN.id] = true;
				HOLDABLE_BLOCKS[Block.MELON_BLOCK.id] = true;
				break;
			case FULL:
				break;
		}
	}
}
