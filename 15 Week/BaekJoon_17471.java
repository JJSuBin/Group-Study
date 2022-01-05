import java.util.*;

public class BaekJoon_17471 {
	static int n,result=Integer.MAX_VALUE;
	static int[] people;
	static boolean[] visited;
	static ArrayList<ArrayList<Integer>> graph=new ArrayList<>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		
		people=new int[n+1]; // 인구수 저장
		for(int i=1;i<=n;i++)
			people[i]=sc.nextInt();
		
		for(int i=0;i<=n;i++)
			graph.add(new ArrayList<>());
		
		for(int i=1;i<=n;i++) {
			int n=sc.nextInt();
			
			for(int j=0;j<n;j++) {
				int num=sc.nextInt();
				
				// 입력이 중복해서 주어지기 때문에 양방향 방식으로 저장하지 않아도 됨
				graph.get(i).add(num); 
			}
		}
		
		visited=new boolean[n+1];
		
		comb(1);
		System.out.println(result==Integer.MAX_VALUE?-1:result);
	}
	
	// n개의 구역을 두 그룹으로 나누는 함수 -> 조합 사용
	static void comb(int depth) {
		if(depth==n) {
			ArrayList<Integer> a=new ArrayList<>(); // a구역
			ArrayList<Integer> b=new ArrayList<>(); // b구역
			
			for(int i=1;i<=n;i++) {
				if(visited[i]) // visited 값이 true면 a구역
					a.add(i);
				else // false 이면 b구역
					b.add(i); 
			}
			
			if(a.size()+b.size()!=n) // 모든 구역이 포함되어 있지 않은 경우 X
				return;
			
			if(a.size()==0||b.size()==0) // 선거구는 적어도 1개의 구역은 포함해야 한다
				return;
			
			// 각 선거구에 속한 구역들이 모두 이어져있다면 두 선거구의 인구수 차 갱신
			if(Connection(a,'a')&&Connection(b,'b')) {
				int a_population=0;
				for(int i=0;i<a.size();i++)
					a_population+=people[a.get(i)];
				
				int b_population=0;
				for(int i=0;i<b.size();i++)
					b_population+=people[b.get(i)];
				
				result=Math.min(result,Math.abs(a_population-b_population));
			}
			return;
		}
		
		visited[depth]=true; // a구역에 속하는 경우(값이 true)
		comb(depth+1);
		visited[depth]=false; // b구역에 속하는 경우(값이 false)
		comb(depth+1);
		
	}
	
	// 각 그룹들이 이어져있는지 확인하는 함수
	static boolean Connection(ArrayList<Integer> arr, char team) {
		boolean[] visi=new boolean[n+1];
		Queue<Integer> q=new LinkedList<>();
		q.offer(arr.get(0)); // 시작 번호 삽입
		visi[arr.get(0)]=true; // 방문처리
		
		while(!q.isEmpty()) {
			int now=q.poll();
			
			for(int i=0;i<graph.get(now).size();i++) {
				int next=graph.get(now).get(i);
				
				if(visi[next]) continue; // 이미 처리된 구역이면 넘어간다.
				
				// 연결된 구역이 같은 선거구에 속한 구역이라면 방문처리&큐에 삽입
				if((team=='a'&&visited[next]==true)||(team=='b'&&!visited[next])) {
					q.offer(next);
					visi[next]=true;
				}
			}
		}
		
		// 모든 구역 중 연결되지 않은 구역이 있다면 해당 경우는 조건을 만족하는 경우가 X
		for(int i=0;i<arr.size();i++)
			if(!visi[arr.get(i)])
				return false;
		
		return true;
	}
}
