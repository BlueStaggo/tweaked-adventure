package io.bluestaggo.tweakedadventure.mixin.enchantment;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import io.bluestaggo.tweakedadventure.TweakedAdventureConfig;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentCategory;
import net.minecraft.enchantment.SilkTouchEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(SilkTouchEnchantment.class)
public abstract class SilkTouchEnchantmentMixin extends Enchantment {
	private SilkTouchEnchantmentMixin(int id, int type, EnchantmentCategory category) {
		super(id, type, category);
	}

	@ModifyExpressionValue(
		method = "getMinXpRequirement",
		at = @At(
			value = "CONSTANT",
			args = "intValue=25"
		)
	)
	private int getMinXpRequirementR13(int original) {
		if (!TweakedAdventureConfig.getInstance().lowerXpRequirement()) {
			return original;
		}
		return 15;
	}
}
