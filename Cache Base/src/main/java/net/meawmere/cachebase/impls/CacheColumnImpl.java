package net.meawmere.cachebase.impls;

import net.meawmere.cachebase.interfaces.CacheBaseSyntax;
import net.meawmere.cachebase.interfaces.CacheColumn;
import net.meawmere.cachebase.interfaces.CacheTable;

import java.util.Map;

public class CacheColumnImpl implements CacheColumn {
    protected Map<String, String> column;

    public  CacheColumnImpl(Map<String, String> column) {
        this.column = column;
    }

    @Override
    public String getString(String index) {
        return column.get(index);
    }

    @Override
    public Integer getInteger(String index) {
        return Integer.parseInt(column.get(index));
    }

    @Override
    public Boolean getBoolean(String index) {
        return Boolean.parseBoolean(column.get(index));
    }

    @Override
    public Float getFloat(String index) {
        return Float.parseFloat(column.get(index));
    }

    @Override
    public Double getDouble(String index) {
        return Double.parseDouble(column.get(index));
    }

    @Override
    public CacheColumn getColumn(String index) {
        return null;
    }

    @Override
    public CacheTable getTable(String index) {
        return null;
    }

    @Override
    public CacheColumn setData(String index, String data) {

    }
}
