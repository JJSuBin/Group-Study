import java.util.*;

class Point_4179 {
	int x,y,count;
	
	public Point_4179(int x, int y, int count) {
		this.x=x;
		this.y=y;
		this.count=count;
	}
	
	public Point_4179(int x, int y) {
		this.x=x;
		this.y=y;
	}
}

public class BaekJoon_4179 {
	static char[][] map;
	static int r,c,result=0;
	static boolean[][] visited;
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,-1,0,1};
	static Queue<Point_4179> human=new LinkedList<>();
	static Queue<Point_4179> fire=new LinkedList<>();
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
				
				if(map[i][j]=='J') // 지훈이의 위치
					human.add(new Point_4179(i,j,0));
								
				if(map[i][j]=='F') // 불의 위치
					fire.add(new Point_4179(i,j));
			}
		}
		
		if(move())
			System.out.println(result);
		else
			System.out.println("IMPOSSIBLE");
	}
	
	static boolean move() {
		while(!human.isEmpty()) { // 지훈이가 더이상 이동할 수 없으면 종료
			int size=fire.size();
			
			/*
			 * 지훈이가 불에 타지 않고 탈출해야 하기 때문에 
			 * 불을 먼저 번지게 한 후 지훈이를 이동시킨다.  
			 */
			while(size-->0) { // 불 전파
				Point_4179 p=fire.poll();
				
				for(int i=0;i<4;i++) {
					int nx=p.x+dx[i];
					int ny=p.y+dy[i];
					
					// 맵의 범위를 넘어간 경우에도 패스
					if(nx<0||ny<0||nx>=r||ny>=c) continue;
					
					// 벽이거나 이미 불이 있는 곳은 패스
					if(map[nx][ny]=='#'||map[nx][ny]=='F') continue;
					
					map[nx][ny]='F';
					fire.offer(new Point_4179(nx,ny));
				}
			}
			
			size=human.size();
			
			while(size-->0) { // 지훈이 이동
				Point_4179 p=human.poll();
				
				for(int i=0;i<4;i++) {
					int nx=p.x+dx[i];
					int ny=p.y+dy[i];
					
					// 지훈이의 경우 맵을 벗어난 경우 탈출에 성공한 것이므로 이동횟수 증가 후 return
					if(nx<0||ny<0||nx>=r||ny>=c) {
						result=p.count+1;
						return true;
					}
					
					// 벽, 불, 이미 방문한 곳은 넘어간다.
					if(map[nx][ny]=='#'||map[nx][ny]=='F'||map[nx][ny]=='J') continue;
					
					map[nx][ny]='J';
					human.add(new Point_4179(nx,ny,p.count+1));
				}
			}
		}
		return false;
	}
}
