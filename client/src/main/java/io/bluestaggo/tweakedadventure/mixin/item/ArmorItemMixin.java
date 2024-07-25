package io.bluestaggo.tweakedadventure.mixin.item;

import com.llamalad7.mixinextras.sugar.Local;
import io.bluestaggo.tweakedadventure.TweakedAdventureConfig;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ArmorItem.class)
public abstract class ArmorItemMixin extends Item {
	@Shadow @Final private static int[] BASE_PROTECTION;
	@Shadow @Final private static int[] BASE_DURABILITY;
	@Unique private static final int[][] PROTECTION_PER_MATERIAL = {
		{1, 3, 2, 1},
		{2, 5, 4, 1},
		{2, 6, 5, 2},
		{3, 8, 6, 3},
		{2, 5, 3, 1}
	};
	@Unique private static final int[] DURABILITY_PER_MATERIAL = {5, 15, 15, 33, 7};

	private ArmorItemMixin(int id) {
		super(id);
	}

	@Redirect(
		method = "<init>",
		at = @At(
			value = "FIELD",
			target = "Lnet/minecraft/item/ArmorItem;BASE_PROTECTION:[I"
		)
	)
	private int[] releaseProtection(@Local(ordinal = 2, argsOnly = true) int materialId) {
		if (!TweakedAdventureConfig.getInstance().releaseArmor()) {
			return BASE_PROTECTION;
		}
		return PROTECTION_PER_MATERIAL[materialId];
	}

	@ModifyArg(
		method = "<init>",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/item/ArmorItem;setMaxDamage(I)Lnet/minecraft/item/Item;"
		)
	)
	private int releaseDurability(int par1,
								  @Local(ordinal = 1, argsOnly = true) int durability,
								  @Local(ordinal = 2, argsOnly = true) int materialId,
								  @Local(ordinal = 3, argsOnly = true) int slot) {
		if (!TweakedAdventureConfig.getInstance().releaseArmor()) {
			return BASE_DURABILITY[slot] * 3 << durability;
		}
		return BASE_DURABILITY[slot] * DURABILITY_PER_MATERIAL[slot];
	}
}
