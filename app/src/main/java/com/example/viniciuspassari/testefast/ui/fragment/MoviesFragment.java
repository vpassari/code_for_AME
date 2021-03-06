package com.example.viniciuspassari.testefast.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.viniciuspassari.testefast.Data.Model.Genre;
import com.example.viniciuspassari.testefast.Data.Model.Movie;
import com.example.viniciuspassari.testefast.Presenter.MoviesContract;
import com.example.viniciuspassari.testefast.Presenter.MoviesPresenter;
import com.example.viniciuspassari.testefast.R;
import com.example.viniciuspassari.testefast.Utils.Common;
import com.example.viniciuspassari.testefast.Utils.FragmentUtil;
import com.example.viniciuspassari.testefast.ui.MainActivity;
import com.example.viniciuspassari.testefast.ui.adapter.MovieAdapter;

import java.util.ArrayList;

public class MoviesFragment extends BaseFragment implements MoviesContract {

    public static final String TAG = MoviesFragment.class.getName();

    private CoordinatorLayout coordinatorLayout;
    private ProgressDialog mProgress;
    private Bundle args;
    private int genreId;
    private TextView tvNoMovies;
    private GridView gridMovies;
    private MovieAdapter adapter;
    private MoviesContract.Presenter moviesListPresenter;

    public static MoviesFragment newInstance(Bundle args){
        MoviesFragment fragment = new MoviesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public MoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movies_list, container, false);

        setup(rootView);

        if(getActivity() != null && getActivity() instanceof MainActivity){
            ((MainActivity)getActivity()).showBackArrow();
        }

        if(args != null){
            genreId = args.getInt(Genre.BUNDLE_ID);

            if(genreId > 0) {
                moviesListPresenter.getMoviesList(genreId);
            }
        }

        return rootView;
    }

    @Override
    public void setup(View rootView) {
        args = getArguments();

        if(getActivity() != null){
            coordinatorLayout = getActivity().findViewById(R.id.coordinator_main);
            if(getActivity() instanceof MainActivity){
                ((MainActivity)getActivity()).showToolbar();
            }
        }

        tvNoMovies = rootView.findViewById(R.id.tv_no_movies);
        gridMovies = rootView.findViewById(R.id.grid_movies);

        moviesListPresenter = new MoviesPresenter(getActivity(), this);
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
    public void onSuccess(ArrayList<Movie> movies) {
        if(movies != null && movies.size() > 0){
            adapter = new MovieAdapter(getActivity(), movies);
            gridMovies.setAdapter(adapter);

            gridMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Movie movie = (Movie) adapterView.getItemAtPosition(position);

                    Bundle bundle = new Bundle();
                    bundle.putLong(Movie.BUNDLE_ID, movie.getId());

                    MoviesDetailFragment fragment = MoviesDetailFragment.newInstance(bundle);
                    FragmentUtil.replaceFragment(MoviesFragment.this.getFragmentManager(), fragment, MoviesDetailFragment.TAG);
                }
            });
        }
        else{
            tvNoMovies.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onError(String msg) {
        Common.showSnackbar(getActivity(), coordinatorLayout, msg, Snackbar.LENGTH_LONG);
    }
}