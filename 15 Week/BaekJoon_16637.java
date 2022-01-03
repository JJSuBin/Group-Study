import java.util.*;

public class BaekJoon_16637 {
	static int result=Integer.MIN_VALUE,n;
	static ArrayList<Integer> num=new ArrayList<>();
	static ArrayList<Character> op=new ArrayList<>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		
		n=sc.nextInt();
		String str=sc.next();
		for(int i=0;i<n;i++) {
			char ch=str.charAt(i);
			
			if(ch=='+'||ch=='-'||ch=='*') 
				op.add(ch);
			else
				num.add(Character.getNumericValue(ch));
		}
		
		DFS(0,num.get(0));
		
		System.out.println(result);
	}
	
	static int cal(int x, int y, char op) {
		if(op=='+')
			return x+y;
		else if(op=='-')
			return x-y;
		else 
			return x*y;
	}
	
	static void DFS(int depth, int answer) {
		if(depth>=op.size()) {
			result=Math.max(result, answer);
			return;
		}
		
		// 괄호가 없는 경우
		int noparen=cal(answer,num.get(depth+1),op.get(depth)); // 단순히 두 값을 연산자에 따라 계산
		DFS(depth+1,noparen); // 사용한 연산자의 개수 늘려주고 재귀호출
		
		// 괄호가 있는 경우
		if(depth+1<op.size()) {
			// answer을 기준으로 오른쪽에 괄호가 있다고 생각했으므로 우선 계산
			int paren=cal(num.get(depth+1),num.get(depth+2),op.get(depth+1));
			/*
			 * answer값과 괄호로 인해 우선계산된 값을 연산자에 따라 계산해준다.
			 * 괄호계산과 answer 계산이 두번 이루어졌기 때문에 사용한 연산자의 개수는 2개 늘려주어야 한다.
			 */
			DFS(depth+2,cal(answer,paren,op.get(depth)));
		}
	}
}
