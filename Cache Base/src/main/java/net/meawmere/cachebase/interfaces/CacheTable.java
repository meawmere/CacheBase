package net.meawmere.cachebase.interfaces;

import java.util.Map;

public interface CacheTable {
    CacheColumn getColumn(String name);
}
