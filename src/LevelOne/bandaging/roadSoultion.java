package LevelOne.bandaging;

public class roadSoultion {

	public static void main(String[] args) {
		
		Solution test = new Solution();
		
		int[] bandage = {5, 1, 5};
		int health = 30;
		int[][] attacks = {{2, 10}, {9, 15}, {10, 5}, {11, 5}};
		
		int answer = test.run(bandage, health, attacks);
		System.out.println("\n answer : " + answer);
	}

}
