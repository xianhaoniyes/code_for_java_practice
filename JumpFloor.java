
// 本质上仍然是斐波那契数列
public class JumpFloor {

    public int JumpFloor(int target) {
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

    public static void main(String[] args){
        JumpFloor solution = new JumpFloor();
        System.out.print(solution.JumpFloor(3));
    }


}
