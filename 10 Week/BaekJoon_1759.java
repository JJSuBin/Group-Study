import java.util.*;

public class BaekJoon_1759 {
	static char[] password;
	static boolean[] visited;
	static int l,c;
	static StringBuilder sb=new StringBuilder();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		l=sc.nextInt(); // l개 선택
		c=sc.nextInt(); 
		
		password=new char[c];
		visited=new boolean[c];
		
		for(int i=0;i<c;i++)
			password[i]=sc.next().charAt(0);
		
		Arrays.sort(password); // 알바벳 순서대로 나열해야 하므로 정렬한 배열로 백트래킹 시작
		choose(0,0);
		
		System.out.println(sb);
	}
	
	static void choose(int depth, int start) {
		if(depth==l) { // l개의 문자를 선택했다면 암호가 될 수 있는지 검사
			int mo=0, ja=0;
			String answer=""; // 검사하는 문자들을 합한 암호
			for(int i=0;i<c;i++) {
				if(visited[i]) {
					answer+=password[i];
					
					// 모음 갯수 count
					if(password[i]=='a'||password[i]=='e'||password[i]=='i'
							||password[i]=='o'||password[i]=='u')
						mo++;
					// 자음 갯수 count
					else 
						ja++;
				}
				
			}
			
			if(mo>=1&&ja>=2) // 조건에 부합한다면 리스트에 추가
				sb.append(answer).append('\n');
			
			return;
		}
		
		// c개의 문자중 중복 없이 l개의 문자 선택 -> 백트래킹
		for(int i=start;i<c;i++) {
			visited[i]=true;
			choose(depth+1,i+1);
			visited[i]=false;
		}
	}
}
