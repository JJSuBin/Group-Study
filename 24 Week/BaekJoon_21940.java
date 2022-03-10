import java.util.*;

public class BaekJoon_21940 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		int INF=(int)1e9;
		
		// 초기화
		int[][] dis=new int[n+1][n+1];
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				if(i==j)
					dis[i][j]=0;
				else
					dis[i][j]=INF;
			}
		}
		
		// 단방향
		for(int i=0;i<m;i++) {
			int a=sc.nextInt();
			int b=sc.nextInt();
			int c=sc.nextInt();
			
			if(dis[a][b]>c)
				dis[a][b]=c;
		}
		
		int c=sc.nextInt();
		ArrayList<Integer> arr=new ArrayList<>();
		for(int i=0;i<c;i++) // 도시 번호 입력받기
			arr.add(sc.nextInt());
		
		// 플로이드 와샬 -> 각 점정에서 최단거리 구하기
		for(int k=1;k<=n;k++)
			for(int i=1;i<=n;i++)
				for(int j=1;j<=n;j++)
					dis[i][j]=Math.min(dis[i][j],dis[i][k]+dis[k][j]);
		
		int[] max=new int[n+1]; // 왕복거리 저장 배열
		int min=Integer.MAX_VALUE;
		for(int i=1;i<=n;i++) {
			// 정점 i에서 각 도시 x로 가는 왕복 거리중 최대를 구한다.
			for(int j=0;j<arr.size();j++) {
				max[i]=Math.max(max[i],dis[arr.get(j)][i]+dis[i][arr.get(j)]);
			}
			// 왕복시간들 중 최소를 구한다.
			min=Math.min(min, max[i]);
		}
		
		ArrayList<Integer> result=new ArrayList<>();
		for(int i=1;i<=n;i++)
			if(min>=max[i]) // 최솟값을 가지는 경우가 2개 이상이라면 
				result.add(i);
		
		// 오름차순으로 정렬 후 출력
		Collections.sort(result);
		
		for(int city:result)
			System.out.print(city+" ");
	}

}
