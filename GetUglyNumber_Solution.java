public class GetUglyNumber_Solution {
    public int GetUglyNumber_Solution(int index) {
        if(index<=0)
            return 0;

        int[] uglynumbers = new int[index]; //这里把一也当做丑数
        uglynumbers[0] = 1;
        int t2 = 0;
        int t3 = 0;
        int t5 = 0;
        int nextuglyindex = 1;

        while(nextuglyindex<index){
            int min = min(uglynumbers[t2]*2,uglynumbers[t3]*3,uglynumbers[t5]*5);
            uglynumbers[nextuglyindex] = min;

            while(uglynumbers[t2]*2<=uglynumbers[nextuglyindex])//这里的等号是必须的
                t2++;
            while(uglynumbers[t3]*3<=uglynumbers[nextuglyindex])
                t3++;
            while(uglynumbers[t5]*5<=uglynumbers[nextuglyindex])
                t5++;

            nextuglyindex++;
        }

        return uglynumbers[index-1];

    }

    private int min(int a, int b, int c){

        int min = (a<b)? a:b;

        min = (min<c)? min:c;

        return min;
    }
}
