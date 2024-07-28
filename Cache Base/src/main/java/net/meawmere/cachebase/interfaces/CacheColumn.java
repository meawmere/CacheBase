package net.meawmere.cachebase.interfaces;

public interface CacheColumn {
    String getString(String index);

    Integer getInteger(String index);

    Boolean getBoolean(String index);

    Float getFloat(String index);

    Double getDouble(String index);

    CacheColumn getColumn(String index);

    CacheTable getTable(String index);

    CacheColumn setData(String index, String data);
}
