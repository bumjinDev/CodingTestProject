package LevelTwo.Clock;

import java.util.Random;

public class Solution {
	
	public static int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
		
		int answer = 0;
		
		return answer;
	}
	
	public static void main(String[] args) {
		
		 Random rand = new Random();
	        int h1 = rand.nextInt(24);
	        int m1 = rand.nextInt(60);
	        int s1 = rand.nextInt(60);
	        int h2 = rand.nextInt(24);
	        int m2 = rand.nextInt(60);
	        int s2 = rand.nextInt(60);

	        // Ensure that the end time is always after the start time
	        while (h2 < h1 || (h2 == h1 && m2 < m1) || (h2 == h1 && m2 == m1 && s2 <= s1)) {
	            h2 = rand.nextInt(24);
	            m2 = rand.nextInt(60);
	            s2 = rand.nextInt(60);
	        }

	        System.out.println("Start time: " + h1 + ":" + m1 + ":" + s1);
	        System.out.println("End time: " + h2 + ":" + m2 + ":" + s2);

	        int startSeconds = h1 * 3600 + m1 * 60 + s1;
	        int endSeconds = h2 * 3600 + m2 * 60 + s2;

	        int overlapCount = 0;
	        for (int i = startSeconds; i <= endSeconds; i++) {
	            int hourHand = i / 3600;
	            int minuteHand = (i % 3600) / 60;
	            int secondHand = i % 60;

	            if (hourHand == minuteHand && minuteHand == secondHand) {
	                overlapCount++;
	            }
	        }

	        System.out.println("The alarm rings " + overlapCount + " times.");
	        
	        int answer = solution(h1, m1, s1, h2, m2, s2);
	}
}
