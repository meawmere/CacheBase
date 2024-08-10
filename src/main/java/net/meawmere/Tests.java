package net.meawmere;

import net.meawmere.cachebase.events.IErrorsListener;
import net.meawmere.cachebase.interfaces.CacheBase;
import net.meawmere.cachebase.builders.CacheBaseBuilder;
import net.meawmere.cachebase.interfaces.CacheBaseManager;

public class Tests implements IErrorsListener {
    public static void main(String[] args) throws Exception {
        CacheBase cacheBase = CacheBaseBuilder.create("db.cache")
                .setLogger(true)
                .addErrorsListeners(new Tests())
                .build();
        CacheBaseManager manager = cacheBase.getManager();
        manager.getTable("das1").getColumn("userid").putData(0, "12");
        manager.update();
    }

    @Override
    public void exception(Exception exception) {
        System.out.println(exception.getMessage());
    }
}
