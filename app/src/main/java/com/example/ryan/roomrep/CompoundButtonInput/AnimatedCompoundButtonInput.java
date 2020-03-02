package com.example.ryan.roomrep.CompoundButtonInput;

import android.animation.Animator;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.ryan.roomrep.R;

public class AnimatedCompoundButtonInput extends CompoundButtonInput {

    private LinearLayout layout;
    private EditText editText;
    public AnimatedCompoundButtonInput(View view, int compoundButton, int layout) {
        super(view, compoundButton);
        this.compoundButton.setOnCheckedChangeListener(onChecked);
        this.layout = view.findViewById(layout);
        editText = view.findViewById(R.id.edtAddHouseParkingAmount);
    }

    private CompoundButton.OnCheckedChangeListener onChecked = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (b) {
                layout.setEnabled(true);
                layout.setVisibility(View.VISIBLE);
                editText.setText("0");
                YoYo.with(Techniques.FadeInDown).duration(700).playOn(layout);
                return;
            }
            layout.setEnabled(false);
            YoYo.with(Techniques.SlideOutLeft).duration(500).onStart(new YoYo.AnimatorCallback() {
                @Override
                public void call(Animator animator) {
                    compoundButton.setEnabled(false);
                }
            }).onEnd(new YoYo.AnimatorCallback() {
                @Override
                public void call(Animator animator) {
                    layout.setVisibility(View.GONE);
                    compoundButton.setEnabled(true);
                    editText.setText("");
                }
            }).playOn(layout);
        }
    };

}
