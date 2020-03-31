package asm;

import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * @Author: mhn
 * @Date: 2020/3/31 16:43
 * @Version 1.0
 * @Software: IntelliJ IDEA
 */
public class AopMethod extends MethodVisitor implements Opcodes{
    public AopMethod(int api, MethodVisitor mv){
        super(api ,mv);
    }

    @Override
    public void visitCode(){
        super.visitCode();
        this.visitMethodInsn(INVOKESTATIC, "asm/AopInterceptor", "beforeInvoke", "()V");
    }

    @Override
    public void visitInsn(int opcode){
        if(opcode==RETURN){
            mv.visitMethodInsn(INVOKESTATIC, "asm/AopInterceptor", "afterInvoke", "()V");
        }
        super.visitInsn(opcode);
    }

}
