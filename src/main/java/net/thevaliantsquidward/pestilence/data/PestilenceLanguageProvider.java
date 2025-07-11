package net.thevaliantsquidward.pestilence.data;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.thevaliantsquidward.pestilence.Pestilence;
import net.thevaliantsquidward.pestilence.PestilenceCreativeTab;
import net.thevaliantsquidward.pestilence.registry.PestilenceEntities;
import net.thevaliantsquidward.pestilence.registry.PestilenceItems;
import net.thevaliantsquidward.pestilence.registry.PestilenceSoundEvents;
import org.apache.commons.lang3.text.WordUtils;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class PestilenceLanguageProvider extends LanguageProvider {

    public PestilenceLanguageProvider(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), Pestilence.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {

        // creative tab
        creativeTab(PestilenceCreativeTab.PESTILENCE_TAB.get(), "Pestilence");

        // items
        PestilenceItems.AUTO_TRANSLATE.forEach(this::forItem);

        // entities
        forEntity(PestilenceEntities.ZAC_THE_RAT);

        // sounds
        sound(PestilenceSoundEvents.ZAC_THE_RAT_HURT, "Zac The Rat hurts");
        sound(PestilenceSoundEvents.ZAC_THE_RAT_DEATH, "Zac The Rat dies");
        sound(PestilenceSoundEvents.ZAC_THE_RAT_IDLE, "Zac The Rat chatters");
        sound(PestilenceSoundEvents.ZAC_THE_RAT_SHOOT, "Zac The Rat shoots");
    }

    private void forBlock(Supplier<? extends Block> block) {
        addBlock(block, PestilenceTextUtils.createTranslation(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block.get())).getPath()));
    }

    private void forItem(Supplier<? extends Item> item) {
        addItem(item, PestilenceTextUtils.createTranslation(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item.get())).getPath()));
    }

    private void forEntity(Supplier<? extends EntityType<?>> entity) {
        addEntityType(entity, PestilenceTextUtils.createTranslation(Objects.requireNonNull(ForgeRegistries.ENTITY_TYPES.getKey(entity.get())).getPath()));
    }

    private String format(ResourceLocation registryName) {
        return WordUtils.capitalizeFully(registryName.getPath().replace("_", " "));
    }

    protected void painting(String name, String author) {
        add("painting." + Pestilence.MOD_ID + "." + name + ".title",  PestilenceTextUtils.createTranslation(name));
        add("painting." + Pestilence.MOD_ID + "." + name + ".author",  author);
    }

    protected void musicDisc(Supplier<? extends Item> item, String description) {
        String disc = item.get().getDescriptionId();
        add(disc, "Music Disc");
        add(disc + ".desc", description);
    }

    public void creativeTab(CreativeModeTab key, String name){
        add(key.getDisplayName().getString(), name);
    }

    public void sound(Supplier<? extends SoundEvent> key, String subtitle){
        add("subtitles." + key.get().getLocation().getPath(), subtitle);
    }

    public void blockEntity(String beName,String name){
        add(Pestilence.MOD_ID + ".blockentity." + beName, name);
    }

    private void dnaItem(Item... items) {
        List.of(items).forEach((item -> this.add(item, "Bottle of " + format(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item))).replace(" Dna Bottle", "") + " DNA")));
    }
}
