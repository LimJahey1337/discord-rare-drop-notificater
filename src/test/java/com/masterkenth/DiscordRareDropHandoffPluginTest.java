package com.masterkenth;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class DiscordRareDropHandoffPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(DiscordRareDropHandoffPlugin.class);
		RuneLite.main(args);
	}
}