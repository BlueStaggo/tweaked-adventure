package io.bluestaggo.tweakedadventure.mixin.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.living.LivingEntity;
import net.minecraft.world.World;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
	private LivingEntityMixin(World world) {
		super(world);
	}

	@Redirect(
		method = "tickAi",
		at = @At(
			value = "FIELD",
			target = "Lnet/minecraft/entity/living/LivingEntity;jumpingCooldown:Z",
			opcode = Opcodes.PUTFIELD
		)
	)
	private void allowContinuousJumping(LivingEntity instance, boolean value) {
	}
}
