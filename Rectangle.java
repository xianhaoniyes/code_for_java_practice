import java.lang.Math;

public class Rectangle implements Shape {

    private int height;
    private int width;


    public Rectangle(int h, int w){
        this.height =h;
        this.width = w;
    }



    public int GetArea(){
        return   height*width;
    }
}



