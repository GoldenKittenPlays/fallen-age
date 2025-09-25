package me.goldenkitten.fallen_age.sound;

import me.goldenkitten.fallen_age.FallenAge;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, FallenAge.MOD_ID);

    public static final RegistryObject<SoundEvent> DESOLATE_ECHOES = registerSoundEvents("desolate_echoes");
    public static final RegistryObject<SoundEvent> DESOLATE_HOPE = registerSoundEvents("desolate_hope");

    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        ResourceLocation id = new ResourceLocation(FallenAge.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
