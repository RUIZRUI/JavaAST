package adapter;

import com.github.javaparser.ast.expr.ConditionalExpr;
import com.github.javaparser.ast.expr.LambdaExpr;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;
import java.util.List;

public class ConditionAdapter extends VoidVisitorAdapter<Object> {

    // 条件语句数
    private int sum;
    // 三目条件语句
    private List<ConditionalExpr> conditionalExprList;
    // if 语句
    private List<IfStmt> ifStmtList;

    public ConditionAdapter(){
        super();
        sum = 0;
        conditionalExprList = new ArrayList<>();
        ifStmtList = new ArrayList<>();
    }

    // 三目条件语句
    @Override
    public void visit(ConditionalExpr n, Object arg) {
        super.visit(n, arg);
        // System.out.print("11111111--");
        System.out.println(" [L " + n.getBegin() + "] " + n);
        conditionalExprList.add(n);
        sum ++;
    }

    @Override
    public void visit(IfStmt n, Object arg) {
        super.visit(n, arg);
        // System.out.print("22222222--");
        System.out.println(" [L " + n.getBegin() + "] ");
        System.out.println(n);
        ifStmtList.add(n);
        sum ++;
    }

    public int getSum(){
        return this.sum;
    }

    public List<ConditionalExpr> getConditionalExprList(){
        return this.conditionalExprList;
    }

    public List<IfStmt> getIfStmtList(){
        return this.ifStmtList;
    }

    public void clear(){
        sum = 0;
        conditionalExprList.clear();
        ifStmtList.clear();
    }

}
