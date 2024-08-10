# CacheBase
Cache Base is a local database designed to store compressed data. You can see the usage example below. IT'S NOT FINISHED
```java
public class Main implements IErrorsListener {
    public static void main(String[] args) throws Exception {
        CacheBase cacheBase = CacheBaseBuilder.create("db.cache")
                .setLogger(true)
                .addErrorsListeners(new Main())
                .build();
        CacheBaseManager manager = cacheBase.getManager();
        manager.getTable("das1").getColumn("userid").putData(0, "12");

        ...

        manager.update(); // save changes
    }

    @Override
    public void exception(Exception exception) {
        System.out.println(exception.getMessage());
    }
}
```
