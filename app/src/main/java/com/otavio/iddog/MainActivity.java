package com.otavio.iddog;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.otavio.iddog.Fragments.Categoria_hound_Fragmente;
import com.otavio.iddog.Fragments.Categoria_husky_Fragment;
import com.otavio.iddog.Fragments.Categoria_labrador_Fragment;
import com.otavio.iddog.Fragments.Categoria_pug_Fragment;

public class MainActivity extends AppCompatActivity {


    private SectionsPageAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


    }

    private void setupViewPager(ViewPager viewPager) {

        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Categoria_husky_Fragment(), "Husky");
        adapter.addFragment(new Categoria_labrador_Fragment(),"Labrador");
        adapter.addFragment(new Categoria_hound_Fragmente(),"Hound");
        adapter.addFragment(new Categoria_pug_Fragment(),"Pug");


        viewPager.setAdapter(adapter);
    }


}
