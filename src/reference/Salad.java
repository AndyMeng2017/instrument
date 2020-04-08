package reference;

import java.lang.ref.WeakReference;

/**
 * @Author: mhn
 * @Date: 2020/4/7 17:13
 * @Version 1.0
 * @Software: IntelliJ IDEA
 */
public class Salad<K> extends WeakReference<K> {
    public Salad(K key) {
        super(key);
    }
}
