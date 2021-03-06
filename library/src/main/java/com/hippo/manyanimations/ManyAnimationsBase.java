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
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Property;
import android.view.View;
import android.widget.TextView;
import com.hippo.manyanimations.reveal.Revealable;
import com.hippo.manyanimations.util.FloatProperty;
import com.hippo.manyanimations.util.IntProperty;

final class ManyAnimationsBase {
  private ManyAnimationsBase() {}

  ///////////////////////////////////////////////////////////////////////////
  // Circular Reveal
  ///////////////////////////////////////////////////////////////////////////

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

  ///////////////////////////////////////////////////////////////////////////
  // Recolor Background
  ///////////////////////////////////////////////////////////////////////////

  public static final Property<ColorDrawable, Integer> COLOR_DRAWABLE_COLOR =
      new IntProperty<ColorDrawable>() {
        @Override
        public void setValue(ColorDrawable object, int value) {
          object.setColor(value);
        }

        @Override
        public Integer get(ColorDrawable object) {
          return object.getColor();
        }
  };

  static Animator recolorBackground(View view, int startColor, int endColor) {
    Drawable drawable = view.getBackground();
    if (drawable instanceof ColorDrawable) {
      ColorDrawable colorDrawable = (ColorDrawable) drawable;
      ObjectAnimator animator = ObjectAnimator.ofInt(
          colorDrawable, COLOR_DRAWABLE_COLOR, startColor, endColor);
      animator.setEvaluator(new ArgbEvaluator());
      return animator;
    } else {
      return null;
    }
  }

  ///////////////////////////////////////////////////////////////////////////
  // Recolor Text
  ///////////////////////////////////////////////////////////////////////////

  public static final Property<TextView, Integer> TEXT_VIEW_COLOR =
      new IntProperty<TextView>() {
        @Override
        public void setValue(TextView object, int value) {
          object.setTextColor(value);
        }

        @Override
        public Integer get(TextView object) {
          return object.getTextColors().getDefaultColor();
        }
      };

  static Animator recolorText(TextView view, int startColor, int endColor) {
    ObjectAnimator animator = ObjectAnimator.ofInt(
        view, TEXT_VIEW_COLOR, startColor, endColor);
    animator.setEvaluator(new ArgbEvaluator());
    return animator;
  }
}
