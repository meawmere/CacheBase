package net.meawmere.cachebase.impls;

import net.meawmere.cachebase.builders.CacheBaseBuilder;
import net.meawmere.cachebase.builders.CacheBaseLoggerBuilder;
import net.meawmere.cachebase.events.IErrorsListener;
import net.meawmere.cachebase.events.IEventsListener;
import net.meawmere.cachebase.events.session.ReadyEvent;
import net.meawmere.cachebase.exceptions.BuilderParametersException;
import net.meawmere.cachebase.interfaces.*;
import net.meawmere.cachebase.managers.CacheBaseManagerImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CacheBaseImpl implements CacheBase {
    protected List<IEventsListener> listeners;
    protected List<IErrorsListener> errorsListeners;
    protected boolean isLogger;
    protected String path;

    protected CacheBaseLogger logger = CacheBaseLoggerBuilder.create("Cache Base").build();
    protected CacheBaseSyntax parser = new CacheBaseSyntaxImpl();
    protected CacheBaseManager manager;

    public CacheBaseImpl(CacheBaseBuilder cacheBaseBuilder) throws IOException, InterruptedException, BuilderParametersException {
        this.errorsListeners = cacheBaseBuilder.getErrorsListeners();
        this.listeners = cacheBaseBuilder.getListeners();
        this.isLogger = cacheBaseBuilder.isLogger();
        this.path = cacheBaseBuilder.getPath();
        this.manager = new CacheBaseManagerImpl(isLogger, listeners, errorsListeners, path, logger);

        if (this.isLogger) this.logger.info("Cache Base is ready to use!");

        for (IEventsListener listener : listeners) {
            listener.readyEvent(new ReadyEvent(this));
        }
    }

    @Override
    public CacheBase setErrorsListeners(List<IErrorsListener> errorsListeners) {
        this.errorsListeners = errorsListeners;
        return this;
    }

    @Override
    public CacheBase setListeners(List<IEventsListener> listeners) {
        this.listeners = listeners;
        return this;
    }

    @Override
    public CacheBase setLogger(boolean logger) {
        this.isLogger = logger;
        return this;
    }

    @Override
    public List<IEventsListener> getListeners() {
        return listeners;
    }

    @Override
    public List<IErrorsListener> getErrorsListeners() {
        return errorsListeners;
    }

    @Override
    public CacheBaseManager getManager() {
        return this.manager;
    }

    @Override
    public boolean isLogger() {
        return isLogger;
    }
}
