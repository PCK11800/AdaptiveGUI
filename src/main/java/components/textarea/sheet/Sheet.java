package components.textarea.sheet;

import java.util.ArrayList;

public class Sheet {

    /*
     * Should hold lines of string
     * Each line acts as an individual row.
     */
    private int rowLength;
    private ArrayList<Line> rows = new ArrayList<>();

    public int caretPosition = 0;
    public int currentRow = 0;

    public Sheet(int rowLength)
    {
        this.rowLength = rowLength - 1;
        rows.add(new Line());
    }

    /*
     *  Adds a single character to a specific point in a
     *  specific row. If it causes such row to overflow,
     *  then it moves those out of bounds to the next row.
     *
     *  If there isn't an available row at the end, then
     *  adds a new row.
     */
    public void push(Character c, int caretPosition, int row)
    {
        // Adds a single character
        Line line = rows.get(row);
        line.add(caretPosition, c);
        restructureRowsToFit(rows);

        this.caretPosition++;
    }

    /*
     * Go through each individual row.
     * If row overflows, shift everything by one
     * to ensure all row fits in rowLength
     */
    private void restructureRowsToFit(ArrayList<Line> rows)
    {
        for(int i = 0; i < rows.size(); i++){
            Line line = rows.get(i);

            // Check if overflows
            boolean overflow = false;
            if(line.size() - 1 > rowLength){
                overflow = true;
            }

            // If overflow, pop last character
            if(overflow){
                char overflow_character = line.popLast();
                // Check if next line exist. If true, push.
                // Else, create new line and push.
                boolean nextLineExist = true;
                if(i + 1 >= rows.size()){
                    nextLineExist = false;
                }
                if(nextLineExist)
                {
                    rows.get(i + 1).pushFirst(overflow_character);
                }
                else{
                    rows.add(new Line());
                    rows.get(rows.size() - 1).pushFirst(overflow_character);
                    this.currentRow++;
                    this.caretPosition = 0;
                }
            }
        }
    }

    /*
     * Deletes a character at specific position.
     */
    public void pop(int caretPosition, int row)
    {
        // Deletes and shift everything in row.
        Line line = rows.get(row);

        if(line.size() > 0){
            line.remove(caretPosition - 1);
            this.caretPosition--;
        }
        else{
            line = rows.get(row - 1);
            line.remove(caretPosition - 1);
        }

        restructureRowsToFill(rows);
    }

    /*
     * Shifts everything after this character to fill
     * in the hole left behind.
     */
    private void restructureRowsToFill(ArrayList<Line> rows)
    {
        for(int i = 0; i < rows.size(); i++)
        {
            Line line = rows.get(i);
            line.trimToSize();
            if(i > 0)
            {
                Line previousLine = rows.get(i - 1);
                if(previousLine.getLast() == null){
                    previousLine.add(line.popFirst());
                }
            }

            if(caretPosition <= 0){
                if(rows.size() != 1){
                    rows.remove(rows.size() - 1);
                    rows.trimToSize();
                    caretPosition = rows.get(rows.size() - 1).size();
                    currentRow--;
                }
                else{
                    caretPosition = 0;
                    currentRow = 0;
                }
            }
        }
    }

    public String toString()
    {
        StringBuilder output = new StringBuilder();
        for(int i = 0; i < rows.size(); i++)
        {
            output.append(rows.get(i).toString());
            if(i != rows.size()){
                output.append("\n");
            }
        }
        return output.toString();
    }

    public int getRowLength()
    {
        return rowLength;
    }

    public int getLastRow()
    {
        return rows.size() - 1;
    }
}
