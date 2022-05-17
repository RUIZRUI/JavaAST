package adapter;

import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;
import java.util.List;

public class MethodCallAdapter extends VoidVisitorAdapter<Object> {

    // 函数调用数
    private int sum;
    private List<MethodCallExpr> methodCallExprList;

    public MethodCallAdapter(){
        super();
        sum = 0;
        methodCallExprList = new ArrayList<>();
    }

    @Override
    public void visit(MethodCallExpr n, Object arg) {
        super.visit(n, arg);
        // System.out.println(" [L " + n.getBegin() + "] " + n);
        methodCallExprList.add(n);
        sum ++;
    }

    public int getSum(){
        return this.sum;
    }

    public List<MethodCallExpr> getMethodCallExprList(){
        return this.methodCallExprList;
    }

    public void clear(){
        sum = 0;
        methodCallExprList.clear();
    }
}
