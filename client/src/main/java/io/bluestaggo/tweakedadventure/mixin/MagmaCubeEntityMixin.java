package io.bluestaggo.tweakedadventure.mixin;

import net.minecraft.entity.living.mob.MagmaCubeEntity;
import net.minecraft.entity.living.mob.SlimeEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(MagmaCubeEntity.class)
public abstract class MagmaCubeEntityMixin extends SlimeEntity {
	private MagmaCubeEntityMixin(World world) {
		super(world);
	}

	@ModifyConstant(
		method = "getDroppedItem",
		constant = @Constant(
			intValue = 0
		)
	)
	protected int getDroppedItem(int constant) {
		return Item.MAGMA_CREAM.id;
	}
}
