package components.textarea.legacy;

import components.textarea.keys.KeyMap;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class TextFieldKeyboardListener implements NativeKeyListener {

    private TextField textField;
    private KeyMap keyMap = new KeyMap();
    private boolean isShift = false;

    public TextFieldKeyboardListener(TextField textField)
    {
        this.textField = textField;
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        int keyCode = nativeKeyEvent.getKeyCode();
        handleInput(keyCode);
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        int keyCode = nativeKeyEvent.getKeyCode();
        if(keyCode == 42){ isShift = false; }
    }

    private void handleInput(int keyCode)
    {
        if(textField.isFocused())
        {
            if(keyCode == 42)
            {
                isShift = true;
            }
            String keyText = keyMap.handle(keyCode, isShift);
            textField.append(keyText);
        }
    }
}
