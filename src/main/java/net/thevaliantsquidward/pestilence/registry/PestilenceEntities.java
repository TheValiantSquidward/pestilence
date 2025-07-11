package net.thevaliantsquidward.pestilence.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thevaliantsquidward.pestilence.Pestilence;
import net.thevaliantsquidward.pestilence.entities.*;

@Mod.EventBusSubscriber(modid = Pestilence.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PestilenceEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Pestilence.MOD_ID);

    public static final RegistryObject<EntityType<ZacTheRat>> ZAC_THE_RAT = ENTITY_TYPES.register(
            "zac_the_rat", () ->
            EntityType.Builder.of(ZacTheRat::new, MobCategory.MONSTER)
                    .sized(1.0F, 1.0F)
                    .clientTrackingRange(10)
                    .fireImmune()
                    .build(new ResourceLocation(Pestilence.MOD_ID, "zac_the_rat").toString())
    );
}
