import java.util.*;

public class BaekJoon_2747 {
	static int[] dp;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		dp=new int[46];
		
		dp[0]=0;
		dp[1]=1;
		dp[2]=1;
		
		// 피보나치 값 채우기
		for(int i=3;i<=n;i++)
			dp[i]=dp[i-1]+dp[i-2];
		
		System.out.println(dp[n]);
	}
}
