package io.collective;

import java.time.Clock;
import java.time.Duration;

public class SimpleAgedCache {

    Object[] key;
    Object[] value;
    Duration[] retentionInMillis;
    int size;

    public SimpleAgedCache(Clock clock) {
        size = 0;
        key = new Object[10];
        value = new Object[10];
        retentionInMillis = new Duration[10];
    }

    public SimpleAgedCache() {
        size = 0;
        key = new Object[10];
        value = new Object[10];
        retentionInMillis = new Duration[10];
    }

    public void put(Object key, Object value, int retentionInMillis) {
        this.key[size] = key;
        this.value[size] = value;

        Duration retention = Duration.ofMillis(retentionInMillis);
        this.retentionInMillis[size] = retention;
        size++;
    }

    public boolean isEmpty() {
        return size>0? false:true;
    }

    public int size() {
        return size;
    }

    public Object get(Object key) {

        for (int i = 0; i < size; i++) {
             if(this.key[i] == key){
                 return this.value[i];
             }
        }
        return null;
    }

    //adding a new function to clear cache and calling it after setting offset
    public void clearCache(Duration offset){
        for(int i=0; i<size; i++){
            if((retentionInMillis[i].compareTo(offset)) < 0){

                key[i] = key[size-1];
                value[i] = value[size-1];
                retentionInMillis[i] = retentionInMillis[size-1];

                key[size-1] = null;
                value[size-1] = null;
                retentionInMillis[size-1] = null;

                size--;
            }
        }
    }

}