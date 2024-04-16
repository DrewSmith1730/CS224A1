// jdh Spring 2023 CS224

import java.awt.Point;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class PointCollection {
    List<Point> points;
    Point y_arr[];
    int origN;

    //--------------------------------------------------------------

    public PointCollection() {
        points = new ArrayList<Point>();
    }

    //--------------------------------------------------------------

    void addPoint(Point p) {
        points.add(p);
    }

    //--------------------------------------------------------------

    PointPair closestPairRec(List<Point> Px, List<Point> Py) {
        // implement this function
        // first get the lenght of Px
        int len = Px.size();
        double d;

        // if the size of the list gets to 3 or less calc min distance
        if (len <= 3){
            // check for the lowest distance between the three
            return bruteforceRec(Px);
        }

        // spliting part of the alg
        List<Point> Q = new ArrayList<>(Px.subList(0, (len) / 2));
        List<Point> R = new ArrayList<>(Px.subList((len) / 2, len));
        // create the qX, qY, rX, rY splits for the next recursive call
        List<Point> qX = Q;
        List<Point> rX = R;
        // building the qY and rY from the points in the qX in order they are from Py
        List<Point> qY = new ArrayList<>(Py.subList(0, (len) / 2));
        List<Point> rY = new ArrayList<>(Py.subList(len/2, len);
        for (Point v : Py) {
            if (qX.contains(v)){
                qY.add(v);
            } else {

            }
        }

        // recursive calls
        PointPair closestQ = closestPairRec(qX, qY);
        PointPair closestR = closestPairRec(rX, rY);

        System.out.println(closestQ);
        System.out.println(closestR);

        return closestQ;
    } // closestPairRec()

    // brute force to run in the base case of the divide and concoure alg
    PointPair bruteforceRec(List<Point> Px){
        PointPair closest = null;
        // double nested loop to check every point to every other point
        Point compare = null;
        Point compareTO = null;
        double lowestDis = 1000000;
        for (int i = 0; i < Px.size(); i++){
            compare = Px.get(i);
            for (int j = 0; j < Px.size(); j++){
                // compare each i to j and save the cheapest value seen as a pair of points
                compareTO = Px.get(j);
                if (compare.distanceTo(compareTO) < lowestDis){
                    closest = new PointPair(compare, compareTO);
                    lowestDis = compare.distanceTo(compareTO);
                }
            }
        }
        return closest;
    }


    //--------------------------------------------------------------

    PointPair closestPair() {
        // implement this function
        // make a list of the points sorted by x and list sorted by y
        List<Point> sortedX = points;
        List<Point> sortedY = points;
        sortedX.sort(Point::compareX);
        sortedY.sort(Point::compareY);
        PointPair closest = null;
        // run closest pair rec func
        // recursive function call for closestPairRec
        closest = closestPairRec(sortedX, sortedY);
        // return the closest pair found
        return closest;
    } // closetPair()

    //--------------------------------------------------------------

    PointPair bruteForce() {
        // implement this function
        PointPair closest = null;
        // double nested loop to check every point to every other point
        Point compare = null;
        Point compareTO = null;
        double lowestDis = 1000000;
        for (int i = 0; i < points.size(); i++){
            compare = points.get(i);
            for (int j = 0; j < points.size(); j++){
                // compare each i to j and save the cheapest value seen as a pair of points
                compareTO = points.get(j);
                if (compare.distanceTo(compareTO) < lowestDis){
                    closest = new PointPair(compare, compareTO);
                    lowestDis = compare.distanceTo(compareTO);
                }
            }
        }
        return closest;
    } // bruteForce()
}

