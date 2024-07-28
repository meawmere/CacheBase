package net.meawmere.cachebase.managers;

import net.meawmere.cachebase.events.IErrorsListener;
import net.meawmere.cachebase.events.IEventsListener;
import net.meawmere.cachebase.exceptions.BaseNotFoundException;
import net.meawmere.cachebase.exceptions.BuilderParametersException;
import net.meawmere.cachebase.impls.CacheBaseCodecImpl;
import net.meawmere.cachebase.impls.CacheBaseSyntaxImpl;
import net.meawmere.cachebase.impls.CacheTableImpl;
import net.meawmere.cachebase.interfaces.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CacheBaseManagerImpl implements CacheBaseManager {
    protected boolean isLogger;
    protected List<IEventsListener> listeners;
    protected List<IErrorsListener> errorsListeners;
    protected String path;

    protected CacheBaseLogger logger;
    protected CacheBaseCodec codec;
    protected CacheBaseSyntax syntaxParser;

    public CacheBaseManagerImpl(boolean isLogger, List<IEventsListener> listeners, List<IErrorsListener> errorsListeners, String path, CacheBaseLogger logger) {
        this.isLogger = isLogger;
        this.listeners = listeners;
        this.errorsListeners = errorsListeners;
        this.path = path;

        this.logger = logger;
        this.codec = new CacheBaseCodecImpl();
        this.syntaxParser = new CacheBaseSyntaxImpl();
    }

    public CacheTable getTable(String name) throws BaseNotFoundException, BuilderParametersException, IOException, InterruptedException {
        try (FileReader fileReader = new FileReader(path)) {
            StringBuilder stringBuilder = new StringBuilder();

            int c;
            while((c=fileReader.read())!=-1){
                stringBuilder.append((char) c);
            }

            String fileContent = this.codec.decode(stringBuilder.toString());
            Map<String, Map<String, Map<String, String>>> tables = this.syntaxParser.parseTables(fileContent);

            return new CacheTableImpl(tables.get(name));
        } catch (IOException e) {

            if (isLogger) logger.error("Base not found!");

            if (!errorsListeners.isEmpty()) {
                for (IErrorsListener listener : errorsListeners) {
                    listener.exception(new BaseNotFoundException("Base not found!"));
                }
                return null;
            }
            throw new BaseNotFoundException("Base not found!");
        }
    }
}
