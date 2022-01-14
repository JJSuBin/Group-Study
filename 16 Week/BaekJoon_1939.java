class Point_1939 {
	int island, weight;
	
	public Point_1939(int island, int weight) {
		this.island=island;
		this.weight=weight;
	}
}
public class BaekJoon_1939 {
	static int n,m;
	static boolean[] visited;
	static ArrayList<ArrayList<Point_1939>> graph=new ArrayList<>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		
		for(int i=0;i<=n;i++)
			graph.add(new ArrayList<>());
		
		int max=0;
		for(int i=0;i<m;i++) {
			int a=sc.nextInt();
			int b=sc.nextInt();
			int w=sc.nextInt();
			
			max=Math.max(max, w); // 이분탐색의 right가 될 값 구하기(중량중 최댓 값)
			graph.get(a).add(new Point_1939(b,w));
			graph.get(b).add(new Point_1939(a,w));
		}
		
		int start=sc.nextInt(); // 시작 위치
		int end=sc.nextInt(); // 도착 위치
		
		// 이분탐색
		int left=0;
		int right=max;
		
		while(left<=right) {
			int mid=(left+right)/2;
			visited=new boolean[n+1];
			
			// mid 중량으로 start에서 end까지 이동할 수 있다면 left 증가 -> 중량이 더 무거워 짐
			if(Cross_Bridge(start,end,mid)) 
				left=mid+1;
			// mid 중량으로 start에서 end까지 이동할 수 없다면 right 감소 -> 중량이 더 가벼워 짐
			else
				right=mid-1;
		}
		
		System.out.println(right);
	}

	// start에서 end까지 이동할 수 있는지 확인하는 함수
	static boolean Cross_Bridge(int start, int end, int mid) {
		Queue<Integer> q=new LinkedList<>();
		q.add(start);
		visited[start]=true;
		
		while(!q.isEmpty()) {
			int now=q.poll();
			
			if(now==end) // end에 도달했다면 성공
				return true;
			
			for(Point_1939 next:graph.get(now)) {
				// 다음 섬이 아직 방문하지 않았으며 mid 중량이 다리를 건널수 있다면
				if(!visited[next.island]&&mid<=next.weight) {
					visited[next.island]=true; // 방문 처리
					q.offer(next.island); // 큐에 삽입
				}
			}
		}
		return false;
	}
}
