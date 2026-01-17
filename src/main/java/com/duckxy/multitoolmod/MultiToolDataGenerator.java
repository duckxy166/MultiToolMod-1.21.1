package com.duckxy.multitoolmod;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class MultiToolDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		// Tags are now defined as static JSON files in data/minecraft/tags/item/
		// This is more reliable for Minecraft 1.21+
	}
}