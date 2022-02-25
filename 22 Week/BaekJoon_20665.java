import java.util.*;

class Time_20665 implements Comparable<Time_20665>{
	int start, end;
	
	public Time_20665(int start, int end) {
		this.start=start;
		this.end=end;
	}
	
	@Override
	public int compareTo(Time_20665 o) {
		// TODO Auto-generated method stub
		if(this.start==o.start)
			return this.end-o.end;
		
		return this.start-o.start;
	}
}

public class BaekJoon_20665 {
	static int n,t,p;
	static Time_20665[] time;
	static boolean[][] isSeated; // isSeated[i][j] : i분에 j자리에 앉은 사람이 있는지 체크하는 배열 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt(); // 좌석 개수 
		t=sc.nextInt(); // 예약 개수
		p=sc.nextInt(); // 좋아하는 좌석 번호
		
		time=new Time_20665[t];
		isSeated=new boolean[transform("21")+100][n+1]; // 독서실은 9시 부터 21시까지만 운영
		
		for(int i=0;i<t;i++) {
			int s=0, e=0;
			
			String start=sc.next();
			s+=transform(start.substring(0,2)); // 시작 시간을 분으로 바꾸기
			s+=Integer.parseInt(start.substring(2,4)); // 분은 그대로 더해주기
			
			String end=sc.next();
			e+=transform(end.substring(0,2));
			e+=Integer.parseInt(end.substring(2,4));
			
			time[i]=new Time_20665(s,e);
		}
		
		Arrays.sort(time); // 시작 시간이 빠른 순서부터 정렬
		
		for(int i=0;i<t;i++) {
			Time_20665 now=time[i];
			
			int s=now.start;
			int e=now.end;
			int seat=find_seat(s);
			
			for(int j=s;j<e;j++)
				isSeated[j][seat]=true;
		}
		
		int result=0;
		int open=transform("9");
		int close=transform("21");
		
		// p번째 좌석에 앉을 수 있는 횟수 count
		for(int i=open;i<close;i++)
			if(!isSeated[i][p])
				result++;
		
		System.out.println(result);
	}
	
	// min 시각에 앉을 수 있는 자리를 찾는 함수
	static int find_seat(int min) {
		int max_dis=0, seat=0;
		
		for(int i=1;i<=n;i++) {
			if(!isSeated[min][i]) {
				int dis=cal_distance(min,i);
				
				// 거리가 가장 먼 좌석을 찾아 좌석 번호 반환
				if(max_dis<dis) {
					max_dis=dis;
					seat=i;
				}
			}
		}
		
		return seat;
	}
	
	// min 시간에 num을 기준으로 가장 가까운 좌석의 거리 -> 이 중 거리가 가장 먼 좌석을 찾아야 함
	static int cal_distance(int min, int num) {
		int min_dis=110;
		for(int i=1;i<=n;i++) {
			if(i==num)
				continue;
			if(isSeated[min][i]) {
				int dis=Math.abs(i-num);
				
				if(min_dis>dis)
					min_dis=dis;
			}
		}
		return min_dis;
	}
	
	// 시간을 분으로 바꿔주는 함수
	static int transform(String str) {
		return Integer.parseInt(str)*60;
	}
}
