import java.util.*;

class Point_16234 {
	int x;
	int y;
	
	public Point_16234(int x, int y) {
		this.x=x;
		this.y=y;
	}
}

public class BaekJoon_16234 {
	static int[][] map;
	static boolean[][] visited;
	static int n,L,R,answer=0;
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,-1,0,1};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		L=sc.nextInt();
		R=sc.nextInt();
		
		map=new int[n][n];
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
				map[i][j]=sc.nextInt();
		
		
		while(true) {
			boolean ismove=false; // 연합이 일어났는지 체크하는 변수
			visited=new boolean[n][n];
			
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					// 이미 연합에 속한 나라는 무시
					if(visited[i][j])
						continue;
					// 연합이 일어나는지 확인
					if(union(i,j))
						ismove=true;
				}
			}
			
			// 연합이 일어났다면 횟수 증가
			if(ismove==true)
				answer++;
			// 연합이 일어나지 않았다면 종료
			else {
				System.out.println(answer);
				break;
			}
		}
	}	
	
	// (x,y) 좌표의 나라를 기준으로 조건에 부합하는 나라들을 연합하는 함수 
	static boolean union(int x, int y) {
		Queue<Point_16234> q=new LinkedList<>();
		ArrayList<Point_16234> group=new ArrayList<>(); // 연합된 나라들을 저장하는 연결리스트
		
		q.offer(new Point_16234(x,y));
		group.add(new Point_16234(x,y));
		visited[x][y]=true; // visited 배열을 통해 해당 좌표에 있는 나라가 연합에 속해있는지의 여부를 알 수 있다. 
		
		int sum=map[x][y]; // 연합 국가들의 인구 총합
		// BFS 알고리즘을 사용해여 맞닿아있는 나라들을 탐색한다.
		while(!q.isEmpty()) {
			Point_16234 p=q.poll();
			
			for(int i=0;i<4;i++) {
				int nx=p.x+dx[i];
				int ny=p.y+dy[i];
				
				// 배열 범위를 초과하거나 이미 연합에 속한 나라는 무시
				if(nx<0||ny<0||nx>=n||ny>=n||visited[nx][ny]) continue;
			
				int diff=Math.abs(map[p.x][p.y]-map[nx][ny]); // 이웃한 나라와의 인구수 차이
				if(diff<L||diff>R) continue; // 연합할 수 없는 조건도 무시
				
				// 연합할 수 있는 나라라면
				sum+=map[nx][ny]; // 인수구 누적
				visited[nx][ny]=true;
				q.offer(new Point_16234(nx,ny));
				group.add(new Point_16234(nx,ny));
			}
		}
		
		// 연합할 수 있는 나라가 없는 경우
		if(group.size()==1)
			return false;
		else {
			// 각 칸의 인구수는 (연합의 인구수) / (연합을 이루고 있는 칸의 개수)
			int people=sum/group.size();
			
			for(Point_16234 p:group)
				map[p.x][p.y]=people;
			
			return true;
		}
	}
}
