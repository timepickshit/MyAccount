package com.asuer.accounttime.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.asuer.accounttime.greendao.Dao.AccountDao;
import com.asuer.accounttime.greendao.Dao.DaoMaster;
import com.asuer.accounttime.greendao.Dao.DaoSession;

import java.util.List;

public class accountManager {

    private volatile static accountManager mInstance = null;
    private Context mContext;
    private static accountStroge mAccountStroge;
    private static DaoMaster.DevOpenHelper mAccountHelper;
    private static SQLiteDatabase mAccount_db;
    private static DaoMaster mAccountDaoMaster;
    private static DaoSession mAccountSession;
    private static AccountDao mAccountDao;

    private accountManager () {}

    //设置单例
    public static synchronized accountManager getmInstance(Context context) {
        if (mInstance == null) {
            synchronized (accountManager.class) {
                if (mInstance == null) {
                    mInstance = new accountManager();
                    CreatDb(context);
                }
            }
        }
        return mInstance;
    }

    private static void CreatDb(Context context) {
        // 生成数据库文件，名为 account_db
        mAccountHelper = new accountDBopenHelper(context, "account.db", null);
        mAccount_db = mAccountHelper.getWritableDatabase();
        // 建立特定模式下的所有的 DAO 对象和数据 db 对象的映射
        mAccountDaoMaster = new DaoMaster(mAccount_db);
        // 管理特定模式下的所有 DAO 对象，并提供一些通用的 CRUD 持久化方法
        mAccountSession = mAccountDaoMaster.newSession();
        // 得到指定的 StudentDao 对象
        mAccountDao = mAccountSession.getAccountDao();

        mAccountStroge = new accountStroge(mAccountDao);
    }

    public Account getAccountById (long id) {
        return mAccountStroge.getAccountById(id);
    }

    public void removeAccount(long id) {
        mAccountStroge.removeAccount(id);
    }

    public void addAccount (Account account) {
        mAccountStroge.addAccount(account);
    }

    public void deleteAll() {
        mAccountStroge.deleteAll();
    }

    public List<Account> loadAll() {
        List<Account> mAccount = mAccountStroge.loadAll();
        return mAccount;
    }

    public List<Account> SelectAccountByType (int Type) {
        List<Account> mAccount = mAccountStroge.SelectAccountByType(Type);
        return mAccount;
    }

    public List<Account> SelectAccountBySource (int Source) {
        List<Account> mAccount = mAccountStroge.SelectAccountBySource(Source);
        return mAccount;
    }




}
