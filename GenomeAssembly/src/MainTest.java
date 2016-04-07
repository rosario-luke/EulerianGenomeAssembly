/**
 * Created by lucasrosario on 4/7/16.
 */
public class MainTest {

    public static void main(String[] args){
        long length = 6;
        int subLength = 5;
        String largeString = RandomGenerator.generateRandomString(length);
        String[] pieces = RandomGenerator.breakString(subLength, largeString);

        System.out.println("String: " + largeString);
        for (int i = 0; i < pieces.length; i ++){
            System.out.println(pieces[i]);
        }

        GenomeSolver solver = new GenomeSolver(pieces);

    }
}
