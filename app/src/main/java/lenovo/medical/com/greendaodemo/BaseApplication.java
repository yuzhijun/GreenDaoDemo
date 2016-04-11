package lenovo.medical.com.greendaodemo;

import android.app.Application;
import android.content.Context;

import com.medical.dao.DaoMaster;
import com.medical.dao.DaoSession;

import lenovo.medical.com.greendaodemo.database.THDevOpenHelper;

/**
 * 官方推荐将取得DaoMaster对象的方法放到Application层这样避免多次创建生成Session对象
 * Created by yuzhijun on 2016/4/11.
 */
public class BaseApplication extends Application {

    private static BaseApplication mInstance;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        if(mInstance == null)
            mInstance = this;
    }

    /**
     * 取得DaoMaster
     *
     * @param context        上下文
     * @return               DaoMaster
     */
    public static DaoMaster getDaoMaster(Context context) {
        if (daoMaster == null) {
            DaoMaster.OpenHelper helper = new THDevOpenHelper(context,"myDb",null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }

    /**
     * 取得DaoSession
     *
     * @param context        上下文
     * @return               DaoSession
     */
    public static DaoSession getDaoSession(Context context) {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster(context);
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }
}
