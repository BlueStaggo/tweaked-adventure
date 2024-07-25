package io.bluestaggo.tweakedadventure.mixin;

import io.bluestaggo.tweakedadventure.TweakedAdventureConfig;
import net.minecraft.entity.living.mob.PathAwareEntity;
import net.minecraft.entity.living.mob.passive.animal.AnimalEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AnimalEntity.class)
public abstract class AnimalEntityMixin extends PathAwareEntity {
	public AnimalEntityMixin(World world) {
		super(world);
	}

	@Inject(
		method = "isHostile",
		at = @At("HEAD"),
		cancellable = true
	)
	private void isHostile(CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(TweakedAdventureConfig.getInstance().animalDespawning());
	}

	@Inject(
		method = "isBreedingItem",
		at = @At("HEAD"),
		cancellable = true
	)
	private void rejectBreedingItem(ItemStack par1, CallbackInfoReturnable<Boolean> cir) {
		if (TweakedAdventureConfig.getInstance().animalDespawning()) {
			cir.setReturnValue(false);
		}
	}
}
