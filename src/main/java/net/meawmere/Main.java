package net.meawmere;

import net.meawmere.CacheBase.Api.CacheApi;
import net.meawmere.CacheBase.Api.CacheObject;
import net.meawmere.CacheBase.Api.events.EventListener.READY;
import net.meawmere.CacheBase.Api.events.ListenerAdapter;
import net.meawmere.CacheBase.connect.CacheBuilder.CacheBuilder;

import java.io.IOException;

public class Main extends ListenerAdapter {

    public static void main(String[] args) throws IOException {
        CacheApi cacheApi = CacheBuilder.create("cachebase").addListener(new Main()).build();
        CacheObject cacheObject = cacheApi.getTable("users");
        Object object = cacheObject.getColumn("id");
        System.out.println(object);
    }

    @Override
    public void onReadyEvent(READY event) {
        super.onReadyEvent(event);
        System.out.println(event.type);
    }
}