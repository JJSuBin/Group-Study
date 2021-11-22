import java.util.*;

class Point_15686 {
	int x,y;
	
	public Point_15686(int x, int y) {
		this.x=x;
		this.y=y;
	}
}

public class BaekJoon_15686 {
	static int[][] map;
	static int n,m,min=Integer.MAX_VALUE;
	static boolean[] visited;
	static ArrayList<Point_15686> chicken=new ArrayList<>();
	static ArrayList<Point_15686> house=new ArrayList<>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		
		map=new int[n][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				map[i][j]=sc.nextInt();
				
				if(map[i][j]==2) // 모든 치킨집의 위치 저장
					chicken.add(new Point_15686(i,j));
				
				if(map[i][j]==1) // 모든 집의 위치 저장
					house.add(new Point_15686(i,j));
			}
		}
		
		visited=new boolean[chicken.size()]; // n개의 치킨집 중 m개를 선택, 선택된 치킨집을 체크하는 배열
		
		dfs(0,0);
		System.out.println(min);
	}
	
	// m개의 치킨집을 선택하는 함수, 백트래킹
	static void dfs(int depth, int start) {
		if(depth==m) { // m개의 치킨집을 선택했다면 치킨거리 구하기
			min=Math.min(min,chicken_street());
			return;
		}
		
		for(int i=start;i<chicken.size();i++) {
			if(!visited[i]) {
				visited[i]=true;
				dfs(depth+1,i+1);
				visited[i]=false;
			}
		}
	}
	
	static int chicken_street() {
		int total=0; // 해당 경우에서 모든 치킨거리의 누적합 구하기
		
		// 모든 집에서부터 치킨집 까지의 거리 구하기
		for(int i=0;i<house.size();i++) {
			int temp=Integer.MAX_VALUE;
			
			for(int j=0;j<visited.length;j++) {
				if(visited[j]) {
					int dis=cal_distance(house.get(i).x,house.get(i).y,
							chicken.get(j).x,chicken.get(j).y); // 거리계산
					
					// m개의 치킨집 중 가장 거리가 짧은 곳이 치킨거리
					temp=Math.min(temp, dis);
				}
			}
			total+=temp; // 모든 집의 치킨거리 구하기
		}
		return total;
	}
	
	static int cal_distance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1-x2)+Math.abs(y1-y2);
	}
}
