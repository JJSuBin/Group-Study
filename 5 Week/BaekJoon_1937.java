import java.util.*;

public class BaekJoon_1937 {
	static int n,max=0;
	static int[][] map,dp;
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,-1,0,1};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		// dp[i][j]에는 해당 칸에서 출발하여 판다가 방문할 수 있는 최대 칸수 저장
		dp=new int[n][n];
		map=new int[n][n];
		
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
				map[i][j]=sc.nextInt();
		
		// 모든 지점을 시작점으로 바꿔가며 최대 방문 칸수 구하기
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
				max=Math.max(max, dfs(i,j));
		
		System.out.println(max);
	}
	
	static int dfs(int x, int y) {
		if(dp[x][y]!=0) // dp에 저장된 값이 있다면 반환
			return dp[x][y];
		
		dp[x][y]=1; // 현재 칸수를 포함해야하므로 1로 초기화
		for(int i=0;i<4;i++) {
			int nx=x+dx[i];
			int ny=y+dy[i];
			
			// 이동 위치가 배열 내에 있으며
			if(nx>=0&&nx<n&&ny>=0&&ny<n) {
				// 이동 위치의 대나무 양이 현재 위치의 대나무 양보다 많으면 이동 가능
				if(map[nx][ny]>map[x][y]) {
					/*
					 * dp 값은 현재 칸의 값과 상,하,좌,우 중 방문할 수 있는 
					 * 칸 수가 가장 많은 값 + 1(x,y 칸으로 이동) 중 큰 값을 선택한다. 
					 */
					dp[x][y]=Math.max(dp[x][y],dfs(nx,ny)+1);
				}
			}
		}
		
		return dp[x][y];
	}
}
