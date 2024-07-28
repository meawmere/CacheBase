package net.meawmere.cachebase.impls;

import net.meawmere.cachebase.builders.CacheBaseBuilder;
import net.meawmere.cachebase.builders.CacheBaseLoggerBuilder;
import net.meawmere.cachebase.events.IErrorsListener;
import net.meawmere.cachebase.events.IEventsListener;
import net.meawmere.cachebase.events.session.ReadyEvent;
import net.meawmere.cachebase.exceptions.BuilderParametersException;
import net.meawmere.cachebase.interfaces.CacheBase;
import net.meawmere.cachebase.interfaces.CacheBaseLogger;
import net.meawmere.cachebase.managers.CacheBaseManagerImpl;

import java.io.IOException;
import java.util.List;


public class CacheBaseImpl implements CacheBase {
    protected List<IEventsListener> listeners;
    protected List<IErrorsListener> errorsListeners;
    protected boolean isLogger;
    protected String path;

    protected CacheBaseLogger logger;

    public CacheBaseImpl(CacheBaseBuilder cacheBaseBuilder) throws IOException, InterruptedException, BuilderParametersException {
        this.errorsListeners = cacheBaseBuilder.getErrorsListeners();
        this.listeners = cacheBaseBuilder.getListeners();
        this.isLogger = cacheBaseBuilder.isLogger();
        this.path = cacheBaseBuilder.getPath();

        this.logger = CacheBaseLoggerBuilder.create("Cache Base").build();

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
    public CacheBaseManagerImpl getManager() {
        return new CacheBaseManagerImpl(isLogger, listeners, errorsListeners, path, logger);
    }

    @Override
    public boolean isLogger() {
        return isLogger;
    }
}
