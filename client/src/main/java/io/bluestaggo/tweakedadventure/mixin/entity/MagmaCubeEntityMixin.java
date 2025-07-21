package io.bluestaggo.tweakedadventure.mixin.entity;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import io.bluestaggo.tweakedadventure.TweakedAdventureConfig;
import net.minecraft.entity.living.mob.MagmaCubeEntity;
import net.minecraft.entity.living.mob.SlimeEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(MagmaCubeEntity.class)
public abstract class MagmaCubeEntityMixin extends SlimeEntity {
	private MagmaCubeEntityMixin(World world) {
		super(world);
	}

	@ModifyExpressionValue(
		method = "getDroppedItem",
		at = @At(
			value = "CONSTANT",
			args = "intValue=0"
		)
	)
	protected int getDroppedItem(int original) {
		if (original == 0
				&& TweakedAdventureConfig.getInstance().droppedMagmaCream()
				&& this.getSize() > 1
				&& this.random.nextInt(4) == 0) {
			original = Item.MAGMA_CREAM.id;
		}
		return original;
	}
}
