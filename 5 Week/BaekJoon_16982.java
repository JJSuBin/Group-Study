import java.util.*;

public class BaekJoon_16982 {
	static int n,m;
	static int[] count=new int[101]; // 해당 칸에 도착하기까지 이동한 횟수가 저장된 배열
	static int[] map=new int[101];
	static boolean[] visited=new boolean[101];
	static int[] snake_ladder=new int[101];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		
		for(int i=0;i<n+m;i++) {
			int start=sc.nextInt();
			int end=sc.nextInt();
			
			/*
			 * 사다리나 뱀이 있는 곳은 따로 출발지를 인덱스로 하고
			 * 도착지를 배열 값으로하는 배열에 저장해둔다.  
			 */
			snake_ladder[start]=end;
		}
		
		bfs(1); // 출발지는 1
	}
	
	static void bfs(int start) {
		Queue<Integer> q=new LinkedList<>();
		visited[start]=true; // 방문처리
		count[start]=0; 
		q.offer(start);

		while(!q.isEmpty()) {
			int now=q.poll();
			
			// 주사위를 굴려 이동할 수 있는(1~6칸 만큼) 곳 모두 탐색
			for(int i=1;i<7;i++) {
				int next=now+i;
				
				// 게임판을 넘어가거나 이미 방문한 곳이라면 최단경로를 구할 수 없기 때문에 무시
				if(next>100||visited[next]) 
					continue;
				
				// 이동한 칸이 도착지라면 이동횟수(next로 이동한 한번도 더해야 함) 출력후 종료
				if(next==100) {
					System.out.println(count[now]+1);
					return;
				}
				
				visited[next]=true; // 방문처리
				
				// 만약 이동한 칸에 뱀 or 사다리가 있다면
				if(snake_ladder[next]!=0) {
					/*
					 * 이동할 곳을 아직 방문하지 않은 경우에만 이동해야 한다.
					 * 만약 이미 방문한 곳이라면 최단거리를 구할 수 없기 때문에 무시 
					 */
					if(!visited[snake_ladder[next]]) {			
						visited[snake_ladder[next]]=true; // 방문처리
						q.offer(snake_ladder[next]); // 큐에 삽입
						count[snake_ladder[next]]=count[now]+1; // 이동횟수 저장
 					}
				}
				// 아무것도 없는 칸이라면
				else {
					q.offer(next);
					count[next]=count[now]+1; // 이동횟수 저장
				}
			}
		}
	}
}
