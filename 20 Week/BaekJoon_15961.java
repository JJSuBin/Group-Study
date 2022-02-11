import java.util.*;

public class BaekJoon_15961 {
	static int[] food, visited;
	static int n,d,k,c,result=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt(); // 벨트에 놓인 접시의 수
		d=sc.nextInt(); // 초밥 가지의 수
		k=sc.nextInt(); // 연속해서 먹는 접시의 수
		c=sc.nextInt(); // 쿠폰 번호
		
		food=new int[n]; // 밸트에 놓인 초밥의 번호
		visited=new int[d+1]; // 각 번호의 초밥을 먹었는지 확인하는 방문 체크 배열
		
		for(int i=0;i<n;i++)
			food[i]=sc.nextInt();
		
		sliding_window();
		
		System.out.println(result);
	}
	
	// 연속 k개의 초밥을 먹었을 때 가장 많은 종류를 먹을 수 있는 수 -> 슬라이딩 윈도우로 구하기
	static void sliding_window() {
		int eat=0;
		
		// 일단 차례대로 k개의 초밥을 먹는다
		for(int i=0;i<k;i++) {
			if(visited[food[i]]==0) // 아직 초밥을 먹지 않았다면
				eat++; // 먹은 가짓수 +1
			
			visited[food[i]]++; // 먹은 초밥의 번호에 해당하는 방문 배열 값 +1
		}
		
		result=eat;
		
		// 연속적인 k개의 집단을 오른쪽으로 한칸씩 밀면서 값 갱신
		for(int i=1;i<n;i++) {
			if(result<=eat) {
				if(visited[c]==0)
					result=eat+1;
				else
					result=eat;
			}
			
			// 이전 값(=가장 왼쪽에 있는 값) 집단에서 빼주기
			visited[food[i-1]]--; 
			/*
			 * i번호의 초밥을 집단에서 뺐을때 visited 값이 0이 됐다는 의미는
			 * i번호의 초밥을 중복적으로 먹지 않았다는 의미 -> 먹은 가짓수 -1 해주어야 한다. 
			 */
			if(visited[food[i-1]]==0) 
				eat--;
			
			/*
			 * 현재 k개의 집단 중 가장 왼쪽 값을 가리키고 있기 때문에  
			 * 다음 칸의 값을 포함시키기 위해서는 i+k-1을 해주어야 한다. 
			 * -1은 배열 인덱스가 0부터 시작하기 때문, 추가적으로 벨트는 원형으로 이어져 있기 때문에
			 * %n한 값을 사용해야 인덱스 초과 오류가 발생하지 않는다. 
			 */
			if(visited[food[(i+k-1)%n]]==0) // 새롭게 집단에 추가된 초밥 번호를 먹은 적이 없다면
				eat++; // 가짓수 증가
			visited[food[(i+k-1)%n]]++; // visited 값 증가
		}
	}
}
