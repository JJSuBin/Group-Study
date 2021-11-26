import java.util.*;

class Point_1941 {
	int x, y;
	
	public Point_1941(int x, int y) {
		this.x=x;
		this.y=y;
	}
}

public class BaekJoon_1941 {
	static String[][] student=new String[5][5];
	static boolean[] visited=new boolean[25];
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,-1,0,1};
	static int result=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		
		for(int i=0;i<5;i++) {
			String str=sc.next();
			for(int j=0;j<5;j++) {
				student[i][j]=str.charAt(j)+"";
			}
		}
		
		comb(0,0);
		System.out.println(result);
	}
	
	// 25명의 학생 중 조합으로 7명을 선택하는 함수
	static void comb(int start, int depth) {
		if(depth==7) { // 7명의 학생을 선택했다면
			int num=0; // 이다솜파 학생 수 count
			int temp=0; // 7명
			int x=0, y=0; // bfs 함수를 시작하는 좌표를 저장할 변수
			int[][] map=new int[5][5];
			
			for(int i=0;i<25;i++) {
				int row=i/5; // 1차원 visited 배열을 2차원 인덱스로 매핑
				int col=i%5;
				
				if(visited[i]) {
					map[row][col]=1; // 선택 표시
					
					if(temp==0) { // temp가 0이라면 탐색 시작 -> bfs 시작 좌쵸
						x=row;
						y=col;
					}
					
					if(student[row][col].equals("S")) // 선택된 7명 중 이다솜파가 몇명인지 count 
						num++;
					
					temp++; // 7명 모두 골랐다면 빠르게 for문 탈출
				}
				
				if(temp==7)
					break;
			}
			
			if(num>=4) // 이다솜파가 4명 이상이라면 모두 붙어 앉았는지 확인
				bfs(x,y,map);
			
			return;
		}
		
		// 25명 중 중복 없이 7명 선택 => 백트래킹
		for(int i=start;i<25;i++) {
			if(!visited[i]) {
				visited[i]=true;
				comb(i+1,depth+1);
				visited[i]=false;
			}
		}
	}
	
	// 7명의 학생이 모두 근처에 앉았는지 확인하는 함수
	static void bfs(int x, int y, int[][] map) {
		Queue<Point_1941> q=new LinkedList<>();
		q.offer(new Point_1941(x,y));
		
		boolean[][] visited=new boolean[5][5];
		visited[x][y]=true;
		
		int count=1; // 붙어앉은 학생 수 count 변수
		
		while(!q.isEmpty()) {
			Point_1941 p=q.poll();
			
			for(int i=0;i<4;i++) {
				int nx=p.x+dx[i];
				int ny=p.y+dy[i];
				
				// 범위 내에 있으며
				if(nx>=0&&ny>=0&&nx<5&&ny<5) {
					// 아직 방문하지 않았고 선택된 학생인 경우 
					if(!visited[nx][ny]&&map[nx][ny]==1) {
						q.offer(new Point_1941(nx,ny));
						visited[nx][ny]=true;
						count++; // count
					}
				}
			}
		}
		
		if(count==7) // count가 7이라면 7명 모두 붙어 앉은 경우
			result++;
	}
}
