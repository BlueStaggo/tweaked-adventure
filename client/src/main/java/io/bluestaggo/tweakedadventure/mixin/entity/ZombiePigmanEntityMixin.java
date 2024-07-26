package io.bluestaggo.tweakedadventure.mixin.entity;

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

	@Redirect(
		method = "getDroppedItem",
		at = @At(
			value = "FIELD",
			target = "Lnet/minecraft/item/Item;COOKED_PORKCHOP:Lnet/minecraft/item/Item;"
		)
	)
	private Item getDroppedFlesh() {
		return TweakedAdventureConfig.getInstance().pigmenDropPorkchops() ? Item.COOKED_PORKCHOP : Item.ROTTEN_FLESH;
	}
}
