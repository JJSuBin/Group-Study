import java.io.*;
import java.util.*;

public class BaekJoon_11000 {
	
	public static void main(String[] args) throws IOException  {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		int[][] time=new int[n][2];
		
		for(int i=0;i<n;i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			
			time[i][0]=Integer.parseInt(st.nextToken()); // 시작 시간
			time[i][1]=Integer.parseInt(st.nextToken()); // 종료 시간
		}
		
		Arrays.sort(time,new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// 시작 시간이 같다면 종료 시간이 이른 순서대로 정렬
				if(o1[0]==o2[0])
					return Integer.compare(o1[1],o2[1]);
				
				// 기본적으로 시작 시간이 빠른 순서대로 정렬
				return Integer.compare(o1[0],o2[0]);
			}
		});
		
		PriorityQueue<Integer> pq=new PriorityQueue<>();
		pq.add(time[0][1]); // 첫 번째 수업의 종료 시간 저장
		
		// 두 번째 수업부터 탐색
		for(int i=1;i<n;i++) {
			 /*
			  * 다음 수업 시작시간이 이전 수업의 종료시간보다 크다면 큐에있는 수업 삭제 
			  * == 같은 강의실 사용 가능
			  */
			if(time[i][0]>=pq.peek())
				pq.poll();
			
			// 현재 수업의 종료 시간을 큐에 삽입
			pq.add(time[i][1]);
		}
		
		System.out.println(pq.size()); // 큐에 남아있는 데이터의 개수가 필요한 강의실의 수
		br.close();
	}

}
