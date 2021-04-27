package disk_scheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Disk_scheduling {

    public static ArrayList<Integer> requests = new ArrayList<Integer>();
    public static int initial, nRequests;

    public static void main(String[] args) {

        int temp;
        //ArrayList<Integer> va = (ArrayList<Integer>) requests.clone();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter initial head start cylinder : ");
        initial = scanner.nextInt();

        System.out.print("Enter number of requests : ");
        nRequests = scanner.nextInt();

        for (int i = 0; i < nRequests; i++) {
            System.out.print("Enter request number " + (i + 1) + " : ");
            temp = scanner.nextInt();
            requests.add(temp);
        }
        ShortestSeekTimeFirstAlgorithm();
        newlyOptimizedAlgorithm();
        Cscane();
        CLook();
    }

    public static void ShortestSeekTimeFirstAlgorithm() {

        ArrayList<Integer> temp = (ArrayList<Integer>) requests.clone();
        int index, sum = 0;
        String print = "< ";

        temp.add(initial);

        Collections.sort(temp);

        index = temp.indexOf(initial);
        for (int i = index - 1, j = index + 1; i >= 0 || j < temp.size(); j++, i--) {

            if (i >= 0 && (j >= temp.size() || temp.get(index) - temp.get(i) < temp.get(j) - temp.get(index))) {

                j--;

                sum += (temp.get(index) - temp.get(i));

                index = i;

                print += temp.get(i) + " , ";
            } else {

                i++;

                sum += (temp.get(j) - temp.get(index));

                index = j;

                print += temp.get(j) + " , ";
            }
        }

        print = print.substring(0, print.length() - 2);
        print += ">";

        System.out.println(print + '\n' + "Total head Movements to shortest seek time first algorithm : " + sum);
    }

    public static void newlyOptimizedAlgorithm() {
        ArrayList<Integer> temp = (ArrayList<Integer>) requests.clone();
        int sum;
        String print = "< ";

        Collections.sort(temp);

        sum = temp.get(temp.size() - 1);

        for (int i = 0; i < temp.size() - 1; i++) {
            print += temp.get(i) + " , ";
        }

        print += sum + " >";

        System.out.println(print + '\n' + "Total head Movements to newly optimized algorithm : " + sum);
    }

    public static void Cscane() {
        ArrayList<Integer> cscane = (ArrayList<Integer>) requests.clone();
        ArrayList<Integer> output = new ArrayList<>();
        cscane.add(initial);
        Collections.sort(cscane);
        int index = cscane.indexOf(initial);
        int total_Head = 0;
        int end = 200;
        // output.add(initial);
        for (int i = index; i > -1; i--) {
            output.add(cscane.get(i));

        }
        output.add(0);
        output.add(end);
        for (int i = cscane.size() - 1; i > index; i--) {
            output.add(cscane.get(i));

        }
        System.out.print("<");
        for (int i = 0; i < output.size(); i++) {
            if (i != 0 && i < output.size()) {
                System.out.print(",");
            }
            System.out.print(" " + output.get(i));
            if (i > 0) {
                total_Head = total_Head + Math.abs(output.get(i) - output.get(i - 1));
            }
        }
        System.out.println(" >");

        System.out.println("Total head Movements to CScan algorithm : " + total_Head);

    }

    public static void CLook() {
        ArrayList<Integer> clook = (ArrayList<Integer>) requests.clone();
        ArrayList<Integer> output = new ArrayList<>();
        clook.add(initial);
        Collections.sort(clook);
        int index = clook.indexOf(initial);
        int total_Head = 0;
        for (int i = index; i > -1; i--) {
            output.add(clook.get(i));

        }
        //  output.add(clook.get(0));
        //output.add(clook.get(clook.size()-1));
        for (int i = clook.size() - 1; i > index; i--) {
            output.add(clook.get(i));

        }
        System.out.print("<");
        for (int i = 0; i < output.size(); i++) {
            if (i != 0 && i < output.size()) {
                System.out.print(",");
            }
            System.out.print(" " + output.get(i));
            if (i > 0) {
                total_Head = total_Head + Math.abs(output.get(i) - output.get(i - 1));
            }
        }
        System.out.println(" >");

        System.out.println("Total head Movements to CLook algorithm : " + total_Head);

    }

}
