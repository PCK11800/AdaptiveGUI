package components.textarea.keys;

import components.textarea.surface.TextArea;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class KeyListener implements NativeKeyListener {

    private TextArea textArea;
    private KeyMap keyMap = new KeyMap();
    private boolean isShift = false;

    public KeyListener(TextArea textArea)
    {
        this.textArea = textArea;
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
        //Do nothing
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        int keyCode = nativeKeyEvent.getKeyCode();
        if(keyCode == 42){ isShift = true; }
        handleInput(keyCode);
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        int keyCode = nativeKeyEvent.getKeyCode();
        if(keyCode == 42){ isShift = false; }
    }

    private void handleInput(int keyCode)
    {
        if(textArea.isFocused())
        {
            String keyText = keyMap.handle(keyCode, isShift);
            if(!keyText.equals("")){
                textArea.append(keyText);
            }
        }
    }
}
