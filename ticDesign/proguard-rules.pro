# Copyright (C) 2015 The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# CoordinatorLayout resolves the behaviors of its child components with reflection.
-keep public class * extends ticwear.design.widget.CoordinatorLayout$Behavior {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>();
}

# GenericInflater create preference instance with name from xml file.
-keep public class * extends ticwear.design.preference.Preference {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>();
}

# Side panel event target should keep for system call.
-keep interface com.mobvoi.ticwear.view.SidePanelEventDispatcher {
    <methods>;
}
-keep interface com.mobvoi.ticwear.view.SidePanelEventDispatcher$SuperCallback {
    <methods>;
}
-keep interface com.mobvoi.ticwear.view.SidePanelEventTarget {
    <methods>;
}
-keep interface com.mobvoi.ticwear.view.SidePanelGestureTarget {
    <methods>;
}
