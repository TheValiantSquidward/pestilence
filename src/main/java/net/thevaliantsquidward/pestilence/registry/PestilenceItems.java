package net.thevaliantsquidward.pestilence.registry;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thevaliantsquidward.pestilence.Pestilence;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class PestilenceItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Pestilence.MOD_ID);
    public static List<RegistryObject<? extends Item>> AUTO_TRANSLATE = new ArrayList<>();


    public static final RegistryObject<Item> CAN_OF_JAM = ITEMS.register("can_of_jam", () -> new Item(new Item.Properties()));

    // spawn eggs
    public static final RegistryObject<Item> ZAC_THE_RAT_SPAWN_EGG = registerSpawnEggItem("zac_the_rat", PestilenceEntities.ZAC_THE_RAT, 0xbab2b2, 0x95fffa);

    private static <I extends Item> RegistryObject<I> registerItem(String name, Supplier<? extends I> supplier) {
        RegistryObject<I> item = ITEMS.register(name, supplier);
        AUTO_TRANSLATE.add(item);
        return item;
    }

    private static <I extends Item> RegistryObject<I> registerItemNoLang(String name, Supplier<? extends I> supplier) {
        RegistryObject<I> item = ITEMS.register(name, supplier);
        return item;
    }

    private static RegistryObject<Item> registerSpawnEggItem(String name, RegistryObject type, int baseColor, int spotColor) {
        return registerItem(name + "_spawn_egg", () -> new ForgeSpawnEggItem(type, baseColor, spotColor, new Item.Properties()));
    }

    public static Item.Properties registerFoodValue(FoodProperties food) {
        return new Item.Properties().food(food);
    }
}
