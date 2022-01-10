import java.util.*;

public class BaekJoon_1520 {
	static int[][] map,dp; 
	static int n,m,result=0;
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,-1,0,1};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		
		map=new int[n][m];
		dp=new int[n][m]; // dp[i][j]에는 (i,j)칸에 도달할 수 있는 경로의 개수 저장
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				map[i][j]=sc.nextInt();
				dp[i][j]=-1; // 방문 여부를 체크하기 위해 -1로 초기화
			}	
		}
			
		System.out.println(dfs(0,0));
	}
	
	static int dfs(int x, int y) {
		if(x==n-1&&y==m-1) // 목표 지점에 도착한 경우
			return 1;
		
		if(dp[x][y]!=-1) // 메모제이션
			return dp[x][y];
		
		else { // 아직 방문하지 않은 곳이라면  
			dp[x][y]=0; // -1로 초기화 되 있기 때문에 0으로 초기화
			for(int i=0;i<4;i++) {
				int nx=x+dx[i];
				int ny=y+dy[i];
				
				if(nx<0||ny<0||nx>=n||ny>=m) 
					continue;
				
				// 내리막 길이라면 (nx,ny)를 시작으로 재탐색
				if(map[nx][ny]<map[x][y])
					dp[x][y]+=dfs(nx,ny);
			}
		}
		return dp[x][y];
	}
}
