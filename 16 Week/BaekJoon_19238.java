import java.util.*;

class Taxi implements Comparable<Taxi>{
	int x,y,move;
	
	public Taxi(int x, int y, int move) {
		this.x=x;
		this.y=y;
		this.move=move;
	}

	@Override
	public int compareTo(Taxi o) {
		// TODO Auto-generated method stub
		if(this.move==o.move) { // 이동거리
			if(this.x==o.x) // 행
				return this.y-o.y;
			else // 열 
				return this.x-o.x;
		}
		
		return this.move-o.move;
	}
}

class Passenger {
	int num,sx,sy,ex,ey;
	
	public Passenger(int num, int sx, int sy, int ex, int ey) {
		this.num=num;
		this.sx=sx;
		this.sy=sy;
		this.ex=ex;
		this.ey=ey;
	}
}
public class BaekJoon_19238 {
	static int n,m,fuel;
	static int[][] map;
	static Taxi taxi;
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,-1,0,1};
	static Passenger[] passenger;
	static Queue<Integer>[][] passengermap;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		fuel=sc.nextInt();
		
		map=new int[n+1][n+1];
		passenger=new Passenger[m+1];
		passengermap=new LinkedList[n+1][n+1];
		
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				passengermap[i][j]=new LinkedList<>();
				map[i][j]=sc.nextInt();
				
				// 벽이 있는 곳은 -1로 변경(승객 넘버링을 1부터 하기 위함)ㄴ
				if(map[i][j]==1)
					map[i][j]=-1;
			}
		}
		
		// 처음 택시의 시작 지점
		int x=sc.nextInt();
		int y=sc.nextInt();
		taxi=new Taxi(x,y,0);
		
		for(int i=1;i<=m;i++) {
			int sx=sc.nextInt();
			int sy=sc.nextInt();
			int ex=sc.nextInt();
			int ey=sc.nextInt();
			
			passenger[i]=new Passenger(i,sx,sy,ex,ey); // 승객 정보 저장
			passengermap[sx][sy].add(i); // 승객들 맵에 승객의 번호 저장
		}
		
		for(int i=0;i<m;i++) {
			if(!find_Passenger()) { // 태울 승객이 없는 경우
				System.out.println(-1);
				return;
			}
			
			/*
			 * find_Passenger 결과로 taxi에는 탑승한 승객의 출발 위치가 
			 * taxi.move에는 해당 승객을 태우기까지 택시가 이동한 칸수가 저장되어 있다.
			 */
			int Time=taxi.move; 
			int index=passengermap[taxi.x][taxi.y].poll();
			
			// 목적지에 도달할 수 없다면 -1 출력
			if(!go_Destination(passenger[index].ex,passenger[index].ey)) {
				System.out.println(-1);
				return;
			}
			
			/*
			 * find_Passenger() -> go_Destination() 결과로 taxi.move에는
			 * 승객을 태우기까지 택시가 이동한 칸수 + 승객이 목적지에 도착할때 까지 택시가 이동한 횟수가 저장되어 있다.
			 * 전체 연료에서 해당 값 빼주기
			 */
			fuel-=taxi.move; 
			
			// 모든 승객을 처리하기 전 연료가 떨어지면 -1 출력
			if(fuel<0) {
				System.out.println(-1);
				return;
			}
			else { // 연료가 남아있는 상태에서 목적지에 도착했다면 연료 채우기
				fuel+=(2*(taxi.move-Time));
				taxi.move=0;
			}
		}
		
		System.out.println(fuel);
		return;
	}
	
	// 현재 택시 위치에서 조건에 알맞는 승객 태우기
	static boolean find_Passenger() {
		Queue<Taxi> q=new LinkedList<>();
		ArrayList<Taxi> candidate=new ArrayList<>(); // 택시에 태울 승객 후보가 저장될 연결리스트
		boolean[][] visited=new boolean[n+1][n+1];
		
		q.offer(taxi);
		visited[taxi.x][taxi.y]=true;
		
		while(!q.isEmpty()) {
			Taxi now=q.poll();
			
			// 택시에 탑승할 승객 후보가 있고, 먼저 태운 승객보다 현재 승객의 이동 횟수가 크다면 넘어간다.
			if(!candidate.isEmpty()&&candidate.get(0).move<now.move)
				continue;
			// 이동횟수가 더 적고 now 위치에 승객이 있다면 후보군에 삽입
			if(!passengermap[now.x][now.y].isEmpty()) {
				candidate.add(now);
				continue;
			}
			
			for(int i=0;i<4;i++) {
				int nx=now.x+dx[i];
				int ny=now.y+dy[i];
				
				// 맵을 벗어났거나, 이미 방문한 칸이거나, 벽인 경우 넘어간다.
				if(nx<1||ny<1||nx>n||ny>n||visited[nx][ny]||map[nx][ny]==-1)
					continue;
				
				visited[nx][ny]=true;
				q.offer(new Taxi(nx,ny,now.move+1)); // 이동거리 +1
			}
		}
		
		// 연결리스트가 빈 경우는 태울 수 있는 승객이 없는 경우
		if(candidate.isEmpty())
			return false;
		
		Collections.sort(candidate);
		
		taxi=candidate.get(0); // 연결리스트의 맨 앞에있는 승객 택시에 탑승
		
		return true;
	}
	
	// 태운 승객 목적지까지 최단거리로 이동
	static boolean go_Destination(int ex, int ey) {
		Queue<Taxi> q=new LinkedList<>();
		q.offer(taxi);
		boolean[][] visited=new boolean[n+1][n+1];
		visited[taxi.x][taxi.y]=true;
		
		while(!q.isEmpty()) {
			Taxi now=q.poll();
			
			// 목적지에 도착했다면
			if(now.x==ex&&now.y==ey) {
				taxi=now; // 택시의 위치를 갱신해주고 true 반환
				return true;
			}
			
			for(int i=0;i<4;i++) {
				int nx=now.x+dx[i];
				int ny=now.y+dy[i];
				
				// 맵을 벗어났거나, 이미 방문한 칸이거나, 벽인 경우 넘어간다.
				if(nx<1||ny<1||nx>n||ny>n||visited[nx][ny]||map[nx][ny]==-1)
					continue;
				
				visited[nx][ny]=true;
				q.offer(new Taxi(nx,ny,now.move+1)); // 이동거리 +1
			}
		}
		
		return false;
	}
}
