package clientname.event.impl;

import clientname.event.Event;
import net.minecraft.client.entity.AbstractClientPlayer;

public class EventNametagRender extends Event {

	public final AbstractClientPlayer p;
	public EventNametagRender(AbstractClientPlayer p) {
		this.p = p;
	}
	
}
