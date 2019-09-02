public class Fibonacci {
    public int Fibonacci(int n) {
        int ai_minus = -1; //注意这里是-1;
        int ai = 1;
        int ai_new = 0;
        int i = 0 ;
        while(i<=n) {

            ai_new  = ai + ai_minus;
            ai_minus = ai;
            ai = ai_new;
            i++;
        }
        return ai_new;

    }

    public static void main(String[] args){

        Fibonacci solution = new Fibonacci();
        System.out.print(solution.Fibonacci(3));

    }



}
