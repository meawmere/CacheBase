package net.meawmere.cachebase.interfaces;

import java.util.Map;

public interface CacheColumn {
    CacheColumn putData(Integer index, String data);

    Map<Integer, String> getData();

    String getName();

    String getString(Integer index);

    Integer getInteger(Integer index);

    Boolean getBoolean(Integer index);

    Float getFloat(Integer index);

    Double getDouble(Integer index);

}
