public class RectCover {
    public int RectCover(int target) {
        if(target==0)
            return 0;
        int ai_mins = 1;
        int ai =1;
        int ai_new = 1;
        int i = 1;
        while(i<target){
            ai_new = ai_mins+ai;
            ai_mins = ai;
            ai = ai_new;
            i++;
        }
        return ai_new;
    }
}
