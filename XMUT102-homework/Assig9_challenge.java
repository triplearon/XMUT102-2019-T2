// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a XMUT102 assignment.
// You may not distribute it in any other way without permission.

/* Code for XMUT102 - 2019T2, Assignment 10
 * Name:DengXinyu
 * Username:xmut_1812409208
 * ID:1812409208
 */

    private static int[][] rotate90(int[][] array){
        int[][] rotate90=new int[array[0].length][array.length];
             
        for(int i=0;i<rotate90.length;i++){
            for(int j=0;j<rotate90[0].length;j++){
                rotate90[i][j]=array[j][rotate90.length-1-i];
            }
        }
        
        return rotate90;
    }

    public void expandImage(){
        int[][] expand=new int[this.selectedRow*2][this.selectedCol*2];
        
        for(int i=0,k=0;i<this.selectedRow;i++,k+=2){
            for(int j=0,t=0;j<this.selectedCol;j++,t+=2){               
                expand[k][t]=this.image[i][j];
                expand[k+1][t]=this.image[i][j];
                expand[k][t+1]=this.image[i][j];
                expand[k+1][t+1]=this.image[i][j];
            }
        }
        this.image=expand;
        this.redisplayImage();
    }

    public void mergeImage(){
        int [][] other = this.loadAnImage(UIFileChooser.open());
        int rows = Math.min(this.image.length, other.length);       // rows and cols
        int cols = Math.min(this.image[0].length, other[0].length); // common to both
        //only change image in region 0..rows-1, 0..cols-1
        int[][] newImage=new int[rows][cols];
        
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                newImage[i][j]=(other[i][j]+this.image[i][j])/2;
            }
        }     
        this.image=newImage;
        this.redisplayImage();
    }
