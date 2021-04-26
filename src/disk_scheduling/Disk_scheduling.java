package disk_scheduling;

import java.util.ArrayList;
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
    }

}
