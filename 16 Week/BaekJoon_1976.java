import java.util.*;

public class BaekJoon_1976 {
	static int[][] graph;
	static int n,m;
	static final int INF=(int)1e9;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt(); // 도시의 수 
		m=sc.nextInt(); // 여행 계획에 속한 도시의 수
		
		graph=new int[n+1][n+1];
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				graph[i][j]=sc.nextInt();
				
				// 출발점과 도착점이 같은 경우도 여행 가능
				if(i==j)
					graph[i][j]=1;
			}
		}
		
		// 플로이드 와샬
		for(int k=1;k<=n;k++)
			for(int i=1;i<=n;i++) 
				for(int j=1;j<=n;j++)
					if(graph[i][k]==1&&graph[k][j]==1)
						graph[i][j]=1;
		
		// 여행 경로가 모두 이어져있는지 확인
		int start=sc.nextInt();
		int next;
		for(int i=0;i<m-1;i++) {
			next=sc.nextInt();
			
			// 여행 경로가 이어져 있지 않은 경우 NO 출력
			if(graph[start][next]==0) {
				System.out.println("NO");
				return;
			}
			
			start=next;
		}
		
		System.out.println("YES");
	}
}
