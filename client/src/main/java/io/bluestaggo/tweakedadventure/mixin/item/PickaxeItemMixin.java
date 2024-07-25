package io.bluestaggo.tweakedadventure.mixin.item;

import io.bluestaggo.tweakedadventure.TAUtil;
import net.minecraft.block.Block;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(PickaxeItem.class)
public abstract class PickaxeItemMixin extends ToolItem {
	@Unique private static final Block[] EXTRA_EFFECTIVE_BLOCKS = {
		Block.REDSTONE_ORE,
		Block.LIT_REDSTONE_ORE,
		Block.RAIL,
		Block.DETECTOR_RAIL,
		Block.POWERED_RAIL,
		Block.FURNACE,
		Block.LIT_FURNACE,
		Block.BRICKS,
		Block.STONE_BRICKS,
		Block.IRON_BARS,
		Block.STONE_STAIRS,
		Block.BRICK_STAIRS,
		Block.STONE_BRICK_STAIRS,
		Block.GLOWSTONE,
		Block.STONE_PRESSURE_PLATE,
		Block.STONE_BUTTON,
		Block.NETHER_BRICKS,
		Block.NETHER_BRICK_STAIRS,
		Block.NETHER_BRICK_FENCE,
		Block.ENCHANTING_TABLE,
		Block.BREWING_STAND,
		Block.CAULDRON,
		Block.END_STONE,
	};

	private PickaxeItemMixin(int id, int attackDamage, ToolMaterial toolMaterial, Block[] blocks) {
		super(id, attackDamage, toolMaterial, blocks);
	}

	@ModifyArg(
		method = "<init>",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/item/ToolItem;<init>(IILnet/minecraft/item/Item$ToolMaterial;[Lnet/minecraft/block/Block;)V"
		),
		index = 3
	)
	private static Block[] combineBlocks(Block[] value) {
		return TAUtil.combineArray(value, EXTRA_EFFECTIVE_BLOCKS, Block[]::new);
	}
}
