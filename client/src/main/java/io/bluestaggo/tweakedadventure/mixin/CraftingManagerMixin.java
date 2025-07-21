package io.bluestaggo.tweakedadventure.mixin;

import io.bluestaggo.tweakedadventure.TweakedAdventureConfig;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntIntImmutablePair;
import it.unimi.dsi.fastutil.ints.IntIntPair;
import net.minecraft.block.Block;
import net.minecraft.crafting.CraftingManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(CraftingManager.class)
public abstract class CraftingManagerMixin {
	@Unique
	private static Int2ObjectMap<IntIntPair> tweakedAdventure$StackSizeModifications;

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

		if (tweakedAdventure$StackSizeModifications == null) {
			tweakedAdventure$StackSizeModifications = new Int2ObjectOpenHashMap<>();
			tweakedAdventure$StackSizeModifications.put(Block.STONE_SLAB.id, new IntIntImmutablePair(3, 6));
			tweakedAdventure$StackSizeModifications.put(Block.LADDER.id, new IntIntImmutablePair(1, 3));
			if (TweakedAdventureConfig.getInstance().moreStackables()) {
				tweakedAdventure$StackSizeModifications.put(Item.SIGN.id, new IntIntImmutablePair(1, 3));
				tweakedAdventure$StackSizeModifications.put(Item.WOODEN_DOOR.id, new IntIntImmutablePair(1, 3));
				tweakedAdventure$StackSizeModifications.put(Item.IRON_DOOR.id, new IntIntImmutablePair(1, 3));
			}
		}

		IntIntPair stackSizeModification = tweakedAdventure$StackSizeModifications.get(item.itemId);
		if (stackSizeModification != null && item.size == stackSizeModification.firstInt()) {
			item.size = stackSizeModification.secondInt();
		}

		return item;
	}
}
