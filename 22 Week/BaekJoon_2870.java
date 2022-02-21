import java.math.*;
import java.util.*;

public class BaekJoon_2870 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int t=sc.nextInt();
		
		ArrayList<BigInteger> arr=new ArrayList<>(); // 숫자를 넣어줄 리스트
		String[] str;
		
		for(int i=0;i<t;i++) {
			/*
			 * 정규 표현식 "\D" : 숫자가 아닌 항목을 일치시킨다.
			 * "\D"를 사용해 숫자들만 연결리스트에 저장 
			 */
			str=sc.next().split("\\D");
			for(int j=0;j<str.length;j++) {
				if(!str[j].equals(""))
					arr.add(new BigInteger(str[j]));
			}
		}
		
		Collections.sort(arr); // 오름차순으로 정렬
		for(int i=0;i<arr.size();i++)
			System.out.println(arr.get(i));
	}

}
