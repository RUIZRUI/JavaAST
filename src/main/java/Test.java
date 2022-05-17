import java.util.Scanner;

public class Test {

	private String Str;
	private int Int;


	public void test() {
		int a, b, c;

		a = 1;
		b = 2;
		c = 3;

		test1();
		test2();
		test3();

		if (a != 0) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}

		int lambda = (a == 1) ? b : c;
		System.out.println("lambda = " + lambda);

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

		for (int i=0; i<10; i++) {
			System.out.println("for循环，i=" + i);
		}

		int i = 0;
		while (i < 10){
			System.out.println("while循环，i=" + i);
		}

		i = 0;
		do {
			System.out.println("do-while循环，i=" + i);
		} while (i < 10);

		int arrInt[] = {1, 2, 3, 4, 5};
		for(int temp : arrInt){
			System.out.println("for循环，i=" + temp);
		}

		System.out.println("输出1");
		System.out.println("输出2");
		System.out.println("输出3");

		Scanner sc = new Scanner(System.in);
		System.out.print("请输入姓名：");
		String name = sc.nextLine();
		System.out.println("欢迎您：" + name);

		a = test4();
		b = test4();
		c = test4();
		int d = test4();
		System.out.println("通过函数调用赋值：a=" + a + ", b=" + b + ", c=" + c + ", d=" + d);
		
	}
	
	public void test1() {
		System.out.println("test1");
		// test2();
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