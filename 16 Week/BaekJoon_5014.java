import java.util.*;

public class BaekJoon_5014 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int f=sc.nextInt(); // 건물의 총 층수
		int s=sc.nextInt(); // 강호가 있는 층수
		int g=sc.nextInt(); // 스타트링크가 있는 층수
		int u=sc.nextInt(); // 한번 누를때 올라가는 층수
		int d=sc.nextInt(); // 한번 누를때 내려가는 층 수
		
		boolean check=false; // g층에 도달했는지 여부를 체크하기 위한 변수
		int[] visited=new int[f+1]; 
		Queue<Integer> q=new LinkedList<>();
		q.add(s);
		visited[s]=1;
		
		while(!q.isEmpty()) {
			int now=q.poll();
			
			if(now==g) { // g층에 도달했다면 while문 종료
				check=true;
				break;
			}		
			if(now+u<=f&&visited[now+u]==0) { // u버튼을 누른 경우
				visited[now+u]=visited[now]+1;
				q.offer(now+u);
			}		
			if(now-d>0&&visited[now-d]==0) { // d버튼을 누른 경우
				visited[now-d]=visited[now]+1;
				q.offer(now-d);
			}
		}	
		
		if(check) // 시작 visited 값을 1로 설정했기 때문에 -1해주기
			System.out.println(visited[g]-1);
		else
			System.out.println("use the stairs");
	}
}
