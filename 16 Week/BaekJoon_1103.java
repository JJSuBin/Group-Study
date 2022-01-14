import java.util.*;

public class BaekJoon_1103 {
	static int n,m,max=Integer.MIN_VALUE;
	static boolean isCycle=false; // 싸이클이 있는치 체크하는 변수(처음에는 싸이클이 업다고 설정)
	static int[][] map,dp;
	static boolean[][] visited;
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,-1,0,1};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		
		map=new int[n][m];
		dp=new int[n][m]; // 해당 좌표에 도달하기까지 동전이 이동한 최대 횟수
		visited=new boolean[n][m];
		
		for(int i=0;i<n;i++) {
			String str=sc.next();
			for(int j=0;j<m;j++) {
				int num=str.charAt(j);
				
				if(num=='H') // 구멍은 맵 값을 10으로 변경
					map[i][j]=10;
				else
					map[i][j]=num-48;
			}
		}
		
		visited[0][0]=true; // 시작 지점 방문 처리
		dfs(0,0,1);
		
		if(isCycle) // 싸이클이 있는 경우
			System.out.println(-1);
		else
			System.out.println(max);
	}
	
	static void dfs(int x, int y, int count) {
		if(count>max) // max 값 갱신
			max=count;
		
		dp[x][y]=count;
		for(int i=0;i<4;i++) {
			int num=map[x][y];
			int nx=x+dx[i]*num;
			int ny=y+dy[i]*num;
			
			if(nx<0||ny<0||nx>=n||ny>=m||map[nx][ny]==10)
				continue;
			
			/*
			 * 이미 방문한 곳을 재방문 한 경우는 싸이클이 생긴 경우
			 * 싸이클이 생긴다면 무한 loop에 빠지게 되기 때문에 처리를 해주어야 한다.
			 */
			if(visited[nx][ny]) {
				isCycle=true;
				return;
			}
			
			/*
			 * 같은 좌표라도 더 많은 count로 방문 기록을 남겨
			 * 더 적은 count로 해당 좌표를 방문하면 가지치기를 하지 않도록 넘어간다.
			 * 이렇게 dp 배열로 경우의 수를 줄여야 시간초과 없이 정답 처리
			 */
			if(dp[nx][ny]>count)
				continue;
			
			visited[nx][ny]=true;
			dfs(nx,ny,count+1);
			visited[nx][ny]=false;
		}
	}
}
