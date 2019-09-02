import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF  structure;
    private  WeightedQuickUnionUF m_structure;
    private int[] status;
    private int line;
    private int num;

    public  Percolation(int n){
        if (n<1)
            throw new IllegalArgumentException("Wrong Argument");

        structure = new WeightedQuickUnionUF(n*n+2);
        m_structure = new WeightedQuickUnionUF(n*n+1);
        status = new int[n*n+2];
        for (int i = 1; i < n*n+1;i++)
            status[i] = 0;
        status[0] = 1;
        status[n*n+1] = 1;
        line = n;
        num = 0;
    }

    private int xyTo1D(int row,int col){
        if ((row < 1|| row > line) || (col < 1 || col > line))
            return 0;
        else return (row - 1) * line + col;
    }

    private void validate(int row,int col){
        if ((row < 1 || row > line) || (col < 1 || col > line))
            throw new IllegalArgumentException("Index Out of Bounds");

    }

    public    void open(int row, int col){

        if(isOpen(row, col))
            return;

        validate(row,col);

        int position = xyTo1D(row,col);
        status[position] = 1;
        num++;

        int position_nei = xyTo1D(row - 1,col);
        if (position_nei != 0)
            if(status[position_nei] == 1) {
                structure.union(position, position_nei);
                m_structure.union(position, position_nei);
            }
        position_nei = xyTo1D(row + 1,col);
        if (position_nei != 0)
            if(status[position_nei] == 1){
                structure.union(position, position_nei);
                m_structure.union(position, position_nei);
            }

        position_nei = xyTo1D(row,col-1);
        if (position_nei != 0)
            if(status[position_nei] == 1){
                structure.union(position, position_nei);
                m_structure.union(position, position_nei);
            }

        position_nei = xyTo1D(row,col+1);
        if (position_nei != 0)
            if(status[position_nei] == 1){
                structure.union(position, position_nei);
                m_structure.union(position, position_nei);
            }
        if (row == 1) {
            structure.union(position, 0);
            m_structure.union(position, 0);
        }

        if(row == line)
            structure.union(position,line * line + 1);

    }

    public boolean isOpen(int row, int col){
        validate(row,col);
        int position = xyTo1D(row, col);
        if (status[position] == 1)
            return true;
        else return false;

    }
    public boolean isFull(int row, int col){
        validate(row,col);
        int position= xyTo1D(row, col);
        if(isOpen(row,col)){
            if(row == 1)
                return true;
            else {
                    if (m_structure.connected(position,0))
                        return true;

                return false;
            }
        }

        else return false;
    }

    public     int numberOfOpenSites(){
        return num;
    }

    public boolean percolates(){
        if (structure.connected(0,line * line + 1))
            return true;
        else return false;
    }
}
