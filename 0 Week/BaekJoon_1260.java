import java.util.*;

public class BaekJoon_1260 {
	static ArrayList<Integer>[] graph;
	static int n,m,v;
	static boolean[] visited;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		v=sc.nextInt();
		
		graph=new ArrayList[n+1];
		
		// n개의 노드 추가
		for(int i=1;i<=n;i++)
			graph[i]=new ArrayList<>();
		
		// 간선 정보 입력
		for(int i=0;i<m;i++) {
			int a=sc.nextInt();
			int b=sc.nextInt();
			
			// 양방향
			graph[a].add(b);
			graph[b].add(a);
		}
		
		// 이진트리로 만들기 위해 정렬
		for(int i=1;i<=n;i++)
			Collections.sort(graph[i]);
		
		visited=new boolean[n+1];
		dfs(v);
		System.out.println();
		visited=new boolean[n+1];
		bfs(v);
	}
	
	// BFS 알고리즘
	static void bfs(int start) {
		Queue<Integer> q=new LinkedList<>();
		q.offer(start);
		visited[start]=true;
		
		while(!q.isEmpty()) {
			int now=q.poll();
			System.out.print(now+" ");
			
			for(int i:graph[now]) {
				if(!visited[i]) {
					q.offer(i);
					visited[i]=true;
				}
			}
		}
	}
	
	// DFS 알고리즘 
	static void dfs(int start) {
		visited[start]=true;
		System.out.print(start+" ");
		
		for(int i:graph[start])
			if(!visited[i])
				dfs(i);
	}
}
