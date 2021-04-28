package disk_scheduling;

import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Arrays;
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
        System.out.println("\n------------------------\n");
        ShortestSeekTimeFirstAlgorithm();
        System.out.println("\n------------------------\n");
        newlyOptimizedAlgorithm();
        System.out.println("\n------------------------\n");
        Cscane();
        System.out.println("\n------------------------\n");
        CLook();
        System.out.println("\n------------------------\n");
        scan();
        System.out.println("\n------------------------\n");
        look();
        System.out.println("\n------------------------\n");
        fcfs();
        System.out.println("\n------------------------");
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

    public static void scan() {
        int[] arr = new int[nRequests + 2];
        arr[0] = 0;
        arr[1] = initial;
        for (int i = 2; i < nRequests + 2; i++) {
            arr[i] = requests.get(i - 2);
        }
        Arrays.sort(arr);
        int x = 0;
        for (int i = 0; i < nRequests + 2; i++) {
            if (arr[i] == initial) {
                x = i;
            }
        }
        System.out.print("< " + initial + " , ");
        int sum = 0;
        sum += abs(initial - arr[x - 1]);
        for (int i = x - 1; i >= 0; i--) {
            if (i != 0) {
                sum += abs(arr[i] - arr[i - 1]);
            }
            if (i == 0) {
                System.out.print(arr[i] + " , ");
            } else {
                System.out.print(arr[i] + " , ");
            }
        }
        sum += abs(arr[0] - arr[x + 1]);
        for (int i = x + 1; i < arr.length; i++) {
            if (i != arr.length - 1) {
                sum += abs(arr[i] - arr[i + 1]);
            }
            if (i == arr.length - 1) {
                System.out.println(arr[i] + " >");
            } else {
                System.out.print(arr[i] + " , ");
            }
        }

        System.out.print("Total head Movements to scan algorithm : ");
        System.out.println(sum);

    }

    public static void look() {
        int[] arr = new int[nRequests + 1];
        arr[0] = initial;
        for (int i = 1; i < nRequests + 1; i++) {
            arr[i] = requests.get(i - 1);
        }
        Arrays.sort(arr);
        int x = 0;
        for (int i = 0; i < nRequests + 1; i++) {
            if (arr[i] == initial) {
                x = i;
            }
        }
        System.out.print("< " + initial + " , ");
        int sum = 0;
        sum += abs(initial - arr[x - 1]);
        for (int i = x - 1; i >= 0; i--) {
            if (i != 0) {
                sum += abs(arr[i] - arr[i - 1]);
            }
            if (i == 0) {
                System.out.print(arr[i] + " , ");
            } else {
                System.out.print(arr[i] + " , ");
            }
        }
        sum += abs(arr[0] - arr[x + 1]);
        for (int i = x + 1; i < arr.length; i++) {
            if (i != arr.length - 1) {
                sum += abs(arr[i] - arr[i + 1]);
            }
            if (i == arr.length - 1) {
                System.out.println(arr[i] + " >");
            } else {
                System.out.print(arr[i] + " , ");
            }
        }

        System.out.print("Total head Movements to look algorithm : ");
        System.out.println(sum);

    }

    public static void fcfs() {
        int sum = 0;
        System.out.print("< " + initial + " , ");
        for (int i = 0; i < nRequests; i++) {
            if (i == nRequests - 1) {
                System.out.println(requests.get(i) + " >");
            } else {
                System.out.print(requests.get(i) + " , ");
            }
        }
        System.out.print("Total head Movements to FCFS algorithm : ");
        sum += abs(initial - requests.get(0));
        for (int i = 0; i < nRequests; i++) {
            if (i != nRequests - 1) {
                sum += abs(requests.get(i) - requests.get(i + 1));
            }
        }
        System.out.println(sum);
    }
}
