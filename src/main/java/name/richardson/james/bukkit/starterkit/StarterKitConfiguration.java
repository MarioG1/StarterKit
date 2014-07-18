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

import name.richardson.james.bukkit.utilities.persistence.configuration.SimplePluginConfiguration;

public class StarterKitConfiguration extends SimplePluginConfiguration {

	public StarterKitConfiguration(final File file, final InputStream defaults) throws IOException {
		super(file, defaults);
	}

	public boolean isProvidingKitOnDeath() {
		return this.getConfiguration().getBoolean("provide-kit-on-death");
	}
        
	public boolean isProvidingKitOnWorldChange() {
		return this.getConfiguration().getBoolean("provide-kit-on-world-change");
	}
        
	public boolean isProvidingKitOnEveryJoin() {
		return this.getConfiguration().getBoolean("provide-kit-on-every-join");
	}

}
