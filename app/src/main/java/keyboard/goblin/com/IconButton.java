package keyboard.goblin.com;

import android.content.Context;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;
import android.view.View;

public class IconButton extends AppCompatImageButton {
    private MainKeyboardLayout.OnButtonEventListener mOnButtonEventListener;

    public IconButton(Context context, int id, int resourceId, MainKeyboardLayout
            .OnButtonEventListener listener) {
        super(context);
        setId(id);
        mOnButtonEventListener = listener;
        init(resourceId);
    }

    public IconButton(Context context, int id, int resourceId) {
        this(context, id, resourceId, null);
    }

    public IconButton(Context context, int resourceId, MainKeyboardLayout
            .OnButtonEventListener listener) {
        super(context);
        init(resourceId);
        mOnButtonEventListener = listener;
    }

    public IconButton(Context context, int resourceId) {
        this(context, resourceId, null);
    }

    private void init(int resourceId) {
        setImageResource(resourceId);
        setBackgroundResource(R.drawable.app_icon_keyboard);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnButtonEventListener != null) {
                    mOnButtonEventListener.onClick(v);
                }
            }
        });
    }

    public IconButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public IconButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
