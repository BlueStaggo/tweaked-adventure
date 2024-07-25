package io.bluestaggo.tweakedadventure.mixin.enchantment;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.*;

@Mixin(EnchantmentHelper.class)
public abstract class EnchantmentHelperMixin {
	/**
	 * @author BlueStaggo
	 * @reason 1.3 enchantment mechanics
	 */
	@Overwrite
	public static int getRequiredXpLevel(Random random, int entry, int max, ItemStack stack) {
		Item var4 = stack.getItem();
		int var5 = var4.getEnchantability();
		if(var5 <= 0) {
			return 0;
		} else {
			if(max > 15) {
				max = 15;
			}

			int var6 = random.nextInt(8) + 1 + (max >> 1) + random.nextInt(max + 1);
			return entry == 0 ? Math.max(var6 / 3, 1) : (entry == 1 ? var6 * 2 / 3 + 1 : Math.max(var6, max * 2));
		}
	}

	@Redirect(
		method = "getEnchantmentEntries",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/item/Item;getEnchantability()I"
		)
	)
	private static int getR13EnchantmentEntries1(Item item) {
		return item.getEnchantability() / 2;
	}

	@Inject(
		method = "getEnchantmentEntries",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/enchantment/EnchantmentHelper;getAvailableEnchantmentEntries(ILnet/minecraft/item/ItemStack;)Ljava/util/Map;",
			shift = At.Shift.BEFORE
		)
	)
	private static void getR13EnchantmentEntries1(Random random, ItemStack stack, int xpLevel,
												  CallbackInfoReturnable<List> cir,
												  @Local(ordinal = 3) LocalIntRef var7) {
		if (var7.get() < 1) {
			var7.set(1);
		}
	}
}
