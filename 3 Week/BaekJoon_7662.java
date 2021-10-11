import java.io.*;
import java.util.*;

public class BaekJoon_7662 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		
		int t=Integer.parseInt(br.readLine()); // 테스트케이스 개수
		while(t-->0) {
			TreeMap<Integer,Integer> map=new TreeMap<>();
			int k=Integer.parseInt(br.readLine()); // pq에 적용할 연산의 개수
			
			while(k-->0) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				String ch=st.nextToken(); // 연산자 I:삽입, D:삭제
				int n=Integer.parseInt(st.nextToken()); // 피연산자
				
				// I n -> pq에 n을 삽입
				if(ch.equals("I")) {
					/*
					 * map.getOrDefault(Key, defaultValue) 
					 * : 지정된 Key의 값을 반환하고, 없을시에는 defaultValue 반환 
					 * 
					 * 즉, treemap에 키카 n인 값이 있다면 기존 n의 value 값에 +1한 값을 저장하고,
					 * treemap에 키가 n인 값이 없다면 defaultValue인 0에 +1한 값을 저장한다.
					 */
					map.put(n,map.getOrDefault(n,0)+1);
				}
					
				// D n은 n이 1일 경우와 -1일 경우로 나뒨다.
				else {
					// 맵이 비어있는 상태에서 삭제 연산은 무시한다.
					if(map.isEmpty())
						continue;
					// n이 -1이면 가작 작은 값 삭제 = firstKey() 함수로 접근 가능
					if(n==-1) {
						int minkey=map.firstKey();
						
						// 가장 작은 수가 1개라면 해당 수 삭제
						if(map.get(minkey)==1)
							map.remove(minkey);
						// 가장 작은 수가 1개 이상이라면 1개만 삭제(개수 1개 줄이기)
						else
							map.put(minkey,map.get(minkey)-1);
						}
						
						// n이 1이면 가작 작은 큰 삭제 = lastKey() 함수로 접근 가능
						else {
							int maxkey=map.lastKey();
							
							// 가장 큰 수가 1개라면 해당 수 삭제
							if(map.get(maxkey)==1)
								map.remove(maxkey);
							// 가장 큰 수가 1개 이상이라면 1개만 삭제(개수 1개 줄이기)
							else
								map.put(maxkey,map.get(maxkey)-1);
						}
					}
				}
			
			// 최종적으로 map이 비었다면 EMPTY 출력
			if(map.isEmpty())
				sb.append("EMPTY").append('\n');
			// map에 숫자가 있다면 큰값, 작은값 출력
			else 
				sb.append(map.lastKey()+" "+map.firstKey()).append('\n');
			
		}
		
		System.out.println(sb.toString());
	}
}
