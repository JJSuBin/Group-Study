import java.util.*;
import java.io.*;

public class BaekJoon_11279 {
	// 큰 값부터 출력해야 하기 때문에 최대힙으로 바꿔주기
	static PriorityQueue<Integer> pq=new PriorityQueue<>(Collections.reverseOrder());
	static int n;
	public static void main(String[] args) throws IOException {
		// Scanner 사용하면 시간초과!
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int n=Integer.parseInt(br.readLine());
		
		while(n-->0) {
			int x=Integer.parseInt(br.readLine());
			
			// x가 0이면서
			if(x==0) {
				// 큐가 비어있으면 0 출력
				if(pq.isEmpty())
					System.out.println(0);
				// 큐에 값이 있으면 가장 큰 값 출력
				else
					System.out.println(pq.poll());
			}
			
			// x가 자연수라면 큐에 삽입
			else 
				pq.add(x);
		}	
	}
}
