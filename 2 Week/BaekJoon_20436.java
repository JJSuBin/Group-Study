import java.util.*;

public class BaekJoon_20436 {
	static Character[][] keyboard= {{'q','w','e','r','t','y','u','i','o','p'}
									,{'a','s','d','f','g','h','j','k','l','X'}
									,{'z','x','c','v','b','n','m','X','X','X'}};
	static char curR,curL;
	static int time=0; // 총 소요 시간
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		curL=sc.next().charAt(0); // 처음의 왼손 위치
		curR=sc.next().charAt(0); // 처음의 오른손 위치
		String str=sc.next(); // 출력할 문자열
		
		for(int i=0;i<str.length();i++) {
			char ch=str.charAt(i); // 입력하려고 하는 숫자
			
			int[] next=findIndex(ch); // 입력하려는 숫자의 키보드상 위치 구하기(행,열)
			
			// 오른손으로 입력해야 하는 문자인 경우
			if(ch=='y'||ch=='u'||ch=='i'||ch=='o'||ch=='p'
			 ||ch=='h'||ch=='j'||ch=='k'||ch=='l'
			 ||ch=='b'||ch=='n'||ch=='m'){
				
				int[] now=findIndex(curR); // 현재 오른손의 위치 구하기
				// 현재 오른손의 위치에서 입력하려는 숫자로 이동하는데 소요되는 시간 구하기
				time+=caltime(now[0],now[1],next[0],next[1]); 
				
				curR=ch; // 현재 위치 갱신
			}
			
			// 왼손으로 입력해야 하는 문자인 경우
			else {
				int[] now=findIndex(curL); // 현재 왼손의 위치 구하기
				// 현재 왼손의 위치에서 입력하려는 숫자로 이동하는데 소요되는 시간 구하기
				time+=caltime(now[0],now[1],next[0],next[1]);
				
				curL=ch; // 현재 위치 갱신
			}
			
			time+=1; // 입력하는데 1초 
		}
		
		System.out.println(time);
	}
	
	// a에 위치한 손가락이 b로 이동하는데 걸리는 시간
	static int caltime(int x1, int y1, int x2, int y2) {
		return Math.abs(x1-x2)+Math.abs(y1-y2);
	}
	
	// 문자 c의 키보드상의 위치 구하기
	static int[] findIndex(char c) {
		int[] index=new int[2]; 
		
		for(int i=0;i<keyboard.length;i++) {
			for(int j=0;j<keyboard[i].length;j++) {
				if(c==keyboard[i][j]) {
					index[0]=i; // 행 저장
					index[1]=j; // 열 저장
					
					break;
				}
			}
		}
		return index; // 인덱스 0에는 행, 인덱스 1에는 열 저장된 배열
	}
}
