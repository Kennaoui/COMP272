/*********************************************************
 *
 * Place your name / section number in the 'HW1.java' file
 * (NOT this file). This main routine is a driver routine for
 * testing the methods in the file 'HW1.java'.
 *
 *********************************************************/

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int     assignmentScore = 0;
        boolean errorFlag = false;

        System.out.println("\\nProgram testing driver starting  ...");

        /**************************************
         *
         * Testing of the LinkedList code.
         *
         ***************************************/

        HW1.SinglyLinkedList list1 = new HW1.SinglyLinkedList();

        // Insert the values using addLast
        list1.addLast(1);
        list1.addLast(2);
        list1.addLast(3);
        list1.addLast(4);
        list1.addLast(1);
        list1.addLast(2);
        list1.addLast(3);
        list1.addLast(4);
        list1.addLast(9);
        list1.addLast(100);
        list1.addLast(35);

        // Check toString after insertions
        String expectedInit = "[1, 2, 3, 4, 1, 2, 3, 4, 9, 100, 35]";
        if (!list1.toString().equals(expectedInit)) {
            System.out.println("ERROR 1: Linked-list test failed initial addLast()/toString()");
            errorFlag = true;
        }

        // removeDuplicates()
        list1.removeDuplicates(); // expected -> [1, 2, 3, 4, 9, 100, 35]
        String expectedAfterDup = "[1, 2, 3, 4, 9, 100, 35]";
        if (!errorFlag && !list1.toString().equals(expectedAfterDup)) {
            System.out.println("ERROR 2: Linked-list test failed removeDuplicates()");
            errorFlag = true;
        }
        /*
         * Score the Linked-list removeDuplicates portion (max points 15)
         */
        if (!errorFlag)
            assignmentScore += 15;
        else
            errorFlag = false;



        // reverse() using stack
        list1.reverse(); // expected -> [35, 100, 9, 4, 3, 2, 1]
        String expectedAfterRev = "[35, 100, 9, 4, 3, 2, 1]";
        if (!errorFlag && !list1.toString().equals(expectedAfterRev)) {
            System.out.println("ERROR 3: Linked-list test failed reverse()");
            errorFlag = true;
        }
        /*
         * Score the Linked-list reverse portion (max points 15)
         */
        if (!errorFlag)
            assignmentScore += 15;
        else
            errorFlag = false;




        // getNthFromEnd(2) on current list -> expect 2
        try {
            int val = list1.getNthFromEnd(2);
            if (!errorFlag && val != 2) {
                System.out.println("ERROR 4: Linked-list test failed getNthFromEnd(2)");
                errorFlag = true;
            }
        } catch (Throwable t) {
            System.out.println("ERROR 5: getNthFromEnd crashed: " + t);
            errorFlag = true;
        }

        /*
         * Score the Linked-list getNthFromEnd portion (max points 15)
         */
        if (!errorFlag)
            assignmentScore += 15;
        else
            errorFlag = false;


        /**************************************
         *
         * Testing of isPalindrome code.
         *
         ***************************************/

        if (!HW1.isPalindrome("Was it a car or a cat I saw")) {
            System.out.println("ERROR 6: isPalindrome test failed");
            errorFlag = true;
        }

        if (!errorFlag && !HW1.isPalindrome("Race Car")) {
            System.out.println("ERROR 7: isPalindrome test failed");
            errorFlag = true;
        }

        if (!errorFlag && !HW1.isPalindrome("Never odd or even")) {
            System.out.println("ERROR 8: isPalindrome test failed");
            errorFlag = true;
        }

        if (!errorFlag && HW1.isPalindrome("civic2")) {
            System.out.println("ERROR 9: isPalindrome test failed");
            errorFlag = true;
        }

        /*
         * Score the isPalindrome portion (max points 25)
         */
        if (!errorFlag)
            assignmentScore += 25;
        else
            errorFlag = false;


        /**************************************
         *
         * Testing of the algorithm analysis.
         *
         ***************************************/

        System.out.println("\\nThe algorithm analysis testing skipped (15 points each, total 30 points) ...");
        // Algorithm analysis will be graded via return values & written justifications.

        System.out.println("\\nTesting complete. Assignment 1 Score is: " + assignmentScore );
    }
}
