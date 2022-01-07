import java.util.*;

class Fish {
	int x,y,num,dir,alive;
	
	public Fish(int x, int y, int num, int dir, int alive) {
		this.x=x;
		this.y=y;
		this.num=num;
		this.dir=dir;
		this.alive=alive;
	}
}
public class BaekJoon_19236 {
	static int result=0;
	static int[][] map=new int[4][4];
	static Fish[] fish=new Fish[17]; // 물고기 정보를 저장하는 배열
	static int[] dx= {-1,-1,0,1,1,1,0,-1};
	static int[] dy= {0,-1,-1,-1,0,1,1,1};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				int num=sc.nextInt();
				int dir=sc.nextInt()-1;
				
				fish[num]=new Fish(i,j,num,dir,1);
				map[i][j]=num; // map에 물고기 번호 저장
			}
		}
		
		// 상어 초기 위치, 방향 설정
		int sx=0;
		int sy=0;
		int sdir=fish[map[0][0]].dir;
		int eatnum=map[0][0];
		
		// 상어가 있는 위치의 물고기는 죽음 -> 0으로 값 변경
		fish[map[0][0]].alive=0;
		map[0][0]=-1;
		
		move_Shark(sx,sy,sdir,eatnum);
		
		System.out.println(result);
	}
	
	// 상어 위치, 상어 방향, 상어가 먹은 물고기의 번호 총 합
	static void move_Shark(int sx, int sy, int sdir, int total) {
		result=Math.max(total, result); 
		
		int[][] temp_map=new int[4][4]; // 맵 복사 배열
		for(int i=0;i<4;i++) 
			for(int j=0;j<4;j++)
				temp_map[i][j]=map[i][j];
		
		Fish[] temp_fish=new Fish[17]; // 물고기 정보 저장 배열 복사 배열ㄴ
		for(int i=1;i<=16;i++)
			temp_fish[i]=new Fish(fish[i].x, fish[i].y, fish[i].num, fish[i].dir, fish[i].alive);
		
		move_Fish(); // 물고기 이동
		
		// 상어는 현재 방향에서 4*4 행렬로 1칸, 2칸, 3칸까지 이동 가능하므로 모든 경우의 수 탐색
		for(int i=1;i<4;i++) {
			int nx=sx+dx[sdir]*i;
			int ny=sy+dy[sdir]*i;
			
			// 상어는 맵을 벗어나지 않고 물고기가 있는 칸으로만 이동 가능
			if(nx>=0&&ny>=0&&nx<4&&ny<4&&map[nx][ny]!=0) {
				int eatnum=map[nx][ny]; // 상어가 먹은 물고기의 번호
				int ndir=fish[eatnum].dir; // 물고기 방향으로 상어 방향도 변환
				map[sx][sy]=0; // 상어가 있던곳 0으로 값 변경
				map[nx][ny]=-1; // 상어가 이동한 곳은 -1로(상어가 있다는 의미) 변경
				fish[eatnum].alive=0; // 죽은 물고기의 alive 값 변경
				
				move_Shark(nx,ny,ndir,total+eatnum); // 재귀호출
				
				// 값 되돌리기
				fish[eatnum].alive=2;
				map[sx][sy]=-1;
				map[nx][ny]=eatnum;
			}
		}
		
		// map 정보 되돌리기
		for(int i=0;i<4;i++) 
			for(int j=0;j<4;j++)
				map[i][j]=temp_map[i][j];
		
		// Fish 배열 정보 되돌리기
		for(int i=1;i<=16;i++)
			fish[i]=new Fish(temp_fish[i].x, temp_fish[i].y, temp_fish[i].num, temp_fish[i].dir, temp_fish[i].alive);
	}
	
	static void move_Fish() {
		for(int i=1;i<=16;i++) { // 번호가 작은 물고기부터 처리
			if(fish[i].alive==0) // 이미 죽은 물고기라면 넘어간다.
				continue;
			
			int dir=fish[i].dir;
			// 물고기가 이동할 수 있는 8방향 모두 탐색
			for(int d=0;d<8;d++) {
				dir%=8; // 탐색하는 방향
				fish[i].dir=dir;
				
				int nx=fish[i].x+dx[dir];
				int ny=fish[i].y+dy[dir];
				
				// 물고기가 이동할 수 있는 곳이면서
				if(nx>=0&&ny>=0&&nx<4&&ny<4&&map[nx][ny]!=-1) {
					if(map[nx][ny]==0) { // 빈칸인 경우
						map[fish[i].x][fish[i].y]=0;
						fish[i].x=nx;
						fish[i].y=ny;
						map[nx][ny]=i;
					}
					else { // 다른 물고기가 있는 경우 두 물고기 위치 swap
						int temp=fish[map[nx][ny]].num;
						
						fish[temp].x=fish[i].x;
						fish[temp].y=fish[i].y;
						map[fish[temp].x][fish[temp].y]=temp;
						
						fish[i].x=nx;
						fish[i].y=ny;
						map[nx][ny]=i;
					}
					break; // 물고기가 이동할 수 있는 경우를 찾았다면 탐색 종료
				}
				else { // d방향으로 이동할 수 없다면 반시계 방향으로 45도 회전
					dir++;
				}
			}
		}
	}
}
