import java.util.*;

public class BaekJoon_2965 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int[] point=new int[3];
		int count=0;
		
		point[0]=sc.nextInt();
		point[1]=sc.nextInt();
		point[2]=sc.nextInt();
		
		Arrays.sort(point);
		
		if(point[2]-point[1]>=point[1]-point[0])
			System.out.println(point[2]-point[1]-1);
		else
			System.out.println(point[1]-point[0]-1);
	}

}
