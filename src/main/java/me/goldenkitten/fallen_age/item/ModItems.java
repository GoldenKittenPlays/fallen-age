package me.goldenkitten.fallen_age.item;

import me.goldenkitten.fallen_age.sound.ModSounds;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.RecordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, me.goldenkitten.fallen_age.FallenAge.MOD_ID);

    public static final RegistryObject<Item> ALEXANDRITE = ITEMS.register("alexandrite",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_ALEXANDRITE = ITEMS.register("raw_alexandrite",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DESOLATE_HOPE_RECORD = ITEMS.register("desolate_hope_record",
            () -> new RecordItem(1, ModSounds.DESOLATE_HOPE, new Item.Properties().stacksTo(1), 4300));
    public static final RegistryObject<Item> DESOLATE_ECHOES_RECORD = ITEMS.register("desolate_echoes_record",
            () -> new RecordItem(1, ModSounds.DESOLATE_ECHOES, new Item.Properties().stacksTo(1), 4820));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
