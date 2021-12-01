import java.util.*;

public class BaekJoon_2580 {
	static int[][] map=new int[9][9];
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		
		for(int i=0;i<9;i++)
			for(int j=0;j<9;j++)
				map[i][j]=sc.nextInt();
		
		sudoku(0,0);
	}
	
	// 백트래킹을 사용하여 빈칸에 숫자를 채워넣는 함수
	static void sudoku(int row, int col) {
		if(col==9) { // 한 열이 다 채워졌다면 다음 행으로 넘어간다.
			sudoku(row+1,0);
			return;
		}
		
		if(row==9) { // 모든 행을 다 채웠다면 결과 출력후 종료
			for(int i=0;i<9;i++) {
				for(int j=0;j<9;j++) {
					System.out.print(map[i][j]+" ");
				}
				System.out.println();
			}
			
			System.exit(0);
		}
		
		// 빈칸이라면
		if(map[row][col]==0) {
			// 1~9 사이의 숫자중 한 숫자로 채우기
			for(int i=1;i<=9;i++) {
				// 해당 숫자가 가능한지 탐색
				if(isPossible(row,col,i)) {
					map[row][col]=i;
					sudoku(row,col+1); // 가능하다면 다음 열 탐색
				}	
			}
			map[row][col]=0; // 다음 경우의 수를 위해 값 복구
			return;
		}
		sudoku(row,col+1); // 빈칸이 아니라면 다음 열로 넘어가기
	}
	
	// 채워진 숫자가 가능한지 검사하는 함수
	static boolean isPossible(int row, int col, int num) {
		for(int i=0;i<9;i++) // 같은 숫자가 행에 있다면 불가능
			if(map[row][i]==num)
				return false;
		
		for(int i=0;i<9;i++) // 같은 숫자가 열에 있다면 불가능
			if(map[i][col]==num)
				return false;
		
		int new_row=(row/3)*3; // 3*3 칸의 시작 위치 
		int new_col=(col/3)*3;
		// 3*3 칸에 같은 숫자가 있다면 불가능
		for(int i=new_row;i<new_row+3;i++)
			for(int j=new_col;j<new_col+3;j++)
				if(map[i][j]==num)
					return false;
		
		return true;		
	}
}
