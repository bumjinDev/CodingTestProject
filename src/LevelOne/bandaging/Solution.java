package LevelOne.bandaging;
class Solution {
	
    public int solution(int[] bandage, int health, int[][] attacks) {
        
    	return 0;
    }
    
    public int run(int[] bandage, int health, int[][] attacks) {
    	
    	int answer = health; // 캐릭터 현재 체력이자 반환 값
        int healthCount = 0; // 체력 회복될 때마다 1씩 증가하여 bandage[0] 횟수 만큼 연속적으로 회복한다면 추가 회복 부여
        int attackCount = 0; // 몬스터에게 피격 당하는 타이밍은 매 횟수(매 초) 인덱스 값으로 실행하는게 아닌 배열 내 각 요소의 타이밍으로 계산.
        int check = 0; // 몬스터에게 피격 받는 타이밍에는 회복 불가.
        
        System.out.println("\n== 입력 값 확인 ==");
        System.out.println("bandage0 : " + bandage[0] + ", bandage1 : " + bandage[1] + ", bandage2 : " + bandage[2]);
        System.out.println("health(최대 값) : " + health + "\n\n");
        
        /* == 본격적인 로직 시작 == */
    
        for(int i = 1; i <= attacks[attacks.length-1][0]; i++) {  // 몬스터 피격 횟수를 기준으로 반복문 구동.
            check = 0; // 체력 회복 알고리즘 실행 전에 check 변수를 0으로 재설정
            
            /* 몬스터에게 피격 당하는 알고리즘 */
            if(attacks[attackCount][0] == i) {          // 현재 반복 횟수(반복 시간 초) 타이밍이 몬스터 공격 타이밍과 일치 시 몬스터 피격 실행.
                answer = answer - attacks[attackCount][1];      // 몬스터 피격 구현
                attackCount += 1;                               // 몬스터 피격 후 다음 피격 시점을 인덱스 'attackCount' 설정.
                
                System.out.println("디버깅 - 인덱스 : " + i + ", 피격 직후 체력 : " + answer + ", attackCount : " + attackCount +"\n");
                
                if(answer <= 0) {                               // 몬스터 피격 직후 유저 체력이 0 이하면 '-1' 반환 후 게임 종료
                    return -1;
                } else {                                        // 게임 종료 아닐 시 연속 붕대감기 횟수 초기화
                    healthCount = 0;
                }
                check = 1;                              // 현재 반복 횟수(반복 시간 초) 타이밍에서 피격 받았으므로 다음 회복 로직 구현 불가토록 설정.
            }
            
            /* 회복 알고리즘, 현재 반복 횟수(반복 시간 초) 타이밍이 몬스터에게 피격 당한 상태라면 실행되지 않음. */
            if(i <= bandage[0] && check == 0) {            // 회복 타이밍 및 피격 여부 확인.
                answer = Math.min(answer + bandage[1], health);         // 회복 수식, 최대 체력 이상으로 회복 되지 않음.
                healthCount += 1;                          // 연속 붕대 감기 횟수 증가
                if(healthCount >= bandage[1]) {            // 연속 붕대 감기 횟수가 기준치 이상일 시 추가 회복량 부여
                    answer = Math.min(answer + bandage[2], health);     // 추가 회복 알고리즘 구현
                    healthCount = 0;                       // 연속 붕대 감기 횟수 초기화
                }
                System.out.println("디버깅 - 인덱스 : " + i + ", 회복 직후 체력 : " + answer + ", healthCount : " + healthCount + "\n");
            }
        }
        return answer;                                     // 모든 로직 종료 후 현재 캐릭터 체력 상태 반환.
   	}
}