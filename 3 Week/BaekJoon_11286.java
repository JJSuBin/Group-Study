import java.io.*;
import java.util.*;

public class BaekJoon_11286 { 
	public static void main(String[] args) throws IOException {
		// Scanner 사용하면 시간초과!
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		/*
		 * 우선순위 큐 재정의
		 * 두 수의 절댓값이 같다면 값의 크기를 비교하여 정렬한다. (-2와 2를 비교하면 -2가 높은 우선순위)
		 * 주 수의 절댓값이 같지 않다면 절댓값을 비교하여 정렬한다. (-1과 3을 비교하면 -1이 높은 우선순위)
		 */
		PriorityQueue<Integer> pq=new PriorityQueue<>((o1,o2)->
		Math.abs(o1)==Math.abs(o2)?Integer.compare(o1,o2):Integer.compare(Math.abs(o1), Math.abs(o2)));
		
		int n=Integer.parseInt(br.readLine());
		
		while(n-->0) {
			int x=Integer.parseInt(br.readLine());
			
			if(x==0) {
				if(pq.isEmpty())
					System.out.println(0);
				else
					System.out.println(pq.poll());
			}
			
			else 
				pq.add(x);
		}
	}

}
