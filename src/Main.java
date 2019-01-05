import cache.Cache;
import cache.LRUCache;
import cache.LRUCacheImplementation;

public class Main {

    public static void main(String[] args) {
        Cache<String, String> cache = new LRUCacheImplementation<>(5);
        cache.put("sumit", "first");
        cache.put("pankaj", "first");
        cache.put("mohit", "first");
        cache.put("payal", "first");
        cache.put("harsh", "first");

        cache.printCache();
        cache.put("vineet", "first");
        cache.printCache();
        String value = cache.get("sumit");
        System.out.println("Sumit - " + value);
        System.out.println("Pankaj - " + cache.get("pankaj"));
        cache.printCache();
    }

}
