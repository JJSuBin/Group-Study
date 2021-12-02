import java.util.*;

public class BaekJoon_9935 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		String str=sc.next();
		String boom=sc.next();
		Stack<Character> stack=new Stack<>();
		
		for(int i=0;i<str.length();i++) {
			stack.add(str.charAt(i));
			
			// 스택에 쌓인 문자의 갯수가 폭발 문자열의 길이와 같거나 클때 폭발 문자열이 있는지 검사
			if(stack.size()>=boom.length()) { 
				boolean flag=true; // 폭발 문자열이 포함되어 있는지 체크하는 변수
				// 스택에 있는 문자열과 폭발 문자열을 비교한다. 
				for(int j=0;j<boom.length();j++) {
					if(stack.get(stack.size()-boom.length()+j)!=boom.charAt(j)) {
						flag=false;
						break;
					}
				}
				if(flag) { // 폭발 문자열이 있다면 그 길이만큼 스택에서 제거해주기
					for(int j=0;j<boom.length();j++)
						stack.pop();
				}
			}
			
		}
		
		StringBuilder sb=new StringBuilder();
		for(char ch:stack)
			sb.append(ch);
		
		System.out.println(sb.length()>0?sb:"FRULA");
	}

}
