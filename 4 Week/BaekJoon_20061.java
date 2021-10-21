import java.util.*;

public class BaekJoon_20061 {
	static int[][] blue=new int[4][6];
	static int[][] green=new int[6][4];
	static int score=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		
		int n=sc.nextInt();
		while(n-->0) {
			int t=sc.nextInt();
			int x=sc.nextInt();
			int y=sc.nextInt();
			
			moveblock(t,x,y); // t 유형의 블록을 쌓기
			getscore(); // 한 행 or 열이 가득 찼다면 점수 올리기
			push_blue(light_blue()); // 연한 파란색 칸에 찬 열 수만큼 전체 블록 내리기
			push_green(light_green()); // 연파 초록색 칸에 찰 행 수만큼 전체 블록 내리기
		}
		System.out.println(score);
		System.out.println(count());
	}
	
	// t 유형의 블록일 경우 블록이 위치할 수 있는 곳으로 이동
	static void moveblock(int t, int x, int y) {
		int index; // 파란 영역은 열을 의미, 초록 영역은 행을 의미
		
		switch(t) {
		// 1칸짜리 블록일 경우
		case 1:
			blue[x][0]=1; // 시작 위치
			index=1;
			// 열을 늘려가면서 블록이 위치할 수 있는 가장 끝까지 이동
			while(index<6&&blue[x][index]==0) {
				// 블록 한 칸 옆으로 밀기
				blue[x][index-1]=0;
				blue[x][index]=1;
				index++;
			}
			
			green[0][y]=1; // 시작 위치
			index=1;
			// 행을 늘려가면서 블록이 위치할 수 있는 가장 끝까지 이동
			while(index<6&&green[index][y]==0) {
				// 블록 한 칸 내리기
				green[index-1][y]=0; 
				green[index][y]=1;
				index++;
			}
			break;
			
		// ㅡ 모양 블록인 경우	
		case 2:
			blue[x][0]=1;
			blue[x][1]=1;
			index=2;
			// 파란 칸의 경우 ㅡ 모양이 옆으로 이동하기 때문에 블록 한칸씩 미뤄가며 검사
			while(index<6&&blue[x][index]==0) {
				blue[x][index-2]=0;
				blue[x][index]=1;
				index++;
			}
			
			green[0][y]=1;
			green[0][y+1]=1;
			index=1;
			// 초록 칸의 경우 ㅡ 모양이 밑으로 이동하기 때문에 이동할 두칸 모두 비어있는지 검사
			while(index<6&&green[index][y]==0&&green[index][y+1]==0) {
				green[index-1][y]=0;
				green[index-1][y+1]=0;
				green[index][y]=1;
				green[index][y+1]=1;
				index++;
			}
			break;
		
		// ㅣ 모양 블록인 겨우
		case 3:
			blue[x][0]=1;
			blue[x+1][0]=1;
			index=1;
			// 파란 칸의 경어 ㅣ 모양이 옆으로 이동하기 때문에 이동할 두 칸 모두 비어있는지 검사
			while(index<6&&blue[x][index]==0&&blue[x+1][index]==0) {
				blue[x][index-1]=0;
				blue[x+1][index-1]=0;
				blue[x][index]=1;
				blue[x+1][index]=1;
				index++;
			}
			
			green[0][y]=1;
			green[1][y]=1;
			index=2;
			// 초록 칸의 경우 ㅣ 모양이 아래로 이동하기 때문에 한칸씩만 미뤄가며 검사
			while(index<6&&green[index][y]==0) {
				green[index-2][y]=0;
				green[index][y]=1;
				index++;
			}
			break;
		}
	}
	
	// 파란 칸은 열, 초록 칸은 행이 가득 차면 해당 줄을 비우고 점수 1점을 얻는다.
	static void getscore() {
		// 파란 칸의 맨 오른쪽 열부터 탐색
		for(int i=5;i>=2;i--) {
			int count=0;
			
			for(int j=0;j<4;j++) {
				if(blue[j][i]==0)
					break;
				else
					count++;
			}
			// 한 열의 모든 행에 블록이 있다면
			if(count==4) {
				score++;
				move_blue(i); // 열 하나 오른쪽으로 이동
				/*
				 * move_blue 함수를 통해 i열 이전의 열들이 한 줄 옆으로 이동했기 때문에
				 * 이를 다시 상쇄시켜주기 위해 i에 +1을 해주어야 한다. 
				 */
				i++;
			}
		}
		
		// 초록 칸의 맨 아래 행부터 탐색
		for(int i=5;i>=2;i--) {
			int count=0;
			for(int j=0;j<4;j++) {
				if(green[i][j]==0)
					break;
				else
					count++;
			}
			if(count==4) {
				score++;
				move_green(i);
				/*
				 * move_green 함수를 통해 i행 이전의 행들이 한 줄 밑으로 내려왔기 때문에
				 * 이를 다시 상쇄시켜주기 위해 i에 +1을 해주어야 한다. 
				 */
				i++;
			}
		}
	}
	
	// row 열 이전의 블록을 오른쪽으로 당기는 함수
	static void move_blue(int row) {
		for(int i=row;i>0;i--) 
			for(int j=0;j<4;j++)
				blue[j][i]=blue[j][i-1];
		
	}
	// col 행 이전의 블록을 아래로 내리는 함수
	static void move_green(int col) {
		for(int i=col;i>0;i--)
			for(int j=0;j<4;j++)
				green[i][j]=green[i-1][j];
	}
	
	/*
	 * 연한 파란 열을 탐색하면서 블록이 있는 열의 갯수 count 
	 * -> 해당 열 개수만큼 블록을 오른쪽으로 당겨야함 
	 */
	static int light_blue() {
		int line=0;
		for(int i=0;i<2;i++) {
			for(int j=0;j<4;j++) {
				if(blue[j][i]==1) {
					line++;
					break;
				}
			}
		}
		return line;
	}
	
	/*
	 * 연한 초록 행을 탐색하면서 블록이 있는 열의 갯수 count 
	 * -> 해당 열 개수만큼 블록을 아래로 당겨야함 
	 */
	static int light_green() {
		int line=0;
		for(int i=0;i<2;i++) {
			for(int j=0;j<4;j++) {
				if(green[i][j]==1) {
					line++;
					break;
				}
			}
		}
		return line;
	}
	
	/*
	 * 연한 파란색의 두 열중 몇 개의 열에 블록이 있는지 count한 ligt_blue 함수를 사용하여
	 * 해당 개수만큼 전체 블록을 오른쪽으로 당기는 함수
	 */
	static void push_blue(int line) {
		// line의 개수만큼 전체 블록 오른쪽으로 당기기
		for(int i=5;i>=2;i--)
			for(int j=0;j<4;j++)
				blue[j][i]=blue[j][i-line];
		
		// 연한 파란 칸에 있는 블록들 없애주기
		for(int i=0;i<2;i++)
			for(int j=0;j<4;j++)
				blue[j][i]=0;
	}
	
	/*
	 * 연한 초록색의 두 행중 몇 개의 행에 블록이 있는지 count한 ligt_green 함수를 사용하여
	 * 해당 개수만큼 전체 블록을 아래로 당기는 함수
	 */
	static void push_green(int line) {
		// line의 개수만큼 전체 블록 아래로 내리기
		for(int i=5;i>=2;i--)
			for(int j=0;j<4;j++)
				green[i][j]=green[i-line][j];
		
		// 연한 초록 칸에 있는 블록들 없애주기
		for(int i=0;i<2;i++)
			for(int j=0;j<4;j++)
				green[i][j]=0;
	}
	
	// 파란 칸과 초록칸에 남아있는 블록의 개수 count하는 함수
	static int count() {
		int count=0;
		for(int i=0;i<4;i++) {
			for(int j=0;j<6;j++) {
				if(blue[i][j]==1)
					count++;
				if(green[j][i]==1)
					count++;
			}
		}
		return count;
	}
}
