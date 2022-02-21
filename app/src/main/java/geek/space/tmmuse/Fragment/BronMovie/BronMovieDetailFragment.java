package geek.space.tmmuse.Fragment.BronMovie;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import geek.space.tmmuse.R;

public class BronMovieDetailFragment extends Fragment {
    private Context context;
    private View view;

    public BronMovieDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_bron_movie_detail, container, false);
        context = getContext();

        return view;
    }
}