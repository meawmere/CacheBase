package net.meawmere.CacheBase.Api;

import java.util.Arrays;
import java.util.List;

public class CacheObject {
    public Object object;

    public CacheObject(Object table) {
        this.object = table;
    }

    public Object getColumn(String name) {
        Object returned = null;

        for (Object column : (List<Object>) this.object) {
            List<Object> thisColumn = (List<Object>) column;
            List<String> params = Arrays.stream(String.valueOf(thisColumn.getFirst()).split(";")).toList();

            if (params.getFirst().equals(name)) {
                returned = ((List<Object>) column).get(1);
                System.out.println(returned);
                for (String string : (List<String>) returned){
                    System.out.println(string);
                }
            };
        }
        return returned;
    }
}
