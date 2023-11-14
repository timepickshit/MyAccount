package com.asuer.accounttime.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.asuer.accounttime.greendao.Dao.DaoMaster;

import org.greenrobot.greendao.database.Database;

public class accountDBopenHelper extends DaoMaster.DevOpenHelper {
    public accountDBopenHelper(Context context, String name) {
        super(context, name);
    }

    public accountDBopenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
    }



}
