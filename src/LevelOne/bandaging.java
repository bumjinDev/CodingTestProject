package LevelOne;

/* 몬스터에게 반복적으로 공격을 받을 때 살아 남는 지 확인.
 * 로직 : 
 * 1. 반복문은 시간 시간(단위 초)만큼 횟수 설정
 * 2. 현재 체력 상태 보여지기
 * 3. 한 차례 회복(맞기 전) :
 * 4. 맞기거나 안 맞거나 : 공격 시간 , 피해량 보고 if 문 실행
	    맞았는다
			죽었으면 게임 끝
			안 죽었으면 체력 빼고 연속 붕대횟수 초기화
		안 맞았다
		 	체력 그대로 가고 붕대횟수 더한다. 그리고 만약 붕대횟수가 다 차지면 횟수를 초기화하면서 체력 회복
 * 반복 ~
 * */

public class bandaging {

	public int solution(int[] bandage, int health, int[][] attacks) {
		
		int length = attacks.length;
		// 결과 체력 = (현재 체력 + 회복 체력) - (최대 체력 - 현재 체력 - 회복 체력)
        int answer = 0;
        return answer;
    }
}
