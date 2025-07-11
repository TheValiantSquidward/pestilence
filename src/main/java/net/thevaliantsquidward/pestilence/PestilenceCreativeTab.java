package net.thevaliantsquidward.pestilence;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.thevaliantsquidward.pestilence.registry.PestilenceItems;

public class PestilenceCreativeTab {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Pestilence.MOD_ID);

    public static final RegistryObject<CreativeModeTab> PESTILENCE_TAB = CREATIVE_TABS.register("pestilence",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(PestilenceItems.ZAC_THE_RAT_SPAWN_EGG.get()))
                    .title(Component.translatable("itemGroup.pestilence"))
                    .displayItems((pParameters, tabOutput) -> {

                        // spawn eggs
                        PestilenceItems.ITEMS.getEntries().forEach(spawnEgg -> {
                            if ((spawnEgg.get() instanceof ForgeSpawnEggItem)) {
                                tabOutput.accept(spawnEgg.get());
                            }
                        });

                    }).build());
}