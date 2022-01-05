import java.util.*;

class Point_15683 {
	int num,x,y;
	
	public Point_15683(int num, int x, int y) {
		this.num=num;
		this.x=x;
		this.y=y;
	}
}

public class BaekJoon_15683 {
	static ArrayList<Point_15683> CCTV=new ArrayList<>();
	static int n,m,result=Integer.MAX_VALUE;
	static int[][] map, temp;
	static int[] direction;
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,1,0,-1};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		
		map=new int[n][m];
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				map[i][j]=sc.nextInt();
				
				// CCTV의 번호, 위치를 저장
				if(map[i][j]!=0&&map[i][j]!=6)
					CCTV.add(new Point_15683(map[i][j],i,j));
			}
		}
		
		direction=new int[CCTV.size()];
		set_direction(0);
		
		System.out.println(result);
	}
	
	static void set_direction(int depth) {
		if(depth==CCTV.size()) { // 모든 CCTV의 방향을 세팅했다면
			temp=new int[n][m];
			
			for(int i=0;i<n;i++) 
				for(int j=0;j<m;j++)
					temp[i][j]=map[i][j];
			
			// 해당 방향으로 CCTV 작동
			for(int i=0;i<CCTV.size();i++)
				operate_CCTV(CCTV.get(i),direction[i]);
			
			count_blind();
			return;
		}
		
		// 0:상, 1:우, 2:하, 3:좌 중 방향 세팅
		for(int i=0;i<4;i++) {
			direction[depth]=i;
			set_direction(depth+1);
		}
	}
	
	static void operate_CCTV(Point_15683 cctv, int dir) {
		int number=cctv.num;
		
		if(number==1) {
			if(dir==0) watch_CCTV(cctv,0); // 상
			else if(dir==1) watch_CCTV(cctv,1); // 우
			else if(dir==2) watch_CCTV(cctv,2); // 하
			else watch_CCTV(cctv,3); // 좌
		}
		
		else if(number==2) {
			if(dir==0||dir==2) { // 상하
				watch_CCTV(cctv,0);
				watch_CCTV(cctv,2);
			}
			else { // 좌우
				watch_CCTV(cctv,1);
				watch_CCTV(cctv,3);
			}
		}
		
		else if(number==3) {
			if(dir==0) { // 상우
				watch_CCTV(cctv,0);
				watch_CCTV(cctv,1);
			}
			else if(dir==1) { // 우하
				watch_CCTV(cctv,1);
				watch_CCTV(cctv,2);
			}
			else if(dir==2) { // 하좌
				watch_CCTV(cctv,2);
				watch_CCTV(cctv,3);
			} 
			else { // 좌상
				watch_CCTV(cctv,3);
				watch_CCTV(cctv,0);
			}
		}
		
		else if(number==4) {
			if(dir==0) { // 좌상우
				watch_CCTV(cctv,3);
				watch_CCTV(cctv,0);
				watch_CCTV(cctv,1);
			}
			else if(dir==1) { // 상우하
				watch_CCTV(cctv,0);
				watch_CCTV(cctv,1);
				watch_CCTV(cctv,2);
			}
			else if(dir==2) { // 좌하우
				watch_CCTV(cctv,1);
				watch_CCTV(cctv,2);
				watch_CCTV(cctv,3);
			}
			else { // 상좌하
				watch_CCTV(cctv,2);
				watch_CCTV(cctv,3);
				watch_CCTV(cctv,0);
			}
		}
		
		else { // 언제나 상하좌우
			watch_CCTV(cctv,0);
			watch_CCTV(cctv,1);
			watch_CCTV(cctv,2);
			watch_CCTV(cctv,3);
		}
	}
	
	static void watch_CCTV(Point_15683 cctv, int dir) {
		Queue<Point_15683> q=new LinkedList<>();
		q.add(cctv);
		
		while(!q.isEmpty()) {
			Point_15683 p=q.poll();
			
			int nx=p.x+dx[dir];
			int ny=p.y+dy[dir];
			
			// 맵을 벗어났거나 벽을 만났다면 바로 종료
			if(nx<0||ny<0||nx>=n||ny>=m||temp[nx][ny]==6) 
				break;
			
			// 감시 가능한 공간이라면 배열 값 -1로 변경
			if(temp[nx][ny]==0) {
				temp[nx][ny]=-1;
				q.add(new Point_15683(cctv.num,nx,ny));
			}
			// CCTV가 있는 칸이라면 통과할 수 있으므로 큐에 삽입
			else
				q.add(new Point_15683(cctv.num,nx,ny));
		}
	}
	
	// 사각지대의 개수를 count하는 함수
	static void count_blind() {
		int count=0;
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++)
				if(temp[i][j]==0)
					count++;
			
		result=Math.min(result, count);
	}

}
