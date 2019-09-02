public class Container {

    public int maxArea(int[] height) {
        int max_area = 0;
        int current_area = 0;
        for(int i =0; i<height.length-1;i++)
            for(int j = i+1 ; j<height.length;j++){
                current_area = (j-i)*min(height[i],height[j]);
                if(current_area>max_area)
                    max_area = current_area;
            }

        return max_area;
    }

    private int min(int a, int b){
        if(a < b)  return a;
        else return b;
    }
}
