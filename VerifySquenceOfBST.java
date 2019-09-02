//二叉树的题一定要总结

public class VerifySquenceOfBST {

    public boolean VerifySquenceOfBST(int [] sequence) {
        if(sequence.length==0)
            return false;
        return VerifySequenceOfBST(sequence,0,sequence.length-1);
    }

    private boolean VerifySequenceOfBST(int[] sequence, int start, int end){

        boolean answer = true;
        int larger_start = start;

        while(sequence[larger_start]<sequence[end])
            larger_start++;

        for(int i = start; i< larger_start; i++){
            if(sequence[i]>sequence[end]){

                return false;}
        }

        for(int i = larger_start; i < end;i++){
            if(sequence[i]<sequence[end]){

                return false;}
        }

         if(start!= larger_start)
         answer = VerifySequenceOfBST(sequence,start,larger_start-1);

        if(larger_start!=end)
           if(answer) answer  = VerifySequenceOfBST(sequence,larger_start,end-1);//这里

        return answer;

    }

    public static void main(String[] args){
        int[] arr = new int[]{7,4,6,5};
        VerifySquenceOfBST solution = new VerifySquenceOfBST();
        System.out.print(solution.VerifySquenceOfBST(arr));
    }


}
