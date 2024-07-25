package io.bluestaggo.tweakedadventure.mixin.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentCategory;
import net.minecraft.enchantment.UnbreakingEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(UnbreakingEnchantment.class)
public abstract class UnbreakingEnchantmentMixin extends Enchantment {
	private UnbreakingEnchantmentMixin(int id, int type, EnchantmentCategory category) {
		super(id, type, category);
	}

	@ModifyConstant(
		method = "getMinXpRequirement",
		constant = @Constant(
			intValue = 10
		)
	)
	private int getMinXpRequirementR13(int constant) {
		return 8;
	}
}
