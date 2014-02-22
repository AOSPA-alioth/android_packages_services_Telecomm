/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.telecomm.testcallservice;

import android.content.ComponentName;
import android.os.IBinder;
import android.os.RemoteException;
import android.telecomm.CallServiceDescriptor;
import android.telecomm.CallServiceProvider;
import android.telecomm.ICallServiceLookupResponse;
import android.util.Log;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Service which provides fake calls to test the ICallService interface.
 * TODO(santoscordon): Build more dummy providers for more CallServiceDescriptor.FLAG_* types.
 */
public class TestCallServiceProvider extends CallServiceProvider {
    private static final String TAG = TestCallServiceProvider.class.getSimpleName();

    /** {@inheritDoc} */
    @Override
    public void lookupCallServices(ICallServiceLookupResponse response) {
        Log.i(TAG, "lookupCallServices()");

        try {
            CallServiceDescriptor.Builder builder = CallServiceDescriptor.newBuilder(this);
            builder.setCallService(TestCallService.class);
            builder.setNetworkType(CallServiceDescriptor.FLAG_WIFI);

            response.setCallServiceDescriptors(Lists.newArrayList(builder.build()));
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to setCallServices().", e);
        }
    }
}