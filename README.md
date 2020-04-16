# HttpApp01
＜アプリ名＞HTTP通信アプリ
＜機能＞
⑴Webサーバにリクエストを送信する
⑵Webサーバ側での処理結果を表示する
＜動作概要＞
⑴テキストボックスに入力した文字列をWebサーバに送信する
⑵PHPプログラムによりレスポンス情報を設定する
⑶Webサーバからレスポンスをトーストで表示する
＜技術＞
⑴HTTP通信の仕組み
⑵AsyncTaskの利用
⑶HttpURLConnectionの利用
⑷データの送信
⑸データの受信
⑹PHPプログラムの作成
  //----receive.php---------------------
  <?php
  //送信文字を取得する
  $moji = $_POST["MOJI"];

  //送信側へメッセージをレスポンスする。
  echo "受信メッセージ:".$moji;
  ?>
  //-----------------------------------