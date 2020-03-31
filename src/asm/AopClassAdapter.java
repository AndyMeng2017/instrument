package asm;

import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * @Author: mhn
 * @Date: 2020/3/31 16:43
 * @Version 1.0
 * @Software: IntelliJ IDEA
 */
public class AopClassAdapter extends ClassVisitor implements Opcodes {
    public AopClassAdapter(int api, ClassVisitor cv) {
        super(api, cv);
    }


    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        // 更改类名，并使新类继承原有的类。
        super.visit(version, access, name + "_Tmp", signature, name, interfaces);
        {
            MethodVisitor mv = super.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, name, "<init>", "()V");
            mv.visitInsn(RETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
    }


    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        if ("<init>".equals(name)) {
            return null;
        }
        if (!name.equals("halloAop")) {
            return null;
        }
        //
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        return new AopMethod(this.api, mv);
    }
}
