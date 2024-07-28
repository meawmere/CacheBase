package net.meawmere;

import net.meawmere.cachebase.events.IErrorsListener;
import net.meawmere.cachebase.interfaces.CacheBase;
import net.meawmere.cachebase.builders.CacheBaseBuilder;
import net.meawmere.cachebase.interfaces.CacheTable;

public class Tests implements IErrorsListener {
    public static void main(String[] args) throws Exception {
        CacheBase cacheBase = CacheBaseBuilder.create("db.cache")
                .setLogger(true)
                .addErrorsListeners(new Tests())
                .build();
        CacheTable cacheObject = cacheBase.getManager().getTable("das1");
        System.out.println(cacheObject.getColumn("userid").getString("1"));
    }

    @Override
    public void exception(Exception exception) {
        System.out.println(exception.getMessage());
    }
}
