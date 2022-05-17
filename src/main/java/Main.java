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
