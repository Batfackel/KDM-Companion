package com.conyargmail.kingdomdeathmonstercompanion.ui.hunt;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;

import com.conyargmail.kingdomdeathmonstercompanion.R;

/**
 * Created by Ryan on 6/9/2018.
 */

public class HuntTrackActivity extends Activity {

    FrameLayout cardSpace;
    boolean showingBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hunt_track_view);
        if(savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.card_space, new HuntSpaceFront(), "front")
                    .commit();
        }
        cardSpace = (FrameLayout) findViewById(R.id.card_space);
        cardSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipCard();
            }
        });
    }

    private void flipCard() {
        if(showingBack) {
            getFragmentManager().popBackStack();
            showingBack = false;
            return;
        }

        showingBack = true;
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out)
                .replace(R.id.card_space, new HuntSpaceBack(), "back")
                .addToBackStack("back")
                .commit();
    }
}
