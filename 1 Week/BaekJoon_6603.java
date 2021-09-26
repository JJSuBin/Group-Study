import java.util.*;

public class BaekJoon_6603 {
	static int[] num;
	static boolean[] visited;
	static int k;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		
		while(true) {
			k=sc.nextInt();
			
			if(k==0)
				break;
			
			num=new int[k];
			visited=new boolean[k];
			for(int i=0;i<k;i++)
				num[i]=sc.nextInt();
			
			dfs(0,0);
			System.out.println(); // 한 테스트케이스 마다 한 줄 띄기
		}
	}
	
	static void dfs(int depth, int start) {
		if(depth==6) {
			for(int i=0;i<k;i++)
				// k개의 수들 중 선택된 수들만 출력
				if(visited[i]==true)
					System.out.print(num[i]+" ");
			System.out.println();
			return;
		}
		
		// k개의 숫자 중 6개를 중복없이 선택하는 경우의 수 구하기
		for(int i=start;i<k;i++) {
			visited[i]=true;
			dfs(depth+1,i+1);
			visited[i]=false;
		}
	}
}
