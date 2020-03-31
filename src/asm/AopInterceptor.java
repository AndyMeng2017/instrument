package asm;

/**
 * @Author: mhn
 * @Date: 2020/3/31 16:41
 * @Version 1.0
 * @Software: IntelliJ IDEA
 */
public class AopInterceptor {
    public static void beforeInvoke() {
        System.out.println("before");
    };
    public static void afterInvoke() {
        System.out.println("after");
    };
}
