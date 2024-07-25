package io.bluestaggo.tweakedadventure.mixin.block;

import net.minecraft.block.Block;
import net.minecraft.block.BookshelfBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import java.util.Random;

@Mixin(BookshelfBlock.class)
public abstract class BookshelfBlockMixin extends Block {
	private BookshelfBlockMixin(int id, Material material) {
		super(id, material);
	}

	@Override
	public int getDropItem(int metadata, Random random) {
		return Item.BOOK.id;
	}

	@ModifyConstant(
		method = "getBaseDropCount",
		constant = @Constant(
			intValue = 0
		)
	)
	public int getBaseDropCount(int constant) {
		return 3;
	}
}
