# CacheBase
Cache Base is a local database designed to store compressed data. You can see the usage example below.
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

# How to install?

Go to the releases

![image](https://github.com/user-attachments/assets/e629a679-03cd-48d7-8818-84fa530c27b7)


Open the latest version and download CacheBase-${version}.jar

![image](https://github.com/user-attachments/assets/dee69c10-f611-44d6-b472-5b13b5ef1843)


Unzip to the packages folder

![image](https://github.com/user-attachments/assets/b948b24c-1623-4d7d-8527-8ccd9a4d5cee)


Ready! Use my resources and expect new releases!


