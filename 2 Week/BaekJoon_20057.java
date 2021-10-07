import java.util.*;

public class BaekJoon_20057 {
	static int n;
	static int[][] map;
	// 토네이도가 움직이는 순서인 서-남-동-북 순으로 모래가 퍼지는 방향 
	static int[][] dsx= {{-1,1,-2,-1,1,2,-1,1,0}, // x 이동방향
					  	{-1,-1,0,0,0,0,1,1,2},  
					  	{1,-1,2,1,-1,-2,1,-1,0},	
					  	{1,1,0,0,0,0,-1,-1,-2}};
	static int[][] dsy= {{1,1,0,0,0,0,-1,-1,-2}, // y 이동방향
		  				{-1,1,-2,-1,1,2,-1,1,0},
		  				{-1,-1,0,0,0,0,1,1,2},	
		  				{1,-1,2,1,-1,-2,1,-1,0}};
	static int[] sandpercent= {1,1,2,7,7,2,10,10,5};
	static int[] dx= {0,1,0,-1}; // 토네이도가 이동하는 서-남-동-북 순서
	static int[] dy= {-1,0,1,0};
	// 토네이도가 서-남-동-북 방향으로 이동하는 횟수, 한 턴이 끝날때마다 +2씩 증가
	static int[] dc= {1,1,2,2}; 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		
		n=sc.nextInt();
		map=new int[n][n];
		
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
				map[i][j]=sc.nextInt();
		
		// 시작은 배열의 중앙
		int result=tornadoes(n/2,n/2);
		System.out.println(result);
	}
	
	static int tornadoes(int x, int y) {
		int totaloutsand=0; // 격자 밖으로 나간 모래의 양
		
		// 현재 위치
		int currentx=x;
		int currenty=y;
		
		while(true) {
			// 서-남-동-북 순으로 토네이도 이동
			for(int i=0;i<4;i++) {
				// 각 방향으로 이동하는 횟수만큼 이동 
				for(int move=0;move<dc[i];move++) {
					// 이동한 위치(예시에서의 y의미)
					int nx=currentx+dx[i];
					int ny=currenty+dy[i];
					
					/*
					 * 토네이도가 범위를 벗어나는 경우는 소멸한 경우
					 * 이때는 격자 밖으로 나간 모래의 양 return 
					 */
					if(nx<0||ny<0||nx>=n||ny>=n) 
						return totaloutsand;
					
					//////////////예시에서의 y칸////////////// 
					int sand=map[nx][ny]; // y에 있는 모래의 양
					map[nx][ny]=0; // y에 있는 모든 모래 이동하기 때문에 0으로 초기화
					int spreadtotal=0; 
					
					// 해당 방향에서 비율이 적혀있는 칸으로 해당 비율만큼 모래 이동
					for(int j=0;j<9;j++) {
						/*
						 * i는 서-남-동-북 중 해당하는 방향 의미
						 * j는 y를 기준으로 설정한 각 비율이 있는 위치를 의미
						 */
						int sandx=nx+dsx[i][j];
						int sandy=ny+dsy[i][j];
						// y에 있었던 모래에서 각 칸에 적혀있는 비율만큼 이동 
						int spread=(sand*sandpercent[j])/100;
						
						// 범위를 넘어가면 격자 밖으로 나간 모래이기 때문에 누적
						if(sandx<0||sandy<0||sandx>=n||sandy>=n)
							totaloutsand+=spread;
						// 범위를 넘어가지 않았다면 비율 칸에 모래 누적
						else
							map[sandx][sandy]+=spread;
						
						spreadtotal+=spread; // y에 있던 모래중 이동한 모래의 양 구하기 -> 나머지 α 칸으로 이동
					}
					
					//////////////예시에서의 α칸////////////// 
					// α은 y에서 서-남-동-북 중 해당하는 방향으로 한 칸 이동한 곳
					int alphax=nx+dx[i];
					int alphay=ny+dy[i];
					// α로 이동하는 모래의 양은 비율이 적혀있는 칸으로 이동하지 않은 남은 모래의 양
					int alpha=sand-spreadtotal;
					
					// 범위를 넘어가면 격자 밖으로 나간 모래이기 때문에 누적
					if(alphax<0||alphay<0||alphax>=n||alphay>=n) 
						totaloutsand+=alpha;
					// 범위를 넘어가지 않았다면 α 칸에 모래 누적
					else
						map[alphax][alphay]+=alpha;
					
					
					// 현재 위치 갱신
					currentx=nx;
					currenty=ny;
				}
			}
			
			// 토네이도가 이동하는 횟수는 한 턴이 끝날때마다 +2씩 증가
			for(int k=0;k<4;k++)
				dc[k]+=2;
		}
	}

}
