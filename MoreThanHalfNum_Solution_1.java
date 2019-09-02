public class MoreThanHalfNum_Solution_1 {

    public int MoreThanHalfNum_Solution(int [] array){
        if(array==null||array.length==0)
            return 0;

        int time = 0;
        int result = array[0];
        for (int i : array) {
            if (i ==result)
                time++;
            else {
                time--;
                if(time ==0){
                    time = 1;
                    result = i;
                }
            }
        }

        if (varification(array,result))
            return result;
        else return 0;

    }

    private boolean varification(int[] array, int result){
        int time = 0;
        for (int i: array) {
            if (i == result){
                time++;
            }
        }

        if(time>array.length/2)
            return true;
        else return false;
    }

    public static void main(String[] strings){
        int[] a = new int[]{1,2,3,2,2,2,5,4,2};
        MoreThanHalfNum_Solution_1 solution = new MoreThanHalfNum_Solution_1();
        System.out.print(solution.MoreThanHalfNum_Solution(a));
    }
}
