//二分法这里也要注意
public class GetNumberOfK {

    public int GetNumberOfK(int [] array , int k) {
        int lo = 0,hi = array.length-1;
        int start=0,end=0;

        while(lo<=hi){
            int mid = lo+(hi-lo)/2;
            if (array[mid]<k)
                lo = mid+1;
            else if(array[mid]>k)
                hi =mid-1;
            else {
                if(mid==0||array[mid-1]!=k) {
                    start = mid;
                    break;
                }
                else hi =mid-1;
            }
        }
        if(lo>hi)
            return 0; //这里是为了排除特殊情况，注意了.

        lo = 0;
        hi = array.length-1;
        while(lo<=hi){
            int mid = lo+(hi-lo)/2;
            if (array[mid]<k)
                lo = mid+1;
            else if(array[mid]>k)
                hi =mid-1;
            else {
                if(mid==array.length-1||array[mid+1]!=k) {
                    end = mid;
                    break;
                }
                else lo =mid+1;
            }
        }
        return  end -start+1;
    }

    public static void main(String[] args){
        int[] a = new int[]{1,2,3,4,4,4,4,4,4,5,5,5,5,6,6,6,6};
        GetNumberOfK getNumberOfK = new GetNumberOfK();
        System.out.print(getNumberOfK.GetNumberOfK(a,6));
    }
}
