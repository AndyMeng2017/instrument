package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 使用动态代理的五大步骤
 * 1.通过实现InvocationHandler接口来自定义自己的InvocationHandler;
 * <p>
 * 2.通过Proxy.getProxyClass获得动态代理类
 * <p>
 * 3.通过反射机制获得代理类的构造方法，方法签名为getConstructor(InvocationHandler.class)
 * <p>
 * 4.通过构造函数获得代理对象并将自定义的InvocationHandler实例对象传为参数传入
 * <p>
 * 5.通过代理对象调用目标方法
 *
 * @Author: mhn
 * @Date: 2020/4/3 16:56
 * @Version 1.0
 * @Software: IntelliJ IDEA
 */
public class MyProxy {
    public interface IHello {
        void sayHello();
    }

    static class Hello implements IHello {
        @Override
        public void sayHello() {
            System.out.println("Hello world!!");
        }
    }

    //自定义InvocationHandler
    static class HWInvocationHandler implements InvocationHandler {
        //目标对象
        private Object target;

        public HWInvocationHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("------插入前置通知代码-------------");
            //执行相应的目标方法
            Object rs = method.invoke(target, args);
            System.out.println("------插入后置处理代码-------------");
            return rs;
        }
    }

    /**
     * 方式一
     * @param args
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
//    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
//        // 方式一
//        // 生成$Proxy0的class文件
//        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
//        // 获取动态代理类
//        Class proxyClazz = Proxy.getProxyClass(IHello.class.getClassLoader(), IHello.class);
//        // 获得代理类的构造函数，并传入参数类型InvocationHandler.class
//        Constructor constructor = proxyClazz.getConstructor(InvocationHandler.class);
//        // 通过构造函数来创建动态代理对象，将自定义的InvocationHandler实例传入
//        IHello iHello = (IHello) constructor.newInstance(new HWInvocationHandler(new Hello()));
//        // 通过代理对象调用目标方法
//        iHello.sayHello();
//    }

    /**
     * 方式二
     *
     * @param args
     */
    public static void main(String[] args) {
        // 生成$Proxy0的class文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        IHello ihello = (IHello) Proxy.newProxyInstance(IHello.class.getClassLoader(),  //加载接口的类加载器
                new Class[]{IHello.class},      //一组接口
                new HWInvocationHandler(new Hello())); //自定义的InvocationHandler
        ihello.sayHello();
        ihello.toString();

        // ############################## 下面的代码测试wakeCache什么情况下失效 #####################################

//        IHello ihello2 = (IHello) Proxy.newProxyInstance(IHello.class.getClassLoader(),  //加载接口的类加载器
//                new Class[]{IHello.class},      //一组接口
//                new HWInvocationHandler(new Hello())); //自定义的InvocationHandler
//        ihello2.sayHello();
//        ihello2.toString();


        // 这里的弱引用不会由于gc失效。因为key为classloader对象，不受gc影响，当classloader移除后，才会将它所对应的所有缓存都清掉，才意味着代理类缓存失效
//        System.gc();
//        try {
//            //休眠一下，在运行的时候加上虚拟机参数-XX:+PrintGCDetails，输出gc信息，确定gc发生了。
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


//        IHello ihello3 = (IHello) Proxy.newProxyInstance(IHello.class.getClassLoader(),  //加载接口的类加载器
//                new Class[]{IHello.class},      //一组接口
//                new HWInvocationHandler(new Hello())); //自定义的InvocationHandler
//        ihello3.sayHello();
//        ihello3.toString();
    }
}
