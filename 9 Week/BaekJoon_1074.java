import java.util.*;

public class BaekJoon_1074 {
	static int n,r,c;
	static int count=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		r=sc.nextInt(); // 행
 		c=sc.nextInt(); // 열
 		
 		int size=(int)Math.pow(2, n);
 		Z(r,c,size);
 		
 		System.out.println(count);
	}
	
	static void Z(int row, int col, int size) {
		if(size==1)
			return;
		
		if(row<size/2&&col<size/2) // 1사분면
			Z(row,col,size/2);
		
		else if(row<size/2&&col>=size/2) { // 2사분면
			count+=size*size/4; // 1사분면의 칸 개수 누적
			Z(row,col-size/2,size/2);
		}
		
		else if(row>=size/2&&col<size/2) { // 3사분면
			count+=(size*size/4)*2; // 1,2사분면 칸 개수 누적
			Z(row-size/2,col,size/2);
		}
		
		else { // 4사분면
			count+=(size*size/4)*3; // 1,2,3사분면 칸 개수 누적
			Z(row-size/2,col-size/2,size/2);
		}
	}
}
