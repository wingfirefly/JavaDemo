package com.zyy.concurrent;
/**
 * 自定义线程
 * @author Shinelon
 *
 */
public class AsycnDemo {

    public static void main(String[] args) {
        MyAsyncTask task = new MyAsyncTask(new ZyyCallable<String>() {

            @Override
            public void call(String result) {
                Thread thread = Thread.currentThread();
                System.out.println("子线程工作结束后执行我, 我是在" + thread.getName() + "中");
                System.out.println(result);
            }
        });
        System.out.println("I am " + Thread.currentThread().getName());
        task.execute("param1", "param2");
    }
}

class MyAsyncTask extends AsyncTask<String, String> {

    public MyAsyncTask(ZyyCallable<String> callable) {
        super(callable);
    }

    @Override
    public String doInBackground(String... params) {
        try {
            System.out.println(params[0]);
            System.out.println("我是请求数据之类的耗时操作, 我1秒后做完");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "sleep 1000";
    }
}

interface ZyyCallable<Result> {
    void call(Result result);
}

abstract class AsyncTask<Params, Result> {

    private ZyyCallable<Result> callable;

    public AsyncTask(ZyyCallable<Result> callable) {
        this.callable = callable;
    }

    public abstract Result doInBackground(@SuppressWarnings("unchecked") Params... params);

    public void execute(@SuppressWarnings("unchecked") final Params... params) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Result result = doInBackground(params);
                if (callable != null) {
                    callable.call(result);
                    /*EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            callable.call(result);
                        }
                    });*/
                }
            }
        }).start();
    }
}
