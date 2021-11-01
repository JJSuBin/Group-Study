import java.util.*;

public class BaekJoon_17609 {
	static String str;
	static char[] arr;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int t=sc.nextInt();
		
		for(int i=0;i<t;i++) {
			str=sc.next();
			arr=str.toCharArray();
			
			// 팰린드롬일 경우 나머지 조검 검사하지 않고 0 출력후 다음 문장 탐색
			if(isPalindrome(0,str.length()-1)) {
				System.out.println(0);
				continue;
			}
			
			// 팰린드롬이 아니라면 유사회문인지 아닌지 탐색
			if(pseudo_palindrome(0,str.length()-1))
				System.out.println(1);
			else 
				System.out.println(2);
		}
	}
	
	// 투포인터를 사용하여 팰린드롬인지 확인하는 함수
	static boolean isPalindrome(int left, int right) {
		while(left<=right) {
			if(arr[left]!=arr[right]) // 대칭되는 알파벳이 같지 않다면 false return 
				return false;
			
			// 다음 알파벳 검사
			left+=1; 
			right-=1;
		}
		return true;
	}
	
	// 투포인터를 사용하여 유사회문인지 확인하는 함수
	static boolean pseudo_palindrome(int left, int right) {
		while(left<=right) {
			if(arr[left]!=arr[right]) { // 대칭되는 알파벳이 같지 않다면 
				// 대칭되지 않은 숫자들을 각각 제외해보고 팰린드롬인지 확인
				boolean new_left=isPalindrome(left+1,right);
				boolean new_right=isPalindrome(left,right-1);
				
				if(!new_left&&!new_right) // 둘다 만족하지 못한다면 false return
					return false;
				else 
					return true;
			}
			
			// 다음 알파벳 검사
			left+=1;
			right-=1;
		}
		
		return true;
	}
}
