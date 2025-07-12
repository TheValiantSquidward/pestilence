package net.thevaliantsquidward.pestilence.items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.thevaliantsquidward.pestilence.registry.PestilenceEntities;
import net.thevaliantsquidward.pestilence.registry.PestilenceSoundEvents;

import java.util.UUID;

public class CanOfJamItem extends Item {
    public CanOfJamItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide) {
            Vec3 spawnPos = player.position();


            CompoundTag persistentData = player.getPersistentData();
            CompoundTag jamData = persistentData.getCompound(Player.PERSISTED_NBT_TAG);

            if (jamData.contains("SummonedJamUUID")) {
                UUID oldUUID = jamData.getUUID("SummonedJamUUID");
                Entity oldEntity = ((ServerLevel)level).getEntity(oldUUID);
                if (oldEntity != null) {
                    oldEntity.discard();
                }
            }

            Entity newEntity = PestilenceEntities.SUMMONED_ZAC_THE_RAT.get().create(level);
            if (newEntity != null) {
                newEntity.moveTo(player.getX(), player.getY(), player.getZ());
                level.addFreshEntity(newEntity);
                level.playSound((Player)null, player.getX(), player.getY(), player.getZ(),
                        PestilenceSoundEvents.ZAC_THE_RAT_DEATH.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
                level.playSound((Player)null, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.RESPAWN_ANCHOR_CHARGE, SoundSource.PLAYERS, 1.0F, 1.0F);



                jamData.putUUID("SummonedJamUUID", newEntity.getUUID());
                persistentData.put(Player.PERSISTED_NBT_TAG, jamData);
            }
        }

        return InteractionResultHolder.success(player.getItemInHand(hand));
    }
}
