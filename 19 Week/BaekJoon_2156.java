package Coding_Study;

import java.util.*;

public class BaekJoon_2156 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		
		int[] wine=new int[n+1];
		int[] dp=new int[n+1];
		
		for(int i=1;i<=n;i++)
			wine[i]=sc.nextInt();
		
		dp[0]=0;
		dp[1]=wine[1];
		
		if(n>=2)
			dp[2]=wine[1]+wine[2];
		
		/*
		 * 와인잔을 선택하는 경우는 크게 i번째 와인잔을 선택할 것이냐 아니냐로 나눌 수 있다.
		 * i번째 와인잔을 선택하는 경우 두 칸 이전의 와인잔을 선택하는 경우와 
		 * 세칸 이전의 와인잔, 한칸 이전의 와인잔을 선택하는 경우로 나눌 수 있다.
		 * 총 3가지의 경우중 합이 가장 큰 경우를 찾아 dp에 저장한다.  
		 */
		for(int i=3;i<=n;i++)
			dp[i]=Math.max(Math.max(dp[i-2],dp[i-3]+wine[i-1])+wine[i],dp[i-1]);
		
		System.out.println(dp[n]);
	}

}
