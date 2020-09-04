package components.textfield;

import java.util.HashMap;

public class KeyMap{

    private HashMap<Integer, String> lowerCaseMap = new HashMap<>();
    private HashMap<Integer, String> upperCaseMap = new HashMap<>();

    public KeyMap()
    {
        setLowerCase();
        setUpperCase();
    }

    private void setLowerCase()
    {
        lowerCaseMap.put(1, "");
        lowerCaseMap.put(2, "1");
        lowerCaseMap.put(3, "2");
        lowerCaseMap.put(4, "3");
        lowerCaseMap.put(5, "4");
        lowerCaseMap.put(6, "5");
        lowerCaseMap.put(7, "6");
        lowerCaseMap.put(8, "7");
        lowerCaseMap.put(9, "8");
        lowerCaseMap.put(10, "9");
        lowerCaseMap.put(11, "0");
        lowerCaseMap.put(12, "-");
        lowerCaseMap.put(13, "=");
        lowerCaseMap.put(14, "\b");

        lowerCaseMap.put(15, "\t");
        lowerCaseMap.put(16, "q");
        lowerCaseMap.put(17, "w");
        lowerCaseMap.put(18, "e");
        lowerCaseMap.put(19, "r");
        lowerCaseMap.put(20, "t");
        lowerCaseMap.put(21, "y");
        lowerCaseMap.put(22, "u");
        lowerCaseMap.put(23, "i");
        lowerCaseMap.put(24, "o");
        lowerCaseMap.put(25, "p");
        lowerCaseMap.put(26, "[");
        lowerCaseMap.put(27, "]");
        lowerCaseMap.put(43, "\"");

        lowerCaseMap.put(58, "");
        lowerCaseMap.put(30, "a");
        lowerCaseMap.put(31, "s");
        lowerCaseMap.put(32, "d");
        lowerCaseMap.put(33, "f");
        lowerCaseMap.put(34, "g");
        lowerCaseMap.put(35, "h");
        lowerCaseMap.put(36, "j");
        lowerCaseMap.put(37, "k");
        lowerCaseMap.put(38, "l");
        lowerCaseMap.put(39, ";");
        lowerCaseMap.put(40, "'");
        lowerCaseMap.put(28, "\n"); //Enter

        lowerCaseMap.put(42, ""); //Shift
        lowerCaseMap.put(44, "z");
        lowerCaseMap.put(45, "x");
        lowerCaseMap.put(46, "c");
        lowerCaseMap.put(47, "v");
        lowerCaseMap.put(48, "b");
        lowerCaseMap.put(49, "n");
        lowerCaseMap.put(50, "m");
        lowerCaseMap.put(51, ",");
        lowerCaseMap.put(52, ".");
        lowerCaseMap.put(53, "/");

        lowerCaseMap.put(29, "");
        lowerCaseMap.put(56, "");
        lowerCaseMap.put(3675, "");
        lowerCaseMap.put(57, " ");
    }

    private void setUpperCase()
    {
        upperCaseMap.put(1, "");
        upperCaseMap.put(2, "!");
        upperCaseMap.put(3, "@");
        upperCaseMap.put(4, "#");
        upperCaseMap.put(5, "$");
        upperCaseMap.put(6, "%");
        upperCaseMap.put(7, "^");
        upperCaseMap.put(8, "&");
        upperCaseMap.put(9, "*");
        upperCaseMap.put(10, "(");
        upperCaseMap.put(11, ")");
        upperCaseMap.put(12, "_");
        upperCaseMap.put(13, "+");
        upperCaseMap.put(14, "\b");

        upperCaseMap.put(15, "    ");
        upperCaseMap.put(16, "Q");
        upperCaseMap.put(17, "W");
        upperCaseMap.put(18, "E");
        upperCaseMap.put(19, "R");
        upperCaseMap.put(20, "T");
        upperCaseMap.put(21, "Y");
        upperCaseMap.put(22, "U");
        upperCaseMap.put(23, "I");
        upperCaseMap.put(24, "O");
        upperCaseMap.put(25, "P");
        upperCaseMap.put(26, "{");
        upperCaseMap.put(27, "}");
        upperCaseMap.put(43, "|");

        upperCaseMap.put(58, "");
        upperCaseMap.put(30, "A");
        upperCaseMap.put(31, "S");
        upperCaseMap.put(32, "D");
        upperCaseMap.put(33, "F");
        upperCaseMap.put(34, "G");
        upperCaseMap.put(35, "H");
        upperCaseMap.put(36, "J");
        upperCaseMap.put(37, "K");
        upperCaseMap.put(38, "L");
        upperCaseMap.put(39, ":");
        upperCaseMap.put(40, "\"");
        upperCaseMap.put(28, "\n");

        upperCaseMap.put(42, ""); //Shift
        upperCaseMap.put(44, "Z");
        upperCaseMap.put(45, "X");
        upperCaseMap.put(46, "C");
        upperCaseMap.put(47, "V");
        upperCaseMap.put(48, "B");
        upperCaseMap.put(49, "N");
        upperCaseMap.put(50, "M");
        upperCaseMap.put(51, "<");
        upperCaseMap.put(52, ">");
        upperCaseMap.put(53, "?");

        upperCaseMap.put(29, "");
        upperCaseMap.put(56, "");
        upperCaseMap.put(3675, "");
        upperCaseMap.put(57, " ");
    }

    private boolean contains(Integer keyCode)
    {
        return lowerCaseMap.containsKey(keyCode) && upperCaseMap.containsKey(keyCode);
    }

    public String handle(Integer keyCode, boolean shifted)
    {
        if(contains(keyCode))
        {
            if(shifted)
            {
                return upperCaseMap.get(keyCode);
            }
            else {
                return lowerCaseMap.get(keyCode);
            }
        }
        else{
            return "";
        }
    }
}

