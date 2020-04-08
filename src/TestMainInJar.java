/**
 * @Author: mhn
 * @Date: 2020/3/31 11:49
 * @Version 1.0
 * @Software: IntelliJ IDEA
 */
public class TestMainInJar {

    // 演示在main方法执行前执行
//    public static void main(String[] args) {
//        System.out.println(new TransClass().getNumber());
//    }

    // 演示在main方法执行后执行，需要指定Agent-Class
    public static void main(String[] args) throws InterruptedException {
        System.out.println(new TransClass().getNumber());
        int count = 0;
        while (true) {
            Thread.sleep(1000 * 5);
            count++;
            int number = new TransClass().getNumber();
            System.out.println(number);
            if (3 == number || count >= 10) {
                break;
            }
        }
    }

}
