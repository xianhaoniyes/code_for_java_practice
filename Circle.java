

public class Circle implements Shape{

    private int radius;


    public Circle(int radius){
        this.radius =radius;
    }



    public int GetArea(){
        return  (int)java.lang.Math.round(3.14*radius*radius);
    }

    public static void main(String[] args){
        Circle a = new Circle(5);
        System.out.print(a.GetArea());
    }


}