import java.util.*;

public class BaekJoon_14888 {
	static int[] num;
	static int[] operation=new int[4];
	static int n;
	static int min=Integer.MAX_VALUE, max=Integer.MIN_VALUE;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		
		num=new int[n];
		for(int i=0;i<n;i++)
			num[i]=sc.nextInt();
		
		for(int i=0;i<4;i++)
			operation[i]=sc.nextInt();
		
		dfs(1,num[0]);
		System.out.println(max);
		System.out.println(min);
	}
	
	static void dfs(int depth, int result) {
		if(depth==n) { // 최댓값, 최솟값 갱신
			min=Math.min(min, result);
			max=Math.max(max, result);
			return;
		}
		
		for(int i=0;i<4;i++) {
			if(operation[i]>0) { // 연산자의 횟수가 0 이상이면 
				operation[i]--; // 횟수 감소
				
				// 연산자에 맞는 계산 값으로 재귀호출
				switch(i) {
				case 0:
					dfs(depth+1,result+num[depth]);
					break;
				case 1:
					dfs(depth+1,result-num[depth]);
					break;
				case 2:
					dfs(depth+1,result*num[depth]);
					break;
				case 3:
					dfs(depth+1,result/num[depth]);
					break;
				}
				
				operation[i]++; // 횟수 복구
			}
		}
	}
}
