import java.util.*;

class Point_14940 {
	int x,y,count;
	
	public Point_14940(int x, int y) {
		this.x=x;
		this.y=y;
	}
}

public class BaekJoon_14940 {
	static int n,m;
	static StringBuilder sb=new StringBuilder();
	static int[][] map,result;
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,-1,0,1};
	static Queue<Point_14940> q=new LinkedList<>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		map=new int[n][m];
		result=new int[n][m];
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				map[i][j]=sc.nextInt();
				
				if(map[i][j]==2)  // 시작 좌표 
					q.offer(new Point_14940(i,j));
			}
		}
			
		bfs();
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(map[i][j]>0) // 시작 좌표의 초기값인 2를 빼고 출력
					sb.append(map[i][j]-2+" ");
				
				else // 갈 수 없는 위치
					sb.append(0+" ");
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
	// bfs 알고리즘으로 시작 위치에서 모든 지점까지의 거리 구하기
	static void bfs() {
		while(!q.isEmpty()) {
			Point_14940 point=q.poll();
			int x=point.x;
			int y=point.y;

			for(int i=0;i<4;i++) {
				int nx=x+dx[i];
				int ny=y+dy[i];
				
				if(nx<0||ny<0||nx>=n||ny>=m||map[nx][ny]==0)
					continue;
				
				if(map[nx][ny]==1) {
					map[nx][ny]=map[x][y]+1;
					q.offer(new Point_14940(nx,ny));
				}
			}
		}
	}
}
