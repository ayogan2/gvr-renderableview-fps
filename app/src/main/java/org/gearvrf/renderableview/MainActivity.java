/* Copyright 2015 Samsung Electronics Co., LTD
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

package org.gearvrf.renderableview;

import org.gearvrf.GVRActivity;
import org.gearvrf.GVRMain;
import org.gearvrf.scene_objects.view.GVRFrameLayout;
import org.gearvrf.scene_objects.view.GVRTextView;
import org.gearvrf.scene_objects.view.GVRView;
import org.gearvrf.scene_objects.view.GVRWebView;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class MainActivity extends GVRActivity {
    private GVRMain mMain;

    private GVRFrameLayout mFrameLayoutLeft;

    TextView myTextView;

    ViewGroup mRootLayout;

    public Handler Handler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createView();

        mMain = new Main(this);
        setMain(mMain, "gvr.xml");
    }

    private void createView() {
        mFrameLayoutLeft = new GVRFrameLayout(this);
        mFrameLayoutLeft.setLayoutParams(new FrameLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        mFrameLayoutLeft.setBackgroundColor(Color.CYAN);

        View.inflate(this, R.layout.activity_main, mFrameLayoutLeft);
        mRootLayout = (ViewGroup) findViewById(R.id.root);

        myTextView = (TextView) mRootLayout.findViewById(R.id.textView3);
        myTextView.setText("initial");


        new Thread( new TimeTicker()).start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) { }
        Handler = new Handler() {
            @Override public void handleMessage(Message msg) {
                String text = (String)msg.obj;
            }
        };


    }

    public GVRFrameLayout getFrameLayoutLeft() {
        return mFrameLayoutLeft;
    }

    public void onStep(){



    }

    class TimeTicker implements Runnable{


        @Override
        public void run() {

            while(true){

                Log.d("GVRActivity", " System.currentTimeMillis() = "+ System.currentTimeMillis());

                try {
                    Thread.sleep(4);
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MainActivity.this.myTextView.setText(Long.toString(System.currentTimeMillis()));
                    }
                });

            }


        }
    }
}
