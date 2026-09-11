package com.masterkenth;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class HandoffValueTest
{
	private static final int TWO_MIL = 2_000_000;

	@Test
	public void zeroDisablesTheCeiling()
	{
		assertFalse(DiscordRareDropNotificaterPlugin.isAtOrAboveMaxValue(0, 50_000_000, 30_000_000, 1));
		assertFalse(DiscordRareDropNotificaterPlugin.isAtOrAboveMaxValue(-1, 50_000_000, 30_000_000, 1));
	}

	@Test
	public void belowTheCeilingIsKept()
	{
		// 1,999,999 by GE, cheap by HA: stays with this plugin
		assertFalse(DiscordRareDropNotificaterPlugin.isAtOrAboveMaxValue(TWO_MIL, 1_999_999, 1_000, 1));
	}

	@Test
	public void exactlyTheCeilingIsHandedOff()
	{
		// "worth this much or more" - 2,000,000 is Dink's, not ours
		assertTrue(DiscordRareDropNotificaterPlugin.isAtOrAboveMaxValue(TWO_MIL, TWO_MIL, 0, 1));
	}

	@Test
	public void haPriceCountsToo()
	{
		assertTrue(DiscordRareDropNotificaterPlugin.isAtOrAboveMaxValue(TWO_MIL, 0, TWO_MIL, 1));
	}

	@Test
	public void stackValueIsUsed()
	{
		// 10 x 250k = 2.5M as a stack, even though one is under the ceiling
		assertTrue(DiscordRareDropNotificaterPlugin.isAtOrAboveMaxValue(TWO_MIL, 250_000, 0, 10));
		assertFalse(DiscordRareDropNotificaterPlugin.isAtOrAboveMaxValue(TWO_MIL, 250_000, 0, 7));
	}

	@Test
	public void largeStacksDoNotOverflow()
	{
		// int overflow would wrap negative and wrongly keep a huge stack
		assertTrue(DiscordRareDropNotificaterPlugin.isAtOrAboveMaxValue(TWO_MIL, 1_000_000, 0, 5_000));
	}
}
