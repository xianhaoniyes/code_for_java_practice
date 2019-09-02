
//第一种解法和快排有关，掌握它比较好
// quciksort: the key point is partition:
//1 Always pick first element as pivot.
//2 Always pick last element as pivot (implemented below)
//3 Pick a random element as pivot.
//4 Pick median as pivot.
//partition的算法并不难,多看记住
public class MoreThanHalfNum_Solution {

    public int MoreThanHalfNum_Solution(int [] array) {
        if(array==null||array.length==0)
            return 0;


        int pi = MoreThanHalfNum_Solution(array,0,array.length-1);

        if (varification(array,array[pi]))
            return array[pi];
        else return 0;


    }
    //其实对于递归，你还没理解透.这道题其实用不着递归,书上描述错了
    private int MoreThanHalfNum_Solution(int[] array, int low, int high){

        int pi = partition(array,low,high);

        while(pi != array.length/2){
            if (pi > array.length/2)
                pi = partition(array,low,pi-1);
            else if(pi<array.length/2)
                pi = partition(array,pi+1,high);
        }

        return pi;
    }

    private int partition(int[] arr,int low, int high){
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++){
            if(arr[j]<=pivot){
                i++;
                swap(arr,i,j);//注意,这里只能这样写
            }
        }
        swap(arr,i+1,high);
        return i+1;
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

    private void swap(int [] arr, int i, int j){
        int imm = arr[i];
        arr[i] = arr[j];
        arr[j] = imm;
    }

    public static  void main(String[] strings){
        int[] a = {1};
        MoreThanHalfNum_Solution solution = new MoreThanHalfNum_Solution();
        System.out.print(solution.MoreThanHalfNum_Solution(a));
    }
}
