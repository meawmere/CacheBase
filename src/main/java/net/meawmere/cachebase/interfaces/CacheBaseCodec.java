package net.meawmere.cachebase.interfaces;

public interface CacheBaseCodec {
    String encode(String text);

    String decode(String text);
}
