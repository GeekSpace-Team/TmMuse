package geek.space.tmmuse.Fragment.BronMovie;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import geek.space.tmmuse.R;

public class BroneMovieFragment extends Fragment {

    private View view;
    private Context context;

    public BroneMovieFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_brone_movie, container, false);
        context = getContext();

        return view;
    }
}