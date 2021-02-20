package com.mobile.azrinurvani.roomwordssample.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mobile.azrinurvani.roomwordssample.R;
import com.mobile.azrinurvani.roomwordssample.model.entity.Word;

//TODO 22 - Add NewWordActivity
public class NewWordActivity extends AppCompatActivity {

    //EXTRA_APPLY adalah KEY untuk PUT Extra, jadi bebas mau dikasih apa
    public static final String EXTRA_REPLY = "EXTRA_APPLY_KEY";
   // public static final String EXTRA_REPLY = "com.mobile.azrinurvani.roomwordssample.REPLY";

    private EditText mEditWordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);

        mEditWordView = findViewById(R.id.edit_word);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditWordView.getText())){
                    setResult(RESULT_CANCELED,replyIntent);
                }else{
                    String word = mEditWordView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY,word);
                    setResult(RESULT_OK,replyIntent);
                }
                finish();
            }
        });
    }


}