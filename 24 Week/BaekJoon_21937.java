import java.util.*;

public class BaekJoon_21937 {
	static int n,m,count=0;
	static boolean[] visited;
	static ArrayList<ArrayList<Integer>> graph=new ArrayList<>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		
		visited=new boolean[n+1];
		for(int i=0;i<=n;i++)
			graph.add(new ArrayList<>());
		
		for(int i=0;i<m;i++) {
			int a=sc.nextInt();
			int b=sc.nextInt();
			
			graph.get(b).add(a); // 단방향
		}
		
		int x=sc.nextInt();
		dfs(x);
		
		System.out.println(count);
	}
	
	static void dfs(int x) {
		visited[x]=true;
		
		for(int next:graph.get(x)) {
			if(!visited[next]) {
				count++;
				dfs(next);
			}
		}
	}

}
