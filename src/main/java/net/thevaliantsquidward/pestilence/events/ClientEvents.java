package net.thevaliantsquidward.pestilence.events;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.thevaliantsquidward.pestilence.Pestilence;
import net.thevaliantsquidward.pestilence.client.models.SummonedZacTheRatModel;
import net.thevaliantsquidward.pestilence.client.models.ZacTheRatModel;
import net.thevaliantsquidward.pestilence.client.renderer.SummonedZacTheRatRenderer;
import net.thevaliantsquidward.pestilence.client.renderer.ZacTheRatRenderer;
import net.thevaliantsquidward.pestilence.registry.PestilenceEntities;
import net.thevaliantsquidward.pestilence.registry.PestilenceModelLayers;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = Pestilence.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientEvents {

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(PestilenceEntities.ZAC_THE_RAT.get(), ZacTheRatRenderer::new);
        event.registerEntityRenderer(PestilenceEntities.SUMMONED_ZAC_THE_RAT.get(), SummonedZacTheRatRenderer::new);

    }

    @SubscribeEvent
    public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(PestilenceModelLayers.ZAC_THE_RAT, ZacTheRatModel::createBodyLayer);
        event.registerLayerDefinition(PestilenceModelLayers.SUMMONED_ZAC_THE_RAT, SummonedZacTheRatModel::createBodyLayer);
    }
}