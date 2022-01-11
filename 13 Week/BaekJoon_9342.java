import java.util.*;

public class BaekJoon_9342 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		
		int t=sc.nextInt();
		
		/*
		 * [A-F]? : A~F 중 문자가 있거나 없다
		 * A+ : A가 1문자이상 있다.
		 * $ : 문자열이 끝난다.
		 */
		String str="[A-F]?A+F+C+[A-F]?$";
		
		for(int i=0;i<t;i++) {
			String word=sc.next();
			
			if(word.matches(str))
				System.out.println("Infected!");
			else
				System.out.println("Good");
		}
	}

}
