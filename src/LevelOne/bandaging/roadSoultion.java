package LevelOne.bandaging;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class roadSoultion {

	public static void main(String[] args) {
		
		Solution test = new Solution();
		Random rand = new Random();
		
		for(int j = 0; j < 5; j++) {
			int[] bandage = {Math.min(rand.nextInt(10), 5), rand.nextInt(10) + 1, rand.nextInt(10) + 1};
			int health = Math.min(rand.nextInt(100), 12);
			int[][] attacks = new int[5][2];
			for(int i = 0; i < 5; i++) {
				attacks[i][0] = Math.min(rand.nextInt(20), bandage[0] + 5) + 1; // 시작값을 1로 설정
				attacks[i][1] = Math.min(rand.nextInt(20), 10);
			}
			
			// attacks 배열을 attacks[0] 값에 따라 오름차순으로 정렬
			Arrays.sort(attacks, Comparator.comparingInt(a -> a[0]));
			
			int answer = test.run(bandage, health, attacks);
			System.out.println("\n answer : " + answer);
		}
	}
}
