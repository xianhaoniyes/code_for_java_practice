import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;
public class irdeto_test {

//    public int solution(int[] A) {
//
//        // first sort the array, if it's quick sort then time complexity: nlogn
//        Arrays.sort(A);
//
//        //if there is no negative number, return 0
//        if (A[0]>-1 || A[A.length-1]<1)
//            return 0;
//
//        // how many negative numbers exists in the Array
//        int negative = 0;
//        for(int i = 0; A[i]<=0;i++)
//            negative++;
//
//
//        //for each positive numbers(from large to small, perform binary search in the negative part
//        for (int i = A.length-1; i> negative;i--)
//            if(binary_search(A,0,negative, -A[i]))
//                return A[i];
//
//        return 0;
//    }
//
//
//    public boolean binary_search(int[] arr, int lo,int hi, int item){
//
//        while(lo<=hi){
//            int middle = (lo+hi)/2;
//            if (arr[middle]<item)
//                lo = middle+1;
//            else if(arr[middle]>item)
//                hi = middle-1;
//            else
//                return true;
//        }
//
//        return false;
//    }


//    public String solution(String S) {
//
//        StringBuilder non_secu_s = new StringBuilder();
//        int i = 1;
//        int consecutive_count = 0;
//
//        while( i < S.length()){
//
//            if(S.charAt(i-1)!= S.charAt(i)){
//                if (consecutive_count < 2)
//                    non_secu_s.append(S.charAt(i-1));
//                    consecutive_count = 0;
//            }
//            else
//                if(consecutive_count<2){
//                consecutive_count++;
//                non_secu_s.append(S.charAt(i-1));
//                }
//
//            i++;
//        }
//
//        if (consecutive_count<2)
//            non_secu_s.append(S.charAt(i-1));
//
//
//        return non_secu_s.toString();
//
//    }


    public int solution(int[] T) {

        //This is a deep first search problem
        boolean[] visitied = new boolean[T.length];
        Stack<Integer> s = new Stack<>();
        //store all the neighbor for each city
        LinkedList<Integer>[] neighbors = (LinkedList<Integer>[]) new Object[T.length];

        for (int i = 0 ; i < T.length; i++){
            for (int j = 0; j< T.length; j++){}
            
        }


        s.push(0);
        visitied[0] =true;
        while(!s.isEmpty()) {
            for (int i = 0; i< T.length; i++){

            }
        }

        return 0;
    }

}
