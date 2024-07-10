package net.meawmere.CacheBase.Api.events;

import net.meawmere.CacheBase.Api.events.EventListener.EventListenerInterface;
import net.meawmere.CacheBase.Api.events.EventListener.READY;

public class ListenerAdapter implements EventListenerInterface {
    public void onReadyEvent(READY event) {}
}
