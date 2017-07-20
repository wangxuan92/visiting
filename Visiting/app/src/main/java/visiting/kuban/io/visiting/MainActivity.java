package visiting.kuban.io.visiting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ImageView qrCode;
//    private String qrCodeText = "https://www.baidu.com/";//二维码信息

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        closeBar();
        qrCode = (ImageView) findViewById(R.id.qr_code);
        Glide.with(getApplicationContext())
                .load("https://media-ssl.kuban.io/static/wechat/app/qrcode/linmiao/1.png")
                .into(qrCode);
//        if (null != qrCode) {
//            qrCode.setVisibility(View.VISIBLE);
//            Bitmap qrCodeBitmap = CodeUtils.createImage(qrCodeText, UIUtils.dip2pix(this, 280), UIUtils.dip2pix(this, 280), null);
//            qrCode.setImageBitmap(qrCodeBitmap);
//        }
    }

    @Override
    protected void onDestroy() {
        showBar();
        super.onDestroy();
    }

    /**
     * 关闭Android导航栏，实现全屏
     */
    private void closeBar() {
        try {
            String command;
            command = "LD_LIBRARY_PATH=/vendor/lib:/system/lib service call activity 42 s16 com.android.systemui";
            ArrayList<String> envlist = new ArrayList<String>();
            Map<String, String> env = System.getenv();
            for (String envName : env.keySet()) {
                envlist.add(envName + "=" + env.get(envName));
            }
            String[] envp = envlist.toArray(new String[0]);
            Process proc = Runtime.getRuntime().exec(
                    new String[]{"su", "-c", command}, envp);
            proc.waitFor();
        } catch (Exception ex) {
            // Toast.makeText(getApplicationContext(), ex.getMessage(),
            // Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 显示导航栏
     */
    public static void showBar() {
        try {
            String command;
            command = "LD_LIBRARY_PATH=/vendor/lib:/system/lib am startservice -n com.android.systemui/.SystemUIService";
            ArrayList<String> envlist = new ArrayList<String>();
            Map<String, String> env = System.getenv();
            for (String envName : env.keySet()) {
                envlist.add(envName + "=" + env.get(envName));
            }
            String[] envp = envlist.toArray(new String[0]);
            Process proc = Runtime.getRuntime().exec(
                    new String[]{"su", "-c", command}, envp);
            proc.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
