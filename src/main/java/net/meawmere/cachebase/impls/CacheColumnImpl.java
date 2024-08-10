package net.meawmere.cachebase.impls;

import net.meawmere.cachebase.interfaces.CacheBaseManager;
import net.meawmere.cachebase.interfaces.CacheBaseSyntax;
import net.meawmere.cachebase.interfaces.CacheColumn;
import net.meawmere.cachebase.interfaces.CacheTable;

import java.util.HashMap;
import java.util.Map;

public class CacheColumnImpl implements CacheColumn {
    protected String name;
    protected Map<Integer, String> data = new HashMap<>();
    protected CacheBaseManager manager;

    public CacheColumnImpl(String name, CacheBaseManager manager) {
        this.name = name;
        this.manager = manager;
    }

    @Override
    public CacheColumn putData(Integer index, String data) {
        this.data.put(index, data);

        return this;
    }

    @Override
    public Map<Integer, String> getData() {
        return this.data;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getString(Integer index) {
        return this.data.get(index);
    }

    @Override
    public Integer getInteger(Integer index) {
        return Integer.parseInt(this.data.get(index));
    }

    @Override
    public Boolean getBoolean(Integer index) {
        return Boolean.parseBoolean(this.data.get(index));
    }

    @Override
    public Float getFloat(Integer index) {
        return Float.parseFloat(this.data.get(index));
    }

    @Override
    public Double getDouble(Integer index) {
        return Double.parseDouble(this.data.get(index));
    }
}
