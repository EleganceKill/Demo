package com.benq.demofashion;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.functions.database.greenDao.db.DaoSession;
import com.aserbao.aserbaosandroid.functions.database.greenDao.db.StudentDao;
import com.benq.demofashion.Utils.RandomValue;
import com.benq.demofashion.model.CreditCard;
import com.benq.demofashion.model.Student;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Random mRandom = new Random();

    private TextView mTvDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpViews();
    }

    private void setUpViews() {
        findViewById(R.id.insert).setOnClickListener(this);
        findViewById(R.id.delete).setOnClickListener(this);
        findViewById(R.id.query).setOnClickListener(this);
        findViewById(R.id.test).setOnClickListener(this);
        mTvDisplay = findViewById(R.id.display);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.insert:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // insertData();
                        addStudent();
                    }
                }).start();
                break;
            case R.id.delete:
                deleteAll();
                break;
            case R.id.query:
                StringBuilder sb = new StringBuilder();
                // List<Student> students = queryAll();
                // List<Student> students = queryData("男");
                List<Student> students = queryWithQueryBuilder("男");
                for (Student item : students) {
                    sb.append(item.toString());
                    List<CreditCard> creditCards = item.getCreditCardsList();
                    for (CreditCard card : creditCards) {
                        Log.i(TAG, "item  credit list is" + card);
                    }
                }
                // mTvDisplay.setText(sb);
                Log.i(TAG, "query result is " + sb);
                break;
            case R.id.test:
                // achieveAPPList();
                /*Intent intent = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
                sendBroadcast(intent);*/
                /*Intent intent = new Intent();
                intent.setAction(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);*/
                break;
        }
    }


    private void achieveAPPList() {
        List<PackageInfo> packageInfoList = getPackageManager().getInstalledPackages(0); //返回已安装的包信息列表
        for (PackageInfo packageInfo : packageInfoList) {
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 1) {
                Log.d(TAG, "system: " + packageInfo.packageName + ", versionName: " + packageInfo.versionName + ", versionCode: " + packageInfo.versionCode);
            } else {
                Log.d(TAG, "normal: " + packageInfo.packageName + ", versionName: " + packageInfo.versionName + ", versionCode: " + packageInfo.versionCode);
            }
        }
    }

    public void insertData() {
        DaoSession daoSession = ((MyApplication) getApplication()).getDaoSession();
        for (int i = 0; i < 100; i++) {
            Student student = new Student();
            student.setStudentNo(i);
            int age = mRandom.nextInt(10) + 10;
            student.setAge(age);
            student.setTelPhone(RandomValue.getTel());
            String chineseName = RandomValue.getChineseName();
            student.setName(chineseName);
            if (i % 2 == 0) {
                student.setSex("男");
            } else {
                student.setSex("女");
            }
            student.setAddress(RandomValue.getRoad());
            student.setGrade(String.valueOf(age % 10) + "年纪");
            student.setSchoolName(RandomValue.getSchoolName());
            Log.d(TAG, "student : " + student.toString());
            daoSession.insert(student);
        }
    }

    public List<Student> queryData(String s) {
        DaoSession daoSession = ((MyApplication) getApplication()).getDaoSession();
        List<Student> students = daoSession.queryRaw(Student.class, " where sex = ?", s);
        return students;
    }

    public List<Student> queryWithQueryBuilder(String s) {
        DaoSession daoSession = ((MyApplication) getApplication()).getDaoSession();
        QueryBuilder<Student> qb = daoSession.queryBuilder(Student.class)
                .where(StudentDao.Properties.Sex.eq(s)).orderAsc(StudentDao.Properties.Name);
        List<Student> students = qb.list();
        return students;
    }

    public List queryAll() {
        DaoSession daoSession = ((MyApplication) getApplication()).getDaoSession();
        List<Student> students = daoSession.loadAll(Student.class);
        return students;
    }

    public void deleteAll() {
        DaoSession daoSession = ((MyApplication) getApplication()).getDaoSession();
        daoSession.deleteAll(Student.class);
    }

    public void updataData(Student s) {
        DaoSession daoSession = ((MyApplication) getApplication()).getDaoSession();
        daoSession.update(s);
    }

    public void addStudent() {
        for (int i = 0; i < 20; i++) {
            DaoSession daoSession = ((MyApplication) getApplication()).getDaoSession();
            Student student = new Student();
            student.setStudentNo(i);
            int age = mRandom.nextInt(10) + 10;
            student.setAge(age);
            student.setTelPhone(RandomValue.getTel());
            String chineseName = RandomValue.getChineseName();
            student.setName(chineseName);
            if (i % 2 == 0) {
                student.setSex("男");
            } else {
                student.setSex("女");
            }
            student.setAddress(RandomValue.getRoad());
            student.setGrade(String.valueOf(age % 10) + "年纪");
            student.setSchoolName(RandomValue.getSchoolName());
            daoSession.insert(student);

            //插入对应的CreditCard数据
            for (int j = 0; j < mRandom.nextInt(5) + 1; j++) {
                CreditCard creditCard = new CreditCard();
                creditCard.setUserId(student.id);
                creditCard.setUserName(student.name);
                creditCard.setCardNum(String.valueOf(mRandom.nextInt(899999999) + 100000000) + String.valueOf(mRandom.nextInt(899999999) + 100000000));
                creditCard.setWhichBank(RandomValue.getBankName());
                creditCard.setCardType(mRandom.nextInt(10));
                daoSession.insert(creditCard);
            }
        }
    }
}
