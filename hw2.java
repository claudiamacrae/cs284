package hw2;
//Written 7 methods that execuye in various stated time complexities
public class hw2 {
	
	public static void method0(int n) {
	//O(n)
		int counter = 0;
		for(int i = 0; i < n; i++) {
			counter++;
			System.out.println("Operation: " + counter);
		}
	}
	
	public static void method1(int n) {
	//O(n^2)
		int counter = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				counter++;
				System.out.println("Operation: " + counter);
			}
		}
	}
	
	public static void method2(int n) {
	//O(n^3)
		int counter = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				for(int k = 0; k < n; k++) {
					counter++;
					System.out.println("Operation: " + counter);
				}
			}
		}
	}
	
	public static void method3(int n) {
	//O(log(n))
		int counter = 0;
		for(int i = 1; i < n; i *= 10) {
			counter++;
			System.out.println("Operation: " + counter);
		}
	}
	
	public static void method4(int n) {
	//O(nlog(n))
		int counter = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 1; j < n; j *= 10) {
				counter++;
				System.out.println("Operation: " + counter);
			}
		}
	}
	
	public static void method5(int n) {
	//O(log(log(n)))
		int counter = 0;
		for(int i = 0; i < n; i *= 2) {
			for(int j = 1; j < n; j *= 10) {
				counter++;
				System.out.println("Operation: " + counter);
			}
		}
	}
	
	public static void method6(int n) {
	//O(2^n)
		rec(1, 0, n);
	}
	
	public static void rec(int count, int i, int n) {
		if(i < n) {
			rec(count, i+1, n);
			rec(count + (int)Math.pow(2, (n-i)-1), i+1, n);
		}else {
			System.out.println("Operation: " + count);
		}
	}
	
	public static void main(String[] args) {
		method6(3);
	}
}
