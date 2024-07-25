package io.bluestaggo.tweakedadventure.mixin;

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

	@ModifyConstant(
		method = "setSprinting",
		constant = @Constant(
			intValue = 600
		)
	)
	private int setInfiniteSprinting(int constant) {
		return Integer.MAX_VALUE;
	}

	@Redirect(
		method = "tickAi",
		at = @At(
			value = "FIELD",
			target = "Lnet/minecraft/client/entity/living/player/InputPlayerEntity;onGround:Z",
			opcode = Opcodes.GETFIELD,
			ordinal = 0
		)
	)
	private boolean flySprinting(InputPlayerEntity player) {
		return player.onGround || player.abilities.flying;
	}
}
