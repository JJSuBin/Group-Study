import java.util.*;

class Point_2206 {
	int x, y, count, drill;
	
	public Point_2206(int x, int y, int count, int drill) {
		this.x=x;
		this.y=y;
		this.count=count; // 이동 횟수
		this.drill=drill; // 드릴 사용 횟수
	}
}

public class BaekJoon_2206 {
	static int n,m,min=Integer.MAX_VALUE;
	static int[][] map; 
	static boolean visited[][][];
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,-1,0,1};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		map=new int[n][m];
		/*
		 * 벽을 부수고 도달하는 경로와 벽을 부수지 않고 도달하는 경로의
		 * 방문처리를 따로 해야하기 때문에 3차원 배열을 사용한다. 
		 * visited[드릴사용횟수 1or0][x][y]
		 */
		visited=new boolean[2][n][m];
		
		for(int i=0;i<n;i++) {
			String str=sc.next();
			for(int j=0;j<m;j++) {
				map[i][j]=str.charAt(j)-'0';
			}	
		}
			
		bfs(0,0);
		
		if(min==Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(min);
	}
	
	static void bfs(int x, int y) {
		Queue<Point_2206> q=new LinkedList<>();
		q.offer(new Point_2206(x,y,1,0)); // 시작 지점을 포함해서 count
		visited[0][x][y]=true;
		
		while(!q.isEmpty()) {
			Point_2206 p=q.poll();
			
			// 도착지에 도달한 경우
			if(p.x==n-1&&p.y==m-1) {
				min=p.count;
				break;
			}
			
			for(int i=0;i<4;i++) {
				int nx=p.x+dx[i];
				int ny=p.y+dy[i];
				
				// 배열 범위를 넘어간 경우
				if(nx<0||ny<0||nx>=n||ny>=m) continue;
				
				// 탐색하는 칸이 벽이 아니고
				if(map[nx][ny]==0) {
					// 아직 방문하지 않은 곳이라면
					if(!visited[p.drill][nx][ny]) {
						visited[p.drill][nx][ny]=true; // 방문처리
						q.offer(new Point_2206(nx,ny,p.count+1,p.drill));
					}
				}
				// 탐색하는 칸이 벽이고
				else {
					// 아직 벽을 뚫지 않았으며, 방문하지 않은 곳이라면
					if(p.drill==0&&!visited[1][nx][ny]) {
						visited[1][nx][ny]=true;
						q.offer(new Point_2206(nx,ny,p.count+1,p.drill+1));
					}
				}
			}
		}	
	}
}
