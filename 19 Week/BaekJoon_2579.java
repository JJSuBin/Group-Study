import java.util.*;

public class BaekJoon_2579 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] dp=new int[n+1];
		int[] stair=new int[n+1];
		
		for(int i=1;i<=n;i++)
			stair[i]=sc.nextInt();
		
		dp[0]=0;
		dp[1]=stair[1];
		
		if(n>=2)
			dp[2]=stair[1]+stair[2];
		
		/*
		 * i번째 계단을 밟을 수 있는 경우의 수는
		 * i번째 계단보다 3칸 아래 계단 -> i칸 아래 -> i번째 계단 순으로 밟는 경우와
		 * i번째 계단보다 2칸 아래 계단 -> i번쨰 계단을 밟는 경우이다.
		 */
		for(int i=3;i<=n;i++) 
			dp[i]=(Math.max(dp[i-3]+stair[i-1],dp[i-2]))+stair[i];
		
		System.out.println(dp[n]);
	}

}
