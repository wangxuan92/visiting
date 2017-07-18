package visiting.kuban.io.visiting.view;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.Calendar;

/**
 * 显示时间的控件
 * Created by wangxuan on 16/10/25.
 */
public class DigitalClock extends TextView {
    private final static String TAG = "DigitalClock";

    private Calendar mCalendar;
    private String mFormat = "HH:mm";

    private Runnable mTicker;
    private Handler mHandler;

    private boolean mTickerStopped = false;

    public DigitalClock(Context context) {
        super(context);
        initClock(context);
    }

    public DigitalClock(Context context, AttributeSet attrs) {
        super(context, attrs);
        initClock(context);
    }

    private void initClock(Context context) {
        if (mCalendar == null) {
            mCalendar = Calendar.getInstance();
        }
    }

    @Override
    protected void onAttachedToWindow() {
        mTickerStopped = false;
        super.onAttachedToWindow();
        mHandler = new Handler();

        mTicker = new Runnable() {
            public void run() {
                if (mTickerStopped)
                    return;
                mCalendar.setTimeInMillis(System.currentTimeMillis());
                // setText(mSimpleDateFormat.format(mCalendar.getTime()));
                setText(DateFormat.format(mFormat, mCalendar));
                invalidate();
                long now = SystemClock.uptimeMillis();
                // long next = now + (1000 - now % 1000);
                long next = now + (1000 - System.currentTimeMillis() % 1000);
                // TODO
                mHandler.postAtTime(mTicker, next);
            }
        };
        mTicker.run();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mTickerStopped = true;
    }

    public void setFormat(String format) {
        mFormat = format;
    }

}