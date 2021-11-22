import java.util.*;

class Move {
	int time;
	char direction;
	
	public Move(int time, char direction) {
		this.time=time;
		this.direction=direction;
	}
}

class Snake {
	int x,y;
	
	public Snake(int x, int y) {
		this.x=x;
		this.y=y;
	}
}

public class BaekJoon_3190 {
	static ArrayList<Move> Info=new ArrayList<>(); 
	static int map[][];
	static int n,k,l;
	static int[] dx= {0,1,0,-1}; // 동,남,서,북
	static int[] dy= {1,0,-1,0};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		k=sc.nextInt(); // 사과의 개수
		
		map=new int[n+1][n+1];
		for(int i=0;i<k;i++) {
			int x=sc.nextInt();
			int y=sc.nextInt();
			
			map[x][y]=1; // 사과가 있는 자리는 1로 변경
		}
		
		l=sc.nextInt(); // 방향 전환 횟수
		for(int i=0;i<l;i++) {
			int t=sc.nextInt();
			char d=sc.next().charAt(0);
			
			Info.add(new Move(t,d));
		}
		
		System.out.println(Dummy());
	}
	
	static int turn(int dir, char c) {
		if(c=='L') dir=(dir==0)?3:dir-1; // 왼쪽으로 회전
		else dir=(dir+1)%4; // 오른쪽으로 회전
		
		return dir;
	}
	
	static int Dummy() {
		int x=1, y=1; // 뱀의 처음 위치
		int now_dir=0; // 처음은 동쪽을 바라보고 있음
		map[x][y]=2; // 뱀이 있는 자리는 2로 설정
		int time=0; // 이동 횟수
		int turn=0; // 방향 전환 횟수
		
		Queue<Snake> q=new LinkedList<>();
		q.offer(new Snake(x,y));
		
		while(!q.isEmpty()) {
			int nx=x+dx[now_dir];
			int ny=y+dy[now_dir];
			
			// 맵의 범위를 벗어나지 않고 자신의 몸과 부딪히지 않았다면 게임 진행
			if(nx>=1&&ny>=1&&nx<=n&&ny<=n&&map[nx][ny]!=2) {
				if(map[nx][ny]==1) { // 사과가 있는 칸에 도착했을 경우
					q.offer(new Snake(nx,ny)); 
					map[nx][ny]=2; // 새로운 머리 추가
				}
				
				if(map[nx][ny]==0) { // 사과가 없는 칸에 도착햇을 경우
					Snake tail=q.poll();
					map[tail.x][tail.y]=0; // 길이 줄이기
					q.offer(new Snake(nx,ny));
					map[nx][ny]=2; // 새로운 머리 추가
				}
			}
			else { // 맵을 벗어났거나 자신의 몸통과 부딪혔다면 종료
				time++;
				break;
			}
			
			x=nx;
			y=ny;
			time++; // 이동시간 증가
			
			// 다음 방향 전환으로 넘어가야 하는 경우 
			if(turn<l&&Info.get(turn).time==time) {
				now_dir=turn(now_dir,Info.get(turn).direction);
				turn++; // 횟수 증가
			}
			
		}
		
		return time;
	}
}
