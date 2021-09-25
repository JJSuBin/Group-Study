import java.util.*;

class Game_2503 {
	String number;
	int strike,ball;
	
	public Game_2503(String number, int strike, int ball) {
		this.number=number;
		this.strike=strike;
		this.ball=ball;
	}
}

public class BaekJoon_2503 {
	static ArrayList<Game_2503> list=new ArrayList<>();
	static int count=0;
	static boolean[] visited=new boolean[10];
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		
		// 영수가 생각하고 있는 답안 저장
		for(int i=0;i<n;i++) {
			String str=sc.next();
			int s=sc.nextInt();
			int b=sc.nextInt();
			
			list.add(new Game_2503(str,s,b));
		}
		
		dfs(0,"");
		
		System.out.println(count);
	}
	static void dfs(int depth, String num) {
		// 3자리의 숫자가 선택됐다면 해당 숫자가 가능한 수인지 체크
		if(depth==3) {
			if(isPossible(num))
				count++;
			
			return;
		}
		
		// 가능한 3자리수 모두 탐색
		for(int i=1;i<=9;i++) {
			if(!visited[i]) {
				visited[i]=true;
				dfs(depth+1,num+i);
				visited[i]=false;
			}
		}
	}
	
	// 3자리 숫자인 num이 가능한 답안인 조건에 모두 만족하는지 체크하는 함수
	static boolean isPossible(String num) {
		for(int i=0;i<list.size();i++) {
			Game_2503 check=list.get(i);
			int st=0,ba=0; // 스트라이크, 볼의 개수 count 변수
			
			for(int j=0;j<3;j++) {
				// 숫자와 자릿수까지 같다면 스트라이크
				if(check.number.charAt(j)==num.charAt(j))
					st++;
				// 숫자만 같고 자릿수는 다르다면 볼
				else if(check.number.contains(num.charAt(j)+""))
					ba++;
			}
			
			// 가능 답안과 스트라이크 또는 볼의 갯수가 다르다면 해당 경우는 불가능한 수
			if(check.strike!=st||check.ball!=ba)
				return false;
		}
		return true;
	}
}
