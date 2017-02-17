package com.truiton.activitytofragment;

// Communication headquarters, makes sure there are public functions, to the main activity. Fragments can use these to speak to each other

public interface IFragmentToActivity {
    void showToast(String msg);

    void communicateToTravel(String pointer, String data);

    void communicateToLocation(String pointer, String data);

    void communicateToInventory(String pointer, String data);

}