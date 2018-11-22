import java.util.Arrays;

public class KMPSearch {
    public static int search(String source, String substring) {
        int indicesArray[] = new int[substring.length()];
        indicesArray[0] = 0;
        int i = 1, j = 0, l = substring.length();
        while (i < l) {
            if (substring.charAt(i) == substring.charAt(j)) {
                indicesArray[i] = j + 1;
                j++;
            } else {
                j = j == 0 ? 0 : indicesArray[j - 1];
                if (j != 0)
                    continue;
            }
            i++;
        }
        System.out.println(Arrays.toString(indicesArray));
        i = j = 0;
        while (i + j < source.length()) {
            if (source.charAt(i + j) == substring.charAt(j)) {
                j++;
                if (j == l)
                    return i;
                else
                    continue;
            } else {
                j = j == 0 ? 0 : indicesArray[j - 1];
            }
            i++;
        }

        return -1;
    }
}
