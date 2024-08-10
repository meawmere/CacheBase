package net.meawmere.cachebase.events;

import net.meawmere.cachebase.events.session.ReadyEvent;

public interface IEventsListener {
    void readyEvent(ReadyEvent event);
}
