package net.meawmere.cachebase.interfaces;

import net.meawmere.cachebase.events.IErrorsListener;
import net.meawmere.cachebase.events.IEventsListener;
import net.meawmere.cachebase.managers.CacheBaseManagerImpl;

import java.util.List;

public interface CacheBase {
    CacheBase setErrorsListeners(List<IErrorsListener> errorsListeners);

    CacheBase setListeners(List<IEventsListener> listeners);

    CacheBase setLogger(boolean logger);

    List<IEventsListener> getListeners();

    List<IErrorsListener> getErrorsListeners();

    CacheBaseManager getManager();

    boolean isLogger();
}
