package io.bluestaggo.tweakedadventure.mixin.block;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Block.class)
public abstract class BlockMixin {
	@ModifyExpressionValue(
		method = "afterMinedByPlayer",
		at = @At(
			value = "CONSTANT",
			args = "floatValue=0.025"
		)
	)
	private float reduceMiningExhaustion(float constant) {
		return 0.005f;
	}
}
