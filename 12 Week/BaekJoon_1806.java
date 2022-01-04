import java.util.*;

// 투포인터
public class BaekJoon_1806 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		
		int n=sc.nextInt();
		int s=sc.nextInt();
		
		int[] num=new int[n+1];
		for(int i=0;i<n;i++)
			num[i]=sc.nextInt();
		
		int min=Integer.MAX_VALUE;
		int start=0;
		int end=0;
		int total=0;
		
		while(true) {
			if(total>=s) { // 조건을 만족하는 경우를 찾았다면 
				total-=num[start]; // 시작지점 앞으로 땡기기
				min=Math.min(min,end-start); // 최소길이 갱신
				start++;
			}
			else if(end==n) // 끝에 도달했다면 while문 종료
				break;
			else // 합이 s 이상이 되기 전까지는 끝 지점을 늘리면서 합 구하기
				total+=num[end++];
		}
		
		if(min==Integer.MAX_VALUE) 
			System.out.println(0);
		else
			System.out.println(min);
	}

}
