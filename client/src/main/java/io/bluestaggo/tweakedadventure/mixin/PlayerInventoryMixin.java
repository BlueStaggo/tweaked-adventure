package io.bluestaggo.tweakedadventure.mixin;

import io.bluestaggo.tweakedadventure.TweakedAdventureConfig;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
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
		if (!TweakedAdventureConfig.getInstance().releaseArmor()) {
			return;
		}

		int protection = 0;
		for (ItemStack armor : this.armorSlots) {
			if (armor != null && armor.getItem() instanceof ArmorItem) {
				protection += ((ArmorItem) armor.getItem()).protection;
			}
		}
		cir.setReturnValue(protection);
	}
}
