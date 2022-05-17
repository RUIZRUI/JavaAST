package adapter;

import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;
import java.util.List;

public class LocalVariableAdapter extends VoidVisitorAdapter<Object> {

    // 局部变量数
    private int sum;
    private List<VariableDeclarator> variableDeclaratorList;

    public LocalVariableAdapter(){
        super();
        sum = 0;
        variableDeclaratorList = new ArrayList<>();
    }

    /* @Override
    public void visit(VariableDeclarationExpr n, Object arg) {
        super.visit(n, arg);

        System.out.print("11111111--");
        System.out.println(" [L " + n.getBegin() + "] " + n);
    } */

    @Override
    public void visit(VariableDeclarator n, Object arg) {
        super.visit(n, arg);
        // System.out.print("2222222--");
        System.out.println(" [L " + n.getBegin() + "] " + n);
        variableDeclaratorList.add(n);
        sum ++;
    }

    public int getSum(){
        return this.sum;
    }

    public List<VariableDeclarator> getVariableDeclaratorList(){
        return this.variableDeclaratorList;
    }

    public void clear(){
        sum = 0;
        variableDeclaratorList.clear();
    }
}
