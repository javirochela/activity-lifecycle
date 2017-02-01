/*
 * Copyright (C) 2012 The Android Open Source Project
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

package com.example.android.lifecycle.util;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.*;

public class StatusTracker {
  private Map<String, List<String>> mStatusMap;
  private List<String> mMethodList;
  private static StatusTracker ourInstance = new StatusTracker();
  private static final String STATUS_SUFFIX = "ed";


  private static final String LOG_STATUS = "StatusTracker";

  public static StatusTracker getInstance() {
    return ourInstance;
  }

  private StatusTracker() {
    mStatusMap = new LinkedHashMap<String, List<String>>();
    mMethodList = new ArrayList<String>();
  }

  public List<String> getMethodList() {
    return mMethodList;
  }

  public void clear() {
    mMethodList.clear();
    mStatusMap.clear();
  }

  private String getTimeStamp(){
    String timeStamp = new SimpleDateFormat("HH:mm:ss").format(new Date());
    return timeStamp;
  }

  /**
   * Adds the status value for the given activityName into the Map.
   *
   * @param activityName
   * @param status
   */
  public void setStatus(String activityName, String status) {
    String timeStamp = getTimeStamp();
    Log.d(LOG_STATUS,timeStamp +" -> " + activityName + "." + status + "()");
    mMethodList.add(timeStamp +" -> " + activityName + "." + status + "()");

    if (mStatusMap.containsKey(activityName)) mStatusMap.remove(activityName);
    List<String> list = new ArrayList<>(2);
    list.add(status);
    list.add(timeStamp);
    mStatusMap.put(activityName, list);
  }

  public void setStatusActivity(String activityName, String status, String timeStamp){
    mMethodList.add(timeStamp +" -> " + activityName + "." + status + "()");

    if (mStatusMap.containsKey(activityName)) mStatusMap.remove(activityName);
    List<String> list = new ArrayList<>(2);
    list.add(status);
    list.add(timeStamp);
    mStatusMap.put(activityName, list);
  }

  /**
   * Gets the status value for the given activityName.
   *
   * @param activityName
   * @return
   */
  public String getStatus(String activityName) {
    List<String> list = mStatusMap.get(activityName);

    String status = list.get(0);
    String timestamp = list.get(1);
    status = status.substring(2, status.length());

    // String manipulation to ensure the status value is spelled correctly.
    if (status.endsWith("e")) {
      status = status.substring(0, status.length() - 1);
    }
    if (status.endsWith("p")) {
      status = status + "p";
    }
    status = status + STATUS_SUFFIX +" "+ timestamp;
    return status;
  }

  public String getTimeStampActivity(String ActivityName){
    List<String> list = mStatusMap.get(ActivityName);
    String timestamp = list.get(1);

    return timestamp;
  }

  public Set<String> keySet() {
    return mStatusMap.keySet();
  }
}
