import java.util.*;

public class BaekJoon_19638 {
	static int n,h,t;
	static PriorityQueue<Integer> pq=new PriorityQueue<>(Collections.reverseOrder());
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		
		n=sc.nextInt(); // 거인의 수
		h=sc.nextInt(); // 센티의 키
		t=sc.nextInt(); // 뿅망치 제한 횟수
		
		// 거인키를 큰 순서대로 정렬
		for(int i=0;i<n;i++)
			pq.add(sc.nextInt());
		
		// 뿅망치 제한 횟수만큼 수행
		for(int i=0;i<t;i++) {
			int giant=pq.peek(); // 가장 키가 큰 거인 선택
			
			/*
			 * 최대힙 우선순위 큐를 사용했기 때문에 우선순위가 가장 큰(값이 가장 큰) 값보다
			 * 센티의 키가 크다면 모든 거인보다 센티의 키가 큰 경우이다.
			 * 뿅망치 제한 횟수 이전에 조건을 달성했으므로 뿅망치를 최소로 사용한 횟수(i)를 출력
			 */
			if(giant<h) {
				System.out.println("YES");
				System.out.println(i); // 뿅망치 사용 횟수
				System.exit(0);
			}
			
			/*
			 * 키가 1인 경우는 뿅망치의 영향을 받지 않기 때문에 
			 * 키가 1보다 큰 경우에만 키를 /2한 뒤 다시 삽입한다.
			 */
			else if(giant>1) {
				pq.poll();
				pq.add(giant/2);
			}
		}
		
		// 뿅망치 제한 횟수를 모두 다 사용한 경우 -> 가장 큰 거인과 센티의 키 비교
		if(pq.peek()<h) {
			System.out.println("YES");
			System.out.println(t); // 뿅망치 사용 횟수 출력
		}
		// 센티보다 큰 거인이 있는 경우
		else {
			System.out.println("NO");
			System.out.println(pq.peek()); // 가장 키가 큰 거인의 키 출력
		}
	}
}
