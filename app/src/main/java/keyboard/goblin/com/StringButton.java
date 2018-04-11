package keyboard.goblin.com;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;

public class StringButton extends AppCompatButton {
    private MainKeyboardLayout.OnButtonEventListener mOnButtonEventListener;

    public StringButton(Context context, int id, String text, MainKeyboardLayout
            .OnButtonEventListener listener) {
        super(context);
        setId(id);
        mOnButtonEventListener = listener;
        init(text);
    }

    public StringButton(Context context, int id, String text) {
        this(context, id, text, null);
    }

    public StringButton(Context context, String text, MainKeyboardLayout
            .OnButtonEventListener listener) {
        super(context);
        init(text);
        mOnButtonEventListener = listener;
    }

    public StringButton(Context context, String text) {
        this(context, text, null);
    }

    private void init(String text) {
        setText(text);
        setTextSize(16);
        setBackgroundResource(R.drawable.app_icon_keyboard);
        setGravity(Gravity.CENTER);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                outputText();
            }
        });
    }

    private void outputText() {
        String text = getText().toString().trim();
            char[] textChar = text.toCharArray();
            if (textChar.length == 1) {
                if (mOnButtonEventListener != null) {
                    mOnButtonEventListener.onTextOutput(this, textChar[0], false);
                }
            } else {
                if (mOnButtonEventListener != null) {
                    mOnButtonEventListener.onTextOutput(this, textChar[0], true);
                }
            }

    }

    public StringButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StringButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
