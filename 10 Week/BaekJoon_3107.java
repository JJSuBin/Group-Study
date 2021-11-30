import java.util.*;

public class BaekJoon_3107 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		String input=sc.next();
		String[] answer=new String[8];
		Arrays.fill(answer,"0000");
		
		if(input.contains("::")) { // 입력된 문자열에 ::이 포함된 경우
			String[] temp= {"",""}; 
			int count=0;
			
			// ::를 기준으로 두 묶음의 문자열로 분해
			for(String str:input.split("::"))
				temp[count++]=str;
			
			String[] left=temp[0].split(":"); // ::를 기준으로 왼쪽 문자열을 :를 기준으로 재분해
			String[] right=temp[1].split(":"); // ::를 기준으로 오른쪽 문자열을 :를 기준으로 재분해
			
			// 각 주소를 4자리에 맞게 0으로 채우기(왼쪽)
			for(int i=0;i<left.length;i++)
				answer[i]=make_Address(left[i]);
			
			// 각 주소를 4자리에 맞게 0으로 채우기(오른쪽)
			int end=7-right.length;
			int index=right.length-1;
			for(int i=7;i>end;i--)
				answer[i]=make_Address(right[index--]);
		}
		else { // 입력된 문자열에 ::이 포함되지 않은 경우
			String[] address=input.split(":"); // :를 기준으로 문자열을 재 분해 후 각 자리를 4자리로 맞춰주기
			for(int i=0;i<address.length;i++)
				answer[i]=make_Address(address[i]);
		}
		
		StringBuilder result=new StringBuilder();
        for (int i=0;i<8;i++) {
            result.append(answer[i]);
            
            if (i<7) 
                result.append(":");
        }
        System.out.println(result);
	}
	
	// 문자열 str을 4자리에 맞춰 나머지 부분을 0으로 채우는 함수
	static String make_Address(String str) {
		String address="";
		int index=4-str.length();
		
		for(int i=0;i<index;i++)
			address+="0";
		
		address+=str;
		
		return address;
	}
}
