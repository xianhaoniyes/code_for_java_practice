
//this is question basically show the idea of kd tree. start from a corner, then narrow the search area.(2-axis naturally has the
//  property of this kind of 2d arrary)
public class TdArraySearch {

    public boolean Find(int target, int [][] array) {
        if(array==null)
            throw new NullPointerException();

        int i = 0;
        int j = array[0].length-1;


        while(i<=array.length-1&&j>=0){
            if(array[i][j]<target)
                i++;
            else if(array[i][j]>target)
                j--;
            else return true;
        }

        return false;
    }

    public static void main(String args[]){
        int[][] array = new int[][]{{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}};

        TdArraySearch td = new TdArraySearch();
        boolean b = td.Find(7,array);
        System.out.println(b);
    }

}
