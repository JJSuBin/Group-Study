import java.util.*;

public class BaekJoon_14891 {
	static int[][] wheels=new int[4][8];
	// 4개 톱니바퀴 각각의 회전 방향이 저장되는 배열, 1:시계,-1:반시계
	static int[] direction;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		
		// 톱니바퀴 초기상태 입력
		for(int i=0;i<4;i++) {
			String str=sc.next();
			for(int j=0;j<8;j++) {
				wheels[i][j]=Integer.parseInt(str.charAt(j)+"");
			}
		}
		
		int k=sc.nextInt();
		for(int i=0;i<k;i++) {
			direction=new int[4];
			
			int num=sc.nextInt()-1; // 회전시킬 톱니바퀴 번호
			int dir=sc.nextInt(); // 1:시계, -1:반시계
			
			check(num,dir);
			rotate(direction);
		}
		
		System.out.println(cal());
	}
	
	static void rotate(int[] direction) {
		for(int i=0;i<4;i++) {
			int temp[]=new int[8]; // 임시배열
			
			int index;
			for(int j=0;j<8;j++) {
				/*
				 * direction[i] 값이 1이면(시계) 각 인덱스 +1한 값에 이전 값을 저장,
				 * direction[i] 값이 -1이면(반시계) 각 인덱스 -1한 값에 이전 값을 저장
				 * 
				 * 배열 범위를 초과하는 경우는 따로 처리해주어야 한다.
				 */
				index=j+direction[i];
				
				if(index==-1)
					index=7;
				if(index==8)
					index=0;
				
				temp[index]=wheels[i][j];
			}
			wheels[i]=temp;
		}
	}
	
	static void check(int num, int dir) {
		direction[num]=dir;
		
		int left=num-1; // 왼쪽 톱니바퀴 번호
		int right=num+1; // 오른쪽 톱니바퀴 번호
		
		// 왼쪽 톱니바퀴의 회전방향이 아직 정해지지 않았다면
		if(left>=0&&direction[left]==0)
			// 현재 톱니바퀴와 맞닿아 있는 부분과 극이 다른지 확인
			if(wheels[num][6]!=wheels[left][2])
				check(left,-dir);
		
		// 오른쪽 톱니바퀴의 회전방향이 아직 정해지지 않았다면
		if(right<4&&direction[right]==0)
			// 현재 톱니바퀴와 맞닿아 있는 부분과 극이 다른지 확인
			if(wheels[num][2]!=wheels[right][6])
				check(right,-dir);
	}
	
	static int cal() {
		int result=0;
		
		for(int i=0;i<4;i++) 
			if(wheels[i][0]==1)
				result+=Math.pow(2,i);
		
		return result;
	}
}
