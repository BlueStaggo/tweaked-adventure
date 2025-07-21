package io.bluestaggo.tweakedadventure.mixin.entity.player;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
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

	@ModifyExpressionValue(
		method = "attack",
		at = @At(
			value = "CONSTANT",
			args = "floatValue=0.3"
		)
	)
	private float reduceAttackExhaustion(float original) {
		if (!TweakedAdventureConfig.getInstance().modernExhaustion()) {
			return original;
		}
		return 0.1f;
	}

	@ModifyExpressionValue(
		method = "jump",
		at = @At(
			value = "CONSTANT",
			args = "floatValue=0.2"
		)
	)
	private float reduceJumpExhaustion(float original) {
		if (!TweakedAdventureConfig.getInstance().modernExhaustion()) {
			return original;
		}
		return 0.05f;
	}

	@ModifyExpressionValue(
		method = "jump",
		at = @At(
			value = "CONSTANT",
			args = "floatValue=0.8"
		)
	)
	private float reduceSprintJumpExhaustion(float original) {
		if (!TweakedAdventureConfig.getInstance().modernExhaustion()) {
			return original;
		}
		return 0.2f;
	}

	@ModifyExpressionValue(
		method = "tickNonRidingMovementRelatedStats",
		at = @At(
			value = "CONSTANT",
			args = "floatValue=0.015"
		)
	)
	private float reduceSwimmingExhaustion(float original) {
		if (!TweakedAdventureConfig.getInstance().modernExhaustion()) {
			return original;
		}
		return 0.01f;
	}

	@WrapOperation(
		method = "tickNonRidingMovementRelatedStats",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/entity/living/player/PlayerEntity;addFatigue(F)V",
			ordinal = 3
		)
	)
	private void removeWalkingExhaustion(PlayerEntity instance, float amount, Operation<Void> original) {
		if (!TweakedAdventureConfig.getInstance().modernExhaustion()) {
			original.call(instance, amount);
		}
	}

	@ModifyExpressionValue(
		method = "attack",
		at = @At(
			value = "CONSTANT",
			args = "floatValue=1.0"
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
		if (TweakedAdventureConfig.getInstance().releaseCrits()) {
			amount = this.inventory.getAttackDamage(target);
			amount += this.random.nextInt(amount / 2 + 2);
		}
		return amount;
	}

	@Inject(
		method = "getNextLevelExperience",
		at = @At("HEAD"),
		cancellable = true
	)
	private void getNextLevelExperienceR13(CallbackInfoReturnable<Integer> cir) {
		if (!TweakedAdventureConfig.getInstance().lowerXpRequirement()) {
			return;
		}

		if (this.xpLevel >= 30) {
			cir.setReturnValue(62 + (this.xpLevel - 30) * 7);
		} else if (this.xpLevel >= 15) {
			cir.setReturnValue(17 + (this.xpLevel - 15) * 3);
		} else {
			cir.setReturnValue(17);
		}
	}

	@ModifyExpressionValue(
		method = "moveEntityWithVelocity",
		at = @At(
			value = "CONSTANT",
			args = "floatValue=0.05"
		)
	)
	private float moveSprintFlying(float original) {
		if (this.isSprinting()) {
			original *= 2.5f;
		}
		return original;
	}

	@Redirect(
		method = "jump",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/entity/living/player/PlayerEntity;increaseXp(I)V"
		)
	)
	private void noDebugXpOnJump(PlayerEntity instance, int levels) {
	}
}
