package adapter;

import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class InAdapter extends VoidVisitorAdapter<Object> {
    private int sum;
    private List<MethodCallExpr> methodCallExprList;

    public InAdapter() {
        super();
        sum = 0;
        methodCallExprList = new ArrayList<>();
    }

    @Override
    public void visit(MethodCallExpr n, Object arg) {
        super.visit(n, arg);

        String expr = n.toString();
        String pattern1 = ".*System\\.in\\..*";
        String pattern2 = ".*\\.next.*\\(.*\\).*";
        if (Pattern.matches(pattern1, expr) || Pattern.matches(pattern2, expr)){
            // System.out.println(" [L " + n.getBegin() + "] " + n);
            methodCallExprList.add(n);
            sum ++;
        }
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
