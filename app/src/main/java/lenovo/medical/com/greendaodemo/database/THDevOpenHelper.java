package lenovo.medical.com.greendaodemo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.medical.dao.AreasDao;
import com.medical.dao.DaoMaster;
import com.medical.dao.StudentsDao;
import com.medical.dao.TeachersDao;
import com.medical.dao.infoTypeDao;
import com.medical.dao.infosDao;
import com.medical.dao.stuAndteaDao;

/**
 * Created by yuzhijun on 2016/4/11.
 * 这是我封装的DaoMaster.OpenHelper，之所以要做一层封装是因为默认的DaoMaster.OpenHelper在碰到数据库升级的时候会删除旧的表来创建新的表，
 * 这样就会导致旧表的数据全部丢失了，所以一定要封装DaoMaster.OpenHelper来实现数据库升级
 */
public class THDevOpenHelper extends DaoMaster.OpenHelper {

    public THDevOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 5:
                //创建新表，注意createTable()是静态方法
                //创建新表，注意createTable()是静态方法
//                infosDao.createTable(db, true);
//                infoTypeDao.createTable(db, true);

//                AreasDao.createTable(db, true);
                StudentsDao.createTable(db, true);
                TeachersDao.createTable(db, true);
                stuAndteaDao.createTable(db, true);
                // 加入新字段
                // db.execSQL("ALTER TABLE 'moments' ADD 'audio_path' TEXT;");
                // TODO
                break;
        }
    }
}
