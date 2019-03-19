package com.benq.demofashion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.functions.database.greenDao.db.DaoSession;
import com.benq.demofashion.Utils.RandomValue;
import com.benq.demofashion.model.Student;

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
                        insertData();
                    }
                }).start();
                break;
            case R.id.delete:
                deleteAll();
                break;
            case R.id.query:
                StringBuilder sb = new StringBuilder();
                List<Student> students = queryAll();
                for (Student item : students) {
                    sb.append(item.toString());
                }
                mTvDisplay.setText(sb);
                break;
            case R.id.test:
                break;
        }
    }
    public void insertData() {
        DaoSession daoSession = ((MyApplication) getApplication()).getDaoSession();
        for (int i = 0; i < 10; i++) {
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

    public void queryData(String s) {
        DaoSession daoSession = ((MyApplication) getApplication()).getDaoSession();
        List<Student> students = daoSession.queryRaw(Student.class, " where id = ?", s);

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
}
