package components.textfield;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class TextFieldKeyboardListener implements NativeKeyListener {

    private TextField textField;
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
        String keyText = NativeKeyEvent.getKeyText(keyCode);
        if(keyCode == 42){ isShift = false; }
    }

    private void handleInput(int keyCode)
    {
        String keyText = NativeKeyEvent.getKeyText(keyCode);
        if(keyCode == 42) { isShift = true; keyText = "";}
        if(!isShift) { keyText = keyText.toLowerCase(); }

        //Handle Comma
        if(keyCode == 51) { keyText = ","; }

        //System.out.println(keyText + ", " + keyCode); //Debugger
        System.out.print(keyText);
    }
}
