package io.bluestaggo.tweakedadventure.mixin;

import io.bluestaggo.tweakedadventure.TweakedAdventureConfig;
import net.minecraft.entity.living.mob.hostile.ZombieEntity;
import net.minecraft.entity.living.mob.hostile.ZombiePigmanEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(ZombiePigmanEntity.class)
public abstract class ZombiePigmanEntityMixin extends ZombieEntity {
	private ZombiePigmanEntityMixin(World world) {
		super(world);
	}

	@ModifyConstant(
		method = "dropLoot",
		constant = @Constant(
			intValue = 2,
			ordinal = 0
		)
	)
	private int dropMoreFlesh(int constant) {
		if (TweakedAdventureConfig.getInstance().pigmenDropPorkchops()) {
			return 3;
		}
		return constant;
	}

	@Redirect(
		method = "dropLoot",
		at = @At(
			value = "FIELD",
			target = "Lnet/minecraft/item/Item;ROTTEN_FLESH:Lnet/minecraft/item/Item;"
		)
	)
	private Item dropFleshLoot() {
		return TweakedAdventureConfig.getInstance().pigmenDropPorkchops() ? Item.COOKED_PORKCHOP : Item.ROTTEN_FLESH;
	}

	@Redirect(
		method = "getDroppedItem",
		at = @At(
			value = "FIELD",
			target = "Lnet/minecraft/item/Item;ROTTEN_FLESH:Lnet/minecraft/item/Item;"
		)
	)
	private Item getDroppedFlesh() {
		return TweakedAdventureConfig.getInstance().pigmenDropPorkchops() ? Item.COOKED_PORKCHOP : Item.ROTTEN_FLESH;
	}
}
