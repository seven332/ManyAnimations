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
import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewAnimationUtils;

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
final class ManyAnimationsLollipop {
  private ManyAnimationsLollipop() {}

  static Animator circularReveal(View view,
      int centerX, int centerY, float startRadius, float endRadius) {
    return ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius);
  }
}
