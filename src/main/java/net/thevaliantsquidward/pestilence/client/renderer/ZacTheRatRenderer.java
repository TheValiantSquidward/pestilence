package net.thevaliantsquidward.pestilence.client.renderer;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thevaliantsquidward.pestilence.Pestilence;
import net.thevaliantsquidward.pestilence.client.models.ZacTheRatModel;
import net.thevaliantsquidward.pestilence.entities.ZacTheRat;
import net.thevaliantsquidward.pestilence.registry.PestilenceModelLayers;
import org.jetbrains.annotations.Nullable;

@OnlyIn(Dist.CLIENT)
public class ZacTheRatRenderer extends MobRenderer<ZacTheRat, ZacTheRatModel<ZacTheRat>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Pestilence.MOD_ID, "textures/entity/zac_the_rat.png");

    public ZacTheRatRenderer(EntityRendererProvider.Context context) {
        super(context, new ZacTheRatModel<>(context.bakeLayer(PestilenceModelLayers.ZAC_THE_RAT)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(ZacTheRat entity) {
        return TEXTURE;
    }

    @Override
    protected @Nullable RenderType getRenderType(ZacTheRat entity, boolean bodyVisible, boolean translucent, boolean glowing) {
        return RenderType.entityCutoutNoCull(TEXTURE);
    }
}
