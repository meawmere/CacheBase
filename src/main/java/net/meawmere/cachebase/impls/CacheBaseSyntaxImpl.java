package net.meawmere.cachebase.impls;

import net.meawmere.cachebase.interfaces.CacheBaseManager;
import net.meawmere.cachebase.interfaces.CacheBaseSyntax;
import net.meawmere.cachebase.interfaces.CacheColumn;
import net.meawmere.cachebase.interfaces.CacheTable;
import net.meawmere.cachebase.managers.CacheBaseManagerImpl;

import java.util.*;

import static net.meawmere.cachebase.codec.Base64.encode;

public class CacheBaseSyntaxImpl implements CacheBaseSyntax {
    @Override
    public Map<String, CacheTable> parse(String fileContent) {
        CacheBaseManager manager = new CacheBaseManagerImpl();

        List<String> base = new ArrayList<>(List.of(fileContent
                .replaceAll(" ", "")
                .replaceAll("[\n\r]", "").split("<table>")));

        base.remove("");

        for (String table : base) {
            List<String> columns = new ArrayList<>(List.of(table.split("<column>")));
            String tableName = columns.getFirst();

            columns.remove(tableName);
            manager.createTable(tableName);

            CacheTable thisTable = manager.getTable(tableName);

            for (String column : columns) {
                List<String> indexes = new ArrayList<>(List.of(column.split("<index>")));
                String columnName = indexes.getFirst();

                indexes.remove(columnName);
                thisTable.createColumn(columnName);

                CacheColumn thisColumn = thisTable.getColumn(columnName);

                for (String index : indexes) {
                    thisColumn.putData(Integer.parseInt(index.split("<data>")[0]),
                            index.split("<data>")[1]);
                }
            }
        }

        return manager.getData();
    }

    @Override
    public String stringify(Map<String, CacheTable> data) {
        StringBuilder output = new StringBuilder();

        for (Map.Entry<String, CacheTable> entry : data.entrySet()) {
            output.append("<table>" + entry.getValue().getName());
            for (Map.Entry<String, CacheColumn> entryColumn : entry.getValue().getData().entrySet()) {
                output.append("<column>" + entryColumn.getValue().getName());
                for (Map.Entry<Integer, String> entryData : entryColumn.getValue().getData().entrySet()) {
                    output.append("<index>" + entryData.getKey() + "<data>" + entryData.getValue());
                }
            }
        }

        return output.toString();
    }
}
