package io.bluestaggo.tweakedadventure.mixin;

import io.bluestaggo.tweakedadventure.TweakedAdventureConfig;
import net.minecraft.block.Block;
import net.minecraft.crafting.CraftingManager;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(CraftingManager.class)
public abstract class CraftingManagerMixin {
	@ModifyVariable(
		method = "registerShaped",
		at = @At("HEAD"),
		argsOnly = true,
		ordinal = 0
	)
	private ItemStack modifyShaped(ItemStack item) {
		if (!TweakedAdventureConfig.getInstance().cheaperRecipes()) {
			return item;
		}

		if (item.itemId == Block.STONE_SLAB.id && item.size == 3) {
			item.size = 6;
		}
		if (item.itemId == Block.LADDER.id && item.size == 1) {
			item.size = 3;
		}
		return item;
	}
}
