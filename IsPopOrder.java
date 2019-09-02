import java.util.Stack;

public class IsPopOrder {
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        Stack<Integer> support_stack = new Stack<>();
        int last_position=0;
        int i = 0;
        while(i!= popA.length){

            if(support_stack.isEmpty()||support_stack.peek()!=popA[i]) {


                //想想这句话为何放在这里而不是后面
                if(last_position == pushA.length)
                    return false;

                while(last_position!=pushA.length){
                    if(pushA[last_position]!=popA[i]) {
                        support_stack.push(pushA[last_position]);
                        last_position++;
                    }
                    else {
                        support_stack.push(pushA[last_position]);
                        last_position++;
                        break;
                    }
                }



            }

            else {
                support_stack.pop();i++;
            }

            }

        return true;
        }

    public static void main(String[] args){
        int[] pushA = new int[]{1,2,3,4,5};
        int[] popA = new int[]{4,5,3,2,1};
        IsPopOrder solution = new IsPopOrder();
        System.out.print(solution.IsPopOrder(pushA,popA));

    }


    }

