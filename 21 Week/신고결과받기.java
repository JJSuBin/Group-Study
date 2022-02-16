import java.util.*;

public class 신고결과받기 {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer=new int[id_list.length];
        /*
         * id_list : 유저 목록
         * report : (신고 한 사람, 신고 당한 사람)이 저장되어 있는 배열
         * k : 게시판 이용이 정지되는 신고 횟수 
         */
        
        Map<String, Integer> count=new HashMap<>(); // 각 유저마다 신고당한 횟수 저장
        Map<String, ArrayList<String>> map=new HashMap<>(); // 각 유저마다 자신을 신고한 사람 리스트 저장
        
        // 초기화
        for(int i=0;i<id_list.length;i++) {
        	count.put(id_list[i],0); // 각 유저의 신고 횟수를 0으로 초기화
        	map.put(id_list[i], new ArrayList<>());
        }
        
        // 신고한 사람들
        for(String name:report) {
        	String[] temp=name.split(" ");
        	String from=temp[0]; // 신고 한 사람
        	String to=temp[1]; // 신고 당한 사람
        	
        	ArrayList<String> list=map.get(to); // to를 신고한 사람이 저장된 연결리스트
        	if(list.contains(from)) // from이 이미 to를 신고한 사람이라면 넘어간다.
        		continue;
        	
        	list.add(from);
        	map.put(to, list); // from 추가해 map 갱신
        }
        
        for(String name:map.keySet()) {
        	// list에는 name을 신고한 사람들의 리스트가 저장된다.
        	ArrayList<String> list=map.get(name); 
        	if(list.size()>=k) { // 신고한 사람 수가 k명을 넘는다면
        		for(String user:list) { // 신고한 사람들 메일 수 증가
        			int temp=count.get(user)+1;
            		count.put(user, temp);
        		}
             }
        }
        
        int i=0;
        for(String name:id_list) 
        	answer[i++]=count.get(name);
        
        return answer;
    }
}
