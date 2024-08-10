package net.meawmere.cachebase.interfaces;

import java.util.Map;

public interface CacheBaseManager {
    CacheBaseManager createTable(String name);

    CacheTable getTable(String name);

    Map<String, CacheTable> getData();

    CacheBaseManager update();
}
