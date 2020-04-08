package reference;

import proxy.MyProxy;

/**
 * @Author: mhn
 * @Date: 2020/4/7 17:15
 * @Version 1.0
 * @Software: IntelliJ IDEA
 */
public class WeakReferenceTest {
    public static void main(String[] args) {
        // 只会清理被弱引用的对象时，在执行gc时，new Apple("红富士")对象就会被清理掉
        Salad salad = new Salad(new Apple("红富士"));

        // 只会清理被弱引用的对象，在执行gc时，这里的类加载器对象是不会被清理掉，所以依然能获取到
        //Salad salad = new Salad(MyProxy.IHello.class.getClassLoader());
        //通过WeakReference的get()方法获取Apple
        System.out.println("Apple:" + salad.get());
        System.gc();
        try {
            //休眠一下，在运行的时候加上虚拟机参数-XX:+PrintGCDetails，输出gc信息，确定gc发生了。
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //如果为空，代表被回收了
        if (salad.get() == null) {
            System.out.println("clear Apple。");
        }else {
            System.out.println("没有清理掉" + salad.get());
        }
    }
}
