package com.brown_chicken.httpapp01;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //画面表示する
        setContentView(R.layout.activity_main);

        //「送信」ボタンのオブジェクト取得とイベントリスナ登録
        Button b = (Button)findViewById(R.id.button1);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //EditTextのオブジェクトを取得
        EditText e = (EditText)findViewById(R.id.editText1);

        //EditTextに入力した文字列を取得
        String moji = e.getText().toString();

        if (moji.length() <= 0) {   //入力された文字がなければ終了
            return;
        }

        //HttpPostAsyncのオブジェクトを生成
        HttpPostAsync task = new HttpPostAsync(this);

        //非同期処理を実行　doInBackgroundメソッドに文字を渡す
        task.execute(moji);

        //EditTextをクリア
        e.setText("");
    }
}
