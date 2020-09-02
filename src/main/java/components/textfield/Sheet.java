package components.textfield;

import java.util.ArrayList;

public class Grid {

    private int length;
    private ArrayList<Character[]> rows = new ArrayList<>();

    public Grid(int length)
    {
        this.length = length;
        rows.add(row(length));
    }

    public boolean add(Character c, int column, int row)
    {
        if(row > rows.size() - 1) { return false; }
        else{
            //Check whether column position is larger than row length
            //If larger, then go to new line






            Character[] thisRow = rows.get(row);
            char prev = c;
            for(int i = column; i < thisRow.length; i++)
            {
                char tmp = prev;
                prev = thisRow[i];
                thisRow[i] = tmp;
            }
            return true;
        }
    }

    public Character[] row(int length)
    {
        return new Character[length];
    }

    private Character[] getLastRow()
    {
        return rows.get(rows.size() - 1);
    }
}
