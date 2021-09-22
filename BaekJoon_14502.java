import java.util.*;

public class BaekJoon_14502 {
	static int[][] map,temp;
	static int n,m,max=-1;
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,-1,0,1};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		
		map=new int[n][m];
		
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++)
				map[i][j]=sc.nextInt();
		
		dfs(0);
		System.out.println(max);
	}
	
	// 3개의 벽을 세우는 함수
	static void dfs(int count) {
		// 3개의 벽을 다 세웠다면 바이러스 전염 시작
		if(count==3) {
			/*
			 * 경우의 수가 많기 때문에 임시 배열을 선언하여 바이러스를 전파시켜야 한다.
			 * 새롭게 설치된 벽은 재귀호출이 끝난 후 값이 복원되기 때문에 맵 복사 지점 주의!
			 * 
			 * 즉, 여기서 map은 3개의 벽이 새롭게 설치된 상태이며
			 * temp는 이후 바이러스가 전파되고 난 이후의 상태이다. 
			 */
			temp=new int[n][m];
			
			for(int i=0;i<n;i++) 
				for(int j=0;j<m;j++)
					temp[i][j]=map[i][j];
			
			// 바이러스 전파
			for(int i=0;i<n;i++)
				for(int j=0;j<m;j++)
					if(temp[i][j]==2)
						virus(i,j);
			
			max=Math.max(max, saftyspace()); // 최대 안전영역의 개수 갱신
			return;
		}
		
		/*
		 * 배열 전체를 탐색하면서 빈칸인 곳에 3개의 벽을 세우는
		 * 모든 경우의 수를 탐색한다.  
		 */
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(map[i][j]==0) {
					map[i][j]=1; // 벽 세우기
					dfs(count+1);
					map[i][j]=0; // 다음 경우의 수를 위해 값 초기화
				}
			}
		}
	}
	
	// 바이러스가 퍼지는 함수
	static void virus(int x, int y) {
		for(int i=0;i<4;i++) {
			int nx=x+dx[i];
			int ny=y+dy[i];
			
			// 배열 범위 초과인 경우 넘어간다.
			if(nx>=n||ny>=m||nx<0||ny<0)
				continue;
			// 벽인 경우도 넘어간다.
			if(temp[nx][ny]==1)
				continue;
			// 바리어스 전염
			if(temp[nx][ny]==0) {
				temp[nx][ny]=2;
				virus(nx,ny);
			}
		}
	}
	
	// 안전영역의 개수를 count하는 함수
	static int saftyspace() {
		int count=0;
		
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++)
				if(temp[i][j]==0)
					count++;
		
		return count;
	}
}
