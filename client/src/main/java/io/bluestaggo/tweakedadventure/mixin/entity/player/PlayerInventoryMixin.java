package io.bluestaggo.tweakedadventure.mixin.entity.player;

import io.bluestaggo.tweakedadventure.TweakedAdventureConfig;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerInventory.class)
public abstract class PlayerInventoryMixin implements Inventory {
	@Shadow public ItemStack[] armorSlots;

	@Inject(
		method = "getArmorProtectionValue",
		at = @At("HEAD"),
		cancellable = true
	)
	private void releaseArmorProtectionValue(CallbackInfoReturnable<Integer> cir) {
		if (TweakedAdventureConfig.getInstance().releaseArmor()) {
			return;
		}

		int totalProtection = 0;
		int totalDurability = 0;
		int totalMaxDamage = 0;
		for (ItemStack armor : this.armorSlots) {
			if (armor != null && armor.getItem() instanceof ArmorItem) {
				int maxDamage = armor.getMaxDamage();
				int damage = armor.getDamage();
				int durability = maxDamage - damage;
				totalDurability += durability;
				totalMaxDamage += maxDamage;
				int protection = ((ArmorItem) armor.getItem()).protection;
				totalProtection += protection;
			}
		}

		if(totalMaxDamage == 0) {
			cir.setReturnValue(0);
		} else {
			cir.setReturnValue((totalProtection - 1) * totalDurability / totalMaxDamage + 1);
		}
	}
}
