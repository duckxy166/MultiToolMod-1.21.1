package com.duckxy.multitoolmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MultiTool implements ModInitializer {
	public static final String MOD_ID = "multitool";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	// Modifier IDs
	private static final Identifier ATTACK_DAMAGE_MODIFIER_ID = Identifier.of(MOD_ID, "base_attack_damage");
	private static final Identifier ATTACK_SPEED_MODIFIER_ID = Identifier.of(MOD_ID, "base_attack_speed");

	// ========== WOODEN MULTI-TOOL ==========
	public static final RegistryKey<Item> WOODEN_MULTI_TOOL_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, "wooden_multi_tool"));
	public static final Item WOODEN_MULTI_TOOL = new MultiToolItem(new Item.Settings()
			.maxDamage(59)
			.attributeModifiers(createAttributes(4.0, -2.4)),
			2.0F, 0 // miningSpeed, miningLevel (wood)
	);

	// ========== STONE MULTI-TOOL ==========
	public static final RegistryKey<Item> STONE_MULTI_TOOL_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, "stone_multi_tool"));
	public static final Item STONE_MULTI_TOOL = new MultiToolItem(new Item.Settings()
			.maxDamage(131)
			.attributeModifiers(createAttributes(5.0, -2.4)),
			4.0F, 1 // miningSpeed, miningLevel (stone)
	);

	// ========== IRON MULTI-TOOL ==========
	public static final RegistryKey<Item> IRON_MULTI_TOOL_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, "iron_multi_tool"));
	public static final Item IRON_MULTI_TOOL = new MultiToolItem(new Item.Settings()
			.maxDamage(250)
			.attributeModifiers(createAttributes(6.0, -2.4)),
			6.0F, 2 // miningSpeed, miningLevel (iron)
	);

	// ========== GOLDEN MULTI-TOOL ==========
	public static final RegistryKey<Item> GOLDEN_MULTI_TOOL_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, "golden_multi_tool"));
	public static final Item GOLDEN_MULTI_TOOL = new MultiToolItem(new Item.Settings()
			.maxDamage(32)
			.attributeModifiers(createAttributes(4.0, -2.4)),
			12.0F, 0 // miningSpeed, miningLevel (gold = wood level but fast)
	);

	// ========== DIAMOND MULTI-TOOL ==========
	public static final RegistryKey<Item> DIAMOND_MULTI_TOOL_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, "diamond_multi_tool"));
	public static final Item DIAMOND_MULTI_TOOL = new MultiToolItem(new Item.Settings()
			.maxDamage(1561)
			.attributeModifiers(createAttributes(7.0, -2.4)),
			8.0F, 3 // miningSpeed, miningLevel (diamond)
	);

	// ========== NETHERITE MULTI-TOOL ==========
	public static final RegistryKey<Item> NETHERITE_MULTI_TOOL_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, "netherite_multi_tool"));
	public static final Item NETHERITE_MULTI_TOOL = new MultiToolItem(new Item.Settings()
			.maxDamage(2031)
			.fireproof() // Fire resistant!
			.attributeModifiers(createAttributes(8.0, -2.4)),
			9.0F, 4 // miningSpeed, miningLevel (netherite)
	);

	// Keep the original MULTI_TOOL as an alias for DIAMOND for backwards compatibility
	public static final RegistryKey<Item> MULTI_TOOL_KEY = DIAMOND_MULTI_TOOL_KEY;
	public static final Item MULTI_TOOL = DIAMOND_MULTI_TOOL;

	private static AttributeModifiersComponent createAttributes(double attackDamage, double attackSpeed) {
		return AttributeModifiersComponent.builder()
				.add(EntityAttributes.GENERIC_ATTACK_DAMAGE,
						new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, attackDamage, EntityAttributeModifier.Operation.ADD_VALUE),
						AttributeModifierSlot.MAINHAND)
				.add(EntityAttributes.GENERIC_ATTACK_SPEED,
						new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, attackSpeed, EntityAttributeModifier.Operation.ADD_VALUE),
						AttributeModifierSlot.MAINHAND)
				.build();
	}

	@Override
	public void onInitialize() {
		// Register all items
		Registry.register(Registries.ITEM, WOODEN_MULTI_TOOL_KEY, WOODEN_MULTI_TOOL);
		Registry.register(Registries.ITEM, STONE_MULTI_TOOL_KEY, STONE_MULTI_TOOL);
		Registry.register(Registries.ITEM, IRON_MULTI_TOOL_KEY, IRON_MULTI_TOOL);
		Registry.register(Registries.ITEM, GOLDEN_MULTI_TOOL_KEY, GOLDEN_MULTI_TOOL);
		Registry.register(Registries.ITEM, DIAMOND_MULTI_TOOL_KEY, DIAMOND_MULTI_TOOL);
		Registry.register(Registries.ITEM, NETHERITE_MULTI_TOOL_KEY, NETHERITE_MULTI_TOOL);
		
		// Add to Creative Tab - Tools and Utilities
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
			entries.add(WOODEN_MULTI_TOOL);
			entries.add(STONE_MULTI_TOOL);
			entries.add(IRON_MULTI_TOOL);
			entries.add(GOLDEN_MULTI_TOOL);
			entries.add(DIAMOND_MULTI_TOOL);
			entries.add(NETHERITE_MULTI_TOOL);
		});
		
		// Also add to Combat tab
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
			entries.add(WOODEN_MULTI_TOOL);
			entries.add(STONE_MULTI_TOOL);
			entries.add(IRON_MULTI_TOOL);
			entries.add(GOLDEN_MULTI_TOOL);
			entries.add(DIAMOND_MULTI_TOOL);
			entries.add(NETHERITE_MULTI_TOOL);
		});
		
		LOGGER.info("Multi-Tool Mod initialized with all tiers!");
	}
}