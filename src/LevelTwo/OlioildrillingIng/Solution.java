package LevelTwo.OlioildrillingIng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/* [PCCP 기출문제] 2번 / 석유 시추 */

/* 석유 탐색 알고리즘 개요:
*
* 1. 탐색 시작:
*    - 각 열의 첫 번째 행부터 시작하여, 열 별로 석유 탐색을 진행합니다.
*    - 이미 탐색된 인덱스는 건너뛰고, 모든 행을 탐색한 후에는 다음 열로 이동합니다.
*
* 2. 방향별 탐색:
*    - 정의된 방향(변수 "dirName"와 "dirIndex")에 따라 석유를 탐색합니다.
*    - 현재 탐색 기준이 되는 인덱스에서 연속적으로 연결되어 있는 인덱스를 점진적으로 탐색하면서 석유가 없거나, 경계를 벗어나거나, 이미 탐색된 인덱스를 발견한 경우 현재 탐색 중인 해당 방향에 대한 탐색 종료
* 
* 3. 탐색 기록:
*    -  탐색을 진행하며, "searchComplete"에 탐색한 인덱스를 기록해 중복 탐색을 방지합니다.
*    - 석유가 있는 인덱스를 발견하면, 현재 그룹 배열에 저장합니다.
*
* 4. 전체 탐색:
*    - 연속적인 석유 인덱스를 더 이상 찾지 못하면, 현재 그룹 배열 내 모든 개수를 변수 내 저장.
*    - 현재 열 내 다음 인덱스 행을 찾아가면서 현재 까지의 과정을 반복하며 현재 열에 대해 모든 탐색을 완료 했을 시 다음 열로 넘어간다.
*    - 다음 열로 넘어갈 때에 현재 열에 대한 배열인 현재 그룹 배열을 ArrayList 컬렉션인 'oliGroups' 내 저장.
*    
* 5. 시추관 꼽는 위치 정하기 : 
*    - 모든 열에 대한 탐색을 마친 후 'oliGroups' 내 저장된 모든 각 배열 크기를 확인.
*    - 각 배열 중 가능 큰 배열 내 포함된 첫번 째 위치의 
*/


public class Solution {

	public static void main(String[] args) {
		// n과 m의 최소 크기를 정의합니다.
        final int MIN_N = 8;
        final int MIN_M = 8;

        // n과 m의 최대 크기를 정의합니다.
        final int MAX_N = 12;
        final int MAX_M = 12;

        // 랜덤 객체를 생성합니다.
        Random rand = new Random();

        // n과 m을 랜덤하게 생성합니다. (8 ≤ n, m ≤ 500)
        int n = rand.nextInt(MAX_N - MIN_N + 1) + MIN_N;
        int m = rand.nextInt(MAX_M - MIN_M + 1) + MIN_M;

        // land 배열을 생성하고 랜덤한 값(0 또는 1)으로 채웁니다.
        int[][] land = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                land[i][j] = rand.nextInt(2); // 0 또는 1
            }
        }

        // 생성된 land 배열을 출력합니다.
        System.out.println("Generated land array (n(세로) : " + n + ", m(가로) : " + m + "):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(land[i][j] + " ");
            }
            System.out.println();
        }
        
        int answer = Olioildrilling(land);
        System.out.println("\n결과 : " + answer);
    }
	
	static public int Olioildrilling(int [][] land) {
		
		int answer = 0;
		
		/* 탐색 순서 : 하 -> 우 -> 상 -> 좌, 각 탐색 방향에 따라 진행하기 위해 인덱스 배열인 "dirName"과 "dirIndex"을 혼용. */
		String [] dirName = {"j", "i", "j", "i"};	/* 하 -> 우 -> 상 -> 좌 순서대로 진행하기 위한 인덱스 */
		int [] dirIndex = {1, 1, -1, -1};			// 순서대로 '하' 와 '우' 방향은 + 인덱스, '상' 과 '하' 방향은 - 인덱스
		
		ArrayList<int[]> oliGroups = new ArrayList<int[]>(); /* 각 탐색 완료 시 해당 석유 포함된 좌표들을 저장한 int[] 배열
															 	을 각 그룹으로써 생성하여 "oliGroups" 내 저장. */
		ArrayList<int [][]> nowIndexs; // 각 인덱스 별 점진적, 연속적으로 탐색된 석유 존재하는 인덱스		
		
		HashMap<String, String> searchComplete = new HashMap<String, String>();	// 이미 한번 탐색이 되었던 배열 인덱스는 탐색 하지 않는다.
		
		int dirCount;	// 각 탐색 방향을 지정하기 위한 인덱스
		
		/* == 탐색 알고리즘 시작 == */
		for(int i = 0; i < land.length; i++) {		// 최대 반복 탐색 횟수는 정해진 열 크기 내에서 진행.
			
			dirCount = 0;
			
			for(int j = 0; j < land[i].length; j++) {	// 각 열 내 탐색할 때에 각 행을 각각의 탐색 시작 지점으로 설정.

				nowIndexs = new ArrayList<int [][]>();		// 현재 시점 인덱스에서 탐색한 연속적인 석유 위치를 저장하는 컬렉션.
				
				/* 현재 탐색 열 내 현재 행을 기준 인덱스로 둔, 각 방향에 대한 탐색 시작. */
				while(dirCount < dirName.length) {
					
					if(dirName[i].equals("j")) {
						
						
					} else if(dirName[i].equals("j")){
						
					}
						
				}
			}
		}
		
		return answer;
	}
}