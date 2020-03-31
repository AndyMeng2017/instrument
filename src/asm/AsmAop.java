package asm;

/**
 * @Author: mhn
 * @Date: 2020/3/31 16:44
 * @Version 1.0
 * @Software: IntelliJ IDEA
 */
public class AsmAop {
    /**
     * ASM只不过是通过 “Visitor” 模式将 “.class” 类文件的内容从头到尾扫描一遍
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Class<?> clazz = new AopClassLoader(Thread.currentThread().getContextClassLoader()).loadClass("asm.TestBean_Tmp");
        clazz.getMethods()[0].invoke(clazz.newInstance());
    }
}
