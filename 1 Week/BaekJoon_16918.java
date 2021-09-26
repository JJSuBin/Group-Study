import java.util.*;

public class BaekJoon_16918 {
	static char[][] map;
	static int[][] boomtime;
	static int r,c,n;
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,-1,0,1};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		r=sc.nextInt();
		c=sc.nextInt();
		n=sc.nextInt();
		
		map=new char[r][c];
		boomtime=new int[r][c];
		
		for(int i=0;i<r;i++) {
			String str=sc.next();
			for(int j=0;j<c;j++) {
				map[i][j]=str.charAt(j);
				
				// 초기에 설치된 폭탄을 3초후 폭발
				if(map[i][j]=='O')
					boomtime[i][j]=3;
			}
		}
		
		int time=1; // 처음 1초동안 봄버맨은 아무것도 하지 않음
		while(time<n) {
			time++;
			/*
			 * 봄버맨이 폭탄을 설치하는 시간은 2,4,6,8...초로 
			 * 해당 초들에 +3한 시간이 폭탄이 터지는 시간이 된다.
			 */
			setboom(time); 
			if(time==n)
				break;
			
			time++;
			boom(time); // 폭탄이 터지는 시간은 3,5,7,9...초가 된다.
			if(time==n)
				break;
		}
		
		print();
	}
	
	// 빈 공간에 폭탄을 설치하는 함수
	static void setboom(int time) {
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				// 빈 공간에 폭탄 설치
				if(map[i][j]=='.') {
					map[i][j]='O'; // 폭탄으로 변경
					boomtime[i][j]=time+3; // 터지는 시간 저장
				}
			}
		}
	}
	
	// 폭탄이 터지는 함수
	static void boom(int time) {
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				// 터져야 하는 시간인 폭탄 상,하,좌,우 폭발
				if(boomtime[i][j]==time) {
					map[i][j]='.';
					
					for(int k=0;k<4;k++) {
						int nx=i+dx[k];
						int ny=j+dy[k];
						
						if(nx<0||ny<0||nx>=r||ny>=c)
							continue;
						
						if(map[nx][ny]=='O') 
							map[nx][ny]='.';
					}
				}
			}
		}
	}
	
	// 맵 출력 함수
	static void print() {
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
			}
	}
}
