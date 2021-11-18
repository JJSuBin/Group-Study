import java.util.*;

class Point_13549 {
	int point, time;
	
	public Point_13549(int point, int time) {
		this.point=point;
		this.time=time;
	}
}
public class BaekJoon_13549 {
	static boolean[] subin=new boolean[100001];
	static int n,k,min=Integer.MAX_VALUE;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		k=sc.nextInt();
		
		if(n>=k) // 수빈이의 위치가 더 큰 경우 한칸 뒤로만 이동 가능
			System.out.println(n-k);
		else {
			find(n);
			System.out.println(min);
		}
	}
	
	static void find(int start) {
		Queue<Point_13549> q=new LinkedList<>();
		subin[start]=true;
		q.offer(new Point_13549(start,0));
		
		while(!q.isEmpty()) {
			Point_13549 now=q.poll();
			subin[now.point]=true;
			
			// 동생의 위치에 도달하는 순간 소요시간 갱신
			if(now.point==k)
				min=Math.min(min,now.time);
			
			// 순간이동
			if(now.point*2<=100000&&!subin[now.point*2])
				q.offer(new Point_13549(now.point*2,now.time));
			// 한칸 뒤로 이동
			if(now.point-1>=0&&!subin[now.point-1])
				q.offer(new Point_13549(now.point-1,now.time+1));
			// 한칸 앞으로 이동
			if(now.point+1<=100000&&!subin[now.point+1])
				q.offer(new Point_13549(now.point+1,now.time+1));
		}
	}
}
