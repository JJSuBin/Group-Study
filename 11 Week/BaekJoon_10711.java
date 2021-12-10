import java.util.*;

class Point_10711 {
	int x, y;
	
	public Point_10711(int x, int y) {
		this.x=x;
		this.y=y;
	}
}

public class BaekJoon_10711 {
	static char[][] map;
	static int n,m;
	static int[] dx= {0,0,1,1,1,-1,-1,-1}; // 상,하,우,좌,대각선
	static int[] dy= {-1,1,-1,0,1,-1,0,1};
	static Queue<Point_10711> nosand=new LinkedList<>(); // 모래가 없는 곳의 좌표 저장
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		map=new char[n][m];
		
		for(int i=0;i<n;i++) {
			String str=sc.next();
			for(int j=0;j<m;j++) {
				map[i][j]=str.charAt(j);
				
				// 모래가 없는 곳 큐에 삽입
				if(map[i][j]=='.') 
					nosand.add(new Point_10711(i,j));
			}
		}
		System.out.println(wave());
	}
	
	/*
	 * 시간초과를 받지 않기 위해 모래성의 주위를 탐색하는 것이 아닌
	 * 모래가 없는 칸을 기준으로 8방향에 모래성의 튼튼함을 1 감소시킨다.
	 * 이때 이미 하번 확인한 모래성이 없는 노드는 다시 확인할 필요가 없기 때문에
	 * 새로 모래성이 없어진 곳만 큐에 삽입해준다.
	 */
	static int wave() {
		int time=0;
		
		// 큐에 값이 없으면 더 이상 변화가 없는 상태 -> 종료
		while(!nosand.isEmpty()) {
			int size=nosand.size();
			
			// 한 턴에 큐에 쌓인 값들만 처리하고 시간 증가 -> 모래성은 한 번에 무너져야 하기 때문에
			while(size-->0) {
				Point_10711 p=nosand.poll();
				
				int x=p.x;
				int y=p.y;
				
				for(int i=0;i<8;i++) {
					int nx=x+dx[i];
					int ny=y+dy[i];
					
					// 배열 범위내에 있으면서 모래성이 있는 칸은 
					if(nx>=0&&ny>=0&&nx<n&&ny<m) {
						if(map[nx][ny]!='.') {
							map[nx][ny]--; // 모래성의 튼튼함 1 감소시킨다.
							
							// 튼튼함이 0이되면 모래가 없는 칸으로 변경하고 큐에 삽입
							if(map[nx][ny]=='0') {
								map[nx][ny]='.';
								nosand.add(new Point_10711(nx,ny));
							}
						}
					}
				}
			}
			time++; // 초 증가
		}
		
		return time-1;
	}
}
