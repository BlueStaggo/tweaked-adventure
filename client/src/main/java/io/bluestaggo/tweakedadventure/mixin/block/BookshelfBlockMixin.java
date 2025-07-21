package io.bluestaggo.tweakedadventure.mixin.block;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.block.Block;
import net.minecraft.block.BookshelfBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
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

	@ModifyExpressionValue(
		method = "getBaseDropCount",
		at = @At(
			value = "CONSTANT",
			args = "intValue=0"
		)
	)
	public int getBaseDropCount(int original) {
		if (original == 0) {
			original = 3;
		}
		return original;
	}
}
