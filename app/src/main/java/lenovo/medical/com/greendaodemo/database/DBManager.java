package lenovo.medical.com.greendaodemo.database;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.medical.dao.DaoSession;
import com.medical.dao.UsersDao;
import com.medical.dao.infoTypeDao;
import com.medical.dao.infosDao;
import com.medical.greendao.Users;
import com.medical.greendao.infoType;
import com.medical.greendao.infos;

import java.util.List;

import lenovo.medical.com.greendaodemo.BaseApplication;

/**
 * Created by yuzhijun on 2016/4/11.
 */
public class DBManager {
    private static final String TAG = DBManager.class.getSimpleName();
    private static DBManager dbManager;
    private static Context appContext;
    private static DaoSession mDaoSession;
    private static UsersDao userDao;
    private infosDao infoDao;
    private infoTypeDao typeDao;

    private DBManager() {

    }

    /**
     * 采用单例模式
     * @param context     上下文
     * @return            dbservice
     */
    public static DBManager getInstance(Context context) {
        if (dbManager == null) {
            dbManager = new DBManager();
            if (appContext == null){
                appContext = context.getApplicationContext();
            }
            dbManager.mDaoSession = BaseApplication.getDaoSession(context);
            dbManager.userDao = dbManager.mDaoSession.getUsersDao();
        }
        return dbManager;
    }

    /**
     * 根据用户id,取出用户信息
     * @param id           用户id
     * @return             用户信息
     */
    public Users loadNote(long id) {
        if(!TextUtils.isEmpty(id + "")) {
            return userDao.load(id);
        }
        return  null;
    }

    /**
     * 取出所有数据
     * @return      所有数据信息
     */
    public List<Users> loadAllNote(){
        return userDao.loadAll();
    }

    /**
     * 生成按id倒排序的列表
     * @return      倒排数据
     */
    public List<Users> loadAllNoteByOrder()
    {
        return userDao.queryBuilder().orderDesc(UsersDao.Properties.Id).list();
    }

    /**
     * 根据查询条件,返回数据列表
     * @param where        条件
     * @param params       参数
     * @return             数据列表
     */
    public List<Users> queryNote(String where, String... params){
        return userDao.queryRaw(where, params);
    }

    /**
     * 根据用户信息,插件或修改信息
     * @param user              用户信息
     * @return 插件或修改的用户id
     */
    public long saveNote(Users user){
        return userDao.insertOrReplace(user);
    }

    /**
     * 批量插入或修改用户信息
     * @param list      用户信息列表
     */
    public void saveNoteLists(final List<Users> list){
        if(list == null || list.isEmpty()){
            return;
        }
        userDao.getSession().runInTx(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<list.size(); i++){
                    Users user = list.get(i);
                    userDao.insertOrReplace(user);
                }
            }
        });
    }

    /**
     * 删除所有数据
     */
    public void deleteAllNote(){
        userDao.deleteAll();
    }

    /**
     * 根据id,删除数据
     * @param id      用户id
     */
    public void deleteNote(long id){
        userDao.deleteByKey(id);
        Log.i(TAG, "delete");
    }

    /**
     * 根据用户类,删除信息
     * @param user    用户信息类
     */
    public void deleteNote(Users user){
        userDao.delete(user);
    }

    /**********************信息类别*********************************/

    /**
     * 添加或修改信息类别
     * @param iType     信息类别
     * @return          返回修改信息的id或是新增的信息id
     */
    public Long SaveInfoType(infoType iType)
    {
        return typeDao.insertOrReplace(iType);
    }

    /**
     * 根据类别id,删除信息类别
     * @param id      信息id
     */
    public void deleteInfoType(long id)
    {
        typeDao.load(id).delete();

    }

    /**
     * 按id倒排序,来显示所信息类别
     * @return      信息类别列表
     */
    public List<infoType> getAllInfoTypeList()
    {
        return  typeDao.queryBuilder().orderDesc(infoTypeDao.Properties.Id).list();
    }

    /**
     * 根据类别id,取出类别信息
     * @param id
     * @return
     */
    public infoType getInfoType(long id)
    {
        return typeDao.load(id);
    }


    /**********************信息列表*********************************/

    /**
     * 根据信息类别id,取出其类别下所有的信息
     * @param typeid         类别id
     * @return               信息列表
     */
    public List<infos> getInfosByTypeId(long typeid)
    {
        return typeDao.load(typeid).getInfoes();
    }

    /**
     * 添加或修改
     * @param info    信息
     * @return        添加或修改的id
     */
    public long saveInfo(infos info)
    {
        return infoDao.insertOrReplace(info);
    }

    /**
     * 返回所有新闻,用于测试类别删除的同步性
     * @return    返回所有新闻
     */
    public List<infos> getAllInfos()
    {
        return infoDao.loadAll();
    }

    /**
     * 分页显示信息
     * @param typeid          信息类别
     * @param pageNum         当前页数
     * @param pageSize        每页显示数
     * @return                信息列表
     */
    public List<infos> getInfosBypageSize(long typeid,int pageNum,int pageSize)
    {
        return infoDao.queryBuilder().where(infosDao.Properties.TypeId.eq(typeid)).offset(pageNum-1).limit(pageSize).list();
    }
}
