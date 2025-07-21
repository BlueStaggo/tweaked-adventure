package io.bluestaggo.tweakedadventure.mixin.item;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.bluestaggo.tweakedadventure.TweakedAdventureConfig;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(SwordItem.class)
public abstract class SwordItemMixin extends Item {
	private SwordItemMixin(int id) {
		super(id);
	}

	@WrapOperation(
		method = "<init>",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/item/Item$ToolMaterial;getAttackDamage()I"
		)
	)
	private int reduceDamage(ToolMaterial instance, Operation<Integer> original) {
		int damage = original.call(instance);
		if (!TweakedAdventureConfig.getInstance().releaseMeleeDamage()) {
			damage *= 2;
		}
		return damage;
	}
}
