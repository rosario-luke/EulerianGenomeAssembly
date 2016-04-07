import java.util.ArrayList;
import java.util.Random;

/**
 * Created by lucasrosario on 4/7/16.
 */
public class RandomGenerator {
    public static String generateRandomString(long length) {
        Random rand = new Random(System.currentTimeMillis());
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < length; i++) {
            switch (rand.nextInt(4)) {
                case 0:
                    sb.append("A");
                    break;
                case 1:
                    sb.append("C");
                    break;
                case 2:
                    sb.append("G");
                    break;
                case 3:
                    sb.append("T");
                    break;
                default:
                    break;
            }
        }
        return sb.toString();
    }


    public static String[] breakString(int len, String str) {
        ArrayList<String> stringList = new ArrayList<String>();
        for (int i = 0; i <= (str.length() - len); i++) {
            stringList.add(str.substring(i, i + len));
        }
        return (String[]) stringList.toArray();
    }
}

