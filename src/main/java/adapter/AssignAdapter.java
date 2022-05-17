package adapter;

import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;
import java.util.List;

public class AssignAdapter extends VoidVisitorAdapter<Object> {
    private int sum;
    private List<AssignExpr> assignExprList;
    // 声明时赋值
    private List<VariableDeclarator> variableDeclaratorList;

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
            // System.out.println(" [L " + n.getBegin() + "] " + n);
            variableDeclaratorList.add(n);
            sum += (exprs.length - 1);
        }
    }

    public AssignAdapter() {
        sum = 0;
        assignExprList = new ArrayList<>();
        variableDeclaratorList = new ArrayList<>();
    }

    @Override
    public void visit(AssignExpr n, Object arg) {
        super.visit(n, arg);
        // System.out.print("1111111111");
        // System.out.println(" [L " + n.getBegin() + "] " + n);
        assignExprList.add(n);
        sum ++;
    }

    /* @Override
    public void visit(AssertStmt n, Object arg) {
        super.visit(n, arg);
        System.out.print("2222222222");
        System.out.println(" [L " + n.getBegin() + "] " + n);
    } */

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
