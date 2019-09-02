// 这道题稍有抽象
public class min_in_reverse_array {
    public int minNumberInRotateArray(int [] array) {
        if(array.length==1)
            return array[0];
        int i = 0 ;    // i 始终在大堆, j 始终在小堆
        int j = array.length-1;
        int mid = 0; // notie why i initiate the mid value to zero
        while(array[i]>= array[j]){

            if(j-i==1)
            {
                mid = j;
                break;
            }
          mid = (i+j)/2;

          if(array[i]==array[j]&&array[i]==array[mid])
              return min(array,i,j);
          if(array[mid]>=array[j])
              i = mid;
          else
              j = mid;

        }
        return array[mid];
    }

    //注意为何这么做.有更好的办法吗
    private int min(int[] array, int start ,int end){
        int result = array[start];
        for(int i = start; i<=end; i++)
            if(array[i]<result)
                result = array[i];
        return result;
    }

    public static void main (String[] args){
        min_in_reverse_array solution = new min_in_reverse_array();
        int[] array  = new int[]{1,0,1,1,1};
        System.out.print(solution.minNumberInRotateArray(array));
    }
}
