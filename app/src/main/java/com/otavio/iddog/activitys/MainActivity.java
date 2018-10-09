package com.otavio.iddog.activitys;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.otavio.iddog.R;
import com.otavio.iddog.fragments.CategoriaHoundFragment;
import com.otavio.iddog.fragments.CategoriaHuskyFragment;
import com.otavio.iddog.fragments.CategoriaLabradorFragment;
import com.otavio.iddog.fragments.CategoriaPugFragment;
import com.otavio.iddog.adapters.SectionsPageAdapter;

public class MainActivity extends AppCompatActivity {


    private SectionsPageAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


    }

    private void setupViewPager(ViewPager viewPager) {

        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new CategoriaHuskyFragment(), getString(R.string.tabHusk));
        adapter.addFragment(new CategoriaLabradorFragment(),getString(R.string.tabLabrador));
        adapter.addFragment(new CategoriaHoundFragment(),getString(R.string.tabHound));
        adapter.addFragment(new CategoriaPugFragment(),getString(R.string.tabPug));


        viewPager.setAdapter(adapter);
    }


}
