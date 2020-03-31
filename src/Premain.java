import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * @Author: mhn
 * @Date: 2020/3/31 11:53
 * @Version 1.0
 * @Software: IntelliJ IDEA
 */
public class Premain {
    /**
     * 启动命令
     * java -javaagent:TestInstrument1.jar=haha -jar TestInstrument1.jar
     *
     * @param agentArgs
     * @param inst
     * @throws ClassNotFoundException
     * @throws UnmodifiableClassException
     */
    public static void premain(String agentArgs, Instrumentation inst)
            throws ClassNotFoundException, UnmodifiableClassException {
        // 方式一：类转换
        //inst.addTransformer(new Transformer());
        //System.out.println(agentArgs);

        // 方式二：类重新定义
        ClassDefinition def = new ClassDefinition(TransClass.class, Transformer.getBytesFromFile(Transformer.classNumberReturns2));
        inst.redefineClasses(new ClassDefinition[]{def});
        System.out.println("success");
    }
}
