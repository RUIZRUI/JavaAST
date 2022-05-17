package adapter;

import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

// TODO: int a=test4(), b=test4()
public class AssignMethodCallAdapter extends VoidVisitorAdapter<Object> {

    private String pattern = ".*=\\s*[A-Za-z_](\\w)*\\s*\\(.*\\).*";
    private int sum;
    private List<AssignExpr> assignExprList;
    // 声明时赋值
    private List<VariableDeclarator> variableDeclaratorList;

    public AssignMethodCallAdapter(){
        sum = 0;
        assignExprList = new ArrayList<>();
        variableDeclaratorList = new ArrayList<>();
    }

    /* @Override
    public void visit(MethodCallExpr n, Object arg) {
        super.visit(n, arg);

        String expr = n.toString();
        // 处理 ==
        expr = expr.replaceAll("==", "");
        // '=' 的索引
        int indexEqual = expr.indexOf("=");
        // '(' 的索引
        int indexMethod = expr.indexOf("(");
        if (indexEqual < 0){
            // 不存在 '='
            return;
        }
        if (indexEqual > indexMethod) {
            // '=' 为参数赋值，比如 test(a=1, b=2)
            return;
        }
        System.out.println(" [L " + n.getBegin() + "] " + n);
        methodCallExprList.add(n);
        sum ++;
    } */

    @Override
    public void visit(AssignExpr n, Object arg) {
        super.visit(n, arg);

        String expr = n.toString();
        boolean isMatch = Pattern.matches(pattern, expr);
        if (isMatch){
            // System.out.print("1111111111");
            System.out.println(" [L " + n.getBegin() + "] " + n);
            assignExprList.add(n);
            sum ++;
        }
    }

    // 处理声明时赋值
    @Override
    public void visit(VariableDeclarator n, Object arg) {
        super.visit(n, arg);

        String expr = n.toString();
        // System.out.print("111111111");
        // System.out.println(expr);

        // 处理 ==
        expr = expr.replaceAll("==", "");
        // 统计 ' = ' 数量
        String[] exprs = expr.split("=");
        /* for (String str: exprs){
            System.out.print(str + ",,,");
        }
        System.out.println(); */
        if (exprs.length > 1){
            boolean isMatch = Pattern.matches(pattern, expr);
            if (isMatch){
                System.out.println(" [L " + n.getBegin() + "] " + n);
                variableDeclaratorList.add(n);
                // sum += (exprs.length - 1);
                sum ++;
            }
        }
    }

    public int getSum(){
        return this.sum;
    }

    public List<AssignExpr> getAssignExprList(){
        return this.assignExprList;
    }

    public List<VariableDeclarator> getVariableDeclaratorList(){
        return this.variableDeclaratorList;
    }

    public void clear(){
        sum = 0;
        assignExprList.clear();
        variableDeclaratorList.clear();
    }
}
