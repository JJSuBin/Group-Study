import java.util.*;

class Point_2638 {
	int x,y;
	
	public Point_2638(int x, int y) {
		this.x=x;
		this.y=y;
	}
}

public class BaekJoon_2638 {
	static int[][] map;
	static boolean[][] visited;
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,-1,0,1};
	static int n,m,time=0,totalcheese=0;
	static ArrayList<Point_2638> cheese=new ArrayList<>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		
		map=new int[n][m];
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				map[i][j]=sc.nextInt();
				
				if(map[i][j]==1) {
					totalcheese++; // 전체 치즈의 수 count
					cheese.add(new Point_2638(i,j)); // 초기의 치즈 정보 연결리스트에 저장
				}
			}
		}
		
		// 치즈의 수가 0개가 될때까지 시간 측정
		while(totalcheese!=0) {
			time++;
			visited=new boolean[n][m];
			outside_air(); // 외부공기 표시
			melting_cheese(); // 치즈 녹이기
		}
		
		System.out.println(time);
	}
	
	// 치즈 녹이는 함수
	static void melting_cheese() {
		for(int i=0;i<cheese.size();i++) {
			Point_2638 p=cheese.get(i);
			int count=0;
			
			// 상,하,좌,우에 외부공기가 있는지 탐색
			for(int d=0;d<4;d++) {
				int nx=p.x+dx[d];
				int ny=p.y+dy[d];
				
				if(nx<0||ny<0||nx>=n||ny>=m) continue; // 범위 초과
				
				if(map[nx][ny]==2)
					count++; // 외부공기 count
			}
			
			if(count>=2) { // 치즈 주변에 외부공기와 맞닿아 있는 변이 2개 이상이라면
				map[p.x][p.y]=0; // 치즈 없애기
				totalcheese--; 
				cheese.remove(i);
				i--; // 연결리스트에서 해당 치즈를 삭제하면 i를 하나 감소시켜야 빠짐없이 검사 가능(밀리지 않게)
			}
		}
	}
	
	/*
	 * 외부공기인지 체크하는 함수
	 * 가장자리인 (0,0) 부터 외부공기인지 체크를 시작하다 치즈가 있는 곳은 큐에 삽입하지 않게되면
	 * 치즈를 기준으로 상,하,좌,우는 탐색하지 않게된다. 이에 따라 치즈 내부는 자연스럽게 탐색하지 않게 되면서
	 * 치즈의 내부에 있는 공기는 고려대상이 되지 않는다. 
	 * 해당 과정을 거치면 최종적으로 치즈 외부에 있는 공기만 큐에 삽입되게 된다. 
	 */
	static void outside_air() {
		Queue<Point_2638> q=new LinkedList<>(); // 큐에는 외부공기의 위치가 저장된다.
		q.offer(new Point_2638(0,0));
		visited[0][0]=true; // (0,0)은 치즈가 반드시 없기 때문에 해당 위치에서 시작
		map[0][0]=2; // 외부공기는 2로 표시
		
		while(!q.isEmpty()) {
			Point_2638 p=q.poll();
			
			// 상,하,좌,우 탐색
			for(int i=0;i<4;i++) {
				int nx=p.x+dx[i];
				int ny=p.y+dy[i];
				
				if(nx<0||ny<0||nx>=n||ny>=m) continue; // 범위 초과
				
				// 이미 방문했거나 치즈인 경우도 넘어간다.
				if(visited[nx][ny]||map[nx][ny]==1) continue; 
				
				map[nx][ny]=2; // 외부공기 표시
				visited[nx][ny]=true;
				q.offer(new Point_2638(nx,ny));
			}
		}
	}
}
