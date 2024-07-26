package io.bluestaggo.tweakedadventure.mixin.entity;

import net.minecraft.entity.living.mob.passive.animal.AnimalEntity;
import net.minecraft.entity.living.mob.passive.animal.PigEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PigEntity.class)
public abstract class PigEntityMixin extends AnimalEntity {
	private PigEntityMixin(World world) {
		super(world);
	}

	@Override
	protected void dropLoot(boolean bl) {
		int drops = this.random.nextInt(3) + 1;
		for (int i = 0; i < drops; ++i) {
			if (this.onFireTimer > 0) {
				this.dropItem(Item.COOKED_PORKCHOP.id, 1);
			} else {
				this.dropItem(Item.PORKCHOP.id, 1);
			}
		}
	}
}