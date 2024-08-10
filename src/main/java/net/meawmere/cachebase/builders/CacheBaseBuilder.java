package net.meawmere.cachebase.builders;

import net.meawmere.cachebase.interfaces.CacheBase;
import net.meawmere.cachebase.impls.CacheBaseImpl;
import net.meawmere.cachebase.events.IErrorsListener;
import net.meawmere.cachebase.events.IEventsListener;
import net.meawmere.cachebase.exceptions.BuilderParametersException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CacheBaseBuilder {
    protected List<IEventsListener> listeners;
    protected List<IErrorsListener> errorsListeners;
    protected boolean logger = false;
    protected String path;

    protected CacheBaseBuilder(List<IEventsListener> listeners,
                            List<IErrorsListener> errorsListeners,
                            boolean logger, String path) {
        this.listeners = listeners;
        this.logger = logger;
        this.errorsListeners = errorsListeners;
        this.path = path;
    }

    public static CacheBaseBuilder create(String path) {
        return (new CacheBaseBuilder(new ArrayList<>(),
                new ArrayList<>(), false, path));
    }

    public CacheBaseBuilder addEventsListeners(IEventsListener... listeners) {
        for (IEventsListener listener : listeners) {
            this.listeners.add(listener);
        }

        return this;
    }

    public CacheBaseBuilder addErrorsListeners(IErrorsListener... listeners) {
        for (IErrorsListener listener : listeners) {
            errorsListeners.add(listener);
        }

        return this;
    }

    public CacheBase build() throws IOException, InterruptedException, BuilderParametersException {
        return (new CacheBaseImpl(this));
    }

    public List<IErrorsListener> getErrorsListeners() {
        return errorsListeners;
    }

    public String getPath() {
        return path;
    }

    public List<IEventsListener> getListeners() {
        return listeners;
    }

    public boolean isLogger() {
        return logger;
    }

    public CacheBaseBuilder setLogger(boolean logger) {
        this.logger = logger;
        return this;
    }

    public CacheBaseBuilder setErrorsListeners(List<IErrorsListener> errorsListeners) {
        this.errorsListeners = errorsListeners;
        return this;
    }

    public CacheBaseBuilder setListeners(List<IEventsListener> listeners) {
        this.listeners = listeners;
        return this;
    }
}
