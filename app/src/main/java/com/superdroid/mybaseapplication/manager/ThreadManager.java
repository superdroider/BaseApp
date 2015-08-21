package com.superdroid.mybaseapplication.manager;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by GT on 2015/8/20.
 * �̹߳�����
 */
public class ThreadManager {
    private static ThreadManager mThreadManager;

    private ThreadPoolExecutor longPoolExecutor;
    private ThreadPoolExecutor shortPoolExecutor;
    private Object longThreadLock = new Object();
    private Object shortThreadLock = new Object();

    public static ThreadManager getThreadManagerInstance() {
        if (mThreadManager == null) {
            mThreadManager = new ThreadManager();
        }
        return mThreadManager;
    }

    /**
     * ִ�г�����ʱ������
     *
     * @param runnable
     */
    public void longTaskExecute(Runnable runnable) {
        synchronized (longThreadLock) {
            if (longPoolExecutor == null) {
                ThreadPoolProxy proxy = new ThreadPoolProxy(5, 6, 100);
                longPoolExecutor = proxy.createThreadPoolExecutor();
            }
        }
        longPoolExecutor.execute(runnable);
    }

    /**
     * ִ�ж�����
     *
     * @param runnable
     */
    public void shortTaskExecute(Runnable runnable) {
        synchronized (shortThreadLock) {
            if (shortPoolExecutor == null) {
                ThreadPoolProxy proxy = new ThreadPoolProxy(3, 5, 100);
                shortPoolExecutor = proxy.createThreadPoolExecutor();
            }
        }
        shortPoolExecutor.execute(runnable);
    }

    /**
     * ȡ��ִ�г�����ʱ������
     *
     * @param runnable
     */
    public void cancleLongTask(Runnable runnable) {
        if (longPoolExecutor == null || longPoolExecutor.isShutdown()
                || longPoolExecutor.isTerminated()) {
            return;
        } else {
            longPoolExecutor.getQueue().remove(runnable);
        }
    }

    /**
     * ȡ��ִ�ж�����
     *
     * @param runnable
     */
    public void cancleShortTask(Runnable runnable) {
        if (shortPoolExecutor == null || shortPoolExecutor.isShutdown()
                || shortPoolExecutor.isTerminated()) {
            return;
        } else {
            shortPoolExecutor.getQueue().remove(runnable);
        }
    }


    /**
     * �̳߳ش�����
     */
    private class ThreadPoolProxy {
        //        corePoolSize - ������������߳��������������̡߳�
//        maximumPoolSize - �������������߳�����
//        keepAliveTime - ���߳������ں���ʱ����Ϊ��ֹǰ����Ŀ����̵߳ȴ���������ʱ�䡣
//        unit - keepAliveTime ������ʱ�䵥λ��
//        workQueue - ִ��ǰ���ڱ�������Ķ��С��˶��н������� execute �����ύ�� Runnable ����
//        threadFactory - ִ�г��򴴽����߳�ʱʹ�õĹ�����
//        handler - ���ڳ����̷߳�Χ�Ͷ���������ʹִ�б�����ʱ��ʹ�õĴ������
        private ThreadPoolExecutor mThreadPoolExecutor;
        private int corePoolSize;
        private int maximumPoolSize;
        private long keepAliveTime;

        /**
         * ���췽��
         *
         * @param corePoolSize    �̳߳�ά���̵߳���������
         * @param maximumPoolSize �̳߳�ά���̵߳��������
         * @param keepAliveTime   �̳߳�ά���߳�������Ŀ���ʱ��
         */
        public ThreadPoolProxy(int corePoolSize, int maximumPoolSize,
                               long keepAliveTime) {
            this.corePoolSize = corePoolSize;
            this.maximumPoolSize = maximumPoolSize;
            this.keepAliveTime = keepAliveTime;
        }

        /**
         * �����̳߳�
         *
         * @return
         */
        public ThreadPoolExecutor createThreadPoolExecutor() {
            if (mThreadPoolExecutor == null) {
                mThreadPoolExecutor = new ThreadPoolExecutor(corePoolSize,
                        maximumPoolSize, keepAliveTime, TimeUnit.SECONDS,
                        new LinkedBlockingQueue<Runnable>(5),
                        Executors.defaultThreadFactory());
            }
            return mThreadPoolExecutor;
        }
    }
}
