import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws Exception{
        // HelloWorldBuilder builder = new HelloWorldBuilder();

        // GenAST.test();

        ParseJava.parseOneFile();

        /* String pattern = ".*=\\s*[A-Za-z_](\\w)*\\s*\\(.*\\).*";
        String expr = "c = test4()";
        System.out.println(Pattern.matches(pattern, expr)); */
    }
}
