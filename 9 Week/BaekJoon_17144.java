import java.util.*;

class Point_17144 {
	int x,y;
	
	public Point_17144(int x, int y) {
		this.x=x;
		this.y=y;
	}
}

public class BaekJoon_17144 {
	static int[][] map;
	static int r,c,t;
	static int[] machine=new int[2];
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,-1,0,1};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		r=sc.nextInt();
		c=sc.nextInt();
		t=sc.nextInt(); // 진행 시간
		
		map=new int[r][c];
		int index=0;
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				map[i][j]=sc.nextInt(); // -1은 공기청정기가 있는 곳
				
				if(map[i][j]==-1) 
					machine[index++]=i; // 공기청정기의 두개의 행 저장
			}	
		}
		
		while(t-->0) {
			map=spread_Dust();
			operate_machine();
		}
		
		System.out.println(calculation());
	}
	
	// t초 후 미세먼지 양 계산
	static int calculation() {
		int sum=2;
			
		for(int i=0;i<r;i++)
			for(int j=0;j<c;j++)
				sum+=map[i][j];
			
		return sum;
	}
	
	// 먼지를 상,하,좌,우로 퍼뜨리는 함수
	static int[][] spread_Dust() {
		int[][] temp=new int[r][c];
		
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				
				if(map[i][j]==-1) {
					temp[i][j]=-1;
					continue;
				}
					
				temp[i][j]+=map[i][j]; 
				
				// 상,하,좌,우 탐색
				for(int d=0;d<4;d++) {
					int nx=i+dx[d];
					int ny=j+dy[d];
					
					if(nx<0||ny<0||nx>=r||ny>=c||map[nx][ny]==-1) continue;
					
					temp[nx][ny]+=map[i][j]/5;
					temp[i][j]-=map[i][j]/5;
				}
			}
		}
		
		return temp;
	}
	
	// 공기청정기 작동시키는 함수 -> 값을 끌어당기는 방식으로 해결하는 편이 쉬움!
	static void operate_machine() {
		// 공기청정기 윗쪽 사이클
		int top=machine[0];
		for(int x=top-1;x>0;x--) // ↓
			map[x][0]=map[x-1][0];
		
		for(int y=0;y<c-1;y++) // ←
			map[0][y]=map[0][y+1];
		
		for(int x=0;x<top;x++) // ↑
			map[x][c-1]=map[x+1][c-1];
		
		for(int y=c-1;y>1;y--) // →
			map[top][y]=map[top][y-1];
		
		map[top][1]=0;
		
		// 공기청정기 아랫쪽 사이클
		int bottom=machine[1];
		for(int x=bottom+1;x<r-1;x++) // ↑
			map[x][0]=map[x+1][0];
		
		for(int y=0;y<c-1;y++)
			map[r-1][y]=map[r-1][y+1]; // ←
		
		for(int x=r-1;x>bottom;x--)
			map[x][c-1]=map[x-1][c-1]; // ↓
		
		for(int y=c-1;y>1;y--)
			map[bottom][y]=map[bottom][y-1]; // →
		
		map[bottom][1]=0;
	}
}
