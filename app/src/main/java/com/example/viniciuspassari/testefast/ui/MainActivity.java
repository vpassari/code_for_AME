package com.example.viniciuspassari.testefast.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toolbar;

import com.example.viniciuspassari.testefast.R;
import com.example.viniciuspassari.testefast.Utils.Fragment;
import com.example.viniciuspassari.testefast.ui.fragment.GenresFragment;

public class MainActivity extends BaseActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setup();
    }

    private void buildGenresListFragment(){
        GenresFragment fragment = GenresFragment.newInstance(null);

        Fragment.replaceFragment(getSupportFragmentManager(), fragment, GenresFragment.TAG);
    }

    @Override
    void setup() {
        toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle(getString(R.string.app_name));

        buildGenresListFragment();
    }
}