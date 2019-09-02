//快排并不难，掌握
public class QuickSort<T extends Comparable<T>> {
    public void QuickSort(T[] arr){

        QuickSort(arr,0,arr.length-1);
    }


    private void QuickSort(T[] arr,int low, int high){
        if(low<high){
            int pi = partition(arr,low,high);
            QuickSort(arr,low,pi-1);
            QuickSort(arr,pi+1,high);
        }
    }

    //这里你值传递又有困惑了,注意看，你这里swap怎么用的,想想head_java_first 里那个关于遥控器的例子
    private int partition(T[] arr,int low, int high){
        T pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++){
            if(arr[j].compareTo(pivot)<=0){
                i++;
                swap(arr,i,j);//注意,这里只能这样写
            }
        }
        swap(arr,i+1,high);
        return i+1;
    }

    private void swap(T[] arr, int i, int j){
        T imm = arr[i];
        arr[i] = arr[j];
        arr[j] = imm;
    }

    public static void main(String[] strings){
        Integer[] a ={0};
        QuickSort<Integer> sort = new QuickSort<>();
        sort.QuickSort(a);
        for(int i = 0; i< a.length; i++)
            System.out.print(a[i]);
    }
}
