
import java.util.Random;

public class Tests {

    public static int[] getShuffleArray(){
        int[] arr=rowArray();
        shuffle(arr);
        return arr;
    }

    private static int[] rowArray(){
        int[] arr=new int[16];
        for (int i=0;i<15;i++) {
            arr[i]=i+1;
        }

        return arr;
    }

    private static void shuffle(int[] inputArr){
        int temp;
        int buffer;
        int randomIdx;
        Random random=new Random();
        for (int i = 0; i < inputArr.length-1; i++) {
            randomIdx=random.nextInt(15);

            temp=inputArr[randomIdx];
            buffer=inputArr[i];

            inputArr[i]=temp;
            inputArr[randomIdx]=buffer;
        }
    }
}
