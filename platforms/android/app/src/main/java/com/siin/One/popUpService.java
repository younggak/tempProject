package com.siin.One;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;

public class popUpService extends Service {
  private static popUpService pus = null;
  private String body;
  private String number;
  private WindowManager wm;
  private View fancyLl;
  private Button stopBtn;
  private TextView bodyText;
  private TextView numberText;
  private TextView checkText;
  private RelativeLayout alert_layout;
  private TextView alert_textview;
  public popUpService() {
  }

  @Override
  public IBinder onBind(Intent intent) {
    // TODO: Return the communication channel to the service.
    throw new UnsupportedOperationException("Not yet implemented");
  }
  @Override
  public void onCreate() {

    wm = (WindowManager) getSystemService(WINDOW_SERVICE);

    LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
    fancyLl = layoutInflater.inflate(R.layout.background_view, null);
    stopBtn = (Button)fancyLl.findViewById(R.id.closeBtn);
    bodyText = (TextView)fancyLl.findViewById(R.id.bodyText);
    numberText = (TextView)fancyLl.findViewById(R.id.numberText);
    checkText = (TextView)fancyLl.findViewById(R.id.checkText);
    alert_layout = (RelativeLayout)fancyLl.findViewById(R.id.alert_layout);
    alert_textview = (TextView)fancyLl.findViewById(R.id.alert_textview);

    WindowManager.LayoutParams parameters = new WindowManager.LayoutParams(800,700,
      WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
      ,WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSLUCENT);

    parameters.x = 0;
    parameters.y = -400;
    parameters.gravity = Gravity.CENTER | Gravity.CENTER;

    stopBtn.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view) {
        removePopup();
      }
    });


    wm.addView(fancyLl, parameters);
    super.onCreate();

  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    body = intent.getStringExtra("text");
    number = intent.getStringExtra("number");
    bodyText.setText(body);
    numberText.setText(number);

    if(body.contains("택배")){
      Drawable drawable = getResources().getDrawable(R.drawable.layout_bg_only_top_radius_red);
      alert_layout.setBackground(drawable);
      alert_textview.setText("피싱위험문자");
    }
    SMSBook sb = new SMSBook();
    sb.setNumber(number);
    sb.setText(body);
    DataCenter.getInstance().getSmsBookList().add(sb);

    return super.onStartCommand(intent, flags, startId);
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
  }

  public void removePopup() {
    wm.removeView(fancyLl);
    stopSelf();
  }
}
