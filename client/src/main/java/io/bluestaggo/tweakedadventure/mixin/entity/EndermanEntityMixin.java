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
				break;
			case FULL:
				HOLDABLE_BLOCKS[Block.STONE.id] = true;
				HOLDABLE_BLOCKS[Block.COBBLESTONE.id] = true;
				HOLDABLE_BLOCKS[Block.PLANKS.id] = true;
				HOLDABLE_BLOCKS[Block.GOLD_ORE.id] = true;
				HOLDABLE_BLOCKS[Block.IRON_ORE.id] = true;
				HOLDABLE_BLOCKS[Block.COAL_ORE.id] = true;
				HOLDABLE_BLOCKS[Block.LOG.id] = true;
				HOLDABLE_BLOCKS[Block.LEAVES.id] = true;
				HOLDABLE_BLOCKS[Block.SPONGE.id] = true;
				HOLDABLE_BLOCKS[Block.GLASS.id] = true;
				HOLDABLE_BLOCKS[Block.LAPIS_ORE.id] = true;
				HOLDABLE_BLOCKS[Block.LAPIS_BLOCK.id] = true;
				HOLDABLE_BLOCKS[Block.SANDSTONE.id] = true;
				HOLDABLE_BLOCKS[Block.WOOL.id] = true;
				HOLDABLE_BLOCKS[Block.GOLD_BLOCK.id] = true;
				HOLDABLE_BLOCKS[Block.IRON_BLOCK.id] = true;
				HOLDABLE_BLOCKS[Block.BRICKS.id] = true;
				HOLDABLE_BLOCKS[Block.BOOKSHELF.id] = true;
				HOLDABLE_BLOCKS[Block.MOSSY_COBBLESTONE.id] = true;
				HOLDABLE_BLOCKS[Block.DIAMOND_ORE.id] = true;
				HOLDABLE_BLOCKS[Block.DIAMOND_BLOCK.id] = true;
				HOLDABLE_BLOCKS[Block.CRAFTING_TABLE.id] = true;
				HOLDABLE_BLOCKS[Block.REDSTONE_ORE.id] = true;
				HOLDABLE_BLOCKS[Block.LIT_REDSTONE_ORE.id] = true;
				HOLDABLE_BLOCKS[Block.ICE.id] = true;
				HOLDABLE_BLOCKS[Block.NETHERRACK.id] = true;
				HOLDABLE_BLOCKS[Block.SOUL_SAND.id] = true;
				HOLDABLE_BLOCKS[Block.GLOWSTONE.id] = true;
				HOLDABLE_BLOCKS[Block.LIT_PUMPKIN.id] = true;
				HOLDABLE_BLOCKS[Block.STONE_BRICKS.id] = true;
				HOLDABLE_BLOCKS[Block.BROWN_MUSHROOM_BLOCK.id] = true;
				HOLDABLE_BLOCKS[Block.RED_MUSHROOM_BLOCK.id] = true;
				HOLDABLE_BLOCKS[Block.MYCELIUM.id] = true;
				HOLDABLE_BLOCKS[Block.NETHER_BRICKS.id] = true;
				HOLDABLE_BLOCKS[Block.END_STONE.id] = true;
				break;
		}
	}
}
