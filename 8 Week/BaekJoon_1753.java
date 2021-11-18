import java.util.*;

class Node_1753 implements Comparable<Node_1753> {
	int node, edge;
	
	public Node_1753(int node, int edge) {
		this.node=node;
		this.edge=edge;
	}
	
	public int compareTo(Node_1753 o) {
		return this.edge-o.edge;
	}
}

public class BaekJoon_1753 {
	static ArrayList<ArrayList<Node_1753>> graph=new ArrayList<>();
	static int v,e,start;
	static int[] d;
	static final int INF=(int)1e9;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		v=sc.nextInt();
		e=sc.nextInt();
		start=sc.nextInt();
		
		d=new int[v+1];
		
		// 그래프 초기화
		for(int i=0;i<=v;i++)
			graph.add(new ArrayList<>());
		
		Arrays.fill(d,INF); // 거리 테이블 초기화
		
		// 간선 정보 입력 받기
		for(int i=0;i<e;i++) {
			int a=sc.nextInt();
			int b=sc.nextInt();
			int c=sc.nextInt();
			
			graph.get(a).add(new Node_1753(b,c));
		}
		
		dijkstra(start);
		
		// 출력
		for(int i=1;i<=v;i++) {
			if(d[i]==INF)
				System.out.println("INF");
			else 
				System.out.println(d[i]);
		}
	}
	
	static void dijkstra(int start) {
		PriorityQueue<Node_1753> pq = new PriorityQueue<>();
		pq.offer(new Node_1753(start,0));
		d[start]=0;
		
		while(!pq.isEmpty()) {
			// 현재 노드
			Node_1753 n=pq.poll();
			int now=n.node;
			int distance=n.edge;
			
			// 기존 거리 테이블의 값이 더 작을 경우 넘어가기
			if(d[now]<distance) continue;
			
			// 현재 노드와 이웃한 노드들 탐색
			for(Node_1753 next:graph.get(now)) {
				// 이웃한 노드의 거리 테이블 값이 현재 노드를 거쳐가는 값 보다 크다면 갱신
				if(d[next.node]>d[now]+next.edge) {
					d[next.node]=d[now]+next.edge;
					pq.add(new Node_1753(next.node,d[now]+next.edge));
				}
			}
		}
	}
}
