package net.thevaliantsquidward.pestilence.registry;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thevaliantsquidward.pestilence.Pestilence;

@OnlyIn(Dist.CLIENT)
public class PestilenceModelLayers {

    public static final ModelLayerLocation ZAC_THE_RAT = main("zac_the_rat");
    public static final ModelLayerLocation SUMMONED_ZAC_THE_RAT = main("zac_the_rat");

    private static ModelLayerLocation register(String id, String name) {
        return new ModelLayerLocation(new ResourceLocation(Pestilence.MOD_ID, id), name);
    }

    private static ModelLayerLocation main(String id) {
        return register(id, "main");
    }
}
