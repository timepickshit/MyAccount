package com.asuer.accounttime.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;


@Entity
public class Account {

    @Id(autoincrement = true)
    private long id;

    private int pay_type;

    private int pay_source;

    private float pay_money;

    private Date pay_time;

    private String Pay_notes;

    @Generated(hash = 644874326)
    public Account(long id, int pay_type, int pay_source, float pay_money,
            Date pay_time, String Pay_notes) {
        this.id = id;
        this.pay_type = pay_type;
        this.pay_source = pay_source;
        this.pay_money = pay_money;
        this.pay_time = pay_time;
        this.Pay_notes = Pay_notes;
    }

    @Generated(hash = 882125521)
    public Account() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPay_type() {
        return this.pay_type;
    }

    public void setPay_type(int pay_type) {
        this.pay_type = pay_type;
    }

    public String getPay_type_str() {
        String str = new String();
        switch (this.pay_type) {
            case 1:
                //银行卡
                str = "银行卡";
                break;

            case 2:
                //支付宝
                str = "支付宝";
                break;

            case 3:
                //微信
                str = "微信";
                break;

            case 4:
                //信用卡
                str = "信用卡";
                break;

            case 5:
                //其他
                str = "其他";
                break;
        }
        return str;
    }

    public int getPay_source() {
        return this.pay_source;
    }

    public void setPay_source(int pay_source) {
        this.pay_source = pay_source;
    }

    public String getPay_source_str() {
        String str = new String();
        switch (getPay_source()) {
            case 1:
                str = "日常饮食";
                break;

            case 2:
                str = "娱乐活动";
                break;

            case 3:
                str = "房租水电";
                break;

            case 4:
                str = "其他项目";
                break;
        }
        return str;
    }

    public float getPay_money() {
        return this.pay_money;
    }

    public void setPay_money(float pay_money) {
        this.pay_money = pay_money;
    }

    public Date getPay_time() {
        return this.pay_time;
    }

    public void setPay_time(Date pay_time) {
        this.pay_time = pay_time;
    }

    public String getPay_time_str() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = getPay_time();
        return dateFormat.format(date);
    }

    public String getPay_notes() {
        return this.Pay_notes;
    }

    public void setPay_notes(String Pay_notes) {
        this.Pay_notes = Pay_notes;
    }


    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", pay_type=" + pay_type +
                ", pay_source=" + pay_source +
                ", pay_money=" + pay_money +
                ", pay_time=" + pay_time +
                ", Pay_notes='" + Pay_notes + '\'' +
                '}';
    }
}
