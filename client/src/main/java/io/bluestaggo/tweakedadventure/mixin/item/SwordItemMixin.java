package io.bluestaggo.tweakedadventure.mixin.item;

import io.bluestaggo.tweakedadventure.TweakedAdventureConfig;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SwordItem.class)
public abstract class SwordItemMixin extends Item {
	private SwordItemMixin(int id) {
		super(id);
	}

	@Redirect(
		method = "<init>",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/item/Item$ToolMaterial;getAttackDamage()I"
		)
	)
	private int reduceDamage(ToolMaterial instance) {
		if (TweakedAdventureConfig.getInstance().releaseMeleeDamage()) {
			return instance.getAttackDamage();
		}
		return instance.getAttackDamage() * 2;
	}
}
