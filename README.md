# CacheBase
Cache Base is a local database designed to store compressed data. You can see the usage example below. IT'S NOT FINISHED
```java
public class Main{

    public static void main(String[] args) throws IOException {
        CacheApi cacheApi = CacheBuilder.create("cachebase").build();
        CacheObject cacheObject = cacheApi.getTable("users");
        Object object = cacheObject.getColumn("id");
        System.out.println(object);
    }
}
```
