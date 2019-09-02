import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class PrintMinNumber {

    public String PrintMinNumber(int [] numbers) {


        String[] str = new String[numbers.length];
        for(int i = 0 ; i< numbers.length;i++)
            str[i] =Integer.toString(numbers[i]);

        sortbystr sortbystr = new sortbystr();
        Arrays.sort(str,sortbystr);
        Arrays.toString(str);
        String result = "";
        for (int i = 0; i<str.length;i++)
            result+= str[i];
        return result;

    }

    private class sortbystr implements Comparator<String>{
        public int compare(String a, String b){

            if ((a+b).compareTo(b+a) <0)
                return -1;
            else if((a+b).compareTo(b+a)==0)
                return 0;
            else return 1;
        }
    }


    public static void main(String[] args){

        int[] numbers = new int[3];
        numbers[0] = 3;
        numbers[1] = 32;
        numbers[2] = 321;

        PrintMinNumber solution = new PrintMinNumber();
        System.out.print(solution.PrintMinNumber(numbers));
    }
}
