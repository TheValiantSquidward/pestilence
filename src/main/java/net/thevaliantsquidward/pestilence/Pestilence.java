package net.thevaliantsquidward.pestilence;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.thevaliantsquidward.pestilence.data.PestilenceItemModelProvider;
import net.thevaliantsquidward.pestilence.data.PestilenceLanguageProvider;
import net.thevaliantsquidward.pestilence.data.PestilenceSoundDefinitionsProvider;
import net.thevaliantsquidward.pestilence.registry.PestilenceEntities;
import net.thevaliantsquidward.pestilence.registry.PestilenceItems;
import net.thevaliantsquidward.pestilence.registry.PestilenceSoundEvents;

import java.util.Locale;
import java.util.concurrent.CompletableFuture;

@Mod(Pestilence.MOD_ID)
public class Pestilence {

    public static final String MOD_ID = "pestilence";

    public Pestilence() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext context = ModLoadingContext.get();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::dataSetup);

        PestilenceItems.ITEMS.register(modEventBus);
        PestilenceEntities.ENTITY_TYPES.register(modEventBus);
        PestilenceSoundEvents.SOUND_EVENTS.register(modEventBus);
        PestilenceCreativeTab.CREATIVE_TABS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void dataSetup(GatherDataEvent data) {
        DataGenerator generator = data.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> provider = data.getLookupProvider();
        ExistingFileHelper helper = data.getExistingFileHelper();

        boolean client = data.includeClient();
        generator.addProvider(client, new PestilenceItemModelProvider(data));
        generator.addProvider(client, new PestilenceSoundDefinitionsProvider(output, helper));
        generator.addProvider(client, new PestilenceLanguageProvider(data));

        boolean server = data.includeServer();
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    public static ResourceLocation modPrefix(String name) {
        return new ResourceLocation(MOD_ID, name.toLowerCase(Locale.ROOT));
    }
}
