import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * LeetCode 1231 Divide Chocolate
 * https://leetcode.com/problems/divide-chocolate/
 */
public class DivideChocolate {


    /**
     * Binary search entry point.
     * Returns the index in arr[] for target (if found).
     * !!! NOT PART OF THE SOLUTION!!!
     */
    static int search(int[] arr, int target) {

        // **** sanity check(s) ****
        if (arr.length == 1) {
            if (arr[0] == target) return 0;
            else return -1;
        }

        // **** start recursion ****
        int ndex = search(arr, target, 0, arr.length - 1);

        // **** return index of target in arr[] ****
        return ndex;
    }


    /**
     * Binary search recursive call.
     * !!! NOT PART OF THE SOLUTION!!!
     */
    private static int search(int[] arr, int target, int left, int right) {

        // **** base case ****
        if (left > right) return -1;

        // **** compute mid value ****
        int mid = left + (right - left) / 2;

        // **** check if target was found ****
        if (arr[mid] == target) return mid;

        // **** decide which way to go ****
        if (target > arr[mid]) left = mid + 1;      // go right
        else right = mid - 1;                       // go left

        // **** recursive call ****
        return search(arr, target, left, right);
    }


    /**
     * Find the maximum total sweetness of the piece you can get by 
     * cutting the chocolate bar `optimally`.
     * 
     * Time: O(n * log(m)) - Space: O(1)
     * n = sweetness.length
     * m = low .. high
     */
    static public int maximizeSweetness(int[] sweetness, int k) {

        // **** initialization ****
        int low     = 1;
        int high    = Arrays.stream(sweetness).sum() / (k + 1);
        
        // **** binary search - O(log(m) ****
        while (low < high) {

            // ???? ????
            System.out.println("maximizeSweetness <<< low: " + low + " high: " + high);

            // **** compute mid ( + 1 allows us to get out of an endless loop) ****
            int mid = (low + high + 1) / 2;

            // ???? ????
            System.out.println("maximizeSweetness <<< mid: " + mid);

            // **** greedy approach ****
            if (canSplit(sweetness, k, mid)) low = mid;
            else high = mid - 1;
        }

        // **** sweetness level ****
        return low;
    }


    /**
     * Implementation of Greedy algorithm.
     */
    static private boolean canSplit(int[] sweetness, int k, int mid) {

        // **** initialization ****
        int chunks  = 0;
        int sum     = 0;

        // **** O(n) ****
        for (int i = 0; i < sweetness.length; i++) {

            // **** update sum ****
            sum += sweetness[i];

            // ???? ????
            // System.out.println("canSplit <<< sum: " + sum);

            // **** split chocolate bar (if needed) ****
            if (sum >= mid) {
                chunks++;
                sum = 0;

                // ???? ????
                // System.out.println("canSplit <<< chunks: " + chunks);
            }
        }

        // **** we can split the chocolate bar ****
        return chunks >= (k + 1);
    }


    /**
     * Test scaffold
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** read array of sweetness ****
        int[] sweetness = Arrays.stream(br.readLine().trim().split(","))
                                .mapToInt(Integer::parseInt)
                                .toArray();

        // **** read k ****
        int k = Integer.parseInt(br.readLine().trim());

        // ???? ????
        System.out.println("main <<< sweetness: " + Arrays.toString(sweetness));
        System.out.println("main <<< k: " + k);


        // ???? prompt for value to search in swetness[] and search (if needed) ????
        System.out.print("main <<< inStr [\"\" to skip]: ");
        String inStr = br.readLine().trim();
        if (!inStr.equals("")) {
            int s = Integer.parseInt(inStr);
            System.out.println("main <<< s: " + s);
            System.out.println("main <<< search: " + search(sweetness, s));
        }


        // **** close buffered reader ****
        br.close();

        // **** call function of interest and display result ****
        System.out.println("main <<< output: " + maximizeSweetness(sweetness, k));
    }
}