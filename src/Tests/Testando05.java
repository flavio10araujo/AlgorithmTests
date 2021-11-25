package Tests;

public class Testando05 {

	public static void main(String[] args) {
		//int target = 4;
		//int[] nums = {1, 2, 3};

		//int target = 32;
		//int[] nums = {4, 2, 1};

		int target = 999;
		int[] nums = {10,20,30,40,50,60,70,80,90,100,110,120,130,140,150,160,170,180,190,200,210,220,230,240,250,260,270,280,290,300,310,320,330,340,350,360,370,380,390,400,410,420,430,440,450,460,470,480,490,500,510,520,530,540,550,560,570,580,590,600,610,620,630,640,650,660,670,680,690,700,710,720,730,740,750,760,770,780,790,800,810,820,830,840,850,860,870,880,890,900,910,920,930,940,950,960,970,980,990,111};

		long startTime = System.nanoTime();
		solution02(target, nums);
		long endTime = System.nanoTime();
		long timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
		
		startTime = System.nanoTime();
		solution01(target, nums);
		endTime = System.nanoTime();
		timeElapsed = endTime - startTime;
		System.out.println("Execution time in nanoseconds: " + timeElapsed);
		System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
	}

	public static void solution01(int target, int[] nums) {
		int[] memo = new int[target + 1];
		System.out.println(imprimirPermutacoes(nums, target, memo, 0));
	}

	public static int imprimirPermutacoes(int[] nums, int target, int[] memo, int soma) {

		if (soma == target) {
			return 1;
		}

		int sobra = target - soma; 

		if (memo[sobra] != 0) {
			return memo[sobra];
		}

		int count = 0;

		for (int i = 0; i < nums.length; i++) {
			soma += nums[i];
			if (soma <= target) {
				count += imprimirPermutacoes(nums, target, memo, soma);
			}
			soma -= nums[i];
		}

		memo[sobra] = count;

		return memo[sobra];
	}

	public static int solution02(int target, int[] nums) {
		// dp[i] := # of combinations that add up to i
		int[] dp = new int[target + 1];
		dp[0] = 1;

		for (int i = 0; i <= target; ++i)
			for (final int num : nums)
				if (i >= num)
					dp[i] += dp[i - num];

		return dp[target];
	}
}