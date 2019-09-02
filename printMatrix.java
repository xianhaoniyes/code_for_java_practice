import java.util.ArrayList;
//这道题涉及到你很烦的数组坐标处理，认真看
//这道题不能把边界分成四条等长线条然后来算，因为这样会把所有特殊条件避开，最好第一条，打印第一行的全部，想象这是为什么
public class printMatrix {

    public ArrayList<Integer> printMatrix(int [][] matrix) {
        if(matrix == null)
            return null;

        ArrayList<Integer> list = new ArrayList<>();

        int rows = matrix.length;
        int colums = matrix[0].length;
        int start = 0;
        while(rows > start*2 && colums > start*2){
            for(int i = start; i <= colums-start-1;i++)
                list.add(matrix[start][i]);

            for(int i = start+1; i < rows-start;i++)
                list.add(matrix[i][colums-start-1]);

            if(start!=rows-start-1)
            for (int i = colums-start-2; i >=start;i--)
                list.add(matrix[rows-start-1][i]);

            if(start!= colums-start-1)
            for(int i = rows-start-2 ; i>start; i--)
                list.add(matrix[i][start]);
            start++;
        }
        return list;
    }


}
