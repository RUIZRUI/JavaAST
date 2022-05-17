import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.FileInputStream;

public class GenAST {

    public static void test() throws Exception{
        // System.out.println("test");

        FileInputStream inputStream = new FileInputStream("E:\\ruizrui\\AST\\Test\\ast4\\src\\main\\java\\Test.java");

        // 解析文件
        CompilationUnit cu = JavaParser.parse(inputStream);

        // 输出解析结果
        System.out.println(cu.toString());

        cu.accept(new MethodVisitor(), null);
    }


    private static class MethodVisitor extends VoidVisitorAdapter<Void> {
        @Override
        public void visit(MethodDeclaration n, Void arg) {
            /* here you can access the attributes of the method.
             this method will be called for all methods in this
             CompilationUnit, including inner class methods */
            System.out.println("method:"+n.getName());
            super.visit(n, arg);
        }

        @Override
        public void visit(ClassOrInterfaceDeclaration n, Void arg) {
            System.out.println("class:"+n.getName());
            System.out.println("extends:"+n.getExtendedTypes());
            System.out.println("implements:"+n.getImplementedTypes());

            super.visit(n, arg);
        }

        @Override
        public void visit(PackageDeclaration n, Void arg) {
            System.out.println("package:"+n.getName());
            super.visit(n, arg);
        }
    }
}
