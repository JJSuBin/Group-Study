import java.util.*;

class Point_3055 {
	int x,y;
	
	public Point_3055(int x, int y) {
		this.x=x;
		this.y=y;
	}
}

public class BaekJoon_3055 {
	static int r,c,x,y,min=Integer.MAX_VALUE;
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,-1,0,1};
	static char[][] map;
	static boolean[][] visited;
	static Queue<Point_3055> water=new LinkedList<>();
	static Queue<Point_3055> beaver=new LinkedList<>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		r=sc.nextInt();
		c=sc.nextInt();
		map=new char[r][c];
		visited=new boolean[r][c];
		
		for(int i=0;i<r;i++) {
			String str=sc.next();
			for(int j=0;j<c;j++) {
				map[i][j]=str.charAt(j);
				
				// 비버의 초기 위치 저장
				if(map[i][j]=='S') 
					beaver.offer(new Point_3055(i,j));
				// 물의 초기 위치 저장
				if(map[i][j]=='*')
					water.offer(new Point_3055(i,j));
			}
		}
		
		int time=0;
		while(true) {
			time++;
			move_Water(); // 고슴도치는 물이 찰 예정인 칸으로 이동할 수 없기 때문에 물 먼저 이동
			
			// 비버의 굴에 도착한 경우
			if(move_Beaver()) {
				System.out.println(time);
				break;
			}
			// 큐에 아무것도 없으면 더이상 비버가 이동할 곳이 없는 경우
			if(beaver.size()==0) {
				System.out.println("KAKTUS");
				break;
			}
		}
	}
	
	// 고슴도치 이동 함수
	static boolean move_Beaver() {
		int loop=beaver.size(); // 기존에 큐에 들어있던 비버의 위치에서 상,하,좌,우 탐색
		while(loop-->0) {
			Point_3055 p=beaver.poll();
			
			for(int i=0;i<4;i++) {
				int nx=p.x+dx[i];
				int ny=p.y+dy[i];
				
				if(nx>=0&&ny>=0&&nx<r&&ny<c) {
					// 이동한 곳이 비버의 굴이면 return true
					if(map[nx][ny]=='D') 
						return true;
					// 아무것도 없는 곳이면
					if(map[nx][ny]=='.') {
						map[nx][ny]='S'; // 위치 이동
						beaver.offer(new Point_3055(nx,ny));
					}
				}
			}
		}
		return false;
	}
	
	// 물 이동 함수
	static void move_Water() {
		int loop=water.size(); // 기존에 큐에 들어있던 물만 퍼져야 함
		while(loop-->0) {
			Point_3055 p=water.poll();
			
			for(int i=0;i<4;i++) {
				int nx=p.x+dx[i];
				int ny=p.y+dy[i];
				
				// 배열 내에 있는 비어있는 곳에 물이 참
				if(nx>=0&&ny>=0&&nx<r&&ny<c) {
					if(map[nx][ny]=='.') {
						map[nx][ny]='*';
						water.offer(new Point_3055(nx,ny));
					}
				}
			}
		}
		
	}
}
