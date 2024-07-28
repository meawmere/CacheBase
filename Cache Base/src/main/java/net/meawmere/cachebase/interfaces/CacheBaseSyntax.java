package net.meawmere.cachebase.interfaces;

import java.util.List;
import java.util.Map;

public interface CacheBaseSyntax {
    Map<String, Map<String, Map<String, String>>> parseTables(String fileContent);

    Map<String, Map<String, String>> parseColumns(List<String> columns);

    String stringify(Map<String, Map<String, Map<String, String>>> data);
}
