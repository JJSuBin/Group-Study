import java.util.*;

class Point_17836 {
	int x,y,count;
	boolean isGram; // 그람의 획득 여부를 체크하는 변수
	
	public Point_17836(int x, int y, int count, boolean isGram) {
		this.x=x;
		this.y=y;
		this.count=count;
		this.isGram=isGram;
	}
}

public class BaekJoon_17836 {
	static int[][] map;
	static boolean[][][] visited;
	static int n,m,t;
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,-1,0,1};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		t=sc.nextInt();
		
		map=new int[n][m];
		/*
		 * 그람이 있을 떄와 없을 때를 나눠 방문처리를 해주어야 하기 때문에 3차원의 배열을 사용한다.
		 * visited[n][m][0] : 그람이 없을 경우 
		 * visited[n][m][0] : 그람이 있을 경우
		 */
		visited=new boolean[n][m][2];
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++)
				map[i][j]=sc.nextInt();
		
		int result=bfs(0,0);
		if(result==-1)
			System.out.println("Fail");
		else 
			System.out.println(result);
	}
	
	static int bfs(int x, int y) {
		Queue<Point_17836> q=new LinkedList<>();
		q.offer(new Point_17836(x,y,0,false)); // (0,0)에서 그람 없이 시작
		visited[x][y][0]=true;
		
		while(!q.isEmpty()) {
			Point_17836 now=q.poll();
			
			if(now.count>t) // 공주를 구할수 없는 경우 : t시간이 지남 
				break;
			
			if(now.x==n-1&&now.y==m-1) // 공주가 있는 칸에 도달한 경우
				return now.count;
			
			for(int i=0;i<4;i++) {
				int nx=now.x+dx[i];
				int ny=now.y+dy[i];
				
				if(nx>=0&&ny>=0&&nx<n&&ny<m) {
					if(!now.isGram) { // 그람을 획득하지 않은 경우 -> 방문한 곳과 벽이 있는 곳은 갈 수 없음
						if(!visited[nx][ny][0]&&map[nx][ny]==0) {
							visited[nx][ny][0]=true;
							q.offer(new Point_17836(nx,ny,now.count+1,false));
						}
						// 상,하,좌,우를 탐색하다 그람을 획득한 경우
						else if(!visited[nx][ny][0]&&map[nx][ny]==2) {
							visited[nx][ny][0]=true;
							q.offer(new Point_17836(nx,ny,now.count+1,true)); // isGram 변수를 true로 변겅
						}
						
					}
					else { // 그람을 획득한 경우 -> 벽 상관없이 방문 여부만 체크하면 됨
						if(!visited[nx][ny][1]) {
							visited[nx][ny][1]=true;
							q.offer(new Point_17836(nx,ny,now.count+1,true));
						}
					}
				}
			}
		}
		return -1;
	}
}
