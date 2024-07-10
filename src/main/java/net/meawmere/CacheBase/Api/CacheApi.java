package net.meawmere.CacheBase.Api;

import net.meawmere.CacheBase.Api.events.EventListener.EventListenerInterface;
import org.jetbrains.annotations.NotNull;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CacheApi {
    protected String path;
    protected File file;
    protected boolean init;
    protected List<EventListenerInterface> listeners;
    protected List<String> requests = new ArrayList<String>();

    public CacheApi(String path,  List<EventListenerInterface> listeners, File file) {
        this.path = path;
        this.file = file;
        this.init = true;
        this.listeners = listeners;
    }

    public CacheApi await() {
        new Thread(() -> {
            while (true) {}
        }).start();
        return (new CacheApi(this.path, this.listeners, this.file));
    }

    @NotNull
    public List<Object> parse(@NotNull String read) throws UnsupportedEncodingException {
        List<String> files = new ArrayList<>(List.of(read
                .replaceAll(" ", "")
                .replaceAll("\n", "")
                .replaceAll("\r", "")
                .split("<table>")));
        List<Object> base = new ArrayList<Object>();

        files.remove("");

        for (String table : files) {
            List<String> block;
            List<Object> tables = new ArrayList<Object>();
            List<String> theFiles = new ArrayList<>(List.of(table.split("<name>")));

            theFiles.remove("");

            for (String columns : theFiles) {
                List<String> column = new ArrayList<>(List.of(columns.split("<column>")));
                List<Object> columns2 = new ArrayList<Object>();

                tables.add(column.getFirst());
                column.removeFirst();

                for (String blocks : column) {
                    List<Object> param = new ArrayList<Object>();
                    block = new ArrayList<>(List.of(blocks.split("<block>")));

                    param.add(block.getFirst());
                    block.removeFirst();
                    param.add(block);
                    columns2.add(param);
                }
                tables.add(columns2);
            }
            base.add(tables);
        }
        return base;
    }

    @NotNull
    public String read() {
        String file = new String();
        try (FileReader fileReader = new FileReader(this.path);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                file += (line + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return file;
    }

    public CacheObject getTable(String name) throws UnsupportedEncodingException {
        List<Object> tables = parse(read());
        List<Object> returned = null;

        for (Object table : tables) {
            for (Object object : (List<Object>) table) {
                if(String.valueOf(object).equals(name)) {
                    returned = (List<Object>) table;
                    break;
                }
            }
        }
        return new CacheObject(returned.get(1));
    }
}
