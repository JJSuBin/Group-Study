import java.util.*;

public class BaekJoon_1713 {
	static int n,m,last=0;
	static HashMap<Integer,Integer> map=new HashMap<>(); // (학생번호,추천횟수)
	static HashMap<Integer,Integer> time=new HashMap<>(); // (학생번호,등록된 시간)
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		
		// 이때 m이 등록된 시간을 의미!
		for(int i=0;i<m;i++) {
			int num=sc.nextInt();
			
			// 이미 사진틀에 게제된 학생이라면 추천수만 증가
			if(map.containsKey(num))
				map.put(num,map.get(num)+1);
			
			// 사진틀에 처음 등록되는 학생이면서
			else {
				// 사진틀에 빈공간이 없는 경우
				if(map.size()>=n) {
					int minfavor=Integer.MAX_VALUE; // 가장 작은 추천수 찾기
					int minnum=Integer.MAX_VALUE; // 삭제할 학생 번호
					
					// 추천수가 가장 적은 학생 찾기(map 전체 탐색)
					for(Integer key:map.keySet()) {
						if(minfavor>map.get(key)) {
							minfavor=map.get(key);
							minnum=key;
						}
					}
					
					// 가장 적은 추천수를 가지면서 게제된 시간이 오래된 학생을 찾는다(map 전체 탐색)
					for(Integer key:map.keySet()) {
						if(minfavor==map.get(key)) {
							// time의 value 값이 작을수록 일찍 게재된 학생
							if(time.get(minnum)>time.get(key))
								minnum=key;
						}
					}
					
					// 해당 학생 삭제
					map.remove(minnum);
					time.remove(minnum);
				}
				
				// 새로운 학생 등록
				map.put(num,1); 
				time.put(num,i); // 사진틀에 게제된 시간도 저장
			}
		}
		
		// 최종적으로 사진틀에 사진이 게재된 학생 번호를 증가하는 순서대로 출력하기 위해 연결리스트 사용
		List<Integer> list=new ArrayList<>(map.keySet());
		Collections.sort(list);
		for(Integer key:list)
			System.out.print(key+" ");
	}

}
