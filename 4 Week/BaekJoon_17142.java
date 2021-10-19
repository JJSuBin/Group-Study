import java.util.*;

class virus {
	int x, y, time;
	
	public virus(int x, int y, int time) {
		this.x=x;
		this.y=y;
		this.time=time;
	}
}

public class BaekJoon_17142 {
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,-1,0,1};
	static int[][] map;
	static ArrayList<virus> arr=new ArrayList<>(); // 초기 바이러스의 위치를 저장한 리스트
	static virus[] active;
	static int n,m,time=Integer.MAX_VALUE,originempty=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		
		map=new int[n][n];
		active=new virus[m];
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				map[i][j]=sc.nextInt();
				
				if(map[i][j]==0) // 빈 공간 갯수 count
					originempty++; 
				else if(map[i][j]==2) // 바이러스 위치 저장(시간은 0으로 초기화)
					arr.add(new virus(i,j,0));
			}
		}
		
		// 안전지역의 갯수가 0개라면 종료
		if(originempty==0)
			System.out.println(0);
		else {
			dfs(0,0);
			// 소요시간이 갱신이 되지 않았다면 바이러스를 모든 칸에 퍼뜨릴수 없는 경우
			System.out.println(time==Integer.MAX_VALUE?-1:time);
		}
	}
	
	// 바이러스 전파 함수
	static void spreadvirus(int emptyspace) {
		Queue<virus> q=new LinkedList<>();
		boolean[][] visited=new boolean[n][n]; 
		
		// 활성화된 m개의 바이러스 방문처리&큐에 삽입
		for(int i=0;i<m;i++) {
			virus v=active[i];
			visited[v.x][v.y]=true;
			q.add(v);
		}
		
		while(!q.isEmpty()) {
			virus v=q.poll();
			
			// 상,하,좌,우 탐색
			for(int i=0;i<4;i++) {
				int nx=v.x+dx[i];
				int ny=v.y+dy[i];
				
				// 배열 범위를 넘어가거나 or 이미 전파가 된 칸이거나 or 벽인 경우는 넘어간다.
				if(nx<0||ny<0||nx>=n||ny>=n||visited[nx][ny]||map[nx][ny]==1) 
					continue;
				
				// 안전지역인 경우 안전지역의 갯수 감소
				if(map[nx][ny]==0)
					emptyspace--;
				
				// 안전지역의 갯수가 0이 된 경우는 소요시간 갱신
				if(emptyspace==0) {
					time=Math.min(time,v.time+1);
					return;
				}
				
				visited[nx][ny]=true; // 방문처리
				q.add(new virus(nx,ny,v.time+1)); // 소요시간을 늘려 큐에 삽입
			}
		}
	}
	
	// m개의 바이러스를 활성화 하는 함수
	static void dfs(int depth, int start) {
		// m개의 바이러스를 활성화 했다면 바이러스 전파
		if(depth==m) {
			spreadvirus(originempty);
			return;
		}
		
		// 연걸리스트에 있는 초기 바이러스 중 중복없이 m개 선택
		for(int i=start;i<arr.size();i++) {
			active[depth]=arr.get(i);
			dfs(depth+1,i+1);
		}
	}
}
