package com.conyargmail.kingdomdeathmonstercompanion.ui.hunt;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.conyargmail.kingdomdeathmonstercompanion.R;

/**
 * Created by Ryan on 6/9/2018.
 */

public class HuntSpaceFront extends Fragment {
    private AnimatorSet rightOut, leftIn;
    View cardFront, cardBack;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.hunt_space_front, container, false);
    }
}
