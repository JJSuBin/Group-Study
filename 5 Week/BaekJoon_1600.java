import java.util.*;

class Point_1600 {
	int x, y, count, k;
	
	public Point_1600(int x, int y, int count, int k) {
		this.x=x;
		this.y=y;
		this.count=count; // 이동 횟수
		this.k=k; // 말처럼 움직일 수 있는 횟수
	}
}

public class BaekJoon_1600 {
	// 말이 이동할 수 있는 경우의 수
	static int[] hx= {-2,-1,2,1,2,1,-2,-1};
	static int[] hy= {1,2,1,2,-1,-2,-1,-2};
	// 원숭이가 이동할 수 있는 경우의 수
	static int[] mx= {-1,0,1,0};
	static int[] my= {0,-1,0,1};
	static int k,h,w,min=Integer.MAX_VALUE;
	static int[][] map;
	/*
	 * visited 배열을 단순히 2차원으로 선언하면 말과 원숭이의 서로 다른 이동 경로를 
	 * 구분할 수 없기 때문에 3차운 배열로 선언하여 체크해주어야 한다.
	 * 즉, 서로 다른 경로로 이동했을때 방문처리를 따로 해주기 위해 3차원 배열 사용(visited[x][y][k번 이동한 횟수])
	 */
	static boolean[][][] visited; 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		k=sc.nextInt();
		w=sc.nextInt();
		h=sc.nextInt();
		map=new int[h][w];
		visited=new boolean[h][w][k+1];
		
		for(int i=0;i<h;i++)
			for(int j=0;j<w;j++)
				map[i][j]=sc.nextInt();
		
		bfs(0,0);
		if(min==Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(min);
	}
	
	static void bfs(int x, int y) {
		Queue<Point_1600> q=new LinkedList<>();
		q.offer(new Point_1600(x,y,0,k));
		visited[x][y][k]=true;
		
		while(!q.isEmpty()) {
			Point_1600 p=q.poll();
			
			// 목적지에 도착했다면 최소 이동횟수 갱신
			if(p.x==h-1&&p.y==w-1)
				min=Math.min(min,p.count);
			
			// 원숭이가 이동할 수 있는 경우의 수 탐색
			for(int i=0;i<4;i++) {
				int nx=p.x+mx[i];
				int ny=p.y+my[i];
				
				// 배열 범위 내에 있고, 아직 방문하지 않았으며, 벽이 있는 칸이 아니라면 이동 가능
				if(nx>=0&&nx<h&&ny>=0&&ny<w&&!visited[nx][ny][p.k]&&map[nx][ny]==0) {
					visited[nx][ny][p.k]=true; // 방문처리
					// 이동 횟수 증가, 말처럼 이동하지 않았기 때문에 k는 그대로
					q.offer(new Point_1600(nx,ny,p.count+1,p.k)); 
				}
			}
			
			// 원숭이가 말처럼 이동할 수 있는 횟수가 아직 남았다면
			if(p.k>0) {
				// 말이 이동할 수 있는 경우의 수 탐색
				for(int i=0;i<8;i++) {
					int nx=p.x+hx[i];
					int ny=p.y+hy[i];
					
					/*
					 * 배열 범위 내에 있고, 아직 방문하지 않았으며, 벽이 있는 칸이 아니라면 이동 가능
					 * 이때 원숭이가 말처럼 이동했기 때문에 k를 1 감소해서 방문 여부 검사, 방문 처리, 큐에 위치 삽입 해야한다.
					 */			
					if(nx>=0&&nx<h&&ny>=0&&ny<w&&!visited[nx][ny][p.k-1]&&map[nx][ny]==0) {
						visited[nx][ny][p.k-1]=true; 
						q.offer(new Point_1600(nx,ny,p.count+1,p.k-1));
					}
				}
			}
		}
	}
}
