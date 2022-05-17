package util;

import adapter.*;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.ConditionalExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.SwitchStmt;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

public class JavaParserUtil {


    /**
     * 解析单个Java文件
     */
    public static void parseMethod(String fileName, String methodName) throws Exception{
        // 开始分析文件
        File file = new File(fileName);
        System.out.println("-------------------------------------------");
        System.out.print("开始分析文件：");
        System.out.println(fileName);
        System.out.print("开始分析方法：");
        System.out.println(methodName);
        CompilationUnit comp = JavaParserUtil.getCompilationUnit(file, methodName);
        // System.out.println(comp.toString());
        // System.out.println("-------------------------------------------");
        System.out.println("-------------------------------------------");

        // 局部变量
        LocalVariableAdapter localVariableAdapter = new LocalVariableAdapter();
        localVariableAdapter.visit(comp, null);
        List<VariableDeclarator> variableDeclaratorList = localVariableAdapter.getVariableDeclaratorList();
        int localVariableSum = localVariableAdapter.getSum();
        localVariableAdapter.clear();
        System.out.println("（1）局部变量数：" + localVariableSum);
        // System.out.println("-------------------------------------------");
        System.out.println("-------------------------------------------");

        // 函数调用
        MethodCallAdapter methodCallAdapter = new MethodCallAdapter();
        methodCallAdapter.visit(comp, null);
        List<MethodCallExpr> methodCallExprList = methodCallAdapter.getMethodCallExprList();
        int methodCallSum = methodCallAdapter.getSum();
        methodCallAdapter.clear();
        // List<MethodCallExpr> methodCallExprs = listMethodCalls(new File("E:\\ruizrui\\AST\\Test\\ast4\\src\\main\\java\\Test1.java"));
        System.out.println("（2）函数调用数：" + methodCallSum);
        // System.out.println("-------------------------------------------");
        System.out.println("-------------------------------------------");

        // 条件语句
        ConditionAdapter conditionAdapter = new ConditionAdapter();
        conditionAdapter.visit(comp, null);
        List<ConditionalExpr> conditionalExprList = conditionAdapter.getConditionalExprList();      // 三目条件语句
        List<IfStmt> ifStmtList = conditionAdapter.getIfStmtList();     // if 语句
        int conditionSum = conditionAdapter.getSum();
        conditionAdapter.clear();
        System.out.println("（3）条件语句数：" + conditionSum);
        // System.out.println("-------------------------------------------");
        System.out.println("-------------------------------------------");

        // 迭代语句
        CycleAdapter cycleAdapter = new CycleAdapter();
        cycleAdapter.visit(comp, null);
        List<Object> cycleList = cycleAdapter.getCycleList();
        int cycleSum = cycleAdapter.getSum();
        cycleAdapter.clear();
        System.out.println("（4）迭代语句数：" + cycleSum);
        // System.out.println("-------------------------------------------");
        System.out.println("-------------------------------------------");

        // 输入语句
        InAdapter inAdapter = new InAdapter();
        inAdapter.visit(comp, null);
        List<MethodCallExpr> methodCallExprList2 = inAdapter.getMethodCallExprList();
        int inSum = inAdapter.getSum();
        inAdapter.clear();
        System.out.println("（5）输入语句数：" + inSum);
        // System.out.println("-------------------------------------------");
        System.out.println("-------------------------------------------");

        // 输出语句
        OutAdapter outAdapter = new OutAdapter();
        outAdapter.visit(comp, null);
        List<MethodCallExpr> methodCallExprList1 = outAdapter.getMethodCallExprList();
        int outSum = outAdapter.getSum();
        outAdapter.clear();
        System.out.println("（6）输出语句数：" + outSum);
        // System.out.println("-------------------------------------------");
        System.out.println("-------------------------------------------");

        // 使用函数调用来赋值
        AssignMethodCallAdapter assignMethodCallAdapter = new AssignMethodCallAdapter();
        assignMethodCallAdapter.visit(comp, null);
        List<AssignExpr> assignExprList1 = assignMethodCallAdapter.getAssignExprList();
        List<VariableDeclarator> variableDeclaratorList2 = assignMethodCallAdapter.getVariableDeclaratorList();
        int assignMethodCallSum = assignMethodCallAdapter.getSum();
        assignMethodCallAdapter.clear();
        System.out.println("（7）使用函数调用来赋值数：" + assignMethodCallSum);
        // System.out.println("-------------------------------------------");
        System.out.println("-------------------------------------------");

        // 选择语句
        SwitchAdapter switchAdapter = new SwitchAdapter();
        switchAdapter.visit(comp, null);
        List<SwitchStmt> switchStmtList = switchAdapter.getSwitchStmtList();
        int switchSum = switchAdapter.getSum();
        switchAdapter.clear();
        System.out.println("（8）选择语句数：" + switchSum);
        // System.out.println("-------------------------------------------");
        System.out.println("-------------------------------------------");

        // 赋值语句
        AssignAdapter assignAdapter = new AssignAdapter();
        assignAdapter.visit(comp, null);
        List<AssignExpr> assignExprList = assignAdapter.getAssignExprList();
        List<VariableDeclarator> variableDeclaratorList1 = assignAdapter.getVariableDeclaratorList();
        int assignSum = assignAdapter.getSum();
        assignAdapter.clear();
        System.out.println("（9）赋值语句数：" + assignSum);
        // System.out.println("-------------------------------------------");
        System.out.println("-------------------------------------------");
    }

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
