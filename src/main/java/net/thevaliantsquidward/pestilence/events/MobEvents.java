package net.thevaliantsquidward.pestilence.events;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.thevaliantsquidward.pestilence.Pestilence;
import net.thevaliantsquidward.pestilence.entities.SummonedZac;
import net.thevaliantsquidward.pestilence.entities.ZacTheRat;
import net.thevaliantsquidward.pestilence.registry.PestilenceEntities;

@Mod.EventBusSubscriber(modid = Pestilence.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MobEvents {

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(PestilenceEntities.ZAC_THE_RAT.get(), ZacTheRat.createAttributes().build());
        event.put(PestilenceEntities.SUMMONED_ZAC_THE_RAT.get(), SummonedZac.createAttributes().build());
    }
}
