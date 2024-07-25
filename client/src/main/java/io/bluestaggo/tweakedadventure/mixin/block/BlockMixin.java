package io.bluestaggo.tweakedadventure.mixin.block;

import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Block.class)
public abstract class BlockMixin {
	@ModifyConstant(
		method = "afterMinedByPlayer",
		constant = @Constant(
			floatValue = 0.025f
		)
	)
	private float reduceMiningExhaustion(float constant) {
		return 0.005f;
	}
}
