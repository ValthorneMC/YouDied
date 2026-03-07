package se.gory_moon.you_died;

import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public class YouDied
{
	public static final String MOD_ID = "you_died";

	public static SoundEvent DEATH_SOUND = SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath(YouDied.MOD_ID, "death"));
}
