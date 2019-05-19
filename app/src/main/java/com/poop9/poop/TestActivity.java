package com.poop9.poop;

import android.os.Bundle;
import android.widget.ImageView;
import com.poop9.poop.base.BaseActivity;
import org.jetbrains.annotations.Nullable;

public class TestActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageView image = new ImageView(this);
        image.setLayoutParams(new android.view.ViewGroup.LayoutParams(80,60));
        image.setMaxHeight(20);
        image.setMaxWidth(20);

    }


}
