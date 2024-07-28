package net.meawmere.cachebase.builders;

import net.meawmere.cachebase.interfaces.CacheBaseLogger;
import net.meawmere.cachebase.impls.CacheBaseLoggerImpl;

public class CacheBaseLoggerBuilder {
    protected String loggerName;
    protected boolean enableInfo;
    protected boolean enableWarns;
    protected boolean enableErrors;

    public CacheBaseLoggerBuilder(String loggerName, boolean enableInfo,
                               boolean enableWarns, boolean enableErrors) {
        this.enableErrors = enableErrors;
        this.enableWarns = enableWarns;
        this.loggerName = loggerName;
        this.enableInfo = enableInfo;
    }

    public static CacheBaseLoggerBuilder create(String name) {
        return new CacheBaseLoggerBuilder(name, true, true, true);
    }

    public CacheBaseLoggerBuilder disableWarns() {
        this.enableWarns = false;
        return this;
    }

    public CacheBaseLoggerBuilder disableErrors() {
        this.enableErrors = false;
        return this;
    }

    public CacheBaseLoggerBuilder disableInfo() {
        this.enableInfo = false;
        return this;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public CacheBaseLogger build() {
        return (new CacheBaseLoggerImpl(loggerName, enableInfo, enableWarns, enableErrors));
    }
}
