import java.util.*;

public class 주차_요금_계산 {
	/*
	 * fees[0] : 기본시간, fees[1] : 기본요금, fees[2] : 단위시간, fees[3] : 단위요금
	 * records : 시각, 차량번호, IN/OUT 형식
	 * 
	 */
	public static int[] solution(int[] fees, String[] records) {
	    Map<String, Integer> parking=new HashMap<>(); // 현재 주차중인 차 리스트
	    Map<String, Integer> times=new HashMap<>(); // 각 차의 누적 시간
	    ArrayList<String> cars=new ArrayList<>(); // 입력되는 모든 차의 번호를 저장하는 리스트
	    
	    for(String record:records) {
	    	String[] command=record.split(" "); // 시각, 차량번호, IN/OUT 순
	    	int time=get_Time(command[0]); // 시간을 분으로 변환한 숫자
	    	String car=command[1]; // 차번호
	    	
	    	if(!cars.contains(car)) { // 처음 입력되는 차라면
	    		cars.add(car); // 리스트에 추가
	    		times.put(car, 0);
	    	}
	    	
	    	if(parking.containsKey(car)) { // 현재 주차 되어있는 차라면 -> 출차
	    		// 이전 주차 누적 시간에 현재 출차되기 까지의 주차 시간을 더해 저장한다.
	    		times.put(car, times.get(car)+(time-parking.get(car)));
	    		parking.remove(car);
	    	}
	    	else // 주차되지 않은 차라면
	    		parking.put(car, time); // 차 번호와 입차 시간을 저장
	    }
	    
	    int[] answer=new int[cars.size()];
    	Collections.sort(cars);
    	
    	int last_Time=get_Time("23:59"); 
    	for(int i=0;i<cars.size();i++) {
    		answer[i]=fees[1]; // 우선 기본요금 누적
    		String car=cars.get(i);
    		
    		int time=times.get(car)-fees[0]; // 누적시간 중 기본요금 시간 빼주기
    		
    		if(parking.containsKey(car)) // 아직 출차가 안됐다면 마지막 시간으로 정산
    			time+=(last_Time-parking.get(car));
    		
    		if(time>0) // 기본요금을 정산하고도 시간이 남았다면 남은 시간 정산
    			answer[i]+=(Math.ceil(time/(fees[2]*1.0))*fees[3]);
    	}
	    return answer;
	    
	}
	
	public static int get_Time(String time) {
		String[] temp=time.split(":"); // 00:00 형식의 시간을 시, 분으로 쪼갬
		
		// 00시 00분을 분으로 바꿔 return
		return Integer.parseInt(temp[0])*60+Integer.parseInt(temp[1]);
	}
}
