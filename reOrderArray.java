public class reOrderArray {
    public void reOrderArray(int [] array) {

        int even_start = 0;
        int[] arr = new int[array.length];
        for(int i = 0; i< array.length; i++){
            if(array[i]%2==1)
                even_start++;
        }
        int odd = 0;
        int even = even_start;
        for(int i = 0; i< array.length;i++){
            if(array[i]%2==1){
                arr[odd] = array[i];
                odd++;
            }
            else{
                arr[even] = array[i];
                even++;
            }
        }

        for(int i = 0 ; i< array.length;i++)
            array[i] = arr[i];

    }
}
