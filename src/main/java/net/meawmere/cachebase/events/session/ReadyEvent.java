package net.meawmere.cachebase.events.session;

import net.meawmere.cachebase.interfaces.CacheBase;

public class ReadyEvent {
    protected CacheBase cacheBase;

    public ReadyEvent(CacheBase cacheBase) {

        this.cacheBase = cacheBase;
    }

    public CacheBase getCacheBase() {
        return cacheBase;
    }
}
