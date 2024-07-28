package net.meawmere.cachebase.impls;

import net.meawmere.cachebase.exceptions.BuilderParametersException;
import net.meawmere.cachebase.interfaces.CacheBaseLogger;

import java.io.IOException;

public class CacheBaseLoggerImpl implements CacheBaseLogger {
    protected String loggerName;
    protected boolean enableInfo = true;
    protected boolean enableWarns = true;
    protected boolean enableErrors = true;

    public CacheBaseLoggerImpl(String loggerName, boolean enableInfo,
                               boolean enableWarns, boolean enableErrors) {
        this.enableErrors = enableErrors;
        this.enableWarns = enableWarns;
        this.loggerName = loggerName;
        this.enableInfo = enableInfo;
    }

    protected static void output(String s) throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "echo " + s).inheritIO().start().waitFor();
    }

    @Override
    public String getLoggerName() {
        return loggerName;
    }

    @Override
    public void info(String message) throws IOException, InterruptedException, BuilderParametersException {
        if (!enableInfo) throw new BuilderParametersException("Info is disable!");
        output(CacheBaseLogger.INFO + "[" + loggerName + "/INFO]: " + CacheBaseLogger.RESET + message);
    }

    @Override
    public void error(String message) throws IOException, InterruptedException, BuilderParametersException {
        if (!enableErrors) throw new BuilderParametersException("Errors is disable!");
        output(CacheBaseLogger.ERROR + "[" + loggerName + "/ERROR]: " + message + CacheBaseLogger.RESET);
    }

    @Override
    public void warn(String message) throws IOException, InterruptedException, BuilderParametersException {
        if (!enableWarns) throw new BuilderParametersException("Warns is disable!");
        output(CacheBaseLogger.WARN + "[" + loggerName + "/WARN]: " + message + CacheBaseLogger.RESET);
    }
}
