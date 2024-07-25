package io.bluestaggo.tweakedadventure.mixin.item;

import io.bluestaggo.tweakedadventure.TweakedAdventureConfig;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(SwordItem.class)
public abstract class SwordItemMixin extends Item {
	private SwordItemMixin(int id) {
		super(id);
	}

	@ModifyConstant(
		method = "<init>",
		constant = @Constant(
			intValue = 2
		)
	)
	private int reduceDamage(int constant) {
		if (TweakedAdventureConfig.getInstance().releaseMeleeDamage()) {
			return 1;
		}
		return 2;
	}
}
