import java.util.ArrayList;
import java.util.HashMap;

//这个解法要改变元素位置， 面试的时候可能不能用
public class GetLeastNumbers_Solution {

    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        if(input==null||input.length<k||k==0)
            return arrayList;


        int pi = partition(input,0,input.length-1);

        while(pi!=k-1){
            if(pi>k-1)
                pi = partition(input,0,pi-1);
            else
                pi = partition(input,pi+1,input.length-1);
        }

        for(int i = 0; i<=pi;i++)
            arrayList.add(input[i]);
        return arrayList;

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

    private void swap(int [] arr, int i, int j){
        int imm = arr[i];
        arr[i] = arr[j];
        arr[j] = imm;
    }

    public static void main(String[] strings){
        int[] a = new int[]{1,3,4,3,5,6,8};
        a = null;
        GetLeastNumbers_Solution solution = new GetLeastNumbers_Solution();
        ArrayList<Integer> result = solution.GetLeastNumbers_Solution(a,4);
        for (int i:result
             ) {
            System.out.print(i);
        }
    }


}
