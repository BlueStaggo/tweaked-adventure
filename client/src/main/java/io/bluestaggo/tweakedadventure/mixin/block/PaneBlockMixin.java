package io.bluestaggo.tweakedadventure.mixin.block;

import io.bluestaggo.tweakedadventure.TweakedAdventureConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BookshelfBlock;
import net.minecraft.block.PaneBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import java.util.Random;

@Mixin(PaneBlock.class)
public abstract class PaneBlockMixin extends Block {
	private PaneBlockMixin(int id, Material material) {
		super(id, material);
	}

	@Override
	public int getBaseDropCount(Random random) {
		if (this.material == Material.GLASS && !TweakedAdventureConfig.getInstance().dropPanes()) {
			return 0;
		}
		return super.getBaseDropCount(random);
	}
}
