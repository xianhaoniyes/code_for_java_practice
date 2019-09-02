//这道题牵涉动态规划,认真看，把动态规划的思路理明白.
public class FindGreatestSumOfSubArray {
    public int FindGreatestSumOfSubArray(int[] array) {
        if(array==null)
            return 0;
        int now_maximum = Integer.MIN_VALUE;
        int currrent_count = 0;
        for(int i = 0; i< array.length;i++){
            if(currrent_count<=0) //负数代表前面的累加起反作用，举个例子(-7,-8),自己想想程序会如何运行.
                currrent_count = array[i];
            else
                currrent_count = currrent_count+ array[i];

            if (currrent_count>now_maximum)
                now_maximum = currrent_count;
        }

        return now_maximum;

    }

    public static void main(String[] strings){
        int[] a = new int[]{6,-3,-2,7,-15,1,2,2};
        FindGreatestSumOfSubArray solution = new FindGreatestSumOfSubArray();
        System.out.print(solution.FindGreatestSumOfSubArray(a));
    }
}
