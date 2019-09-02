//对归并排序的复习，注意了，本质上就是归并排序
public class InversePairs{

    public int InversePairs(int [] array) {
        int[] aux = new int[array.length];
        return inverse(array,aux,0,array.length-1);
    }

    private int inverse (int[] array, int[] aux, int lo, int hi){
        if (lo>=hi)
            return 0;
        int mid = lo +(hi-lo)/2;
        int left = inverse(array,aux,lo,mid)%1000000007;
        int right = inverse(array,aux,mid+1,hi)%1000000007;
        int cur = merge(array,aux,lo,mid,hi)%1000000007;
        return (left+right+cur)%1000000007;

    }

    private int merge(int[] array, int[] aux, int lo,int mid,int hi){
        System.arraycopy(array,lo, aux,lo,hi-lo+1);
//        这个地方有诀窍, 这个地方元素是怎么挪动的，要牢记
        int i = lo,j = mid+1,amount = 0;
        for(int k=lo;k<=hi;k++){
            if (i>mid) array[k] = aux[j++];
            else if(j>hi) array[k] = aux[i++]; //太粗心了这个地方
            else if (aux[i]>aux[j]){
                amount += (mid-i+1);
                amount = amount%1000000007; //这个曲余操作真是烦
                array[k] = aux[j++];
            }
            else array[k] = aux[i++];
        }
        return amount;
    }

    public static void main(String[] args){
        int[] a=new int[]{1,2,3,4,5,6,7,0};
        InversePairs solution = new InversePairs();
        int result = solution.InversePairs(a);
        System.out.print(result);

    }
}
