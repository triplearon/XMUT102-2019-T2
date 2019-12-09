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
import java.text.*;

/**
 * Earthquake
 * Describes a single earthquake, with information from the earthquake data
 * available from http://quakesearch.geonet.org.nz/
 * Has methods for computing the distance and time difference between two earthquakes
 */

public class Earthquake{

    //Constants needed to parse the date and time from the file and print it out
    public static final SimpleDateFormat DATE_PARSER = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    public static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("HH:mm:ss' UTC, 'dd/MM/yyyy ");

    private String ID;
    private Date time;
    private String region;
    private double longitude, latitude;
    private double depth;
    private double magnitude;

    /** Construct a new EarthQuake object */
    public Earthquake(String ID, String time,
    double longitude, double latitude,
    double magnitude, double depth, String region){
        this.ID = ID;
        try{ this.time = DATE_PARSER.parse(time); }catch(ParseException e){System.out.println(e);}
        this.longitude = longitude;
        this.latitude = latitude;
        this.magnitude = magnitude;
        this.depth = depth;
        this.region = region;
    }

    /**
     * Distance from this earthquake to the other earthquake, in km
     * Formula from http://www.movable-type.co.uk/scripts/latlong.html
     */
    public double distanceTo(Earthquake other){
        double lat1 = this.latitude *Math.PI/180;
        double lat2 = other.latitude *Math.PI/180;
        double difLong = other.longitude*Math.PI/180 - this.longitude*Math.PI/180;

        return Math.acos( (Math.sin(lat1)*Math.sin(lat2)) +
            (Math.cos(lat1)*Math.cos(lat2))* Math.cos(difLong) ) * 6371;
    }
    
    public boolean isCloseTo(Earthquake other){
        return (this.distanceTo(other)<=1) && (Math.abs(this.depth-other.depth)<=1) && (Math.abs(this.timeBetween(other))>=1440);
    }
    
    public boolean isCloseTo_Big(Earthquake other){
        return (this.distanceTo(other)<=10) && (Math.abs(this.depth-other.depth)<=10);
    }
    
    public void printCloseMsg(Earthquake other){
        UI.printf("%s & %s\n",this.ID,other.ID);
        UI.printf("Distance: %.2f KM\n",this.distanceTo(other));
        UI.printf("Depth: %.2f KM\n",Math.abs(this.depth-other.depth));
        UI.printf("Day: %.2f\n",this.timeBetween(other)/60/24);
        UI.println("====================================");
    }

    /**
     * Time difference between this earthquake and the other, in minutes
     */
    public double timeBetween(Earthquake other){
        return (other.time.getTime()-this.time.getTime())/60000; 
    }

    /** Get depth of the earthquake */
    public double getDepth(){
        return this.depth;
    }

    /** Get magnitude of the earthquake */
    public double getMagnitude(){
        return this.magnitude;
    }

    /** Get earthquake ID */
    public String getID(){
        return this.ID;
    }

    /** Get earthquake region */
    public String getRegion(){
        return this.region;
    }

    /** Get time */
    public Date getTime(){
        return this.time;
    }

    /**
     * Returns a nicely formatted String describing the earthquake
     */
    public String toString(){
        return (this.ID +
            " at " + DATE_FORMATTER.format(this.time) +
            " mag:" + this.magnitude +
            " depth:" + this.depth +
            " at (" + this.longitude +","+ this.latitude +")" +
            " in " + this.region);
    }

}
