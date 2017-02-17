package com.truiton.activitytofragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class inventoryTab extends Fragment implements View.OnClickListener {
    Player player1 = new Player();
    private static final String LOG_TAG = "tag";
    private IFragmentToActivity mCallback;
    private CheckBox treasureCheck;
    private Button Card;
    private Button sendtoLocation;
    private Button sendtoTravel;
    private int countTop = 0;
    private int countBottom = 0;
    private ImageButton swordImage, staffImage, potionImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.inventory_tab, container, false);

        //ard = (Button) view.findViewById(R.id.GetCard);
        //Card.setOnClickListener(this);

        //treasureCheck = (CheckBox) view.findViewById(R.id.checkBox);
        //treasureCheck.setOnClickListener(this);

        swordImage = (ImageButton) view.findViewById(R.id.SwordImage);
        swordImage.setOnClickListener(this);

        staffImage = (ImageButton) view.findViewById(R.id.StaffImage);
        staffImage.setOnClickListener(this);

        potionImage = (ImageButton) view.findViewById(R.id.PotionImage);
        potionImage.setOnClickListener(this);

        //sendtoLocation = (Button) view.findViewById(R.id.button1);
        //sendtoTravel = (Button) view.findViewById(R.id.button2);

        //sendtoLocation.setOnClickListener(this);
        //sendtoTravel.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageButton swordImage = (ImageButton) view.findViewById(R.id.SwordImage);
        ImageButton staffImage = (ImageButton) view.findViewById(R.id.StaffImage);
        ImageButton potionImage = (ImageButton) view.findViewById(R.id.PotionImage);
        swordImage.setImageResource(R.mipmap.none);
        staffImage.setImageResource(R.mipmap.none);
        potionImage.setImageResource(R.mipmap.none);
    }

    @Override
    public void onAttach(Context context) {
        Log.i(LOG_TAG, "onAttach inventory");
        super.onAttach(context);
        try {
            mCallback = (IFragmentToActivity) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement IFragmentToActivity");
        }
    }
/*
    @Override
    public void onDetach() {
        Log.i(LOG_TAG, "onDetach inventory");
        mCallback = null;
        super.onDetach();
    }
*/
    public void onRefresh() {
        Toast.makeText(getActivity(), "Fragment 2: Refresh called.",
                Toast.LENGTH_SHORT).show();
    }

    /*
    Incoming communications, the pointer string is specified by the sender and indicates where the string value should go.
    Each tab will have a specific static layout with labels for each object.
    For example the pointer "Top" currently sets the top text view on any Tab to the String "data"
    */
    public void fragmentCommunication(String pointer, String data) {

        switch (pointer) {
            case "item":
                switch (data){
                    case "sword":
                        player1.swordItem = Boolean.TRUE;
                        setImage("sword");
                        break;
                    case "staff":
                        player1.staffItem = Boolean.TRUE;
                        setImage("staff");
                        break;
                    case "health":
                        player1.potionItem = Boolean.TRUE;
                        setImage("health");
                        break;

                }
                //top.setText(data);
                break;
            case "Bottom":
                Log.i(LOG_TAG, "inventory recieved bottom instance"+data);
                //bottom.setText(data);
                break;

        }
    }

    // button clicks processed by id
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /* To activity comms
            case R.id.button:
                mCallback.showToast("Hello from Fragment 2");
                break;
                */
            /*

            case R.id.button1:
                countTop++;
                mCallback.communicateToLocation("Top", "From inventory: "+countTop+"");
                break;

            case R.id.button2:
                countBottom++;
                mCallback.communicateToTravel("Bottom", "From inventory: "+countBottom+"");
                break;



            case R.id.GetCard:
                getCard();
                break;

            case R.id.checkBox:
                if (treasureCheck.isChecked())
                    player1.treasureFound = Boolean.TRUE;

                else
                    player1.treasureFound = Boolean.FALSE;
                break;

                */
            case R.id.SwordImage:
                if (player1.swordItem)
                    msg("sword equiped");
                else
                    msg("No item to equip");
                break;

            case R.id.StaffImage:
                if (player1.staffItem)
                    msg("sword equiped");
                else
                    msg("No item to equip");
                break;

            case R.id.PotionImage:
                if (player1.potionItem)
                    msg("health potion used");
                else
                    msg("No item to equip");
                break;

        }
    }

    public void setImage(String picture){

        ImageView swordImage = (ImageView) getView().findViewById(R.id.SwordImage);
        ImageView staffImage = (ImageView) getView().findViewById(R.id.StaffImage);
        ImageView potionImage = (ImageView) getView().findViewById(R.id.PotionImage);

        switch (picture){
            case "sword":
                swordImage.setImageResource(R.mipmap.sword1);
                break;
            case "staff":
                staffImage.setImageResource(R.mipmap.staff1);
                break;
            case "health":
                potionImage.setImageResource(R.mipmap.health);
                break;

        }
    }

    private void getCard() {

        if (player1.treasureFound)
            msg("treasure was found");
        else
            msg("no treasure found");

    }

    // Call Toast
    private void msg(String s)
    {
        Toast.makeText(getActivity(),s,Toast.LENGTH_LONG).show();
    }
}
