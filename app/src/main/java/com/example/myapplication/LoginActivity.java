package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private DBOpenHelper mDBOpenHelper;
    EditText login_name;
    EditText login_password;
    ImageView login_login;
    TextView login_register;
    ImageView login_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mDBOpenHelper = new DBOpenHelper(this);
        initView();
        login_login.setOnClickListener(this);
        login_exit.setOnClickListener(this);
        login_register.setOnClickListener(this);
    }

    public void initView(){
        login_login = findViewById(R.id.iv_send_login);
        login_exit = findViewById(R.id.iv_close_login);
        login_name = findViewById(R.id.login_name);
        login_password = findViewById(R.id.login_password);
        login_register = findViewById(R.id.login_register);
    }


    /**
     * 都声明并实例化之后
     * 需要为实例化的这些对象设置行为
     * 在写 activity_login.xml 的时候
     * 有些控件 Button、ImageView、TextView 都有一个属性：
     * android:onClick="onClick"
     * 其实这也是开源库ButterKnife中的东西
     * 人家不仅解决了findViewById代码过长的问题，
     * 还解决了setOnClickListener(new View.OnClickListener() {}代码过长的问题
     * 让你不再写findViewById、setOnClickListener
     * 所以人家叫 ButterKnife 黄油刀
     * 就是专门管切割长代码的
     * Talk is cheap, show me your code.
     */


    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.iv_close_login:
                finish();
                break;
            case R.id.login_register:
                startActivity(new Intent(this, RegisterActivity.class));
                finish();
                break;

            /**
             * 登录验证：
             *
             * 从EditText的对象上获取文本编辑框输入的数据，并把左右两边的空格去掉
             *  String name = mEtLoginactivityUsername.getText().toString().trim();
             *  String password = mEtLoginactivityPassword.getText().toString().trim();
             *  进行匹配验证,先判断一下用户名密码是否为空，
             *  if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password))
             *  再进而for循环判断是否与数据库中的数据相匹配
             *  if (name.equals(user.getName()) && password.equals(user.getPassword()))
             *  一旦匹配，立即将match = true；break；
             *  否则 一直匹配到结束 match = false；
             *
             *  登录成功之后，进行页面跳转：
             *
             *  Intent intent = new Intent(this, MainActivity.class);
             *  startActivity(intent);
             *  finish();//销毁此Activity
             */
            case R.id.iv_send_login:
                String name = login_name.getText().toString().trim();
                String password = login_password.getText().toString().trim();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)) {
                    ArrayList<User> data = mDBOpenHelper.getAllData();
                    boolean match = false;
                    for(int i=0;i<data.size();i++) {
                        User user = data.get(i);
                        if (name.equals(user.getName()) && password.equals(user.getPassword())){
                            match = true;
                            break;
                        }else{
                            match = false;
                        }
                    }
                    if (match) {
                        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                        finish();//销毁此Activity
                    }else {
                        Toast.makeText(this, "用户名或密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "请输入你的用户名或密码", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }
}



