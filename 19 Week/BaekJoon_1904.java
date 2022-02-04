import java.util.*;
/*
 * n=1일때 1개
 * n=2일때 00, 11 2개
 * n=3일떼 001, 100, 111 3개
 * n=4일때 0000, 0011, 1100, 1001, 1111 5개
 * n=5일때 00001, 00100, 10000, 00111, 10011, 11001, 11100, 11111 8개
 * => 피보나치 수열처럼 늘어나는 것은 확인할 수 있다
 *
 */
public class BaekJoon_1904 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] dp=new int[1000001];
		
		dp[1]=1;
		dp[2]=2;
		dp[3]=3;
		
	
		for(int i=4;i<=n;i++)
			dp[i]=(dp[i-1]+dp[i-2])%15746;
		
		System.out.println(dp[n]);
	}

}
