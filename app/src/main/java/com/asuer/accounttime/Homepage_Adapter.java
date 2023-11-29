package com.asuer.accounttime;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.asuer.accounttime.greendao.Account;

import java.util.ArrayList;
import java.util.List;

public class Homepage_Adapter extends BaseAdapter {

    private Context mContext;

    private List<Account> mlist;

    private LayoutInflater mInflater;

    public Homepage_Adapter(Context context, List<Account> accountList){
        this.mContext = context;
        this.mlist = new ArrayList<>();
        this.mlist.addAll(accountList);
        Log.e("ATG", mlist.size() + "///");

        this.mInflater = LayoutInflater.from(mContext);
    }
    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1;
        view1 = mInflater.inflate(R.layout.listview_account_single, null);
        ImageView iv_icon = view1.findViewById(R.id.iv_icon);
        TextView tv_source = view1.findViewById(R.id.tv_source);
        TextView tv_time = view1.findViewById(R.id.tv_time);
        TextView tv_cost = view1.findViewById(R.id.tv_cost);

        tv_source.setText(mlist.get(i).getPay_source_str());
        tv_time.setText(mlist.get(i).getPay_time_str());
        tv_cost.setText(String.valueOf(mlist.get(i).getPay_money()));

        return view1;
    }
}
