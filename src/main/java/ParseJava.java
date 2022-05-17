import adapter.*;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.ConditionalExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import util.JavaParserUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParseJava {

    /**
     * 解析单个Java文件
     */
    public static void parseOneFile() throws Exception{

        FileInputStream inputStream = new FileInputStream("E:\\ruizrui\\AST\\Test\\ast4\\src\\main\\java\\Test1.java");

        // 解析文件
        CompilationUnit cu = JavaParser.parse(inputStream);

        // System.out.println(cu.toString());

        // 类型声明
        NodeList<TypeDeclaration<?>> types = cu.getTypes();
        for (TypeDeclaration<?> type : types){
            System.out.println("## " + type.getName());
            // 成员
            NodeList<BodyDeclaration<?>> members = type.getMembers();
            members.forEach(ParseJava::processNode);
        }



        // 开始分析文件
        String fileName = "E:\\ruizrui\\AST\\Test\\ast4\\src\\main\\java\\Test1.java";
        File file = new File(fileName);
        System.out.println("-------------------------------------------");
        System.out.print("开始分析文件：");
        System.out.println(fileName);
        CompilationUnit comp = JavaParserUtil.getCompilationUnit(file, "test");
        // System.out.println(comp.toString());
        System.out.println("-------------------------------------------");
        System.out.println("-------------------------------------------");

        // 局部变量
        LocalVariableAdapter localVariableAdapter = new LocalVariableAdapter();
        localVariableAdapter.visit(comp, null);
        List<VariableDeclarator> variableDeclaratorList = localVariableAdapter.getVariableDeclaratorList();
        int localVariableSum = localVariableAdapter.getSum();
        localVariableAdapter.clear();
        System.out.println("（1）局部变量数：" + localVariableSum);
        System.out.println("-------------------------------------------");
        System.out.println("-------------------------------------------");

        // 函数调用
        MethodCallAdapter methodCallAdapter = new MethodCallAdapter();
        methodCallAdapter.visit(comp, null);
        List<MethodCallExpr> methodCallExprList = methodCallAdapter.getMethodCallExprList();
        int methodCallSum = methodCallAdapter.getSum();
        methodCallAdapter.clear();
        // List<MethodCallExpr> methodCallExprs = listMethodCalls(new File("E:\\ruizrui\\AST\\Test\\ast4\\src\\main\\java\\Test1.java"));
        System.out.println("（2）函数调用数：" + methodCallSum);
        System.out.println("-------------------------------------------");
        System.out.println("-------------------------------------------");

        // 条件语句
        ConditionAdapter conditionAdapter = new ConditionAdapter();
        conditionAdapter.visit(comp, null);
        List<ConditionalExpr> conditionalExprList = conditionAdapter.getConditionalExprList();      // 三目条件语句
        List<IfStmt> ifStmtList = conditionAdapter.getIfStmtList();     // if 语句
        int conditionSum = conditionAdapter.getSum();
        conditionAdapter.clear();
        System.out.println("（3）条件语句数：" + conditionSum);
        System.out.println("-------------------------------------------");
        System.out.println("-------------------------------------------");

        // 迭代语句
        CycleAdapter cycleAdapter = new CycleAdapter();
        cycleAdapter.visit(comp, null);
        List<Object> cycleList = cycleAdapter.getCycleList();
        int cycleSum = cycleAdapter.getSum();
        cycleAdapter.clear();
        System.out.println("（4）迭代语句数：" + cycleSum);
        System.out.println("-------------------------------------------");
        System.out.println("-------------------------------------------");

        // 输入语句
        InAdapter inAdapter = new InAdapter();
        inAdapter.visit(comp, null);
        List<MethodCallExpr> methodCallExprList2 = inAdapter.getMethodCallExprList();
        int inSum = inAdapter.getSum();
        inAdapter.clear();
        System.out.println("（5）输入语句数：" + inSum);
        System.out.println("-------------------------------------------");
        System.out.println("-------------------------------------------");

        // 输出语句
        OutAdapter outAdapter = new OutAdapter();
        outAdapter.visit(comp, null);
        List<MethodCallExpr> methodCallExprList1 = outAdapter.getMethodCallExprList();
        int outSum = outAdapter.getSum();
        outAdapter.clear();
        System.out.println("（6）输出语句数：" + outSum);
        System.out.println("-------------------------------------------");
        System.out.println("-------------------------------------------");

        // 使用函数调用来赋值
        AssignMethodCallAdapter assignMethodCallAdapter = new AssignMethodCallAdapter();
        assignMethodCallAdapter.visit(comp, null);
        List<AssignExpr> assignExprList1 = assignMethodCallAdapter.getAssignExprList();
        List<VariableDeclarator> variableDeclaratorList2 = assignMethodCallAdapter.getVariableDeclaratorList();
        int assignMethodCallSum = assignMethodCallAdapter.getSum();
        assignMethodCallAdapter.clear();
        System.out.println("（7）使用函数调用来赋值数：" + assignMethodCallSum);
        System.out.println("-------------------------------------------");
        System.out.println("-------------------------------------------");

        // 选择语句
        SwitchAdapter switchAdapter = new SwitchAdapter();
        switchAdapter.visit(comp, null);
        List<SwitchStmt> switchStmtList = switchAdapter.getSwitchStmtList();
        int switchSum = switchAdapter.getSum();
        switchAdapter.clear();
        System.out.println("（8）选择语句数：" + switchSum);
        System.out.println("-------------------------------------------");
        System.out.println("-------------------------------------------");

        // 赋值语句
        AssignAdapter assignAdapter = new AssignAdapter();
        assignAdapter.visit(comp, null);
        List<AssignExpr> assignExprList = assignAdapter.getAssignExprList();
        List<VariableDeclarator> variableDeclaratorList1 = assignAdapter.getVariableDeclaratorList();
        int assignSum = assignAdapter.getSum();
        assignAdapter.clear();
        System.out.println("（9）赋值语句数：" + assignSum);
        System.out.println("-------------------------------------------");
        System.out.println("-------------------------------------------");
    }




    public static List<MethodCallExpr> listMethodCalls2(CompilationUnit comp) throws FileNotFoundException{
        List<MethodCallExpr> methodCallExprs = new ArrayList<>();
        final int sum = 0;

        try {
            new VoidVisitorAdapter<Object>() {
                int sum = 0;

                @Override
                public void visit(MethodCallExpr n, Object arg) {
                    super.visit(n, arg);
                    System.out.println(" [L " + n.getBegin() + "] " + n);
                    methodCallExprs.add(n);
                    sum ++;
                }

                int getSum(){
                    return sum;
                }
            }.visit(comp,  null);
        } catch (Exception e){
            new RuntimeException(e);
        }
        System.out.println(methodCallExprs.size());
        return methodCallExprs;
    }

    public static List<MethodCallExpr> listMethodCalls(File file) throws FileNotFoundException{
        List<MethodCallExpr> methodCallExprs = new ArrayList<>();
        final int sum = 0;

        try {
            CompilationUnit cu = JavaParser.parse(file);
            new VoidVisitorAdapter<Object>() {
                int sum = 0;

                @Override
                public void visit(MethodCallExpr n, Object arg) {
                    super.visit(n, arg);
                    System.out.println(" [L " + n.getBegin() + "] " + n);
                    methodCallExprs.add(n);
                    sum ++;
                }

                int getSum(){
                    return sum;
                }
            }.visit(cu,  null);
        } catch (IOException e) {
            new RuntimeException(e);
        }

        System.out.println(methodCallExprs.size());
        return methodCallExprs;
    }

    /**
     * 处理类型,方法,成员
     *
     * @param node
     */
    public static void processNode(Node node) {
        if (node instanceof TypeDeclaration) {
            // 类型声明
            // do something with this type declaration

        } else if (node instanceof MethodDeclaration) {
            // 方法声明
            // do something with this method declaration
            String methodName = ((MethodDeclaration) node).getName().getIdentifier();
            System.out.println("方法: " + methodName);
            // System.out.println("1" + methodName + "1");

            // 处理 test 方法
            if ("test".equals(methodName)){

                // listMethodCalls2(node);

                /* System.out.println("  开始处理" + methodName + "方法");
                // node.getChildNodes().forEach(ParseJava::processNode);
                List<Node> children = node.getChildNodes();
                int i=0;
                for (Node child : children){
                    // System.out.println(i + " " + child.toString());
                    // System.out.println(child.toString());
                    if (i == 2){
                        // 处理函数体
                        System.out.println(child.getChildNodes().size());
                        // child.getChildNodes().forEach(ParseJava::processNode);
                        List<Node> nodes = child.getChildNodes();
                        int j=0;
                        for (Node node1 : nodes){
                            System.out.println(j + " " + node1.toString());
                            j ++;
                        }
                    }
                    i ++;
                } */
            }

        } else if (node instanceof FieldDeclaration) {
            // 成员变量声明
            // do something with this field declaration
            // 注释
            Comment comment = node.getComment().orElse(null);

            // 变量
            NodeList<VariableDeclarator> variables = ((FieldDeclaration) node).getVariables();
            SimpleName fieldName = variables.get(0).getName();
            if (comment != null) {
                System.out.print(handleComment(comment.getContent()));

            }
            System.out.print("\t");
            System.out.print(fieldName);
            System.out.println();
        }

        for (Node child : node.getChildNodes()) {
            processNode(child);
        }
    }

    private static String handleComment(String content) {
        return content;
    }
}
