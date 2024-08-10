package net.meawmere.cachebase.interfaces;

import net.meawmere.cachebase.exceptions.BuilderParametersException;

import java.io.IOException;

public interface CacheBaseLogger {
    String RESET = "\u001B[0m";
    String INFO = "\u001B[37m";
    String ERROR = "\033[0;1m\u001B[31m";
    String WARN = "\033[0;1m\u001B[33m";

    String getLoggerName();

    void info(String message) throws IOException, InterruptedException, BuilderParametersException;

    void error(String message) throws IOException, InterruptedException, BuilderParametersException;

    void warn(String message) throws IOException, InterruptedException, BuilderParametersException;
}
