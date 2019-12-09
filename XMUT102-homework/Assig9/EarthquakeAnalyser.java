// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a XMUT102 assignment.
// You may not distribute it in any other way without permission.

/* Code for XMUT102 - 2019T2, Assignment 9
 * Name:
 * Username:
 * ID:
 */

import ecs100.*;
import java.util.*;
import java.io.*;

/**
 * EarthquakeAnalyser
 * Analyses data about a collection of 4335 NZ earthquakes from May 2016 to May 2017
 * Each line of the file "earthquake-data.txt" has a description of one earthquake:
 *   ID time longitude  latitude magnitude depth region
 * Data is from http://quakesearch.geonet.org.nz/
 *  Note bigearthquake-data.txt has just the 421 earthquakes of magnitude 4.0 and above
 *   which may be useful for testing, since it is not as big as the full file.
 * 
 * Core:  two methods:
 *   loadData
 *      Loads the data from a file into a field containing an
 *      ArrayList of Earthquake objects.
 *      Hint : to make an Earthquake object, read all the lines from the file
 *              and then take each line apart into the values to pass to the
 *              Earthquake constructor
 *   findBigOnes
 *      Prints out all the earthquakes in the ArrayList that have a magnitude 5.5 and over.
 *      Hint: see the methods in the Earthquake class, especially getMagnitude and toString
 *   
 * Completion: one method:
 *   findPairs
 *  Compares each Earthquake in the list with every other Earthquake
 *      and finds every pair of "close" earthquakes - earthquakes that
 *        - are within 1km of each other, and
 *        - have depths within 1km of each other, and
 *        - are separated by at least 24 hours days 
 *      For each pair, prints
 *        - their ID's,
 *        - the distance between them
 *        - the depth difference
 *        - the number of days between them.

 * Challenge:
 *  findFollowOns;
 *      Constructs a new ArrayList containing every earthquake with a magnitude that is at least 6.0
 *      For each such earthquake on this list
 *       - finds a list of all the "follow-on" earthquakes:
 *         later earthquakes within a distance of 10km and depth difference <= 10km.
 *       - If there are at least two follow-on earthquakes, then it prints out
 *          the full details of the big earthquake followed by
 *          ID, magnitude and days since the big one for each follow-on earthquake
 *
 * The file "example-output.txt" has sample output for the "bigearthquake-data.txt"
 *   file, which only contains earthquakes with magnitude 4 and above.
 */

public class EarthquakeAnalyser{

    private ArrayList<Earthquake> earthquakes = new ArrayList<Earthquake>();

    /*
     * Load data from the data file into the earthquakes field:
     * clear the earthquakes field.
     * Read lines from file
     * For each line, use Scanner to break up each line and make an Earthquake
     *  adding it to the earthquakes field.
     */
    public void loadData(){
        String filename = UIFileChooser.open("Data File");
        
        try{
            Scanner sc=new Scanner(new FileInputStream(new File(filename)));
            String line=null;
            
            while(sc.hasNext()){
               line=sc.nextLine();
               String[] dates=line.split("\\s+");
               this.earthquakes.add(new Earthquake(dates[0],dates[1],Double.parseDouble(dates[2]),Double.parseDouble(dates[3]),
               Double.parseDouble(dates[4]),Double.parseDouble(dates[5]),dates[6]));
            }
            
        }catch(IOException e){
            e.printStackTrace();
        }

        UI.printf("Loaded %d earthquakes into list\n", this.earthquakes.size());
        UI.println("----------------------------");
    }

    /**
     * Utility method: 
     * Read each line in a file into a list of Strings
     * Returns the list
     */
    public ArrayList<String> readAllLines(String fname){
        ArrayList<String> ans = new ArrayList<String>();
        try {
            Scanner scan = new Scanner(new File(fname));
            while (scan.hasNext()){
                ans.add(scan.nextLine());
            }
            scan.close();
            return ans;
        }
        catch(IOException e){UI.println("File reading failed");}
        return null;
    }

    /**
     * Print details of all earthquakes with a magnitude of 5.5 or more
     */
    public void findBigOnes(){
        UI.println("Earthquakes 5.5 and above");
        
        for(Earthquake eq:earthquakes){
            if(eq.getMagnitude()>=5.5){
                UI.println(eq);
            }
        }

        UI.println("------------------------");
    }

    /**
     * Print all pairs of earthquakes within 1km of each other and within 1km depth from each other
     * and separated by at least 1 day;
     */
    public void findPairs(){
        UI.println("Close earthquakes");
        for(int i=0;i<earthquakes.size();i++){
            for(int k=earthquakes.size()-1;k>i;k--){
                if(earthquakes.get(i).isCloseTo(earthquakes.get(k))){
                    earthquakes.get(i).printCloseMsg(earthquakes.get(k));
                }
            }        
        }

        UI.println("----------------------------");
    }

    /**
     * CHALLENGE 
     * Constructs a new ArrayList containing every earthquake with a magnitude that is at least 6 
     * For each earthquake on this list
     * - finds a list of all the "follow-on" earthquakes:
     *   later earthquakes within a distance of 10km and depth difference <= 10km.
     * - If there are at least two follow-on earthquakes, then it prints out
     *     the full details of the big earthquake followed by
     *    ID, magnitude and days since the big one for each follow-on earthquake
     */

    public void findFollowOns(){
        UI.println("Big earthquakes and their follow-on earthquakes");
        if(earthquakes== null){UI.println("Please press load"); return;}
        ArrayList<Earthquake> bigList=new ArrayList<>();
        
        for(Earthquake eq:earthquakes){
            if(eq.getMagnitude()>=6){
                bigList.add(eq);
               
            }
        }
        
        for(Earthquake bigOne:bigList){
            ArrayList<Earthquake> followOn=new ArrayList<>();
            for(Earthquake eq:earthquakes){
                if(!(eq.equals(bigOne)) && bigOne.isCloseTo_Big(eq) &&bigOne.timeBetween(eq)>=0){
                    followOn.add(eq);
                }
            }
            
            if(followOn.size()>=2){
                UI.println(bigOne);
                for(Earthquake follow:followOn){
                  UI.printf("%s msg=%.1f days later: %.0f\n",follow.getID(),follow.getMagnitude(),bigOne.timeBetween(follow)/60/24);
                }
                UI.println("------------------------");
            }
            
            
        }
       
    }


    /** Setup the GUI */
    public void setupGUI(){
        UI.initialise();
        UI.addButton("Load", this::loadData);
        UI.addButton("Big ones",  this::findBigOnes);
        UI.addButton("Pairs", this::findPairs);
        UI.addButton("Follow-ons", this::findFollowOns);
        UI.addButton("Quit", UI::quit);
        UI.setDivider(1.0);  //text pane only 
    }

    public static void main(String[] arguments){
        EarthquakeAnalyser obj = new EarthquakeAnalyser();
        obj.setupGUI();
    }   

}
