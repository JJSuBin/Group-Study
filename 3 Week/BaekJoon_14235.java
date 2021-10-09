import java.util.*;

public class BaekJoon_14235 {
	static PriorityQueue<Integer> pq=new PriorityQueue<>(Collections.reverseOrder());
	static int n;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		
		while(n-->0) {
			int a=sc.nextInt();
			
			// a가 0이면 아이에게 가장 가치가 큰 선물 하나를 선물
			if(a==0) {
				// 만약 설매가 비었다면 -1 출력
				if(pq.isEmpty())
					System.out.println(-1);
				// 선물이 있다면 우선순위가 높은 선물 출력&삭제
				else
					System.out.println(pq.poll());
			}
			// a가 0이 아니라면 a개의 선물 충전
			else {
				for(int i=0;i<a;i++)
					pq.add(sc.nextInt());
			}
		}
	}

}
