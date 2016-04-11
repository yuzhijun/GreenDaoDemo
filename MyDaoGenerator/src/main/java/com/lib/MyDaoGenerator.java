package com.lib;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

public class MyDaoGenerator {

    public static void main(String[] args) throws Exception{
        //初始化一下数据库
        //第一个参数是版本号,第二个参数是为你生成的bean类放的文件夹名
        Schema schema = new Schema(5, "com.medical.greendao");
        //设置一下生成的三个java文件的目录
        schema.setDefaultJavaPackageDao("com.medical.dao");

        //建立一个表
        Entity userBean = schema.addEntity("Users");
        //此行可有可无,就是对表进行重命名
        userBean.setTableName("Users");
        //此行被注,因为加上引行,下面的字段总会少一个.不知道为什么
        //userBean.addLongProperty("id").primaryKey().index().autoincrement();
        //建立自增的主键
        userBean.addIdProperty();
        userBean.addStringProperty("uSex");
        userBean.addStringProperty("uTelphone");
        userBean.addStringProperty("uAge");
        userBean.addStringProperty("uName");

        //上面是我们用于单表操作的时建立的表


        //下面是我们要建的两个新表，一个上信息类别，一个是信自，它们的关系是1:n.
        //对于信息类别表，没有什么好说的，和上面一样，直接建立一个表就完了，我们主要来看一下信息表中，如何设置外键
        Entity infoTypeBean = schema.addEntity("infoType");
        //此处是用来实现序列化的接口
        infoTypeBean.implementsSerializable();
        infoTypeBean.addIdProperty();
        infoTypeBean.addStringProperty("infoName");

        //信息表进行建立
        Entity infoBean = schema.addEntity("infos");
        infoBean.implementsSerializable();
        infoBean.addIdProperty();
        infoBean.addStringProperty("infoTitle");
        infoBean.addStringProperty("infoAuthor");
        infoBean.addStringProperty("infoContent");
        //这里我们为信息表，添加一个typeId外键，它就是infoType表的id
        Property typeId = infoBean.addLongProperty("typeId").getProperty();

        //这里是重点，我们为这两个表建立1:n的关系，并设置关联字段。
        infoBean.addToOne(infoTypeBean, typeId);
        ToMany addToMany = infoTypeBean.addToMany(infoBean,typeId);
        addToMany.setName("infoes");


        Entity areaBean = schema.addEntity("Areas");
        areaBean.implementsSerializable();
        areaBean.addIdProperty();
        areaBean.addStringProperty("areaName");
        Property parentId = areaBean.addLongProperty("parentId").getProperty();
        areaBean.addToOne(areaBean,parentId).setName("parent");
        areaBean.addToMany(areaBean,parentId).setName("children");
        Entity studentBean = schema.addEntity("Students");
        studentBean.implementsSerializable();
        studentBean.addIdProperty();
        studentBean.addStringProperty("stuName");
        studentBean.addStringProperty("stuSex");

        Entity teacherBean = schema.addEntity("Teachers");
        teacherBean.implementsSerializable();
        teacherBean.addIdProperty();
        teacherBean.addStringProperty("teaName");
        teacherBean.addStringProperty("teaSex");

        Entity stuAndteaBean = schema.addEntity("stuAndtea");
        Property studentId = stuAndteaBean.addLongProperty("studentId").getProperty();
        Property teacherId = stuAndteaBean.addLongProperty("teacherId").getProperty();

        //这里其实很简单，就是建立两个1：n的关系
        stuAndteaBean.addToOne(studentBean,studentId).setName("onestu");
        stuAndteaBean.addToOne(teacherBean,teacherId).setName("onetea");
        studentBean.addToMany(stuAndteaBean,studentId).setName("manystu");
        teacherBean.addToMany(stuAndteaBean,teacherId).setName("manytea");

        new DaoGenerator().generateAll(schema, args[0]);
    }
}
