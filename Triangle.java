public class Triangle implements Shape{

    private int height;
    private int width;


    public Triangle(int h, int w){
        this.height =h;
        this.width = w;
    }



    public int GetArea(){
        return  (int)((double)height*width/2);
    }




}