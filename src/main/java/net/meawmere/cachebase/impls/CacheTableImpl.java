package net.meawmere.cachebase.impls;

import net.meawmere.cachebase.interfaces.CacheBaseManager;
import net.meawmere.cachebase.interfaces.CacheColumn;
import net.meawmere.cachebase.interfaces.CacheTable;

import java.util.HashMap;
import java.util.Map;

public class CacheTableImpl implements CacheTable {
    protected String name;
    protected Map<String, CacheColumn> data = new HashMap<>();
    protected CacheBaseManager manager;

    public CacheTableImpl(String name, CacheBaseManager manager) {
        this.name = name;
        this.manager = manager;
    }

    @Override
    public Map<String, CacheColumn> getData() {
        return this.data;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public CacheColumn getColumn(String name) {
        return this.data.get(name);
    }

    @Override
    public CacheTable createColumn(String name) {
        this.data.put(name, new CacheColumnImpl(name, this.manager));
        return this;
    }

}
