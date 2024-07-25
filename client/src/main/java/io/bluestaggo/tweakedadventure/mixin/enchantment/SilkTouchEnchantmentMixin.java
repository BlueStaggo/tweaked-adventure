package io.bluestaggo.tweakedadventure.mixin.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentCategory;
import net.minecraft.enchantment.SilkTouchEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(SilkTouchEnchantment.class)
public abstract class SilkTouchEnchantmentMixin extends Enchantment {
	private SilkTouchEnchantmentMixin(int id, int type, EnchantmentCategory category) {
		super(id, type, category);
	}

	@ModifyConstant(
		method = "getMinXpRequirement",
		constant = @Constant(
			intValue = 25
		)
	)
	private int getMinXpRequirementR13(int constant) {
		return 15;
	}
}
