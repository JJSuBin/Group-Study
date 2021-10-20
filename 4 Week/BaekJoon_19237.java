import java.util.*;

class Shark {
	int x, y, dir;
	
	public Shark(int x, int y, int dir) {
		this.x=x;
		this.y=y;
		this.dir=dir;
	}
}

public class BaekJoon_19237 {
	static int n,m,k;
	static Shark[] shark; // 1~m번째까지 각 상어의 위치와 방향 저장
	static int[][] resttime; // 각 냄새의 남은 시간 -> 냄새를 뿌리면 k로 초기화
	static int[][] smellowner; // 각 냄새를 뿌린 상어의 번호
	static int[][][] priority; // 각 상어의 방향에 따른 우선순위
	static int[] dx= {0,-1,1,0,0};
	static int[] dy= {0,0,0,-1,1};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt(); // 배열 크기
		m=sc.nextInt(); // 상어의 수
		k=sc.nextInt(); // k번 이동하면 냄새 소멸
		
		resttime=new int[n+1][n+1];
		smellowner=new int[n+1][n+1];
		shark=new Shark[m+1]; 
		priority=new int[m+1][5][4];
		
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				 int n=sc.nextInt();
				 
				 // 상어가 있는 위치라면
				 if(n>0) {
					 shark[n]=new Shark(i,j,0); // 해당 번호의 상어 위치 저장 
					 smellowner[i][j]=n; // 냄새의 주인 번호 저장
					 resttime[i][j]=k; // 냄새 생존 시간인 k로 초기화
				 }
			}
		}
		
		// 각 상어의 방향 입력 받기 1:위, 2:아래, 3:왼쪽, 4:오른쪽
		for(int i=1;i<=m;i++)
			shark[i].dir=sc.nextInt();
		
		// 각 상어마다 4방향에서의 우선 순위 입력받기
		for(int i=1;i<=m;i++) 
			for(int j=1;j<=4;j++) 
				for(int k=0;k<4;k++)
					priority[i][j][k]=sc.nextInt();
			
		System.out.println(solve());
		
	}
	
	static int solve() {
		int time=0;
		
		while(true) {
			// 1번 상어만 살아남았는지 확인하는 과정 -> 종료 조건
			int count=0;
			for(int i=1;i<=m;i++)
				if(shark[i]!=null)
					count++;
			
			// 1번 상어만 살아남았다면 소요시간 return 후 종료
			if(count==1&&shark[1]!=null)
				return time;
			// 1번 상어만 남지 못하고 1000초가 지났다면 실패
			if(time>=1000)
				return -1;
			
			int[][] temp=new int[n+1][n+1];
			for(int i=1;i<=m;i++)
				// 격자 안에 있는 상어 이동시키기
				if(shark[i]!=null)
					moveShark(temp,i);
			
			// 냄새의 유효시간 1씩 감소
			for(int i=1;i<=n;i++) {
				for(int j=1;j<=n;j++) {
					if(resttime[i][j]>0)
						resttime[i][j]--;
					
					// 냄새 유효시간이 끝났다면 냄새의 주인도 없애주기
					if(resttime[i][j]==0)
						smellowner[i][j]=0;
				}
			}
			
			/*
			 * 1초마다 상어가 이동한 결과는 temp 배열에 저장되어 있으므로
			 * temp 배열을 탐색해 상어가 새롭게 이동한 칸의 냄새 유효시간 k로 저장
			 * 해당 칸의 냄새의 상어 번호 저장
			 */
			for(int i=1;i<=n;i++) {
				for(int j=1;j<=n;j++) {
					if(temp[i][j]>0) {
						resttime[i][j]=k;
						smellowner[i][j]=temp[i][j];
					}
				}
			}
			
			time++;
		}
	}
	/*
	 * 상어를 이동시키는 함수, m은 이동시킬 상어의 번호
	 * temp 배열에는 1번부터 m번까지의 상어가 순서대로 우선순위에 따라 이동한 결과가 저장되어 있다. 
	 * 상어가 이동했을 때 실제 map을 바로 변경하지 않고 임시 배열에 저장했다가 한꺼번에 바꾸는 것이 오류 예방에 좋다.
	 */
	static void moveShark(int[][] temp, int m) {
		int nx=0;
		int ny=0;
		int d=0;
		
		/*
		 * 상어가 이동할 곳의 우선순위 첫번째는 아무 냄새로 없는 칸이다.
		 * 이 조건을 만족하는 곳으로 이동했는지 확인하기 위한 변수 
		 */
		boolean flag=false; 
		
		for(int i=0;i<4;i++) {
			// m번 상어의 현재 방향에서의 우선순위에 따라 순서대로 탐색
			d=priority[m][shark[m].dir][i]; 
			nx=shark[m].x+dx[d];
			ny=shark[m].y+dy[d];
			
			// 이동한 곳이 배열 범위 내에 있으며 다른 상어의 냄새가 없는 곳이라면 탐색 종료
			if(nx>=1&&ny>=1&&nx<=n&&ny<=n&&smellowner[nx][ny]==0) {
				flag=true;
				break;
			}
		}
		
		// 냄새가 없는 곳이 없는 경우
		if(!flag) {
			for(int i=0;i<4;i++) {
				// m번 상어의 현재 방향에서의 우선순위에 따라 순서대로 탐색
				d=priority[m][shark[m].dir][i]; 
				nx=shark[m].x+dx[d];
				ny=shark[m].y+dy[d];
				
				// 인접한 칸 중에서 자신의 냄새가 있는 칸의 위치를 찾는다.
				if(nx>=1&&ny>=1&&nx<=n&&ny<=n&&smellowner[nx][ny]==m)
					break;
			}
		}
		
		/*
		 * m번째 상어가 이동할 위치에 자신보다 이전에 이동한 상어가 없다면(자신보다 우선순위가 높은 상어)
		 * 이동한 위치로 상어 정보 수정(좌표, 바라보고 있는 방향) 
		 */
		if(temp[nx][ny]==0) {
			temp[nx][ny]=m;
			shark[m].dir=d;
			shark[m].x=nx;
			shark[m].y=ny;
		}
		
		// 이동하려는 위치에 이미 자신보다 우선순위가 높은 상어가 있다면 m번째 상어는 격자 밖으로 쫒겨난다.
		else 
			shark[m]=null;
	}
}
