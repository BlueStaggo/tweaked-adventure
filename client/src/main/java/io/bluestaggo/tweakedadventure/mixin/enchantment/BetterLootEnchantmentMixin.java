package io.bluestaggo.tweakedadventure.mixin.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentCategory;
import net.minecraft.enchantment.BetterLootEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(BetterLootEnchantment.class)
public abstract class BetterLootEnchantmentMixin extends Enchantment {
	private BetterLootEnchantmentMixin(int id, int type, EnchantmentCategory category) {
		super(id, type, category);
	}

	@ModifyConstant(
		method = "getMinXpRequirement",
		constant = @Constant(
			intValue = 20
		)
	)
	private int getMinXpRequirementR13_1(int constant) {
		return 15;
	}

	@ModifyConstant(
		method = "getMinXpRequirement",
		constant = @Constant(
			intValue = 12
		)
	)
	private int getMinXpRequirementR13_2(int constant) {
		return 9;
	}
}
