/**
 * Created by lucasrosario on 4/7/16.
 */
public class MainTest {

    public static void main(String[] args){
        int success = 0;
        int fail = 0;
        for (int i = 0; i < 10000; i ++) {
            long length = 50;
            int subLength = 2;
            String largeString = RandomGenerator.generateRandomString(length);
            String[] pieces = RandomGenerator.breakString(subLength, largeString);

//        System.out.println("String: " + largeString);
//        for (int i = 0; i < pieces.length; i ++){
//            System.out.println(pieces[i]);
//        }

            GenomeSolver solver = new GenomeSolver(pieces);
            String solution = solver.solve();
            if (solution == null) {
                //System.out.println("Could not solve puzzle");
                fail ++;
            } else {
               /* System.out.println("Solution: " + solution);
                if (solution.equals(largeString)) {
                    System.out.println("Solution Correct!");
                }*/
                success ++;
            }
        }
        System.out.println("Success: " + success);
        System.out.println("Fail: "  + fail);
    }
}
