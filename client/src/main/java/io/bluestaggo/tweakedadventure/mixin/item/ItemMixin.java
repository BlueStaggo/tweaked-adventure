package io.bluestaggo.tweakedadventure.mixin.item;

import io.bluestaggo.tweakedadventure.TweakedAdventureConfig;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Item.class)
public class ItemMixin {
	@Inject(
		method = "<clinit>",
		at = @At("TAIL")
	)
	private static void modifyItems(CallbackInfo ci) {
		if (TweakedAdventureConfig.getInstance().moreStackables()) {
			Item.SIGN.setMaxStackSize(16);
			Item.WOODEN_DOOR.setMaxStackSize(64);
			Item.IRON_DOOR.setMaxStackSize(64);
		}
	}
}
