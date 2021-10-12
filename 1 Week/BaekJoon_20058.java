import java.util.*;

class Point_20058 {
	int x,y;
	
	public Point_20058(int x, int y) {
		this.x=x;
		this.y=y;
	}
}

public class BaekJoon_20058 {
	static int[][] map;
	static boolean[][] visited;
	static int n,q,size,sum,max=0;
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,-1,0,1};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		q=sc.nextInt();
		size=(int)Math.pow(2, n); // 맵 가로, 세로 크기
		map=new int[size][size];
		
		// 2^N × 2^N인 격자 채우기
		for(int i=0;i<size;i++)
			for(int j=0;j<size;j++)
				map[i][j]=sc.nextInt();
		
		for(int i=0;i<q;i++) {
			int l=sc.nextInt();
			
			rotate(l);
			melt();
		}
		
		// 해당 얼음이 어떤 얼음덩이에 속해있는지 확인하는 방문 체크 배열
		visited=new boolean[size][size]; 
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				// 아직 어떤 그룹에도 속해있지 않으면서 얼음이 있는 곳에서부터 그룹 생성
				if(!visited[i][j]&&map[i][j]>0) {
					visited[i][j]=true; // 방문처리
					max=Math.max(max,dfs(i,j)); // 가장 큰 얼음덩어리가 차지하는 칸수 갱신
				}
			}
		}
		
		System.out.println(sum+"\n"+max);
	}
	
	// 가장 큰 얼음 덩어리와 전체 얼음의 양을 구하는 함수
	static int dfs(int x, int y) {
		int count=1; // 시작 좌표를 포함해야 함으로 1로 초기화
		sum+=map[x][y]; // 전체 얼음의 양 구하기
		
		for(int i=0;i<4;i++) {
			int nx=x+dx[i];
			int ny=y+dy[i];
			
			// 탐색하고자 하는 곳이 배열 범위 내에 있으며
			if(nx>=0&&ny>=0&&nx<size&&ny<size) {
				// 얼음이 있고 아직 어떤 그룹에도 속하지 않았다면
				if(map[nx][ny]>0&&!visited[nx][ny]) {
					visited[nx][ny]=true; // 방문처리
					count+=dfs(nx,ny); // 칸수 누적
				}
			}
		}
		return count; // 칸수 return
	}
	
	// 얼음 덩어리를 녹이는 함수
	static void melt() {
		// 녹여야 하는 얼음의 좌표가 들어있는 칸
		Queue<Point_20058> q=new LinkedList<>();
		
		for(int x=0;x<size;x++) {
			for(int y=0;y<size;y++) {
				int count=0; // 얼음과 인접해있는 칸의 개수
				
				// 상,하,좌,우 탐색
				for(int d=0;d<4;d++) {
					int nx=x+dx[d];
					int ny=y+dy[d];
					
					if(nx>=0&&ny>=0&&nx<size&&ny<size) 
						if(map[nx][ny]>=1) // 얼음이 있는 칸
							count++;
				}
				// 3칸 이상 얼음이 있는 칸과 인접해 있지 않다면
				if(count<3) 
					q.offer(new Point_20058(x,y)); // 큐에 삽입
			
			}
		}
		
		// 큐에 있는 좌표들을 녹야아하는 얼음의 좌표가 들어있으므로 얼음의 양 1 줄이기
		while(!q.isEmpty()) {
			Point_20058 p=q.poll();
			
			map[p.x][p.y]--;
		}
	}
	
	// 격자를 90도 회전하는 함수
	static void rotate(int l) {
		int[][] temp=new int[size][size]; // 회전된 배열을 저장할 임시 배열
		int loop=size/(int)Math.pow(2, l); // 맵의 한 변의 크기 / 격자의 한 변의 크기
		
		int x=0;
		// 세로
		for(int i=0;i<loop;i++) {
			int y=0;
			
			if(i!=0)
				x+=(int)Math.pow(2, l); // 다음 회전 시작할 x 좌표 구하기
			
			// 가로
			for(int j=0;j<loop;j++) {
				
				if(j!=0)
					y+=(int)Math.pow(2, l); // 다음 회전 시작할 y 좌표 구하기
				
				// 한 격자의 좌표 값들 90도 회전시키기
				for(int a=0;a<(int)Math.pow(2, l);a++)
					for(int b=0;b<(int)Math.pow(2, l);b++)
						temp[x+b][y-a+(int)Math.pow(2, l)-1]=map[x+a][y+b];
			}
		}
		map=temp;
	}
}
