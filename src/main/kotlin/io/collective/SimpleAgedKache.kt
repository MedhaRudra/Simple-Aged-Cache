package io.collective

import java.time.Clock
import java.time.Duration

class SimpleAgedKache {
    var key: Array<Any?>
    var value: Array<Any?>
    var retentionInMillis: Array<Duration?>
    var size: Int

    constructor(clock: Clock?) {
        size = 0
        key = arrayOfNulls(10)
        value = arrayOfNulls(10)
        retentionInMillis = arrayOfNulls(10)
    }

    constructor() {
        size = 0
        key = arrayOfNulls(10)
        value = arrayOfNulls(10)
        retentionInMillis = arrayOfNulls(10)
    }

    fun put(key: Any?, value: Any?, retentionInMillis: Int) {
        this.key[size] = key
        this.value[size] = value
        val retention = Duration.ofMillis(retentionInMillis.toLong())
        this.retentionInMillis[size] = retention
        size++
    }

    fun isEmpty(): Boolean {
        return size <= 0
    }

    fun size(): Int {
        return size
    }

    fun get(key: Any?): Any? {
        for (i in 0 until size) {
            if (this.key[i] == key) {
                return value[i]
            }
        }
        return null
    }

    //adding a new function to clear cache and calling it after setting offset
    fun clearCache(offset: Duration?) {
        var i = 0
        while (i < size){
            if (retentionInMillis[i]!! < offset) {
                key[i] = key[size - 1]
                value[i] = value[size - 1]
                retentionInMillis[i] = retentionInMillis[size - 1]
                key[size - 1] = null
                value[size - 1] = null
                retentionInMillis[size - 1] = null
                size--
            }
            i++
        }
    }
}
