package adapter;

import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;
import java.util.List;

public class SwitchAdapter extends VoidVisitorAdapter<Object> {

    private int sum;
    private List<SwitchStmt> switchStmtList;

    public SwitchAdapter(){
        sum = 0;
        switchStmtList = new ArrayList<>();
    }

    @Override
    public void visit(SwitchStmt n, Object arg) {
        super.visit(n, arg);
        System.out.print(" [L " + n.getBegin() + "] ");
        System.out.println(n);
        switchStmtList.add(n);
        sum ++;
    }

    public int getSum(){
        return this.sum;
    }

    public List<SwitchStmt> getSwitchStmtList(){
        return this.switchStmtList;
    }

    public void clear(){
        sum = 0;
        switchStmtList.clear();
    }
}
