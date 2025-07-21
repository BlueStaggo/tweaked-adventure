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
		method = "dropLoot",
		at = @At(
			value = "CONSTANT",
			args = "intValue=2",
			ordinal = 0
		)
	)
	private int dropMoreFlesh(int original) {
		if (TweakedAdventureConfig.getInstance().pigmenDropPorkchops()) {
			return 3;
		}
		return original;
	}

	@ModifyExpressionValue(
		method = "dropLoot",
		at = @At(
			value = "FIELD",
			target = "Lnet/minecraft/item/Item;ROTTEN_FLESH:Lnet/minecraft/item/Item;"
		)
	)
	private Item dropFleshLoot(Item original) {
		if (TweakedAdventureConfig.getInstance().pigmenDropPorkchops()) {
			return Item.COOKED_PORKCHOP;
		}
		return original;
	}

	@ModifyExpressionValue(
		method = "getDroppedItem",
		at = @At(
			value = "FIELD",
			target = "Lnet/minecraft/item/Item;ROTTEN_FLESH:Lnet/minecraft/item/Item;"
		)
	)
	private Item getDroppedFlesh(Item original) {
		if (TweakedAdventureConfig.getInstance().pigmenDropPorkchops()) {
			return Item.COOKED_PORKCHOP;
		}
		return original;
	}
}
