package org.zzq.oauth.core;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainThreadPool {

    public static final ThreadPoolExecutor executor = new ThreadPoolExecutor(20, 30, 2000, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<Runnable>(20));


}
