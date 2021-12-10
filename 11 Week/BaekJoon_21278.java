import java.util.*;

public class BaekJoon_21278 {
	static int n,m;
	static int[][] graph;
	static final int INF=(int)1e9;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		
		graph=new int[n+1][n+1];
		
		// 초기화
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				if(i==j) continue;
				
				graph[i][j]=INF;
			}
		}
		
		for(int i=0;i<m;i++) {
			int a=sc.nextInt();
			int b=sc.nextInt();
			
			graph[a][b]=graph[b][a]=1; // 양방향
		}
		
		// 플로이드 와샬
		for(int k=1;k<=n;k++)
			for(int i=1;i<=n;i++)
				for(int j=1;j<=n;j++)
					graph[i][j]=Math.min(graph[i][j],graph[i][k]+graph[k][j]);
		
		int chicken1=0;
		int chicken2=0;
		int distance=Integer.MAX_VALUE;
		
		for(int i=1;i<=n;i++) {
			for(int j=i+1;j<=n;j++) {
				int dis=distance(i,j); // 두 개의 치킨집 선택해 거리 구하기
				
				// 거리의 최소를 구했다면 두 치킨집의 위치, 최단거리 갱신
				if(dis<distance) {
					chicken1=i;
					chicken2=j;
					distance=dis;
				}
			}
		}
		// 왕복 시간의 합을 출력해야 하기 때문에 *2 해주어야 함!
		System.out.println(chicken1+" "+chicken2+" "+distance*2);
	}
	
	static int distance(int x, int y) {
		int result=0;
		
		// 두 치킨집 중 더 가까운 곳을 누적해주기
		for(int i=1;i<=n;i++)
			result+=Math.min(graph[x][i],graph[y][i]);
		
		return result;
	}
}
