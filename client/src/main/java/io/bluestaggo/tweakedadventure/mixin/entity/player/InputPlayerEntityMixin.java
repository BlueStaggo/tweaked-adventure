package io.bluestaggo.tweakedadventure.mixin.entity.player;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.entity.living.player.InputPlayerEntity;
import net.minecraft.entity.living.player.PlayerEntity;
import net.minecraft.world.World;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(InputPlayerEntity.class)
public abstract class InputPlayerEntityMixin extends PlayerEntity {
	private InputPlayerEntityMixin(World world) {
		super(world);
	}

	@ModifyExpressionValue(
		method = "setSprinting",
		at = @At(
			value = "CONSTANT",
			args = "intValue=600"
		)
	)
	private int setInfiniteSprinting(int constant) {
		return Integer.MAX_VALUE;
	}

	@ModifyExpressionValue(
		method = "tickAi",
		at = @At(
			value = "FIELD",
			target = "Lnet/minecraft/client/entity/living/player/InputPlayerEntity;onGround:Z",
			opcode = Opcodes.GETFIELD,
			ordinal = 0
		)
	)
	private boolean flySprinting(boolean original) {
		return original || this.abilities.flying;
	}
}
