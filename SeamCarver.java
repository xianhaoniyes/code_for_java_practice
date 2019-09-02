import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.StdOut;

import java.awt.Color;
import java.lang.Math;

public class SeamCarver {

    private int[][] colors;
    private int height;
    private int width;

    public SeamCarver(Picture picture){
        if(picture ==null) throw new IllegalArgumentException();
        this.height = picture.height();
        this.width = picture.width();
        colors = new int[height][width];

        for(int i = 0 ;i < width; i++)
            for(int j = 0 ;j < height; j++)
            colors[j][i]= picture.getRGB(i,j);
    }

    public Picture picture(){
        Picture picture = new Picture(width,height);
        for(int i = 0 ;i < width; i++)
            for(int j = 0 ;j < height; j++)
                picture.setRGB(i,j,colors[j][i]);
        return picture;
    }

    public  int width(){
        return width;
    }

    public  int height(){
        return height;
    }

    public  double energy(int x, int y){
        if(x<0||y<0||(x > width -1)||(y > height -1))
            throw new IllegalArgumentException();
        if(x==0||y==0||(x==width-1)||(y==height-1))
            return 1000;
        else{
            int color_l = colors[y][x-1];
            int color_u = colors[y-1][x];
            int color_r = colors[y][x+1];
            int color_d = colors[y+1][x];
            return  Math.sqrt(
                    ((getRed(color_r)-getRed(color_l))*(getRed(color_r)-getRed(color_l))
            +       (getGreen(color_r)-getGreen(color_l))*(getGreen(color_r)-getGreen(color_l))
            +       (getBlue(color_r)-getBlue(color_l))*(getBlue(color_r)-getBlue(color_l))

            +       (getRed(color_d)-getRed(color_u))*(getRed(color_d)-getRed(color_u))
            +       (getGreen(color_d)-getGreen(color_u))*(getGreen(color_d)-getGreen(color_u))
            +       (getBlue(color_d)-getBlue(color_u))*(getBlue(color_d)-getBlue(color_u)))

            );
        }

    }

    private int getRed(int rgb){
        return (rgb >> 16) & 0xFF;
    }

    private int getGreen(int rgb){
        return (rgb >>  8) & 0xFF;
    }

    private int getBlue(int rgb){
        return (rgb >>  0) & 0xFF;
    }

    public  int[] findHorizontalSeam(){

        double[][] energy = new double[width][height];
        for(int i = 0 ;i < width; i++)
            for(int j = 0; j < height;j++)
                energy[i][j] =energy(i,j);// notice here!!!
        //StdOut.printf("yes\n");
        AcyclicSP_special asp = new AcyclicSP_special(energy);
        //StdOut.printf("%d",asp.path().length);
        return asp.path();
    }


    public  int[] findVerticalSeam(){

        double[][] energy = new double[height][width];
        for(int i =0; i < width; i++)
            for(int j = 0; j<height;j++)
                energy[j][i] = energy(i,j);
        //StdOut.printf("ok it didn't happen again\n");
        AcyclicSP_special  asp =new AcyclicSP_special(energy);
       // StdOut.printf("%d",asp.path().length);
        return asp.path();
    }


    public  void removeHorizontalSeam(int[] seam){
        if(seam == null ) throw new IllegalArgumentException();
        if(seam.length!=width) throw  new IllegalArgumentException();;

        for(int i = 0 ; i<seam.length-1;i++){
            if(seam[i]<0||seam[i]>height-1)
                throw new IllegalArgumentException();
            if(Math.abs(seam[i]-seam[i+1])>1)
                throw new IllegalArgumentException();
        }

        if(seam[seam.length-1]<0||seam[seam.length-1]>height-1)
            throw new IllegalArgumentException();

            for(int j = 0 ; j<width;j++){

            for(int i = seam[j]; i<height-1;i++)
                colors[i][j]=colors[i+1][j];
            }
            height--;

    }

    public  void removeVerticalSeam(int[] seam){
        if(seam == null ) throw new IllegalArgumentException();
        if(seam.length!=height) throw new IllegalArgumentException();
        for(int i = 0 ; i<seam.length-1;i++){

            if(seam[i]<0||seam[i]>width-1)
                throw new IllegalArgumentException();

            if(Math.abs(seam[i]-seam[i+1])>1)
                throw new IllegalArgumentException();
        }

        if(seam[seam.length-1]<0||seam[seam.length-1]>width-1)
            throw new IllegalArgumentException();

        for(int i = 0 ; i < height; i++) {
            for(int j = seam[i]; j < width-1;j++)
                colors[i][j]=colors[i][j+1];
        }
        width--;

    }

}