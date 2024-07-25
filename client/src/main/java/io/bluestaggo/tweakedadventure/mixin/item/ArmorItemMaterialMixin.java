package io.bluestaggo.tweakedadventure.mixin.item;

import io.bluestaggo.tweakedadventure.TweakedAdventureConfig;
import net.minecraft.item.ArmorItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.item.ArmorItem.Material.CHAIN;
import static net.minecraft.item.ArmorItem.Material.IRON;
import static net.minecraft.item.ArmorItem.Material.DIAMOND;
import static net.minecraft.item.ArmorItem.Material.GOLD;

@Mixin(ArmorItem.Material.class)
public class ArmorItemMaterialMixin {
	@Unique private static final int[] BASE_DURABILITY = {11, 16, 15, 13};
	@Unique private static final int[] BASE_PROTECTION = {3, 8, 6, 3};

	@SuppressWarnings("ConstantValue")
	@Inject(
		method = "getDurability",
		at = @At("HEAD"),
		cancellable = true
	)
	private void getB18Durability(int slot, CallbackInfoReturnable<Integer> cir) {
		if (TweakedAdventureConfig.getInstance().releaseArmor()) {
			return;
		}
		cir.setReturnValue(BASE_DURABILITY[slot] * 3 <<
			((Object)this == CHAIN || (Object)this == GOLD ? 1
				: (Object)this == IRON ? 2
				: (Object)this == DIAMOND ? 3
				: 0));
	}

	@Inject(
		method = "getProtection",
		at = @At("HEAD"),
		cancellable = true
	)
	private void getB18Protection(int slot, CallbackInfoReturnable<Integer> cir) {
		if (TweakedAdventureConfig.getInstance().releaseArmor()) {
			return;
		}
		cir.setReturnValue(BASE_PROTECTION[slot]);
	}
}
