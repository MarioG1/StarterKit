/*******************************************************************************
 * Copyright (c) 2012 James Richardson.
 * 
 * StarterKitConfiguration.java is part of StarterKit.
 * 
 * StarterKit is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * StarterKit is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * StarterKit. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package name.richardson.james.bukkit.starterkit;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.Bukkit;
import org.bukkit.World;

import name.richardson.james.bukkit.starterkit.kit.ArmourKit;
import name.richardson.james.bukkit.starterkit.kit.InventoryKit;
import name.richardson.james.bukkit.utilities.persistence.configuration.SimplePluginConfiguration;

public class StarterKitSave extends SimplePluginConfiguration {

	private final HashMap inventory = new HashMap();
	private final HashMap armour = new HashMap();

	public StarterKitSave(final File file, final InputStream defaults) throws IOException {
		super(file, defaults);
                for(World w : Bukkit.getServer().getWorlds()){
                    this.setDefaultKit(w);
                    final ConfigurationSection section = this.getConfiguration().getConfigurationSection("kit."+w.getName());
                    this.armour.put(w, (ArmourKit) section.get("armour"));
                    this.inventory.put(w, (InventoryKit) section.get("backpack"));
                }
	}

	public ArmourKit getArmourKit(World world) {
		return (ArmourKit) this.armour.get(world);
	}

	public InventoryKit getInventoryKit(World world) {
		return (InventoryKit) this.inventory.get(world);
	}

	public int getItemCount(World world) {
                ArmourKit arm = (ArmourKit) this.armour.get(world);
                InventoryKit inv = (InventoryKit) this.inventory.get(world);
		return arm.getItemCount() + inv.getItemCount();
	}

	public void setInventory(final PlayerInventory inventory, final World world) throws IOException {
		final ConfigurationSection section = this.getConfiguration().getConfigurationSection("kit");
		this.inventory.put(world, new InventoryKit(inventory));
		section.set(world.getName() + ".backpack", this.inventory.get(world));
		this.armour.put(world, new ArmourKit(inventory));
		section.set(world.getName() + ".armour", this.armour.get(world));
		this.save();
	}

	private void setDefaultKit(World world)
	throws IOException {
		if (!this.getConfiguration().isConfigurationSection("kit."+world.getName())) {
			final ConfigurationSection section = this.getConfiguration().createSection("kit." + world.getName());
			section.set("backpack", new InventoryKit());
			section.set("armour", new ArmourKit());
		}
		this.save();
	}

}
