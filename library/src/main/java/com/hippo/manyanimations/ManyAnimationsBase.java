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

package com.hippo.manyanimations;

/*
 * Created by Hippo on 3/30/2017.
 */

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.View;
import com.hippo.manyanimations.reveal.Revealable;
import com.hippo.manyanimations.util.FloatProperty;

final class ManyAnimationsBase {
  private ManyAnimationsBase() {}

  private static class RevealProperty extends FloatProperty<Revealable> {

    private final int x;
    private final int y;

    public RevealProperty(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public void setValue(Revealable object, float value) {
      object.setReveal(x, y, value);
    }

    @Override
    public Float get(Revealable object) {
      // Ignore
      return null;
    }
  }

  private static class RevealAnimatorListener extends AnimatorListenerAdapter {
    private Revealable revealable;

    public RevealAnimatorListener(Revealable revealable) {
      this.revealable = revealable;
    }

    @Override
    public void onAnimationStart(Animator animation) {
      revealable.setRevealEnabled(true);
    }

    @Override
    public void onAnimationEnd(Animator animation) {
      revealable.setRevealEnabled(false);
    }
  }

  static Animator circularReveal(View view,
      int centerX, int centerY, float startRadius, float endRadius) {
    if (view instanceof Revealable){
      Animator animator = ObjectAnimator.ofFloat((Revealable) view,
          new RevealProperty(centerX, centerY), startRadius, endRadius);
      animator.addListener(new RevealAnimatorListener((Revealable) view));
      return animator;
    } else {
      return null;
    }
  }
}