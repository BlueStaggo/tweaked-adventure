package io.bluestaggo.tweakedadventure.mixin.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentCategory;
import net.minecraft.enchantment.DamageEnchantment;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DamageEnchantment.class)
public abstract class DamageEnchantmentMixin extends Enchantment {
	@Shadow @Final private static int[] XP_MODIFIER;

	private DamageEnchantmentMixin(int id, int type, EnchantmentCategory category) {
		super(id, type, category);
	}

	@Inject(
		method = "<clinit>",
		at = @At("TAIL")
	)
	private static void clinitR13(CallbackInfo ci) {
		XP_MODIFIER[0] = 11;
	}
}
