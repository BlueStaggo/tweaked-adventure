package io.bluestaggo.tweakedadventure.mixin.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentCategory;
import net.minecraft.enchantment.ProtectionEnchantment;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ProtectionEnchantment.class)
public abstract class ProtectionEnchantmentMixin extends Enchantment {
	@Shadow @Final private static int[] XP_MODIFIER;

	private ProtectionEnchantmentMixin(int id, int type, EnchantmentCategory category) {
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
