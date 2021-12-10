import java.util.*;

class Point_2573 {
	int x,y;
	
	public Point_2573(int x, int y) {
		this.x=x;
		this.y=y;
	}
}

public class BaekJoon_2573 {
	static int n,m,time=0;
	static int[][] map;
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,-1,0,1};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		
		map=new int[n][m];
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++)
				map[i][j]=sc.nextInt();
		
		while(seperate()<2) { // 빙하가 두 덩이 이하로 나올때만 진행
			if(seperate()==0) { // 빙하가 한번에 녹아 없어진 경우
				time=0;
				break;
			}
			
			bfs();
			time++;
		}
		
		System.out.println(time);
	}
	
	// 빙하가 총 몇개의 덩어리로 분리됐는지 count하는 함수
	static int seperate() {
		int count=0;
		boolean[][] visited=new boolean[n][m];
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(map[i][j]!=0&&!visited[i][j]) {
					dfs(i,j,visited);
					count++;
				}
			}
		}
		
		return count;
	}
	
	// 연결된 빙하들을 방문처리 해주는 함수
	static void dfs(int x, int y, boolean[][] visited) {
		visited[x][y]=true;
	
		for(int i=0;i<4;i++) {
			int nx=x+dx[i];
			int ny=y+dy[i];
			
			if(nx>=0&&ny>=0&&nx<n&&ny<m) 
				if(!visited[nx][ny]&&map[nx][ny]!=0) 
					dfs(nx,ny,visited);
		}
	}
	
	static void bfs() {
		Queue<Point_2573> q=new LinkedList<>();
		boolean[][] visited=new boolean[n][m];
		
		// 녹은 빙하는 다른 빙하에 영향을 주면 안되기 때문에 초기의 빙하를 방문처리 하여 구별해준다.
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(map[i][j]!=0) {
					visited[i][j]=true;
					q.offer(new Point_2573(i,j));
				}
			}
		}
		
		while(!q.isEmpty()) {
			Point_2573 p=q.poll();
			
			int sea=0; // 맞닿은 바다의 개수
			
			for(int i=0;i<4;i++) {
				int nx=p.x+dx[i];
				int ny=p.y+dy[i];
				
				if(nx>=n||ny>=m||nx<0||ny<0) // 맵을 벗어난 경우
					continue;
				
				if(map[nx][ny]==0&&!visited[nx][ny]) // visited가 false이어야 처음부터 바다였던 곳
					sea++;
			}
			
			if(map[p.x][p.y]-sea<0) // 모두 녹아 없어진 경우
				map[p.x][p.y]=0;
			else 
				map[p.x][p.y]-=sea;
		}
	}
	
}
