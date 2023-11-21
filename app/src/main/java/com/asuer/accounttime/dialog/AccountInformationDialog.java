package com.asuer.accounttime.dialog;



import android.app.AlertDialog;
import android.content.Context;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.asuer.accounttime.R;
import com.asuer.accounttime.greendao.Account;
import com.asuer.accounttime.greendao.accountManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AccountInformationDialog {

    private Context mContext;

    private Account mAccount;

    private accountManager mAccountManager;

    public AccountInformationDialog(Context context) {
        this.mContext = context;
        mAccount = new Account();

        mAccountManager = accountManager.getmInstance(mContext);
    }

    public void ShowAddAccountDialog() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext,R.style.add_account_dialog_style);
        View mView = View.inflate(mContext, R.layout.dialog_add_account, null);

        final EditText et_paytype = mView.findViewById(R.id.et_paytype);
//        et_paytype.setInputType(InputType.TYPE_NULL);
        final EditText et_paysource = mView.findViewById(R.id.et_paysource);
//        et_paysource.setInputType(InputType.TYPE_NULL);
        final EditText et_paymoney = mView.findViewById(R.id.et_paymoney);

        final EditText et_paytime = mView.findViewById(R.id.et_paytime);
        et_paytime.setInputType(InputType.TYPE_NULL);
        final EditText et_pay_notes = mView.findViewById(R.id.et_pay_notes);

        final TextView tv_canel_add = mView.findViewById(R.id.tv_canel_add);
        final TextView tv_confirm_add = mView.findViewById(R.id.tv_confirm_add);

        alertDialog.setView(mView)
                .create();

        final AlertDialog addAlerdialog = alertDialog.show();

        tv_canel_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAlerdialog.dismiss();
            }
        });

        tv_confirm_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!et_paytype.getText().toString().isEmpty() && !et_paysource.getText().toString().isEmpty()
                        && !et_paymoney.getText().toString().isEmpty() && !et_paytime.getText().toString().isEmpty()) {
                    Toast.makeText(mContext, "确定添加", Toast.LENGTH_SHORT).show();
                    float money = Float.parseFloat(et_paymoney.getText().toString());
                    mAccount.setPay_money(money);

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    try {
                        Date date = dateFormat.parse(et_paytime.getText().toString());
                        mAccount.setPay_time(date);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }

                    String notes = et_pay_notes.getText().toString();
                    mAccount.setPay_notes(notes);

                    Log.e("TAG", mAccount.toString());

                    mAccountManager.addAccount(mAccount);

                    addAlerdialog.dismiss();
                } else {
                    Toast.makeText(mContext, "消费信息不全", Toast.LENGTH_SHORT).show();
                }

            }
        });



        et_paytype.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    et_paytype.setInputType(InputType.TYPE_NULL); // 设置输入类型为空
                    et_paytype.onTouchEvent(motionEvent); // 处理触摸事件
                    Type_showPopupWindow(et_paytype);
                    return true; // 返回 true 消费触摸事件
                }
                return false; // 返回 false 表示未消费触摸事件
            }
        });



        et_paysource.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    et_paysource.setInputType(InputType.TYPE_NULL); // 设置输入类型为空
                    et_paysource.onTouchEvent(motionEvent); // 处理触摸事件
                    Source_showPopupWindow(et_paysource);
                    return true; // 返回 true 消费触摸事件
                }
                return false; // 返回 false 表示未消费触摸事件
            }
        });

        et_paytime.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    et_paytime.setInputType(InputType.TYPE_NULL); // 设置输入类型为空
                    et_paytime.onTouchEvent(motionEvent); // 处理触摸事件
                    showDateTimePickerPopup(et_paytime);
                    return true; // 返回 true 消费触摸事件
                }
                return false;
            }
        });




    }

    private void Source_showPopupWindow(EditText editText) {
        // 加载弹窗布局
        View popupView = LayoutInflater.from(mContext).inflate(R.layout.popup_account_source, null);

        final Button bt_source_yhk = popupView.findViewById(R.id.bt_source_yhk);
        final Button bt_source_zfb = popupView.findViewById(R.id.bt_source_zfb);
        final Button bt_source_wx = popupView.findViewById(R.id.bt_source_wx);
        final Button bt_source_xyk = popupView.findViewById(R.id.bt_source_xyk);
        final Button bt_source_qt = popupView.findViewById(R.id.bt_source_qt);
        // 创建PopupWindow并设置属性
        final PopupWindow popupWindow = new PopupWindow(popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(editText); // 显示在EditText下方

        bt_source_yhk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText("");
                editText.setText("银行卡");
                mAccount.setPay_source(1);
                popupWindow.dismiss();

            }
        });

        bt_source_zfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText("");
                editText.setText("支付宝");
                mAccount.setPay_source(2);
                popupWindow.dismiss();
            }
        });

        bt_source_wx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText("");
                editText.setText("微信");
                mAccount.setPay_source(3);
                popupWindow.dismiss();
            }
        });

        bt_source_xyk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText("");
                editText.setText("信用卡");
                mAccount.setPay_source(4);
                popupWindow.dismiss();
            }
        });

        bt_source_qt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText("");
                editText.setText("其他");
                mAccount.setPay_source(5);
                popupWindow.dismiss();
            }
        });
    }



    private void Type_showPopupWindow(EditText editText) {
        // 加载弹窗布局
        View popupView = LayoutInflater.from(mContext).inflate(R.layout.popup_account_type, null);

        final Button bt_ys = popupView.findViewById(R.id.bt_ys);
        final Button bt_fzsd = popupView.findViewById(R.id.bt_fzsd);
        final Button bt_qt = popupView.findViewById(R.id.bt_qt);
        final Button bt_yl = popupView.findViewById(R.id.bt_yl);
        // 创建PopupWindow并设置属性
        final PopupWindow popupWindow = new PopupWindow(popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(editText); // 显示在EditText下方

        bt_ys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText("");
                editText.setText("日常饮食");
                mAccount.setPay_type(1);
                popupWindow.dismiss();
            }
        });

        bt_yl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText("");
                editText.setText("娱乐活动");
                mAccount.setPay_type(2);
                popupWindow.dismiss();
            }
        });

        bt_fzsd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText("");
                editText.setText("房租水电");
                mAccount.setPay_type(3);
                popupWindow.dismiss();
            }
        });

        bt_qt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText("");
                editText.setText("其他项目");
                mAccount.setPay_type(4);
                popupWindow.dismiss();
            }
        });

    }

    private NumberPicker yearPicker;
    private NumberPicker monthPicker;
    private NumberPicker dayPicker;
    private NumberPicker hourPicker;
    private NumberPicker minutePicker;

    private void showDateTimePickerPopup(EditText editText) {
        // 创建布局填充器
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_account_time, null);

        // 创建 PopupWindow
        PopupWindow popupWindow = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true
        );
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(editText); // 显示在EditText下方

        // 获取当前年份和月份
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1; // 月份从 0 开始
        int crrentDay = calendar.get(Calendar.DAY_OF_MONTH);
        int Day;
        //判断是否闰年
        if (currentYear % 4 == 0 && currentYear % 100 != 0) {
            Day = 31;
        } else if (currentYear % 400 == 0) {
            Day = 31;
        } else {
            Day = 30;
        }

//        Log.e("TAG", "///" + currentYear + "年" + currentMonth + "月" + crrentDay + "日" + "///   一个月有" + Day + "天");

        // 初始化天选择器
        yearPicker = popupView.findViewById(R.id.yearpicker);
        yearPicker.setMaxValue(currentYear + 10);
        yearPicker.setMinValue(currentYear - 10);

        // 初始化天选择器
        monthPicker = popupView.findViewById(R.id.monthpicker);
        monthPicker.setMaxValue(12);
        monthPicker.setMinValue(1);

        // 初始化天选择器
        dayPicker = popupView.findViewById(R.id.daypicker);
        dayPicker.setMaxValue(Day);
        dayPicker.setMinValue(1);

        yearPicker.setValue(currentYear);
        monthPicker.setValue(currentMonth);
        dayPicker.setValue(crrentDay);

        // 初始化小时选择器
        hourPicker = popupView.findViewById(R.id.hourpicker);
        hourPicker.setMinValue(0);
        hourPicker.setMaxValue(23);

        // 初始化分钟选择器
        minutePicker = popupView.findViewById(R.id.minutepicker);
        minutePicker.setMinValue(0);
        minutePicker.setMaxValue(59);

        // 设置确定按钮的点击事件
        Button btnDone = popupView.findViewById(R.id.btndone);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 处理选择的日期和时间，这里可以更新 EditText 或执行其他操作
                int selectedYear = yearPicker.getValue();
                int selectedMonth = monthPicker.getValue();
                int selectedDay = dayPicker.getValue();
                int selectedHour = hourPicker.getValue();
                int selectedMinute = minutePicker.getValue();



                String selectedDateTime = String.format("%04d-%02d-%02d %02d:%02d",
                        selectedYear, selectedMonth, selectedDay, selectedHour, selectedMinute);

                editText.setText(selectedDateTime);

                // 关闭 PopupWindow
                popupWindow.dismiss();
            }
        });

        // 设置取消按钮的点击事件
        Button btnCancel = popupView.findViewById(R.id.btncancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 关闭 PopupWindow
                popupWindow.dismiss();
            }
        });

        // 显示 PopupWindow
        popupWindow.showAtLocation(editText, Gravity.CENTER, 0, 0);
    }

}
