package net.meawmere.cachebase.impls;

import net.meawmere.cachebase.interfaces.CacheColumn;
import net.meawmere.cachebase.interfaces.CacheTable;

import java.util.Map;

public class CacheTableImpl implements CacheTable {
    protected Map<String, Map<String, String>> table;

    public CacheTableImpl(Map<String, Map<String, String>> table) {
        this.table = table;
    }

    @Override
    public CacheColumn getColumn(String name) {
        return new CacheColumnImpl(table.get(name));
    }
}
