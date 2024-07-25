package io.bluestaggo.tweakedadventure.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import io.bluestaggo.tweakedadventure.TweakedAdventureConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.living.LivingEntity;
import net.minecraft.entity.living.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
	@Shadow public PlayerInventory inventory;
	@Shadow public int xpLevel;

	private PlayerEntityMixin(World world) {
		super(world);
	}

	@ModifyConstant(
		method = "attack",
		constant = @Constant(
			floatValue = 0.3f
		)
	)
	private float reduceAttackExhaustion(float constant) {
		return 0.1f;
	}

	@ModifyConstant(
		method = "jump",
		constant = @Constant(
			floatValue = 0.2f
		)
	)
	private float reduceJumpExhaustion(float constant) {
		return 0.05f;
	}

	@ModifyConstant(
		method = "jump",
		constant = @Constant(
			floatValue = 0.8f
		)
	)
	private float reduceSprintJumpExhaustion(float constant) {
		return 0.2f;
	}

	@ModifyConstant(
		method = "tickNonRidingMovementRelatedStats",
		constant = @Constant(
			floatValue = 0.015f
		)
	)
	private float reduceSwimmingExhaustion(float constant) {
		return 0.01f;
	}

	@Redirect(
		method = "tickNonRidingMovementRelatedStats",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/entity/living/player/PlayerEntity;addFatigue(F)V",
			ordinal = 3
		)
	)
	private void removeWalkingExhaustion(PlayerEntity instance, float v) {
	}

	@ModifyConstant(
		method = "attack",
		constant = @Constant(
			floatValue = 0.5f
		)
	)
	private float reduceKnockback(float constant) {
		if (TweakedAdventureConfig.getInstance().releaseKnockback()) {
			return 0.5f;
		}
		return 1.0f;
	}

	@ModifyArg(
		method = "attack",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/damage/DamageSource;I)Z"
		),
		index = 1
	)
	private int reduceCrits(int amount, @Local(ordinal = 0, argsOnly = true) Entity target) {
		if (!TweakedAdventureConfig.getInstance().releaseCrits()) {
			return this.inventory.getAttackDamage(target) * 3 / 2 + 1;
		}
		return amount;
	}

	@Inject(
		method = "getNextLevelExperience",
		at = @At("HEAD"),
		cancellable = true
	)
	private void getNextLevelExperienceR13(CallbackInfoReturnable<Integer> cir) {
		if (this.xpLevel >= 30) {
			cir.setReturnValue(62 + (this.xpLevel - 30) * 7);
		} else if (this.xpLevel >= 15) {
			cir.setReturnValue(17 + (this.xpLevel - 15) * 3);
		} else {
			cir.setReturnValue(17);
		}
	}

	@ModifyConstant(
		method = "moveEntityWithVelocity",
		constant = @Constant(
			floatValue = 0.05f
		)
	)
	private float moveSprintFlying(float constant) {
		if (this.isSprinting()) {
			constant *= 2.5f;
		}
		return constant;
	}

	@ModifyConstant(
		method = "m_9018481",
		constant = @Constant(
			intValue = 6
		)
	)
	private int broChillTheHellOutYoureGonnaTriggerEpilepsyOwMyEyesHurtAndIDontEvenHaveEpilepsyAAAAA(int constant) {
		return 8;
	}
}
