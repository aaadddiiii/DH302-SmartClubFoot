// Generated code from Butter Knife. Do not modify!
package com.ant.smartclubfoot.activity;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ant.smartclubfoot.R;
import com.ant.smartclubfoot.graph.SmartFootGraph;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeActivity_ViewBinding implements Unbinder {
  private HomeActivity target;

  @UiThread
  public HomeActivity_ViewBinding(HomeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public HomeActivity_ViewBinding(HomeActivity target, View source) {
    this.target = target;

    target.errorTv = Utils.findRequiredViewAsType(source, R.id.tv_error, "field 'errorTv'", TextView.class);
    target.sfgDay1 = Utils.findRequiredViewAsType(source, R.id.sfg_day1, "field 'sfgDay1'", SmartFootGraph.class);
    target.sfgDay2 = Utils.findRequiredViewAsType(source, R.id.sfg_day2, "field 'sfgDay2'", SmartFootGraph.class);
    target.sfgDay3 = Utils.findRequiredViewAsType(source, R.id.sfg_day3, "field 'sfgDay3'", SmartFootGraph.class);
    target.sfgDay4 = Utils.findRequiredViewAsType(source, R.id.sfg_day4, "field 'sfgDay4'", SmartFootGraph.class);
    target.sfgDay5 = Utils.findRequiredViewAsType(source, R.id.sfg_day5, "field 'sfgDay5'", SmartFootGraph.class);
    target.sfgDay6 = Utils.findRequiredViewAsType(source, R.id.sfg_day6, "field 'sfgDay6'", SmartFootGraph.class);
    target.sfgDay7 = Utils.findRequiredViewAsType(source, R.id.sfg_day7, "field 'sfgDay7'", SmartFootGraph.class);
    target.totalTv = Utils.findRequiredViewAsType(source, R.id.tv_total, "field 'totalTv'", TextView.class);
    target.midPoint = Utils.findRequiredViewAsType(source, R.id.tv_midPoint, "field 'midPoint'", TextView.class);
    target.headerDate = Utils.findRequiredViewAsType(source, R.id.header_date, "field 'headerDate'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.errorTv = null;
    target.sfgDay1 = null;
    target.sfgDay2 = null;
    target.sfgDay3 = null;
    target.sfgDay4 = null;
    target.sfgDay5 = null;
    target.sfgDay6 = null;
    target.sfgDay7 = null;
    target.totalTv = null;
    target.midPoint = null;
    target.headerDate = null;
  }
}
