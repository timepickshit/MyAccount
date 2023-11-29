package com.asuer.accounttime.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.WorkerThread;

import com.asuer.accounttime.greendao.Dao.DaoMaster;
import com.asuer.accounttime.greendao.Dao.DaoSession;
import com.asuer.accounttime.greendao.Dao.AccountDao;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class accountStroge {

    private AccountDao mAccountDao; // 获取 dao 对象
    QueryBuilder<Account> queryBuilder;


    accountStroge(AccountDao mAccountDao) {
        this.mAccountDao = mAccountDao;
        queryBuilder = mAccountDao.queryBuilder();
    }

    public Account getAccountById (long id) {
        if (!isDataBaseValid()){
            return null;
        }
        return queryBuilder.where(AccountDao.Properties.Id.eq(id)).unique();
    }

    /**
     * 根据帐目id删除对应的记录
     */
    public void removeAccount(long id) {
        if (!isDataBaseValid()) {
            return;
        }
        try {
            QueryBuilder<Account> qb = mAccountDao.queryBuilder();
            DeleteQuery<Account> bd = qb.where(AccountDao.Properties.Id.eq(id))
                    .buildDelete();
            bd.executeDeleteWithoutDetachingEntities();
        } catch (Exception e) {
            Log.e("TAG", e.toString());
        }
    }

    /**
     * 新增记录，如果存在则更新，不存在直接插入
     */
    @WorkerThread
    public synchronized void addAccount(Account account) {
        if (!isDataBaseValid()) {
            return;
        }

        try {
            Account maccount = new Account();
            maccount.setId(account.getId());
            maccount.setPay_type(account.getPay_type());
            maccount.setPay_source(account.getPay_source());
            maccount.setPay_money(account.getPay_money());
            maccount.setPay_time(account.getPay_time());
            maccount.setPay_notes(account.getPay_notes());
            /**
             * 解决 Exception：
             * Cannot update entity without key - was it inserted before?
             */
            Account oldaccount = getAccountById(account.getId());
            if (oldaccount != null) {
                account.setId(oldaccount.getId());
            }
            // mUserDao.save(user);
            if (getAccountById(account.getId()) == null) {
                mAccountDao.insert(account);
                Log.e("TAG", "插入成功");
            } else {
                mAccountDao.update(account);
                Log.e("TAG", "更新成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("TAG", "插入失败");
        }
    }

    /**
     * 删除所有数据信息
     */
    @WorkerThread
    public synchronized void deleteAll() {
        mAccountDao.deleteAll();
    }

    /**
     * 获取所有消费信息
     * @return
     */
    @WorkerThread
    public synchronized List<Account>  loadAll() {
        List<Account> AccountList = mAccountDao.loadAll();
        return AccountList;
    }


    /**
     * 获取此类型所有数据信息
     * @param Type 类型号
     * @return 此类型所有数据
     */
    public List<Account> SelectAccountByType(int Type){
        List<Account> AccountList = mAccountDao.queryBuilder()
                .where(AccountDao.Properties.Pay_type.eq(Type)).list();
        return AccountList;
    }

    /**
     * 获取此消费来源所有数据信息
     * @param Source 来源编号
     * @return 此类型所有数据
     */
    public List<Account> SelectAccountBySource(int Source){
        List<Account> AccountList = mAccountDao.queryBuilder()
                .where(AccountDao.Properties.Pay_source.eq(Source)).list();
        return AccountList;
    }



    public Account GetLastAccount () {
        queryBuilder.orderDesc(AccountDao.Properties.Id);
        queryBuilder.limit(1);
        Account account = queryBuilder.unique();
        return account;
    }





    private boolean isDataBaseValid() {
        return mAccountDao != null;
    }

}
