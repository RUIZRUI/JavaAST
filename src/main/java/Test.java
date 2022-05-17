import java.util.Scanner;

public class Test{

	private String Str;
	private int Int;

	/**
	 * 用于过滤的方法
	 */
	public void test() {
		// 局部变量
		int a, b, c;
		
		// 赋值语句
		a = 1;
		b = 2;
		c = 3;
		
		// 函数调用
		test1();
		test2();
		test3();
		
		// 条件语句
		if (a != 0) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}
		
		// 选择语句
		switch(a) {
			case 0:
				System.out.println("选择语句：a=" + 0);
				break;
			case 1:
				System.out.println("选择语句：a=" + 1);
				break;
			default:
				System.out.println("选择语句：default");
				break;
		}
		
		// 迭代语句
		for (int i=0; i<10; i++) {
			System.out.println("迭代语句，i=" + i);
		}
		
		// 输出语句
		System.out.println("输出1");
		System.out.println("输出2");
		System.out.println("输出3");
		
		// 输入语句
		Scanner sc = new Scanner(System.in);
		System.out.print("请输入姓名：");
		String name = sc.nextLine();
		System.out.println("欢迎您：" + name);
		
		// 通过函数调用赋值
		a = test4();
		b = test4();
		c = test4();
		System.out.println("通过函数调用赋值：a=" + a + ", b=" + b + ", c=" + c);
		
	}
	
	public void test1() {
		System.out.println("test1");
	}
	
	public void test2() {
		System.out.println("test2");
	}
	
	public void test3() {
		System.out.println("test3");
	}
	
	public int test4() {
		return 0;
	}
}