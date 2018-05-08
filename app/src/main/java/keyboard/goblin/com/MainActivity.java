package keyboard.goblin.com;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity12";
    private MainKeyboardLayout keyboard ;
    StringBuilder string = new StringBuilder();
    private TextView txtView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        keyboard = findViewById(R.id.keyboard);
        txtView = findViewById(R.id.txt_view) ;


        keyboard.setOnButtonEventListener(new MainKeyboardLayout.OnButtonEventListener() {
            @Override
            public void onTextOutput(View view, String text, boolean isDel) {
                if (!isDel){
                    string.append(text);
                }else {
                    if (string.length() > 0){
                        string.deleteCharAt(string.length()-1);
                    }
                }
                txtView.setText(string);
                Log.i(TAG, "onTextOutput: " + string );
            }

            @Override
            public void onClick(View view) {

            }
        });

    }
}
