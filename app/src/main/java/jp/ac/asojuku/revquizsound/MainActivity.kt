package jp.ac.asojuku.revquizsound

import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //SoundPool型のインスタンス変数のフィールドプロパティを宣言
    private lateinit var soundPool: SoundPool;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        kaiButton.setOnClickListener {
            //ボタンがクリックされた時の処理
            val intent = Intent(this,QuestActivity::class.java)
            this.startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        this.soundPool = if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
            //ロリポップ以前のバージョンの時のsoundPool設定
            SoundPool(
                2,              //同時音源数
                AudioManager.STREAM_ALARM,  //オーディオの種類
                0
            );
        }else{
            //新バージョン以降のsoundPool設定
            val audioAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ALARM).build()
            //オーディオ設定を使ってsoundPoolのインスタンスを作成
            SoundPool.Builder().setMaxStreams(2)                //同時音源数
                .setAudioAttributes(audioAttributes).build()    //オーディオの種類
        }
        this.answer = soundPool.load(this,R.raw.answer,1)
    }
}
