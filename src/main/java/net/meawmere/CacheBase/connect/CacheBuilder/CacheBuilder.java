package net.meawmere.CacheBase.connect.CacheBuilder;

import net.meawmere.CacheBase.Api.CacheApi;
import net.meawmere.CacheBase.Api.events.EventListener.EventListenerInterface;
import net.meawmere.CacheBase.Api.events.EventListener.READY;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CacheBuilder {
    protected String path;
    protected boolean logger;
    protected List<EventListenerInterface> listeners;

    public CacheBuilder(String path, boolean logger, List<EventListenerInterface> listeners) {
        this.path = path;
        this.logger = logger;
        this.listeners = listeners;
    }

    @NotNull
    @Contract(value = "_, _ -> new", pure = true)
    public static CacheBuilder create(String path) {
        return (new CacheBuilder(path,false, new ArrayList<EventListenerInterface>()));
    }

    public CacheBuilder logger(boolean logger) {
        return (new CacheBuilder(this.path, logger, this.listeners));
    }

    public CacheApi build() throws IOException {
        File file = new File(this.path + ".cache");

        if (file.createNewFile() & this.logger) {
            System.out.println("[CacheApi] The file has been created");
        }
        for (EventListenerInterface event : listeners)
            event.onReadyEvent(new READY());

        return (new CacheApi(this.path + ".cache", this.listeners, file));
    }

    public CacheBuilder addListener(EventListenerInterface listener) {
        this.listeners.add(listener);
        return (new CacheBuilder(this.path, logger, this.listeners));
    }

}
