package adapter;

import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.ForeachStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;
import java.util.List;

public class CycleAdapter extends VoidVisitorAdapter<Object> {

    private int sum;
    List<Object> cycleList;

    public CycleAdapter(){
        sum = 0;
        cycleList = new ArrayList<>();
    }

    @Override
    public void visit(DoStmt n, Object arg) {
        super.visit(n, arg);
        // System.out.print("11111111--");
        System.out.println(" [L " + n.getBegin() + "] ");
        System.out.println(n);
        cycleList.add(n);
        sum ++;
    }

    @Override
    public void visit(ForStmt n, Object arg) {
        super.visit(n, arg);
        // System.out.print("222222222--");
        System.out.println(" [L " + n.getBegin() + "] ");
        System.out.println(n);
        cycleList.add(n);
        sum ++;
    }

    @Override
    public void visit(ForeachStmt n, Object arg) {
        super.visit(n, arg);
        // System.out.print("33333333--");
        System.out.println(" [L " + n.getBegin() + "] ");
        System.out.println(n);
        cycleList.add(n);
        sum ++;
    }

    @Override
    public void visit(WhileStmt n, Object arg) {
        super.visit(n, arg);
        // System.out.print("444444444--");
        System.out.println(" [L " + n.getBegin() + "] ");
        System.out.println(n);
        cycleList.add(n);
        sum ++;
    }

    public int getSum(){
        return this.sum;
    }

    public List<Object> getCycleList(){
        return this.cycleList;
    }

    public void clear(){
        sum = 0;
        cycleList.clear();
    }

}
