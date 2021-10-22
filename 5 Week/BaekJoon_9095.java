import java.util.*;

public class BaekJoon_9095 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int[] dp=new int[11];
		int testcase=sc.nextInt();
		
		dp[1]=1; // 1을 만들수 있는 경우의 수는 1 
		dp[2]=2; // 2를 만들수 있는 경우의 수는 1+1, 2
		dp[3]=4; // 3을 만들수 있는 경우의 수는 1+1+1, 1+2, 2+1, 3
		
		/*
		 * 4를 만들수 있는 경우의 수는 예시에 주어진 것 처럼 7가지이다
		 * 이는 dp[1]+dp[2]+dp[3]과 같다. 
		 * 따라서 점화식을 세워본다면 dp[i]=dp[i-3]+dp[i-2]+dp[i-1]이 된다.
		 */
		for(int i=4;i<=10;i++)
			dp[i]=dp[i-3]+dp[i-2]+dp[i-1];
		
		while(testcase-->0) {
			int n=sc.nextInt();
			
			System.out.println(dp[n]);
		}
	}
}
