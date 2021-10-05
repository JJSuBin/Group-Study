import java.util.*;

public class BaekJoon_5212 {
	static int r,c;
	static char[][] map;
	static int[][] nummap; // 주변의 육지 개수를 저장하는 배열
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,1,0,-1};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		r=sc.nextInt();
		c=sc.nextInt();
		map=new char[r][c];
		nummap=new int[r][c];
		
		for(int i=0;i<r;i++) {
			String str=sc.next();
			for(int j=0;j<c;j++) {
				map[i][j]=str.charAt(j);
			}
		}
		
		warming();
		print();		
	}
	
	static void warming() {
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				int land=0;
				/*
				 * 땅이 있는 곳의 상,하,좌,우 탐색
				 * 이때 육지의 개수를 count 해야함! -> 바다의 갯수를 count하려면
				 * 배열 범위를 넘어가는 경우도 고려해야 하는데 이때 오류 발생
				 * 따라서 육지의 개수가 1개 이하라면 바다로 변경
				 */
				if(map[i][j]=='X') {
					for(int k=0;k<4;k++) {
						int nx=i+dx[k];
						int ny=j+dy[k];
						
						// 배열 범위 초과
						if(nx<0||ny<0||nx>=r||ny>=c)
							continue;
						
						if(map[nx][ny]=='.')
							continue;
						
						if(map[nx][ny]=='X')
							land++;
					}
				}
				nummap[i][j]=land;
			}
		}
		
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				/*
				 * 주변에 육지의 개수가 1개 이하라면 
				 * 3면 이상이 바다이기 때문에 해당 좌표 바다로 변경
				 */
				if(nummap[i][j]<2)
					map[i][j]='.';
			
			}		
		}
	}
	
	// 모든 육지를 포함하는 최소한의 지도를 출력하는 함수
	static void print() {
		int lx=Integer.MAX_VALUE;
		int ly=Integer.MAX_VALUE;
		int rx=Integer.MIN_VALUE;
		int ry=Integer.MIN_VALUE;
		
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				if(map[i][j]=='X') {
					lx=Math.min(i, lx);
					ly=Math.min(j, ly);
					rx=Math.max(i, rx);
					ry=Math.max(j, ry);
				}
			}
		}
		
		// 지도 출력
		for(int i=lx;i<=rx;i++) {
			for(int j=ly;j<=ry;j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}
