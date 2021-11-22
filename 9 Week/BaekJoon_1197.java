import java.util.*;

class Node_1197 implements Comparable<Node_1197> {
	int node, weight;
	
	public Node_1197(int node, int weight) {
		this.node=node;
		this.weight=weight;
	}
	
	@Override
	public int compareTo(Node_1197 o) {
		return this.weight-o.weight;
	}
}

// 최소 스패닝 트리
public class BaekJoon_1197 {
	static ArrayList<ArrayList<Node_1197>> graph=new ArrayList<>();
	static int v,e;
	static boolean[] visited;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		v=sc.nextInt();
		e=sc.nextInt();
		
		for(int i=0;i<=v;i++)
			graph.add(new ArrayList<>());
		
		for(int i=0;i<e;i++) {
			int a=sc.nextInt();
			int b=sc.nextInt();
			int c=sc.nextInt();
			
			graph.get(a).add(new Node_1197(b,c));
			graph.get(b).add(new Node_1197(a,c));
		}
		
		System.out.println(prim());
	}

	/*
	 * Prim 방식
	 * - 인접 행렬 방식
	 * - 우선순위 큐에 시작 정점 1을 삽입하고 시작
	 * - 연결괸 정점의 모든 간선들 중 방문하지 않은 노드 탐색, 우선순위 큐에 삽입
	 * - 모든 노드를 방물 할때까지 반복 
	 */
	static int prim() {
		PriorityQueue<Node_1197> pq=new PriorityQueue<>();
		pq.add(new Node_1197(1,0));
		visited=new boolean[v+1];
		
		int result=0;
		int count=0;
		
		while(!pq.isEmpty()) {
			Node_1197 now=pq.poll();
			
			if(visited[now.node]) // 이미 방문한 노드라면 넘어간다.
				continue;
			
			result+=now.weight; // 가중치 합  
			count++; // 방문 노드 개수
			visited[now.node]=true;
			
			// 모든 노드를 방문했다면 종료
			if(count==v)
				return result;
			
			// now와 인접한 노드 탐색
			for(int i=0;i<graph.get(now.node).size();i++) {
				Node_1197 next=graph.get(now.node).get(i);
				
				if(visited[next.node])
					continue;
				
				pq.add(next);
			}
		}
		
		return result;
	}
}
