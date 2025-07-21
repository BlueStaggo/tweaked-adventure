package io.bluestaggo.tweakedadventure.mixin.enchantment;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import io.bluestaggo.tweakedadventure.TweakedAdventureConfig;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.*;

@Mixin(EnchantmentHelper.class)
public abstract class EnchantmentHelperMixin {
	@Inject(
		method = "getRequiredXpLevel",
		at = @At("HEAD"),
		cancellable = true
	)
	private static void getRequiredXpLevel(Random random, int entry, int max, ItemStack stack, CallbackInfoReturnable<Integer> cir) {
		if (!TweakedAdventureConfig.getInstance().lowerXpRequirement()) {
			return;
		}

		Item var4 = stack.getItem();
		int var5 = var4.getEnchantability();
		if(var5 <= 0) {
			cir.setReturnValue(0);
		} else {
			if(max > 15) {
				max = 15;
			}

			int var6 = random.nextInt(8) + 1 + (max >> 1) + random.nextInt(max + 1);
			cir.setReturnValue(entry == 0 ? Math.max(var6 / 3, 1) : (entry == 1 ? var6 * 2 / 3 + 1 : Math.max(var6, max * 2)));
		}
	}

	@WrapOperation(
		method = "getEnchantmentEntries",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/item/Item;getEnchantability()I"
		)
	)
	private static int getR13EnchantmentEntries1(Item instance, Operation<Integer> original) {
		int enchantability = original.call(instance);
		if (TweakedAdventureConfig.getInstance().lowerXpRequirement()) {
			enchantability /= 2;
		}
		return enchantability;
	}

	@Inject(
		method = "getEnchantmentEntries",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/enchantment/EnchantmentHelper;getAvailableEnchantmentEntries(ILnet/minecraft/item/ItemStack;)Ljava/util/Map;",
			shift = At.Shift.BEFORE
		)
	)
	private static void getR13EnchantmentEntries2(Random random, ItemStack stack, int xpLevel,
												  CallbackInfoReturnable<List> cir,
												  @Local(ordinal = 3) LocalIntRef var7) {
		if (TweakedAdventureConfig.getInstance().lowerXpRequirement() && var7.get() < 1) {
			var7.set(1);
		}
	}
}
