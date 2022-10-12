// Generated code from Butter Knife. Do not modify!
package com.ant.smartclubfoot.activity;

import android.view.View;
import android.widget.Button;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ant.smartclubfoot.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ConnectDeviceActivity_ViewBinding implements Unbinder {
  private ConnectDeviceActivity target;

  private View view7f08005e;

  private View view7f08005d;

  @UiThread
  public ConnectDeviceActivity_ViewBinding(ConnectDeviceActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ConnectDeviceActivity_ViewBinding(final ConnectDeviceActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btn_continue, "field 'btnContinue' and method 'continueToNextScreen'");
    target.btnContinue = Utils.castView(view, R.id.btn_continue, "field 'btnContinue'", Button.class);
    view7f08005e = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.continueToNextScreen();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_connect, "method 'connectDevice'");
    view7f08005d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.connectDevice();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ConnectDeviceActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnContinue = null;

    view7f08005e.setOnClickListener(null);
    view7f08005e = null;
    view7f08005d.setOnClickListener(null);
    view7f08005d = null;
  }
}
