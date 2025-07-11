package net.thevaliantsquidward.pestilence.data;

import net.minecraft.data.PackOutput;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.common.data.SoundDefinitionsProvider;
import net.thevaliantsquidward.pestilence.Pestilence;
import net.thevaliantsquidward.pestilence.registry.PestilenceSoundEvents;

import java.util.function.Supplier;

@SuppressWarnings("SameParameterValue")
public class PestilenceSoundDefinitionsProvider extends SoundDefinitionsProvider {

    public PestilenceSoundDefinitionsProvider(PackOutput packOutput, ExistingFileHelper helper) {
        super(packOutput, Pestilence.MOD_ID, helper);
    }

    @Override
    public void registerSounds() {
        this.sound(PestilenceSoundEvents.ZAC_THE_RAT_HURT,
                sound(Pestilence.modPrefix("entity/zac_the_rat/hurt1")),
                sound(Pestilence.modPrefix("entity/zac_the_rat/hurt2")),
                sound(Pestilence.modPrefix("entity/zac_the_rat/hurt3"))
        );
        this.sound(PestilenceSoundEvents.ZAC_THE_RAT_DEATH,
                sound(Pestilence.modPrefix("entity/zac_the_rat/death1"))
        );
        this.sound(PestilenceSoundEvents.ZAC_THE_RAT_IDLE,
                sound(Pestilence.modPrefix("entity/zac_the_rat/idle1")),
                sound(Pestilence.modPrefix("entity/zac_the_rat/idle2")),
                sound(Pestilence.modPrefix("entity/zac_the_rat/idle3")),
                sound(Pestilence.modPrefix("entity/zac_the_rat/idle4")),
                sound(Pestilence.modPrefix("entity/zac_the_rat/idle5")),
                sound(Pestilence.modPrefix("entity/zac_the_rat/idle6")),
                sound(Pestilence.modPrefix("entity/zac_the_rat/idle7"))
        );
        this.sound(PestilenceSoundEvents.ZAC_THE_RAT_SHOOT,
                sound(Pestilence.modPrefix("entity/zac_the_rat/shoot1")),
                sound(Pestilence.modPrefix("entity/zac_the_rat/shoot2"))
        );
    }

    private void soundDefinition(Supplier<SoundEvent> soundEvent, String subtitle, SoundDefinition.Sound... sounds) {
        this.add(soundEvent.get(), SoundDefinition.definition().subtitle("subtitles." + subtitle).with(sounds));
    }

    private void sound(Supplier<SoundEvent> soundEvent, SoundDefinition.Sound... sounds){
        this.soundDefinition(soundEvent, soundEvent.get().getLocation().getPath(), sounds);
    }
}
