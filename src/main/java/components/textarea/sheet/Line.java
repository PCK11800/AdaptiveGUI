package components.textarea.sheet;

import java.util.ArrayList;

public class Line extends ArrayList<Character> {

    public Character popLast()
    {
        char c = this.get(size() - 1);
        this.remove(size() - 1);
        return c;
    }

    public void pushFirst(char c)
    {
        add(0, c);
    }

    public Character getLast()
    {
        return this.get(size() - 1);
    }

    public Character popFirst()
    {
        char c = this.remove(0);
        trimToSize();
        return c;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder(this.size());
        for(Character ch: this)
        {
            builder.append(ch);
        }
        return builder.toString();
    }
}
