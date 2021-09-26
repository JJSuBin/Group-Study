import java.io.*;
import java.util.*;

public class BaekJoon_1325 {
	static int n;
	static int[] result;
	static ArrayList<Integer>[] map;
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		map = new ArrayList[n+1];
		
		result=new int[n+1];
		for(int i=1;i<=n;i++) {
			map[i]=new ArrayList<>();
		}
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			
			map[a].add(b); // 단방향임을 주의!
		}
		
		// 각 컴퓨터마다 bfs 호출
		for(int i=1;i<=n;i++) 
			bfs(i);
		
		// 해킹할 수 있는 컴퓨터 개수의 최댓값 찾기
		int max=Integer.MIN_VALUE;
		for(int i=1;i<=n;i++) 
			max=Math.max(result[i],max);
		
		// 최댓값을 갖는 검퓨터가 1대가 아닐 수 있으므로 모든 값 탐색
		for(int i=1;i<n+1;i++) {
			if(max==result[i]) {
				bw.write(i+" ");
			}
		}
		
		bw.flush();
		bw.close();
	}
	
	// DFS는 시간초과로 인해 bfs 사용
	static void bfs(int start) {
		Queue<Integer> q=new LinkedList<>();
		boolean[] check=new boolean[n+1]; // 매 경우마다 방문여부 체크 배열 초기화 해주어야 함
		q.add(start);
		check[start]=true; // 방문처리
		
		while(!q.isEmpty()) {
			int now=q.poll();
			for(int next:map[now]) {
				if(!check[next]) {
					check[next]=true; // 방문처리
					result[next]++; // 해킹할 수 있는 컴퓨터 개수 count
					q.add(next);
				}
			}
		}
	}
}
