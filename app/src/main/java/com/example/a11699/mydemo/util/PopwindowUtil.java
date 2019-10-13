package com.example.a11699.mydemo.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.example.a11699.mydemo.R;

/**
 * 作者：余智强
 * 2019/9/25
 */
public class PopwindowUtil {
    //从底部弹起来
    /**
     *  context:表示是在哪个Activity启动
     *  layid：启动样式
     *  viewout：popwindow弹出的相对位置
     *  showType:popvindow跳出位置
     *
     **/
    private static  PopupWindow popupWindow;
    public static View showShop(final Activity context,int layid,View viewout,int showtype){
        View view = LayoutInflater.from(context).inflate(layid, null, false);
          popupWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        popupWindow.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        //进入退出的动画
        if(showtype==1){
            popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
        }
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //点击外部消失
        popupWindow.setOutsideTouchable(false);
        //设置可以点击
        popupWindow.setTouchable(true);
        //设置SelectPicPopupWindow弹出窗体的背景
        popupWindow.setBackgroundDrawable(dw);
        //注意  要是点击外部空白处弹框消息  那么必须给弹框设置一个背景色  不然是不起作用的
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 注：此处的R.id.main则是最外层布局View
        if(showtype==1){
            popupWindow.showAtLocation(viewout, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        }else if(showtype == 2){
            popupWindow.showAsDropDown(viewout);
        }

        setBackgroundAlpha(0.5f,context);//设置屏幕透明度
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackgroundAlpha(1f,context);//设置屏幕透明度
            }
        });
        return view;
    }
    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     *            屏幕透明度0.0-1.0 1表示完全不透明
     */
    public static void  setBackgroundAlpha(float bgAlpha,Activity activity) {
        WindowManager.LayoutParams lp =  activity.getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        activity.getWindow().setAttributes(lp);
    }
    public static void dissmiss(){
        if(popupWindow!=null){
            popupWindow.dismiss();
        }
    }
}
