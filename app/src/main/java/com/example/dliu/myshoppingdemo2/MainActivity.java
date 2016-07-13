package com.example.dliu.myshoppingdemo2;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    /*

    默认显示第一个fragment
    点击不同的按钮，主界面显示不同的fragment
     */

    int oldOperFlag;//前一次操作的是哪个按钮
    int currentOperFlag;//现在操作的是哪个按钮

    List<Fragment> fragmentList=new ArrayList<Fragment>();
    List<Button> buttonList=new ArrayList<Button>();

    Button btnPersonCenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPersonCenter= (Button) findViewById(R.id.btn_person_center);
        //获取个人中心的图片
       Drawable drawable= getResources().getDrawable(R.drawable.person_center_bg);
        drawable.setBounds(0,0,30,30);
        btnPersonCenter.setCompoundDrawables(null,drawable,null,null);

        //集合初始化
        fragmentList.add(new MainPageFragment());
        fragmentList.add(new ShopingCartFragment());
        fragmentList.add(new PersonCenterFragment());

        //按钮集合
        buttonList.add((Button)findViewById(R.id.btn_main_page));
        buttonList.add((Button)findViewById(R.id.btn_shoping_cart));
        buttonList.add((Button)findViewById(R.id.btn_person_center));




        //默认显示第一个fragment
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,fragmentList.get(0))
                .commit();

        Button btnPage= (Button) findViewById(R.id.btn_main_page);
        //默认第一个按钮被选中
        btnPage.setSelected(true);


    }


    //按钮点击事件：点击按钮，对应的fragmetn显示，其余的fragmetn隐藏
    /*
    1->2
    2->3
    1->3

    1->2->3

    记录用户前一次点击的按钮时哪个；
    按钮1-》按钮2-》按钮3-》按钮6
    currentoperFlag：0-》1-》2-》5-》*
    oldoperFlag:     0->0-》1-》2->5

     */


    public void onTabClicked(View v){
        switch (v.getId()){
            case R.id.btn_main_page:
                currentOperFlag=0;//第一个按钮
                break;
            case R.id.btn_shoping_cart:
                currentOperFlag=1;

                break;
            case R.id.btn_person_center:
                currentOperFlag=2;
                break;
        }

        //实现旧的隐藏，新的显示
        switchFragment(fragmentList.get(oldOperFlag),fragmentList.get(currentOperFlag));

        buttonList.get(oldOperFlag).setSelected(false);//上一次点击的按钮，取消select

        buttonList.get(currentOperFlag).setSelected(true);//当前点击的按钮，设置select

        Log.i("MainActivity","oldOperFlag:"+oldOperFlag+",currentOperFlag:"+currentOperFlag);




        //当前的操作，变成上一次的操作
        oldOperFlag=currentOperFlag;


    }

    //隐藏当前显示的fragment,新的fragment:1)没有添加过，添加；2）添加过，显示
    public void switchFragment(Fragment oldFragment, Fragment newFragment){

        //判断是否是同一个fragment
        if(oldFragment!=newFragment) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            //隐藏oldfragment
            fragmentTransaction.hide(oldFragment);

            if (!newFragment.isAdded()) {
                //没有添加过，添加
                fragmentTransaction.add(R.id.fragment_container, newFragment).commit();

            } else {
                fragmentTransaction.show(newFragment).commit();
            }
        }

    }

}
