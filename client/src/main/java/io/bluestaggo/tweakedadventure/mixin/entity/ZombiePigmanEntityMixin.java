package io.bluestaggo.tweakedadventure.mixin.entity;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import io.bluestaggo.tweakedadventure.TweakedAdventureConfig;
import net.minecraft.entity.living.mob.hostile.ZombieEntity;
import net.minecraft.entity.living.mob.hostile.ZombiePigmanEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ZombiePigmanEntity.class)
public abstract class ZombiePigmanEntityMixin extends ZombieEntity {
	private ZombiePigmanEntityMixin(World world) {
		super(world);
	}

	@ModifyExpressionValue(
		method = "getDroppedItem",
		at = @At(
			value = "FIELD",
			target = "Lnet/minecraft/item/Item;COOKED_PORKCHOP:Lnet/minecraft/item/Item;"
		)
	)
	private Item getDroppedFlesh(Item original) {
		if (TweakedAdventureConfig.getInstance().pigmenDropPorkchops()) {
			return original;
		}
		return Item.ROTTEN_FLESH;
	}
}
