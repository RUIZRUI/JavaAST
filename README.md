# JavaAST

## 分析Java文件的一个内部方法，度量指标如下：
1. 局部变量数
2. 函数调用数
3. 条件语句数
4. 迭代语句数
5. 输入语句数
6. 输出语句数
7. 使用函数调用来赋值数
8. 选择语句数
9. 赋值语句数

## 使用
修改 Main.java 中的变量 fileName（Java文件地址）和 methodName（方法名）
```java
import util.JavaParserUtil;

public class Main {

    public static void main(String[] args){
        try {
            String fileName = "E:\\ruizrui\\AST\\Test\\ast5\\src\\main\\java\\Test.java";
            String methodName = "test";
            JavaParserUtil.parseMethod(fileName, methodName);
        } catch (Exception e){
            System.out.println("JavaParserUtil.parseMethod 方法异常：" + e.getMessage());
            new RuntimeException(e);
        }
    }

}
```