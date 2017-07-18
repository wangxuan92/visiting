package visiting.kuban.io.visiting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private ImageView qrCode;
//    private String qrCodeText = "https://www.baidu.com/";//二维码信息

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}
