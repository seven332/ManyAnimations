/*
 * Copyright 2017 Hippo Seven
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hippo.manyanimations.demo;

/*
 * Created by Hippo on 3/30/2017.
 */

import android.animation.Animator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.hippo.manyanimations.ManyAnimations;
import com.hippo.manyanimations.reveal.RevealableFrameLayout;

public class CircularRevealActivity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_circular_reveal);

    RevealableFrameLayout view = (RevealableFrameLayout) findViewById(R.id.view);
    view.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Animator animator = ManyAnimations.circularReveal(v, v.getWidth() / 2, v.getHeight() / 2, 0,
            (float) Math.hypot(v.getWidth() / 2, v.getHeight() / 2));
        animator.setDuration(1000);
        animator.start();
      }
    });
  }
}
