package net.meawmere.cachebase.impls;

import net.meawmere.cachebase.interfaces.CacheBaseCodec;

public class CacheBaseCodecImpl implements CacheBaseCodec {

    public CacheBaseCodecImpl() {}

    @Override
    public String encode(String text) {
        return text;
    }

    @Override
    public String decode(String text) {
        return text.replaceAll("[\n\r]", "").replaceAll(" ", "");
    }
}
