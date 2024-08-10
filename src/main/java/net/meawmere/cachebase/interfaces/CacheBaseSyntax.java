package net.meawmere.cachebase.interfaces;

import java.util.List;
import java.util.Map;

public interface CacheBaseSyntax {
    Map<String, CacheTable> parse(String path);

    String stringify(Map<String, CacheTable> data);
}
