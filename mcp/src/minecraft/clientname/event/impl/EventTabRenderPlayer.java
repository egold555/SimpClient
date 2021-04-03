package clientname.event.impl;

import clientname.event.Event;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.network.NetworkPlayerInfo;

public class EventTabRenderPlayer extends Event {

	public final NetworkPlayerInfo p;
	public final int p_175245_2_, p_175245_1_, p_175245_3_;
	public EventTabRenderPlayer(NetworkPlayerInfo networkPlayerInfoIn, int p_175245_2_, int p_175245_1_, int p_175245_3_) {
		this.p = networkPlayerInfoIn;
		this.p_175245_1_ = p_175245_1_;
		this.p_175245_2_ = p_175245_2_;
		this.p_175245_3_ = p_175245_3_;
	}
	
}
