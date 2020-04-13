package com.brown_chicken.httpapp01;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpPostAsync extends AsyncTask<String, Void, String> {
    private Context context;

    //引数のcontextをメンバ変数のcontextに設定する
    public HttpPostAsync(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection con = null;
        URL url = null;

        String urlStr = "http://サーバー名/receive.php";
        //MOJI+送信データをString型dataに格納
        String data = "MOJI=" + params[0];

        //送信するデータのバイト数＝初期値０
        int length = 0;
        String result = "";

        try {
            //URLの作成
            url = new URL(urlStr);

            //接続用HttpURLConnectionオブジェクト生成
            con = (HttpURLConnection) url.openConnection();

            //送信データのバイト数を取得
            length = data.getBytes("UTF-8").length;

            //キャッシュを使用しない
            con.setUseCaches(false);

            //リダイレクトを許可しない
            con.setInstanceFollowRedirects(false);

            //データを読み取る
            con.setDoInput(true);

            //データを書き込む
            con.setDoOutput(true);

            //POST送信の設定
            con.setRequestMethod("POST");

            //OutputStreamに書き込まれるバイト数を設定
            con.setFixedLengthStreamingMode(length);

            //タイムアウト
            con.setReadTimeout(10000);  //10秒
            con.setConnectTimeout(10000);   //10秒

            //接続
            con.connect();

            //ネットワークを利用してデータを送信するOutputStreamオブジェクトを取得
            OutputStream out = con.getOutputStream();

            //データをString型からバイト数に変換して送信
            out.write(data.getBytes("UTF-8"));
            out.flush();

            //出力ストリームを閉じる
            out.close();

            //ステータスコードの取得
            int status = con.getResponseCode();

            if (status == HttpURLConnection.HTTP_OK) { //正常
                //ネットワークを利用してデータを受信するInputStreamオブジェクトを取得
                InputStream in = con.getInputStream();

                //受信データ格納用配列bの設定（1024バイトまで格納可能とする）
                byte[] b = new byte[1024];

                //データを受信
                in.read(b);

                //バイトデータをString型に変換しresultに収納（文字コードUTF-8で設定する）
                result = new String(b, "UTF-8");

                //入力ストリームを閉じる
                in.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        //トーストで受信データを表示
        Toast t = Toast.makeText(context, result, Toast.LENGTH_SHORT);
        t.show();
    }
}
