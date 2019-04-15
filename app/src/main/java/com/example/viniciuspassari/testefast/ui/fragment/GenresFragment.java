package com.example.viniciuspassari.testefast.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.viniciuspassari.testefast.Data.Model.Genre;
import com.example.viniciuspassari.testefast.Presenter.GenresContract;
import com.example.viniciuspassari.testefast.Presenter.GenresPresenter;
import com.example.viniciuspassari.testefast.R;
import com.example.viniciuspassari.testefast.Utils.Common;
import com.example.viniciuspassari.testefast.Utils.FragmentUtil;
import com.example.viniciuspassari.testefast.ui.adapter.GenreAdapter;

import java.util.ArrayList;

public class GenresFragment extends BaseFragment implements GenresContract {

    public static final String TAG = GenresFragment.class.getName();

    private CoordinatorLayout coordinatorLayout;
    private TextView tvNoGenres;
    private GridView gridGenres;
    private GenreAdapter adapter;
    private ProgressDialog mProgress;
    private GenresContract.Presenter genresListPresenter;

    public static GenresFragment newInstance(Bundle bundle){
        GenresFragment fragment = new GenresFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public GenresFragment() {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_genres_list, container, false);

        setup(rootView);

        genresListPresenter.getGenresList();

        return rootView;
    }

    @Override
    public void setup(View rootView) {
        if(getActivity() != null) {
            coordinatorLayout = getActivity().findViewById(R.id.coordinator_main);
        }
        tvNoGenres = rootView.findViewById(R.id.tv_no_genres);
        gridGenres = rootView.findViewById(R.id.grid_genres);

        genresListPresenter = new GenresPresenter(getActivity(), this);
    }

    @Override
    public void showProgress() {
        hideProgress();
        mProgress = Common.showLoadingDialog(getActivity(), "");
    }

    @Override
    public void hideProgress() {
        if (mProgress != null && mProgress.isShowing()) {
            mProgress.cancel();
        }
    }

    @Override
    public void onSuccess(ArrayList<Genre> genres) {
        if(genres != null && genres.size() > 0) {
            adapter = new GenreAdapter(getActivity(), genres);

            gridGenres.setAdapter(adapter);

            gridGenres.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Genre genre = (Genre) adapterView.getItemAtPosition(position);

                    Bundle bundle = new Bundle();
                    bundle.putInt(Genre.BUNDLE_ID, genre.getId());

                    MoviesFragment fragment = MoviesFragment.newInstance(bundle);
                    FragmentUtil.replaceFragment(GenresFragment.this.getFragmentManager(), fragment, MoviesFragment.TAG);
                }
            });
        }
        else{
            tvNoGenres.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onError(String msg) {
        Common.showSnackbar(getActivity(), coordinatorLayout, msg, Snackbar.LENGTH_LONG);
    }
}