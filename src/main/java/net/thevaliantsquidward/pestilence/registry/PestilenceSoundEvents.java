package net.thevaliantsquidward.pestilence.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thevaliantsquidward.pestilence.Pestilence;

@Mod.EventBusSubscriber(modid = Pestilence.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PestilenceSoundEvents {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Pestilence.MOD_ID);

    public static final RegistryObject<SoundEvent> ZAC_THE_RAT_HURT = registerSoundEvent("zac_the_rat_hurt");
    public static final RegistryObject<SoundEvent> ZAC_THE_RAT_DEATH = registerSoundEvent("zac_the_rat_death");
    public static final RegistryObject<SoundEvent> ZAC_THE_RAT_IDLE = registerSoundEvent("zac_the_rat_idle");
    public static final RegistryObject<SoundEvent> ZAC_THE_RAT_SHOOT = registerSoundEvent("zac_the_rat_shoot");

    private static RegistryObject<SoundEvent> registerSoundEvent(final String soundName) {
        return SOUND_EVENTS.register(soundName, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Pestilence.MOD_ID, soundName)));
    }
}
