package Tests;

class Testando01 {

	public static int decodeWays(String digits) {
        return dfs(digits.split(""), 0);
    }
    
    public static int dfs(String[] digitsArr, int index) {
    
        if (index == digitsArr.length) {
            return 1;
        }
        
        int count = 0;
        String str = "";
        
        for (int i = index; i < digitsArr.length; i++) {
            
            str = str + "" + digitsArr[i];
            
            //System.out.println(str);
            
            if (Integer.parseInt(str) <= 24) {
                count = count + dfs(digitsArr, i + 1);
            }
        }
        
        return count;
    }
	
    public static void main(String[] args) {
    	long startTime = System.nanoTime();
    	String digits = "123456";
        int res = decodeWays(digits);
        System.out.println(res);
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;
        System.out.println("Execution time in nanoseconds: " + timeElapsed);
        System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
    }
}