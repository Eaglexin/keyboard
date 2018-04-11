package keyboard.goblin.com;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.Random;

public class MainKeyboardLayout extends ViewGroup {
    private static final String TAG = "MainKeyboardLayout";

    private static final int MARGIN_LR = 2;
    private static final int MARGIN_TB = 3;
    private static final int LINE_TOP = 2;
    private static final int COLUMN = 3;
    private String[] NUMBER_PANEL = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
    private int mIconIndexCount = 4;
    private OnButtonEventListener mOnKeyboardButtonEventListener;


    public void setOnButtonEventListener(OnButtonEventListener listener) {
        mOnKeyboardButtonEventListener = listener;
    }

    interface OnButtonEventListener {
        void onTextOutput(View view, char text, boolean isDel);

        void onClick(View view);
    }


    private String[] disorderArray(String[] strs){
        String [] arr2 = new String[12];
        int count = strs.length;
        int cbRandCount = 0;// 索引
        int cbPosition = 0;// 位置
        int k =0;
        do {
            Random rand = new Random();
            int r = count - cbRandCount;
            cbPosition = rand.nextInt(r);
            arr2[k++] = strs[cbPosition];
            cbRandCount++;
            strs[cbPosition] = strs[r - 1];// 将最后一位数值赋值给已经被使用的cbPosition
        } while (cbRandCount < count);
        String s = arr2[9];
        arr2[9] = "" ;
        arr2[10] = s ;
        arr2[11] = "del" ;

        for (int i = 0; i < arr2.length; i++) {
            Log.i(TAG, "disorderArray: "  +arr2[i]);
        }
        return arr2;
    }



    public MainKeyboardLayout(Context context) {
        super(context);
    }

    public MainKeyboardLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        NUMBER_PANEL = disorderArray(NUMBER_PANEL);
        init(context);
    }

    public MainKeyboardLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private OnButtonEventListener mOnButtonEventListener = new OnButtonEventListener() {
        @Override
        public void onTextOutput(View view, char text, boolean isAppend) {
            if (mOnKeyboardButtonEventListener != null) {
                mOnKeyboardButtonEventListener.onTextOutput(view, text, isAppend);
            }
        }

        @Override
        public void onClick(View view) {
            if (mOnKeyboardButtonEventListener != null) {
                mOnKeyboardButtonEventListener.onClick(view);
            }
        }
    };

    private void init(Context context) {
        for (int i = 0; i < NUMBER_PANEL.length; i++) {
            addView(new StringButton(context, NUMBER_PANEL[i], mOnButtonEventListener), i);
        }
        setUnlockOrQrCodeEnable();
    }

    public void setUnlockOrQrCodeEnable() {
        int index = 0;
        mIconIndexCount = index;
        if (index == 1) {
            setBackgroundResource(R.drawable.app_img_keyboard_background_3);
        } else if (index == 2) {
            setBackgroundResource(R.drawable.app_img_keyboard_background_2);
        } else if (index == 3) {
            setBackgroundResource(R.drawable.app_img_keyboard_background_1);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int itemWidth = widthSize / COLUMN - MARGIN_LR * 2;
        int itemHeight = itemWidth / 3  - MARGIN_TB * 2;
        for (int i = 0; i < NUMBER_PANEL.length; i++) {
            View child = getChildAt(i);
            child.measure(MeasureSpec.makeMeasureSpec
                    (itemWidth, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec
                    (itemHeight, MeasureSpec.EXACTLY));
        }
        if (mIconIndexCount == 1) {
            itemHeight = itemHeight * 2 + MARGIN_TB + LINE_TOP;
        }
        for (int i = 0; i < mIconIndexCount; i++) {
            View child = getChildAt(12 + i);
            if (i == mIconIndexCount && mIconIndexCount == 2) {
                itemHeight = itemHeight * 2 + MARGIN_TB + LINE_TOP;
            }
            child.measure(MeasureSpec.makeMeasureSpec
                    (itemWidth, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec
                    (itemHeight, MeasureSpec.EXACTLY));
        }
        setMeasuredDimension(widthSize, heightSize);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        layoutPanel();
    }

    private void layoutPanel() {
        int itemWidth = getMeasuredWidth() / COLUMN;
        int itemHeight = itemWidth / 3;
        for (int i = 0; i < NUMBER_PANEL.length; i++) {
            int line = i / (COLUMN );
            int lineOffset = i % (COLUMN );
            View view = getChildAt(i);
            int left = lineOffset * itemWidth + MARGIN_LR;
            int top = line * itemHeight + MARGIN_TB + LINE_TOP;
            view.layout(left, top, left + view.getMeasuredWidth(), top + view.getMeasuredHeight());
        }
    }
}
