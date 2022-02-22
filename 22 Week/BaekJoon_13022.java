import java.util.*;

public class BaekJoon_13022 {
	static int[] count;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		String str=sc.next();
		
		if(str.length()<4)
			System.out.println(0);
		else {
			count=new int[4];
			HashMap<Character, Integer> map=new HashMap<>();
			// 각 알파벳과 숫자를 map을 통해 매핑해준다.
			map.put('w', 0);
			map.put('o', 1);
			map.put('l', 2);
			map.put('f', 3);
			
			boolean flag=true;
			count[map.get(str.charAt(0))]=1;
			
			for(int i=1;i<str.length();i++) {
				int pre=map.get(str.charAt(i-1)); // 이전 알파벳에 매핑된 숫자
				int cur=map.get(str.charAt(i)); // 현재 알파벳에 매핑된 숫자
				int diff=cur-pre;
				
				// 현재-이전이 -3일 경우 = (w-f) 한 싸이클이 끝난 경우
				if(diff==-3) {
					if(check()) { // 각 알파벳이 나온 횟수가 같은지 검사
						count=new int[4];
						count[0]=1;
					}
					else { // 같지 않다면 올바르지 않은 단어
						flag=false;
						break;
					}
				}
				else {
					if(diff==1||diff==0)
						count[cur]++; // 현재 알파벳 횟수 증가
					else { // 알파벳 순서가 올바르지 않은 경우
						flag=false;
						break;
					}
				}
			}
			
			if(!check())
				flag=false;
			
			if(flag)
				System.out.println(1);
			else
				System.out.println(0);
		}
	}
	
	static boolean check() {
		if(count[0] == count[1] && count[1] == count[2] && count[2] == count[3])
				return true;
		else 
			return false;
	}
}	
