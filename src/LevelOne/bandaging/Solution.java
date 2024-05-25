package LevelOne.bandaging;
class Solution {
	
    public int solution(int[] bandage, int health, int[][] attacks) {
        
        int answer = health;		// answer : 캐릭터 현재 체력이자 반환 값
        int healthCount = 0;		// 체력 회복될 때마다 1씩 증가하여 bandage[0] 횟수 만큼 연속적으로 회복한다면 추가 회복 부여
        int attackCount = 0;		// 몬스터에게 피격 당하는 타이밍은 매 횟수(매 초) 인덱스 값으로 실행하는게 아닌 배열 내 각 요소의 타이밍으로 계산.
        
        System.out.println("bandage[0](붕대횟수) : " + bandage[0] + ", bandage[1](초당회복량) : " + bandage[1] + ", bandage[2](추가회복량) : " + bandage[2] + "\n");
        System.out.println("health(최대 값) : " + health + "\n");
        
        for(int i = 0; i<attacks.length; i++){
            
            System.out.println("attacks" + i + " : " + attacks[i][0]+ " " + attacks[i][1] + ", ");
        }
        
        System.out.println("====== 회복 알고리즘 구현 =======");
        
        // 전체 몬스터에게 맞는 횟수 만큼 반복하며 각 횟수는 초 단위라고 규정.
        for(int i = 1; i<=attacks[attacks.length-1][0] ; i++) {
        	
             System.out.println("맞기 전 체력 상태 : " + answer + " , 반복 초 : " + i);
            
            // 캐릭터 맞는 알고리즘, 각 반복문 실행 하면서 인덱스 횟수 값(반복문 카운터 값)이 몬스터 공격 시점(반복문 반복 횟수 값)과 동일하면 공격 실행
            if(attacks[attackCount][0] == i) {

                answer = answer - attacks[attackCount][1];
                System.out.println("\n뚜둘겨 맞은 직후 체력 회복 상태 : " + answer + " 현재 맞은 데미지 : " + attacks[attackCount][0] + "\n");
                
                attackCount += 1;

                if(answer <= 0) {
                    return -1;
                }else {
                    healthCount = 0;
                }
            }
            
        	/* 체력 회복 알고리즘 구현 */
        	if(i < bandage[0]) {  	// 매 초마다 체력 회복을 우선 실행하며 i(인덱스)로 카운터한 결과가 시전 시간(회복 카운터 초) 횟수 초과시 체력 회복 불가능
                
                answer = Math.min(answer + bandage[1], health); healthCount += 1;   // 체력 회복 및 붕대감기 횟수 1 증가
                System.out.println("회복 실행, 회복량 : " + bandage[1] + " 회복 직후 체력 상태 : " + answer + " 현재 붕대 연속 감기 성공 횟수 : " + healthCount + "\n\n");
                
        		/* 지정된 회복량(bondage[1] 만큼 회복하면서 지정된 회복 수를 연속적으로 채웠을 시 추가 회복 부여 */
        		if(healthCount >= bandage[0]) {
        			
                    answer = Math.min(answer + bandage[2], health); healthCount = 0;
                    System.out.println("추가 회복 직후 체력 상태 : " + answer + " 붕대감기 횟수 초기화 : " + healthCount);
                    
        		}
        	}
        }
        
        return answer;
    }
    
    public int run(int[] bandage, int health, int[][] attacks) {
    	
    	 int answer = health;		// answer : 캐릭터 현재 체력이자 반환 값
         int healthCount = 0;		// 체력 회복될 때마다 1씩 증가하여 bandage[0] 횟수 만큼 연속적으로 회복한다면 추가 회복 부여
         int attackCount = 0;		// 몬스터에게 피격 당하는 타이밍은 매 횟수(매 초) 인덱스 값으로 실행하는게 아닌 배열 내 각 요소의 타이밍으로 계산.
         
         System.out.println("bandage[0](붕대횟수) : " + bandage[0] + ", bandage[1](초당회복량) : " + bandage[1] + ", bandage[2](추가회복량) : " + bandage[2] + "\n");
         System.out.println("health(최대 값) : " + health + "\n");
         
         for(int i = 0; i<attacks.length; i++){
             
             System.out.println("attacks" + i + " : " + attacks[i][0]+ " " + attacks[i][1] + ", ");
         }
         
         System.out.println("====== 본격적인 시작 =======");
         
         // 전체 몬스터에게 맞는 횟수 만큼 반복하며 각 횟수는 초 단위라고 규정.
         for(int i = 1; i<=attacks[attacks.length-1][0] ; i++) {
         	
              System.out.println("반복 초 : " + i + " 맞기 전 체력 상태 : " + answer);
             
             // 캐릭터 맞는 알고리즘, 각 반복문 실행 하면서 인덱스 횟수 값(반복문 카운터 값)이 몬스터 공격 시점(반복문 반복 횟수 값)과 동일하면 공격 실행
             if(attacks[attackCount][0] == i) {

            	 System.out.println("\n몬스터에게 피격 당함!");
                 answer = answer - attacks[attackCount][1];
                 System.out.println("뚜둘겨 맞은 직후 체력 상태 : " + answer + ", 현재 맞은 데미지 : " + attacks[attackCount][1] + "\n");
                 
                 attackCount += 1;

                 if(answer <= 0) {
                     return -1;
                 }else {
                     healthCount = 0;
                 }
             }
             
         	/* 체력 회복 알고리즘 구현 */
         	if(i <= bandage[0]) {  	// 매 초마다 체력 회복을 우선 실행하며 i(인덱스)로 카운터한 결과가 시전 시간(회복 카운터 초) 횟수 초과시 체력 회복 불가능
                 
                 answer = Math.min(answer + bandage[1], health); healthCount += 1;   // 체력 회복 및 붕대감기 횟수 1 증가
                 System.out.println("회복 실행, 회복량 : " + bandage[1] + " 회복 직후 체력 상태 : " + answer + " 현재 붕대 연속 감기 성공 횟수 : " + healthCount + "\n\n");
                 
         		/* 지정된 회복량(bondage[1] 만큼 회복하면서 지정된 회복 수를 연속적으로 채웠을 시 추가 회복 부여 */
         		if(healthCount >= bandage[1]) {
         			
                     answer = Math.min(answer + bandage[2], health); healthCount = 0;
                     System.out.println("추가 회복 직후 체력 상태 : " + answer + " 붕대감기 횟수 초기화 : " + healthCount);
                     
         		}
         	}
         }
         
         return answer;
    }
}