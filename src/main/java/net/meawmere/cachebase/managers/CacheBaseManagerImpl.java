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
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheBaseManagerImpl implements CacheBaseManager {
    protected boolean isLogger;
    protected List<IEventsListener> listeners;
    protected List<IErrorsListener> errorsListeners;
    protected String path;

    protected Map<String, CacheTable> data;

    protected CacheBaseLogger logger;
    protected CacheBaseCodec codec;
    protected CacheBaseSyntax syntaxParser = new CacheBaseSyntaxImpl();

    public CacheBaseManagerImpl(boolean isLogger, List<IEventsListener> listeners, List<IErrorsListener> errorsListeners, String path, CacheBaseLogger logger) {
        this.isLogger = isLogger;
        this.listeners = listeners;
        this.errorsListeners = errorsListeners;
        this.path = path;
        this.logger = logger;
        this.codec = new CacheBaseCodecImpl();

        try (FileReader fileReader = new FileReader(path)) {
            StringBuilder stringBuilder = new StringBuilder();

            int c;
            while((c=fileReader.read())!=-1){
                stringBuilder.append((char) c);
            }

            String baseContent = stringBuilder.toString();

            data = syntaxParser.parse(baseContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CacheBaseManagerImpl() {
        data = new HashMap<>();
    }

    @Override
    public CacheBaseManager createTable(String name) {
        this.data.put(name, new CacheTableImpl(name, this));
        return this;
    }

    @Override
    public CacheTable getTable(String name) {
        return this.data.get(name);
    }

    @Override
    public Map<String, CacheTable> getData() {
        return this.data;
    }

    @Override
    public CacheBaseManager update() {
        String stringify = this.syntaxParser.stringify(this.data);
        try(FileWriter writer = new FileWriter(this.path, false))
        {
            writer.write(stringify);
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        return this;
    }
}
