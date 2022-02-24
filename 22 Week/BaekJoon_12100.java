import java.util.*;

public class BaekJoon_12100 {
	static int n;
	static int[][] map,temp;
	static int[] dir;
	static boolean[][] visited;
	static int max=0;
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,1,0,-1};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		
		n=sc.nextInt();
		
		map=new int[n+1][n+1];
		dir=new int[5]; // 최대 5번 이동 가능
		
		for(int i=1;i<=n;i++)
			for(int j=1;j<=n;j++)
				map[i][j]=sc.nextInt();
		
		dfs(0);
		System.out.println(max);
	}
	
	static void dfs(int depth) {
		if(depth==5) { // 5번의 이동의 방향을 모두 선택했다면
			isPossible();
		}
		
		else {
			// 5번 이동에 상,하,좌,우 모든 경우의 수를 탐색 
			for(int i=0;i<4;i++) {
				if(dir[depth]==0) {
					dir[depth]=i;
					dfs(depth+1);
				}
			}
		}
		
	}
	
	static void isPossible() {
		temp=new int[n+1][n+1];
		
		// 임시 배열에 맵 복사
		for(int i=1;i<=n;i++)
			for(int j=1;j<=n;j++)
				temp[i][j]=map[i][j];
		
		for(int i=0;i<dir.length;i++) {
			visited=new boolean[n+1][n+1]; // 블록이 합쳐진 적이 있는지의 여부를 체크하는 배열
			
			if(dir[i]==0) { // 상
				for(int x=1;x<=n;x++)
					for(int y=1;y<=n;y++)
						move(x,y,dir[i]);
			}
			else if(dir[i]==2) { // 하
				for(int x=n;x>=1;x--) 
					for(int y=1;y<=n;y++)
						move(x,y,dir[i]);
			}
			else if(dir[i]==1) { // 우
				for(int x=n;x>=1;x--) 
					for(int y=1;y<=n;y++)
						move(y,x,dir[i]); // 오른쪽 부터 탐색해야 함
			}
			else { // 좌
				for(int x=1;x<=n;x++)
					for(int y=1;y<=n;y++)
						move(y,x,dir[i]); // 왼쪽 부터 탐색해야 함
			}
		}
		
		// 가장 값이 높은 블록 찾기
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				if(temp[i][j]>max)
					max=temp[i][j];
			}
		}
	}
	
	static void move(int x, int y, int dir) {
		if(temp[x][y]==0)
			return;
		
		while(true) {
			// dir 방향으로 계속 이동
			int nx=x+dx[dir];
			int ny=y+dy[dir];
			
			// 맵을 벗어난 경우 return
			if(nx<1||ny<1||nx>n||ny>n)
				return;
			
			// 이미 합쳐진 블록이라면 return
			if(visited[nx][ny])
				return;
			
			// 이동하다 만난 블록이 값이 같은 블록이라면 -> 합치기
			if(temp[nx][ny]==temp[x][y]) { 
				visited[nx][ny]=true;
				temp[x][y]=0;
				temp[nx][ny]*=2;
				return; // 두번 이상 합쳐질 수 없기 때문에 한번 합치기를 했다면 return
			}
			else if(temp[nx][ny]!=0) // 이동하다 만난 블록의 값이 다르다면 return -> 블록을 넘어서 합칠수 없기 때문에
				return;
			
			// 여기까지 온 블록은 아무것도 없은 블록과 자리를 바꿔주는 경우
			temp[nx][ny]=temp[x][y];
			temp[x][y]=0;
			x=nx;
			y=ny;
		}
	}
}
