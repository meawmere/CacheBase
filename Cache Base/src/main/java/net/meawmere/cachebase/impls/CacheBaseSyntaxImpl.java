package net.meawmere.cachebase.impls;

import net.meawmere.cachebase.interfaces.CacheBaseSyntax;

import java.util.*;

public class CacheBaseSyntaxImpl implements CacheBaseSyntax {
    @Override
    public Map<String, Map<String, Map<String, String>>> parseTables(String fileContent) {
        Map<String, Map<String, Map<String, String>>> keys = new HashMap<>();
        List<String> tables = new ArrayList<>(List.of(fileContent.split("<table>")));

        tables.remove("");

        for (String table : tables) {
            String name = table.split("<column>")[0];
            keys.put(name, parseColumns(new ArrayList<>(List.of(table.split("<column>")))));
        }

        return keys;
    }

    @Override
    public Map<String, Map<String, String>> parseColumns(List<String> columns) {
        HashMap<String, Map<String, String>> columnsData = new HashMap<>();
        columns.removeFirst();

        for (String columnData : columns) {
            List<String> splitColumnData = new ArrayList<>(List.of(columnData.split("<index>")));
            HashMap<String, String> column = new HashMap<>();
            String columnDataName = splitColumnData.getFirst();

            splitColumnData.removeFirst();

            for (String data : splitColumnData) {
                column.put(data.split("<data>")[0], data.split("<data>")[1]);
            }

            columnsData.put(columnDataName, column);
        }
        return columnsData;
    }

    @Override
    public String stringify(Map<String, Map<String, Map<String, String>>> data) {
        StringBuilder output = new StringBuilder();

        for (Map.Entry<String, Map<String, Map<String, String>>> entry : data.entrySet()) {
            output.append("<table>" + entry.getKey());
            for (Map.Entry<String, Map<String, String>> entryColumn : entry.getValue().entrySet()) {
                output.append("<column>" + entryColumn.getKey());
                for (Map.Entry<String, String> entryData : entryColumn.getValue().entrySet()) {
                    output.append("<index>" + entryData.getKey() + "<data>" + entryData.getValue());
                }
            }
        }

        return output.toString();
    }
}
