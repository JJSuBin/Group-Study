import java.util.*;

class Node_9370 implements Comparable<Node_9370> {
	int node, weight;
	
	public Node_9370(int node, int weight) {
		this.node=node;
		this.weight=weight;
	}

	@Override
	public int compareTo(Node_9370 o) {
		// TODO Auto-generated method stub
		return this.weight-o.weight;
	}
}

public class BaekJoon_9370 {
	static final int INF=(int)1e9;
	static int T,n,m,t,s,g,h;
	static ArrayList<ArrayList<Node_9370>> graph;
	static int[] d;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		T=sc.nextInt();
		
		for(int k=0;k<T;k++) {
			n=sc.nextInt(); // 도시 개수
			m=sc.nextInt(); // 도로 개수 
			t=sc.nextInt(); // 목적지 후보 개수
			
			graph=new ArrayList<>();
			
			s=sc.nextInt(); // 시작지점
			g=sc.nextInt(); // 교차로
			h=sc.nextInt(); // 교차로
			
			// 초기화
			for(int i=0;i<=n;i++)
				graph.add(new ArrayList<>());
			
			for(int i=0;i<m;i++) {
				int a=sc.nextInt();
				int b=sc.nextInt();
				int w=sc.nextInt();
				
				// 양방향
				graph.get(a).add(new Node_9370(b,w));
				graph.get(b).add(new Node_9370(a,w));
			}
			
			PriorityQueue<Integer> pq=new PriorityQueue<>();
			
			int[] arr=new int[t]; // 목적지 입력
			for(int i=0;i<t;i++)
				arr[i]=sc.nextInt();
			
			for(int d:arr) {
				// result1, result2는 g와 h의 교차로를 거쳐서 가는 경우
				long result1=dijkstra(s,g)+dijkstra(g,h)+dijkstra(h,d);
				long result2=dijkstra(s,h)+dijkstra(h,g)+dijkstra(g,d);
                long result3=dijkstra(s,d); // 시작지 -> 도착지 최단경로
                
                // result1과 result2중 작은 값과 최단경로 값이 같다면 가능한 목적지
                if(Math.min(result1, result2)==result3)
                	pq.add(d);
			}
			
			while(!pq.isEmpty())
				System.out.print(pq.poll()+" ");
			
			System.out.println();
		}
	}
	
	static int dijkstra(int start, int end) {
		// 최단거리 테이블은 여기서 초기화 해야함, 여러면 다익스트라 함수를 호출하기 때문
		d=new int[n+1];
		Arrays.fill(d,INF);
		
		PriorityQueue<Node_9370> pq=new PriorityQueue<>();
		pq.offer(new Node_9370(start,0));
		d[start]=0;
		
		while(!pq.isEmpty()) {
			Node_9370 node=pq.poll();
			
			int now=node.node;
			int dis=node.weight;
			
			if(d[now]>dis) // 이미 처리가 된 경우
				continue;
			
			// 최단거리 갱신
			for(Node_9370 next:graph.get(now)) {
				int cost=d[now]+next.weight;
				
				if(cost<d[next.node]) {
					d[next.node]=cost;
					pq.offer(new Node_9370(next.node,cost));
				}
			}
		}
		
		return d[end];
	}
}
