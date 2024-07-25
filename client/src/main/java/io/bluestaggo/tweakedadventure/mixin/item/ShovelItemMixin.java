package io.bluestaggo.tweakedadventure.mixin.item;

import io.bluestaggo.tweakedadventure.TAUtil;
import net.minecraft.block.Block;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ShovelItem.class)
public abstract class ShovelItemMixin extends ToolItem {
	@Unique private static final Block[] EXTRA_EFFECTIVE_BLOCKS = {
		Block.FARMLAND,
		Block.SOUL_SAND,
		Block.MYCELIUM,
	};

	private ShovelItemMixin(int id, int attackDamage, ToolMaterial toolMaterial, Block[] blocks) {
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
