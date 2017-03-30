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
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import com.hippo.manyanimations.util.EmptyAnimator;

public final class ManyAnimations {
  private ManyAnimations() {}

  private interface ManyAnimationsImpl {
    Animator circularReveal(View view,
        int centerX, int centerY, float startRadius, float endRadius);
  }

  private static class BaseManyAnimationsImpl implements ManyAnimationsImpl {
    @Override
    public Animator circularReveal(View view,
        int centerX, int centerY, float startRadius, float endRadius) {
      return ManyAnimationsBase.circularReveal(view, centerX, centerY, startRadius, endRadius);
    }
  }

  @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
  private static class LollipopManyAnimationsImpl extends BaseManyAnimationsImpl {
    @Override
    public Animator circularReveal(View view,
        int centerX, int centerY, float startRadius, float endRadius) {
      return ManyAnimationsLollipop.circularReveal(view, centerX, centerY, startRadius, endRadius);
    }
  }

  private static final ManyAnimationsImpl IMPL;
  static {
    final int version = android.os.Build.VERSION.SDK_INT;
    if (version >= Build.VERSION_CODES.LOLLIPOP) {
      IMPL = new LollipopManyAnimationsImpl();
    } else {
      IMPL = new BaseManyAnimationsImpl();
    }
  }

  /**
   * Creates circular reveal animation.
   * <p>
   * Returns an empty animation if can't create it.
   */
  @NonNull
  public static Animator circularReveal(View view,
      int centerX, int centerY, float startRadius, float endRadius) {
    Animator animator = IMPL.circularReveal(view, centerX, centerY, startRadius, endRadius);
    if (animator != null) {
      return animator;
    } else {
      return EmptyAnimator.create();
    }
  }

  /**
   * Creates circular reveal animation.
   * <p>
   * Returns {@code null} if can't create it.
   */
  @Nullable
  public static Animator circularRevealNullable(View view,
      int centerX, int centerY, float startRadius, float endRadius) {
    return IMPL.circularReveal(view, centerX, centerY, startRadius, endRadius);
  }
}
