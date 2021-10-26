import java.util.*;

public class BaekJoon_1525 {
	// 퍼즐의 상태를 일렬로 나타낸 값과 해당 값이 나오기까지 퍼즐을 이동환 횟수를 저장하는 해시맵
	static HashMap<String,Integer> map=new HashMap<>(); 
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,-1,0,1};
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String str="";
		
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				int temp=sc.nextInt();
				
				if(temp==0)
					temp=9;
				
				str+=temp;
			}
		}
		
		map.put(str,0);
		bfs(str);
		
		// 나와야 하는 퍼즐의 상태를 일렬로 나타내면 123456789가 된다.
		if(!map.containsKey("123456789")) 
			System.out.println(-1);
		else
			System.out.println(map.get("123456789"));
	}
	
	static void bfs(String start) {
		Queue<String> q=new LinkedList<>();
		q.offer(start);
		
		while(!q.isEmpty()) {
			String now=q.poll();
			
			int nine_index=now.indexOf("9"); // 일렬로 된 퍼즐에서 공백의 위치
			int x=nine_index/3; // 공백의 위치를 2차원 배열의 행으로 변환
			int y=nine_index%3; // 공백의 위치를 2차원 배열의 열로 변환
			
			// 상,하,좌,우로 공백의 위치 이동
			for(int i=0;i<4;i++) {
				int nx=x+dx[i];
				int ny=y+dy[i];
				
				// 퍼즐의 크기를 벗어난 경우
				if(nx<0||ny<0||nx>=3||ny>=3) continue;
				
				int move=nx*3+ny; // 옮길 퍼즐이 있는 위치
				
				// 공백의 위치와 퍼즐의 위치를 바꾼다.
				StringBuilder next=new StringBuilder(now);
				char temp=next.charAt(move);
				next.setCharAt(move,'9');
				next.setCharAt(nine_index, temp);
				
				// 새롭게 만들어진 퍼즐 상태가 기존에 나온적이 없는 것이라면 이동횟수 늘려 저장, 큐에 삽입
				if(!map.containsKey(next.toString())) {
					map.put(next.toString(),map.get(now)+1);
					q.offer(next.toString());
				}	
			}
			
		}
	}
}
