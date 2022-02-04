import java.util.*;

public class BaekJoon_5073 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		
		while(true) {
			int a=sc.nextInt();
			int b=sc.nextInt();
			int c=sc.nextInt();
			
			if(a==0&&b==0&&c==0)
				break;
			
			int max=Math.max(a,Math.max(b, c));
			
			// 삼각형이 될 수 없는 경우는 경우의 수 따지면 X
			if(max>=a+b||max>=a+c||max>=b+c) {
				System.out.println("Invalid");
				continue;
			}
				
			if(a==b&&b==c)
				System.out.println("Equilateral");
			else if(a==b||a==c||b==c)
				System.out.println("Isosceles");
			else if(max<a+b&&max<a+c&&max<b+c)
				System.out.println("Scalene");
				
		}
	}

}
