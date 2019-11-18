public class Assig8_Challenge{
    /**
     * [CHALLENGE]
     * If there is a selected region, then add a copy of that section to the waveform,
     * immediately following the selected region
     * selection should be reset to be the whole waveform
     * redisplay the waveform
     */
    public void duplicateRegion(){
        UI.println(regionStart);
        UI.println(regionEnd);
        
        MyList<Double> mid=this.waveform.subList(regionStart,regionEnd-1);
        
        
        this.waveform.addAll(regionEnd-1,mid);
        
        regionStart=0;
        regionEnd=waveform.size();

        this.display();
    }

    /**
     * [CHALLENGE]
     * Displays the envelope (upper and lower) with GREEN lines connecting all the peaks.
     *    A peak is defined as a point that is greater than or equal to *both* neighbouring points.
     */
    public void displayEnvelope(){
        if (this.waveform == null){ //there is no data to display
            UI.println("No waveform to display");
            return;
        }
        this.display();  // display the waveform,
        
        MyList<HighPoint> positive=new MyList<>();
        MyList<HighPoint> negative=new MyList<>();
        
        for(int i=1;i<this.waveform.size()-1;i++){
            if(Math.abs(waveform.get(i-1))<=Math.abs(waveform.get(i)) && Math.abs(waveform.get(i))>=Math.abs(waveform.get(i+1))){
                if(waveform.get(i)>=0){
                    positive.add(new HighPoint(i,waveform.get(i)));
                }else{
                    negative.add(new HighPoint(i,waveform.get(i)));
                }
            }
        }
        
        for(int i=0;i<positive.size()-1;i++){
            positive.get(i).connection(positive.get(i+1));
        }
        
        for(int i=0;i<negative.size()-1;i++){
            negative.get(i).connection(negative.get(i+1));
        }

    }

    /**
     * [CHALLENGE]
     * Saves the current waveform values into a file
     */
    public void save(){
        try{
        OutputStream os=new FileOutputStream(new File(UIFileChooser.save()));
        OutputStreamWriter osw=new OutputStreamWriter(os);
        for(double d:waveform){
            osw.append(""+d);
            osw.append("\r\n");
        }
        osw.flush();
        osw.close();
        os.close();
    }catch(Exception e1){
        e1.printStackTrace();
    }
        
    }
    }   
    
