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

}
