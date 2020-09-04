package components.textarea.sheet;

import java.util.ArrayList;

public class Sheet {

    /*
     * Should hold lines of string
     * Each line acts as an individual row.
     */
    private int rowLength;
    private ArrayList<Line> rows = new ArrayList<>();

    private int caretPosition = 0;
    private int currentRow = 0;

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
        if(c != '\n'){
            // Adds a single character
            Line line = rows.get(row);
            line.add(caretPosition, c);
            restructureRowsToFit(rows);

            this.caretPosition++;
        }
        else{
            // If it's a new line, add a new line
            rows.add(currentRow + 1, new Line());
            rows.trimToSize();

            this.currentRow++;
            this.caretPosition = 0;
        }
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

        if(caretPosition == 0)
        {
            line = rows.get(row - 1);
            caretPosition = line.size() - 1;
        }

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
                if(previousLine.size() < rowLength){
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

    public int[] getCaretPixelPosition(float spaceWidth, float spaceHeight)
    {
        int[] caretPosition = new int[2];
        caretPosition[0] = (int) (spaceWidth * this.caretPosition);
        caretPosition[1] = (int) (spaceHeight * (this.currentRow + 1));
        return caretPosition;
    }

    public void moveCaretUp()
    {
        if(currentRow > 0){
            Line currentLine = rows.get(currentRow);
            Line previousLine = rows.get(currentRow - 1);

            if(currentLine.size() > previousLine.size()){
                this.caretPosition = previousLine.size() - 1;
            }

            this.currentRow--;
        }
    }

    public void moveCaretDown()
    {
        if(currentRow < rows.size() - 1)
        {
            Line currentLine = rows.get(currentRow);
            Line nextLine = rows.get(currentRow + 1);

            if(currentLine.size() > nextLine.size()) {
                this.caretPosition = nextLine.size() - 1;
            }

            this.currentRow++;
        }
    }

    public void moveCaretLeft()
    {
        if(caretPosition <= 0)
        {
            if(currentRow != 1){
                this.currentRow--;
                this.caretPosition = rows.get(currentRow - 1).size() - 1;
            }
        }
        else{
            this.caretPosition--;
        }
    }

    public void moveCaretRight()
    {
        if(caretPosition >= rows.get(currentRow).size())
        {
            if(currentRow != rows.size() - 1)
            {
                this.currentRow++;
                this.caretPosition = 0;
            }
        }
        else{
            this.caretPosition++;
        }
    }

    public void resetCaret()
    {
        if(currentRow < 0){
            currentRow = 0;
            caretPosition = 0;
        }
    }

    public int getCaretPosition() {
        return caretPosition;
    }

    public int getCurrentRow() {
        return currentRow;
    }
}
