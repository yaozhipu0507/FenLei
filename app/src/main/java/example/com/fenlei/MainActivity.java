package example.com.fenlei;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

 /*       *//**
     * 左侧Fragment
     *//*
    @BindView(R.id.fragment_left)
    Fragment fragmentLeft;
    *//**
     * 右侧Fragment
     *//*
    @BindView(R.id.fragment_right)
    Fragment fragmentRight;
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }
}
