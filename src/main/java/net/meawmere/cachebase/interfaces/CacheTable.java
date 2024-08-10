package net.meawmere.cachebase.interfaces;

import java.util.Map;

public interface CacheTable {
    Map<String, CacheColumn> getData();

    String getName();

    CacheColumn getColumn(String name);

    CacheTable createColumn(String name);
}
