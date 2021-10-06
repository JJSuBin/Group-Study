import java.util.*;

public class BaekJoon_20055 {
	static boolean[] robot;
	static int[] belt;
	static int n,k,count=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		k=sc.nextInt(); // 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료
		belt=new int[2*n];
		robot=new boolean[n];
		
		for(int i=0;i<belt.length;i++)
			belt[i]=sc.nextInt();
		
		while(true) {
			rotatebelt();
			count++;
			moverobot();
			
			if(!check()) {
				System.out.println(count);
				break;
			}
		}
	}
	
	// 벨트와 각 칸 위에 있는 로봇이 함께 한 칸 회전하는 함수
	static void rotatebelt() {
		// 벨트 한칸 이동
		int temp=belt[belt.length-1]; // 마지막 값 임시저장
		
		for(int i=belt.length-1;i>0;i--) 
			belt[i]=belt[i-1];
		
		belt[0]=temp; // 올라오는 칸 덮어쓰기
		
		// 로봇 이동
		for(int i=robot.length-1;i>0;i--)
			robot[i]=robot[i-1];
		
		robot[0]=false; // 로봇이 올리는 곳은 반드시 비워야한다.
	}
	
	// 로봇 이동 함수
	static void moverobot() {
		// 1. 내리는 칸에서 로봇 제거해주기
		if(robot[n-1])
			robot[n-1]=false;
		
		// 2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동
		for(int i=n-2;i>=0;i--) {
			// 로봇이 있는 칸을 기준으로 다음 칸의 내구도가 1 이상이고, 로봇이 없다면 로봇 한칸 이동
			if(robot[i]&&belt[i+1]>0&&!robot[i+1]) {
				robot[i]=false;
				robot[i+1]=true;
				belt[i+1]--; // 로봇이 이동하면 내구도 감소
			}
		}
		
		// 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
		if(belt[0]>0) {
			robot[0]=true;
			belt[0]--; // 로봇이 투입되면 내구도 감소
		}
	}
	
	// 내구도가 0인 칸의 개수가 K개 이상인지 검사하는 함수
	static boolean check() {
		int check=0;
		
		for(int i=0;i<belt.length;i++)
			if(belt[i]==0)
				check++;
		
		// 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료
		if(check>=k)
			return false;
		else 
			return true;
			
	}
}
