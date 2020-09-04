package tools;

public class Maths {

    public static int numDiv(int a, int b) {
        if (b < 2) // nonsense value
            throw new IllegalArgumentException();
        int result = 0;
        for (; a % b == 0; a /= b)
            result++;
        return result;
    }
}
