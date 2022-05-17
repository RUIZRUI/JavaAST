package util;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;

import java.io.File;

public class JavaParserUtil {

    /**
     * 获取指定 Java 文件内方法的 AST
     * @param file
     * @param methodName
     * @return
     */
    public static CompilationUnit getCompilationUnit(File file, String methodName){
        if (file == null || methodName == null){
            System.out.println("file或methodName不能为空");
            return null;
        }
        try {
            CompilationUnit cu = JavaParser.parse(file);

            // 类型声明
            NodeList<TypeDeclaration<?>> types = cu.getTypes();
            for (TypeDeclaration<?> type: types){
                // System.out.println("## " + type.getName());
                // 成员
                NodeList<BodyDeclaration<?>> members = type.getMembers();
                for (Node node: members){
                    if (node instanceof MethodDeclaration){
                        String nodeName = ((MethodDeclaration) node).getName().getIdentifier();
                        if (methodName.equalsIgnoreCase(nodeName)){
                            // System.out.println("-------------------------------------------");
                            // System.out.println(node.toString());
                            // 为方法创建虚拟类
                            StringBuilder builder = new StringBuilder("class A { ");
                            builder.append(node.toString());
                            builder.append(" }");
                            CompilationUnit comp = JavaParser.parse(builder.toString());
                            return comp;
                        }
                    }
                }
            }

        } catch (Exception e){
            System.out.println("getCompilationUnit 方法异常：" + e.getMessage());
            new RuntimeException(e);
        }

        System.out.print("未找到方法：");
        System.out.println(methodName);
        return null;
    }
}
