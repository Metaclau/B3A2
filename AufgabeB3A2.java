package Kurzaufgabe3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class AufgabeB3A2 {

    public int[] data;

    public AufgabeB3A2(int[] data) {
        this.data = data;
    }

    public int[] choosePermutation(int kSmallest) {

        int[] dataCopy = Arrays.copyOf(this.data, this.data.length);
        Arrays.sort(dataCopy);
        int[] result = new int[dataCopy.length];
        for(int i=0; i < result.length; i++) {
            result[i] = dataCopy[i];
        }
        for(int i=1; i < kSmallest; i++) {
            int j = result.length - 2;
            while(j >= 0 && result[j] >= result[j + 1]) {
                j--;
            }
            if(j < 0) {
                break;
            }
            int k = result.length - 1;
            while(result[k] <= result[j]) {
                k--;
            }
            swap(result, j, k);
            reverse(result, j + 1, result.length - 1);
        }
        return result;
      /*  int n = data.length;
        int[] result = new int[n];
        boolean[] used = new boolean[n];

        //Berechne die lexikographische kSmallest-kleinste Permutation
        for(int i=0; i <n; i++) {
            int count = 0;
            for(int j=0; j < n; j++) {
                if(!used[j]) {
                    if(count == kSmallest) {
                        result[i] = data[j];
                        used[j] = true;
                        break;
                    } 
                    count++;
                }
            }
        }
        return result; */
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int i, int j) {
        while(i < j) {
            swap(nums, i++, j--);
        }
    }

    public static int[] readStandardIn() throws NumberFormatException {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> puffer = new ArrayList<Integer>();
 
        while(sc.hasNextLine()) {
            try {
                puffer.add(Integer.parseInt(sc.nextLine()));
            } catch(NumberFormatException e) {
                System.out.println("Input list contains a non-number!");
                e.getStackTrace();
            }
        }
        sc.close();

        int[] out = new int[puffer.size()];

        for(int i=0; i < out.length; i++) {
            out[i] = puffer.get(i);
        }
        return out;
    }

    public static void main(String[] args) {
       int k = 0;

       if(args.length != 1) {
        System.out.println("One argument is required!");
        return;
       }

       k = Integer.parseInt(args[0]);

       if(k < 1) {
        System.out.println("k has to be a positive number!");
        return;
       }

       int data[] = readStandardIn();
       AufgabeB3A2 a = new AufgabeB3A2(data);

       int[] permutation = a.choosePermutation(k);
       System.out.println("Sorted input: ");
       System.out.println(Arrays.toString(data));
       System.out.println("Die " + k + "-smallest permutation is: ");
       System.out.println(Arrays.toString(permutation));
    }
}
