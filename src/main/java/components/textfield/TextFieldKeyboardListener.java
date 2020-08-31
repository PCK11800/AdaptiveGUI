package components.textfield;

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
        if(keyCode == 42)
        {
            isShift = true;
        }
        textField.setText(keyMap.handle(keyCode, isShift));
        System.out.print(keyMap.handle(keyCode, isShift));
    }
}
