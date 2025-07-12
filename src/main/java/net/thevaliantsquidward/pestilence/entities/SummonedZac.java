package net.thevaliantsquidward.pestilence.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.thevaliantsquidward.pestilence.registry.PestilenceSoundEvents;

import java.util.EnumSet;

public class SummonedZac extends Animal {

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState shootAnimationState = new AnimationState();

    public SummonedZac(EntityType<? extends SummonedZac> entityType, Level level) {
        super(entityType, level);
        this.xpReward = 50;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 250.0D).add(Attributes.FOLLOW_RANGE, 128.0D).add(Attributes.FLYING_SPEED, 0.15D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new RandomStrollGoal(this, 1.0));
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            this.setupAnimationStates();
        }
    }

    private void setupAnimationStates() {
        this.idleAnimationState.animateWhen(this.isAlive(), this.tickCount);
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return null;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
        return dimensions.height * 0.75F;
    }

    @Override
    public int getMaxHeadXRot() {
        return 500;
    }


    @Override
    public SoundSource getSoundSource() {
        return SoundSource.HOSTILE;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return PestilenceSoundEvents.ZAC_THE_RAT_IDLE.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return PestilenceSoundEvents.ZAC_THE_RAT_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return PestilenceSoundEvents.ZAC_THE_RAT_DEATH.get();
    }

    protected float getSoundVolume() {
        return 50.0F;
    }



}
