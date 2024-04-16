// jdh CS224 Spring 2023

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math;

public class BruteForce {
    public static void main(String args[]) {
        testOne();
        testTwo();
    }

    //---------------------------------------------------
    // An example, with four friends: Jack, Mary, Henry, and Elizabeth
    // - Jack has a conflict with Mary and Elizabeth
    // - Mary has conflict with Henry and Elizabeth
    // - Henry has a conflict with Elizabeth
    // There is a single subset of size = 2 having no conflicts:
    // Jack and Henry

    public static void testOne() {
        Friend jack = new Friend("Jack");
        Friend mary = new Friend("Mary");
        Friend henry = new Friend("Henry");
        Friend elizabeth = new Friend("Elizabeth");
        Friend[] allFriends = {jack, mary, henry, elizabeth};

        Friend[] jack_conflicts = {mary, elizabeth};
        jack.setConflicts(new ArrayList<Friend>(Arrays.asList(jack_conflicts)));
        Friend[] mary_conflicts = {henry, elizabeth};
        mary.setConflicts(new ArrayList<Friend>(Arrays.asList(mary_conflicts)));
        Friend[] henry_conflicts = {elizabeth};
        henry.setConflicts(new ArrayList<Friend>(Arrays.asList(henry_conflicts)));

        List<Friend> best = optimize(allFriends);
        System.out.println("largest independent set:");
        for (Friend f: best)
            System.out.print(f + " ");
        System.out.println();
    }

    //---------------------------------------------------

    public static void testTwo() {
        // implement this
        Friend frances = new Friend("Frances");
        Friend tom = new Friend("Tom");
        Friend elihu = new Friend("Elihu");
        Friend willem = new Friend("Willem");
        Friend salma = new Friend("Salma");
        Friend esteban = new Friend("Esteban");
        Friend zach = new Friend("Zach");
        Friend sonali = new Friend("Sonali");
        Friend ron = new Friend("Ron");
        Friend helena = new Friend("Helena");
        Friend[] allFriends2 = {frances, tom, elihu, willem, salma, esteban, zach, sonali, ron, helena};
        
        Friend[] frances_conflicts = {elihu, willem, salma, esteban, zach, sonali, helena};
        frances.setConflicts(new ArrayList<Friend>(Arrays.asList(frances_conflicts)));
        Friend[] tom_conflicts = {willem, esteban, sonali, ron, helena};
        tom.setConflicts(new ArrayList<Friend>(Arrays.asList(tom_conflicts)));
        Friend[] elihu_conflicts = {frances, willem, salma, zach, ron};
        elihu.setConflicts(new ArrayList<Friend>(Arrays.asList(elihu_conflicts)));
        Friend[] willem_conflicts = {frances, tom, elihu, salma, esteban, zach, ron};
        willem.setConflicts(new ArrayList<Friend>(Arrays.asList(willem_conflicts)));
        Friend[] salma_conflicts = {frances, elihu, willem, esteban, sonali, helena};
        salma.setConflicts(new ArrayList<Friend>(Arrays.asList(salma_conflicts)));
        Friend[] esteban_conflicts = {frances, tom, willem, salma, zach, ron};
        esteban.setConflicts(new ArrayList<Friend>(Arrays.asList(esteban_conflicts)));
        Friend[] zach_conflicts = {frances, elihu, willem, esteban, helena};
        zach.setConflicts(new ArrayList<Friend>(Arrays.asList(zach_conflicts)));
        Friend[] sonali_conflicts = {frances, tom, salma, ron};
        sonali.setConflicts(new ArrayList<Friend>(Arrays.asList(sonali_conflicts)));
        Friend[] ron_conflicts = {tom, elihu, willem, esteban, sonali};
        ron.setConflicts(new ArrayList<Friend>(Arrays.asList(ron_conflicts)));
        Friend[] helena_conflicts = {frances, tom, salma, zach};
        helena.setConflicts(new ArrayList<Friend>(Arrays.asList(helena_conflicts)));

        List<Friend> best = optimize(allFriends2);
        System.out.println("largest independent set:");
        for (Friend f: best)
            System.out.print(f + " ");
        System.out.println();
    }

    //----------------------------------------------------------

    public static List<Friend> optimize(Friend allFriends[]) {
            // go through all friends check conflicts return the set of friends with the least conflicts
            Boolean[] choose = new Boolean[allFriends.length];
            List<Friend> best = new ArrayList<>(allFriends.length);
            List<Friend> curr = new ArrayList<>(allFriends.length);
            // i = index
            int allfrendlen = allFriends.length;
            int bitwise;
            for (int i = 0; i <  Math.pow(2, allFriends.length)- 1; i++){
                for (int j = 0; j < allFriends.length; j++){
                    choose[j] = false;
                } // reset all choose to false per index
                for(int j = 0; j < allFriends.length; j++){ // j: 1, 2, 3, 4 (0001, 0010, 0100, 1000)
                    bitwise = (int) Math.pow(2, j);
                    int outcome = i & bitwise;
                    if (outcome > 0) {
                        choose[j] = true;
                    }
                }
                // build the ith subset
                // for j in 0 to N - 1, include friends j if choose[j] is true
                for(int k = 0; k < allFriends.length; k++){
                    if(choose[k] == true) {
                        curr.add(allFriends[k]);
                    }
                }
                // check for conflicts if size > 1

                boolean conflict = false;
                Friend check = null;
                if(curr.size() > 1) {
                    for (int l = 0; l < curr.size() - 1 ; l++) {
                        check = curr.get(l);
                        for(int k = 1; k < curr.size(); k++) {
                           if(check.conflicts.contains(curr.get(k)) == true){
                               conflict = true;
                           }
                        }
                    }

                }
                // keep track of the largest sub set seen
                if(conflict != true && curr.size() > best.size()) {
                    best = new ArrayList<>(curr);
                }
                // reset curr after even if not saved
                curr.removeAll(curr);
            }


            return best;
    } // optimize()
}
