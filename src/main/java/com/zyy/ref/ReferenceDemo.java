package com.zyy.ref;
/**
 * GC没看懂
 */
import java.lang.ref.WeakReference;

import org.junit.Test;

public class ReferenceDemo {

    @Test
    public void demo() {
        // WeakReference<String> weakReference = new WeakReference<String>("wild");
        WeakReference<String> weakReference = new WeakReference<String>(new String("wild"));
        System.gc();
        System.runFinalization();
        String str = weakReference.get();
        System.out.println(str);
    }
}
