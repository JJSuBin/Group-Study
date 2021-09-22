import java.util.*;

class Point_2178 {
	int x, y;
	
	public Point_2178(int x, int y) {
		this.x=x;
		this.y=y;
	}
}

public class BaekJoon_2178 {
	static int[][] map;
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,-1,0,1};
	static int n,m;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		
		map=new int[n][m];
		
		// 맵 입력
		for(int i=0;i<n;i++) {
			String str=sc.next();
			for(int j=0;j<m;j++) {
				map[i][j]=str.charAt(j)-'0';
			}
		}
		
		bfs(0,0);
		
		System.out.println(map[n-1][m-1]);
	}
	
	// (0,0) 부터 (n-1,m-1) 까지 길 탐색
	static void bfs(int x, int y) {
		Queue<Point_2178> q=new LinkedList<>();
		q.offer(new Point_2178(x,y));
		
		while(!q.isEmpty()) {
			Point_2178 p=q.poll();
			x=p.x;
			y=p.y;
			
			// 상,하,좌,우 탐색
			for(int i=0;i<4;i++) {
				// 이동 좌표 값 계산
				int nx=x+dx[i];
				int ny=y+dy[i];
				
				// 배열 볌위 밖은 넘어간다.
				if(nx<0||ny<0||nx>=n||ny>=m)
					continue;
				
				// 벽인 경우도 넘어간다.
				if(map[nx][ny]==0)
					continue;
				
				// 이동한 좌표가 배열 범위 내에 있으면서, 벽이 아니라면 이동
				if(map[nx][ny]==1) {
					map[nx][ny]=map[x][y]+1; // 이동 횟수 갱신(이전 이동횟수+1)
					q.offer(new Point_2178(nx,ny));
				}
			}
		}
	}
}
