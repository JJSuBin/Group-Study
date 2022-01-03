import java.util.*;

public class BaekJoon_1107 {
	static int n,m;
	static boolean[] broken=new boolean[10];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		
		n=sc.nextInt();
		m=sc.nextInt();
		for(int i=0;i<m;i++) {
			int num=sc.nextInt();
			
			broken[num]=true;
		}
		
		int min=Math.abs(n-100); // 시작 채널은 100번, 채널 n까지 +,- 버튼으로만 누르는 경우 
		// 완전탐색
		for(int i=0;i<=1000000;i++) {
			int length=press(i); // i 채널을 숫자 버튼으로 누른다면 몇번 눌러아 하는지 구하기 
			
			if(length>0) {
				int count=Math.abs(n-i); // i채널에서 n채널까지 + or -로 이동
				min=Math.min(min, length+count); // min값 갱신
			}
		}
		
		System.out.println(min);
	}
	
	static int press(int num) {
		if(num==0) { // 0일 경우 예외처리
			if(broken[0]) // 고장난 경우
				return 0;
			else 
				return 1;
		}
		
		int count=0;
		while(num>0) {
			if(broken[num%10])
				return 0;
			
			count+=1; // 누르는 횟수 증가
			num/=10;
		}
		
		return count;
	}
}
