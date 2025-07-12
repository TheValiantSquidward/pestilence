package net.thevaliantsquidward.pestilence.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.thevaliantsquidward.pestilence.registry.PestilenceSoundEvents;

import java.util.EnumSet;

public class ZacTheRat extends Ghast {

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState shootAnimationState = new AnimationState();

    public ZacTheRat(EntityType<? extends ZacTheRat> entityType, Level level) {
        super(entityType, level);
        this.xpReward = 50;
        this.moveControl = new ZacTheRatMoveControl(this);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 250.0D).add(Attributes.FOLLOW_RANGE, 128.0D).add(Attributes.FLYING_SPEED, 0.15D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(5, new ZacTheRatRandomFloatAroundGoal(this));
        this.goalSelector.addGoal(7, new ZacTheRatLookGoal(this));
        this.goalSelector.addGoal(7, new ZacTheRatAttackGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, (target) -> Math.abs(target.getY() - this.getY()) <= 8.0D));
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
        this.shootAnimationState.animateWhen(this.isAlive() && this.isCharging(), this.tickCount);
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

    private static boolean isReflectedFireball(DamageSource source) {
        return source.getDirectEntity() instanceof LargeFireball && source.getEntity() instanceof Player;
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        return !isReflectedFireball(source) && super.isInvulnerableTo(source);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
            return !this.isInvulnerableTo(source) && super.hurt(source, amount);
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
    public boolean isNoGravity() {
        return true;
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

    @Override
    public void travel(Vec3 vec3) {
        if (this.isControlledByLocalInstance()) {
            if (this.isInWater()) {
                this.moveRelative(0.02F, vec3);
                this.move(MoverType.SELF, this.getDeltaMovement());
                this.setDeltaMovement(this.getDeltaMovement().scale(0.8F));
            } else if (this.isInLava()) {
                this.moveRelative(0.02F, vec3);
                this.move(MoverType.SELF, this.getDeltaMovement());
                this.setDeltaMovement(this.getDeltaMovement().scale(0.8F));
            } else {
                BlockPos ground = getBlockPosBelowThatAffectsMyMovement();
                float f = 0.91F;

                if (this.onGround()) {
                    f = this.level().getBlockState(ground).getFriction(this.level(), ground, this) * 0.91F;
                }

                float f1 = 0.16277137F / (f * f * f);
                f = 0.91F;

                if (this.onGround()) {
                    f = this.level().getBlockState(ground).getFriction(this.level(), ground, this) * 0.91F;
                }

                this.moveRelative(this.onGround() ? 0.1F * f1 : 0.02F, vec3);
                this.move(MoverType.SELF, this.getDeltaMovement());
                this.setDeltaMovement(this.getDeltaMovement().scale(f));
            }
        }
        this.calculateEntityAnimation(false);
    }

    // goals
    static class ZacTheRatAttackGoal extends Goal {

        private final ZacTheRat zacTheRat;
        public int chargeTime;

        public ZacTheRatAttackGoal(ZacTheRat zacTheRat) {
            this.zacTheRat = zacTheRat;
        }

        public boolean canUse() {
            return this.zacTheRat.getTarget() != null;
        }

        @Override
        public boolean canContinueToUse() {
            LivingEntity target = this.zacTheRat.getTarget();
            if (target == null) {
                return false;
            } else if (!target.isAlive()) {
                return false;
            } else if (!this.zacTheRat.isWithinRestriction(target.blockPosition())) {
                return false;
            } else {
                return !(target instanceof Player) || !target.isSpectator() && !((Player) target).isCreative() || !this.zacTheRat.getNavigation().isDone();
            }
        }

        public void start() {
            this.chargeTime = 0;
            this.zacTheRat.setCharging(false);
            this.zacTheRat.setAggressive(true);
        }

        public void stop() {
            this.zacTheRat.setCharging(false);
            LivingEntity target = this.zacTheRat.getTarget();
            if (!EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(target)) {
                this.zacTheRat.setTarget(null);
            }
            this.zacTheRat.setAggressive(false);
            this.zacTheRat.getNavigation().stop();
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            LivingEntity target = this.zacTheRat.getTarget();

            if (target != null) {
                if (target.distanceToSqr(this.zacTheRat) < 4096.0D && this.zacTheRat.getSensing().hasLineOfSight(target)) {
                    this.chargeTime++;

                    this.zacTheRat.getLookControl().setLookAt(target, 10.0F, this.zacTheRat.getMaxHeadXRot());

                    if (this.chargeTime == 32) {
                        zacTheRat.playSound(PestilenceSoundEvents.ZAC_THE_RAT_IDLE.get(), 10.0F, zacTheRat.getVoicePitch());
                    }

                    if (this.chargeTime == 40) {
                        if (!this.zacTheRat.isSilent()) {
                            this.zacTheRat.playSound(PestilenceSoundEvents.ZAC_THE_RAT_SHOOT.get(), 10.0F, this.zacTheRat.getVoicePitch());
                        }

                        Vec3 vec3 = this.zacTheRat.getViewVector(1.0F);
                        double targetX = target.getX() - (this.zacTheRat.getX() + vec3.x() * 4.0D);
                        double targetY = target.getBoundingBox().minY + this.zacTheRat.getTarget().getBbHeight() / 2.0F - (0.5D + this.zacTheRat.getY() + this.zacTheRat.getBbHeight() / 2.0F);
                        double targetZ = target.getZ() - (this.zacTheRat.getZ() + vec3.z() * 4.0D);
                        LargeFireball fireball = new LargeFireball(this.zacTheRat.level(), this.zacTheRat, targetX, targetY, targetZ, this.zacTheRat.getExplosionPower());
                        fireball.setPos(this.zacTheRat.getX() + vec3.x() * 4.0D, this.zacTheRat.getY(0.5D) + 0.5D, this.zacTheRat.getZ() + vec3.z() * 4.0D);
                        this.zacTheRat.level().addFreshEntity(fireball);

                        this.chargeTime = -40;
                    }
                } else if (this.chargeTime > 0) {
                    this.chargeTime--;
                }

                this.zacTheRat.setCharging(this.chargeTime > 10);
            }
        }
    }

    static class ZacTheRatRandomFloatAroundGoal extends Goal {

        private final ZacTheRat zacTheRat;

        public ZacTheRatRandomFloatAroundGoal(ZacTheRat zacTheRat) {
            this.zacTheRat = zacTheRat;
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        public boolean canUse() {
            MoveControl moveControl = this.zacTheRat.getMoveControl();
            if (!moveControl.hasWanted()) {
                return true;
            } else {
                double d0 = moveControl.getWantedX() - this.zacTheRat.getX();
                double d1 = moveControl.getWantedY() - this.zacTheRat.getY();
                double d2 = moveControl.getWantedZ() - this.zacTheRat.getZ();
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                return d3 < 1.0D || d3 > 3600.0D;
            }
        }

        public boolean canContinueToUse() {
            return false;
        }

        public void start() {
            RandomSource randomsource = this.zacTheRat.getRandom();
            double d0 = this.zacTheRat.getX() + (double) ((randomsource.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d1 = this.zacTheRat.getY() + (double) ((randomsource.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d2 = this.zacTheRat.getZ() + (double) ((randomsource.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.zacTheRat.getMoveControl().setWantedPosition(d0, d1, d2, 1.0D);
        }
    }

    static class ZacTheRatMoveControl extends MoveControl {
        private final ZacTheRat zacTheRat;
        private int floatDuration;

        public ZacTheRatMoveControl(ZacTheRat zacTheRat) {
            super(zacTheRat);
            this.zacTheRat = zacTheRat;
        }

        public void tick() {
            if (!this.zacTheRat.isCharging()) {
                if (this.operation == MoveControl.Operation.MOVE_TO) {
                    if (this.floatDuration-- <= 0) {
                        this.floatDuration += this.zacTheRat.getRandom().nextInt(5) + 2;
                        Vec3 vec3 = new Vec3(this.wantedX - this.zacTheRat.getX(), this.wantedY - this.zacTheRat.getY(), this.wantedZ - this.zacTheRat.getZ());
                        double d0 = vec3.length();
                        vec3 = vec3.normalize();
                        if (this.canReach(vec3, Mth.ceil(d0))) {
                            this.zacTheRat.setDeltaMovement(this.zacTheRat.getDeltaMovement().add(vec3.scale(this.zacTheRat.getAttribute(Attributes.FLYING_SPEED).getValue())));
                        } else {
                            this.operation = MoveControl.Operation.WAIT;
                        }
                    }
                }
            }
        }

        private boolean canReach(Vec3 vec3, int distance) {
            AABB aabb = this.zacTheRat.getBoundingBox();

            for(int i = 1; i < distance; ++i) {
                aabb = aabb.move(vec3);
                if (!this.zacTheRat.level().noCollision(this.zacTheRat, aabb)) {
                    return false;
                }
            }

            return true;
        }
    }

    static class ZacTheRatLookGoal extends Goal {
        private final ZacTheRat zacTheRat;

        public ZacTheRatLookGoal(ZacTheRat zacTheRat) {
            this.zacTheRat = zacTheRat;
            this.setFlags(EnumSet.of(Flag.LOOK));
        }

        public boolean canUse() {
            return true;
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void tick() {
            if (this.zacTheRat.getTarget() == null) {
                Vec3 $$0 = this.zacTheRat.getDeltaMovement();
                this.zacTheRat.setYRot(-((float)Mth.atan2($$0.x, $$0.z)) * (180F / (float) Math.PI));
                this.zacTheRat.yBodyRot = this.zacTheRat.getYRot();
            } else {
                LivingEntity $$1 = this.zacTheRat.getTarget();
                if ($$1.distanceToSqr(this.zacTheRat) < (double)4096.0F) {
                    double $$3 = $$1.getX() - this.zacTheRat.getX();
                    double $$4 = $$1.getZ() - this.zacTheRat.getZ();
                    this.zacTheRat.setYRot(-((float)Mth.atan2($$3, $$4)) * (180F / (float) Math.PI));
                    this.zacTheRat.yBodyRot = this.zacTheRat.getYRot();
                }
            }
        }
    }
}
