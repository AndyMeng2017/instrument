import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * 这里要指定 Agent-Class，可以在运行后进行动态修改
 * @Author: mhn
 * @Date: 2020/3/31 15:21
 * @Version 1.0
 * @Software: IntelliJ IDEA
 */
public class AgentMain {
    public static void agentmain(String agentArgs, Instrumentation inst)
            throws ClassNotFoundException, UnmodifiableClassException,
            InterruptedException {
        inst.addTransformer(new Transformer(), true);
        inst.retransformClasses(TransClass.class);
        System.out.println("Agent Main Done");
    }
}
