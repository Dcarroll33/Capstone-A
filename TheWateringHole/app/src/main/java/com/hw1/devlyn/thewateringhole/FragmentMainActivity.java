package com.hw1.devlyn.thewateringhole;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentMainActivity.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentMainActivity#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMainActivity extends Fragment implements  View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private boolean mIntentInProgress;

    /*Fields for the buttons to be used in this class.*/
    Button Events;
    Button Friends;
    Button Profile;
    Button Settings;
    Button SignOut;

    GoogleApiClient mGoogleApiClient = MyApplicationClass.getClient();
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentMainActivity.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentMainActivity newInstance(String param1, String param2) {
        FragmentMainActivity fragment = new FragmentMainActivity();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentMainActivity() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main_activity, container, false);

        getButtons(rootView);

        SignOut = (Button) rootView.findViewById(R.id.sign_out_button);
        Events = (Button) rootView.findViewById(R.id.events_btn);
        Friends = (Button) rootView.findViewById(R.id.friends_btn);
        Profile = (Button) rootView.findViewById(R.id.profile_btn);
        Settings = (Button) rootView.findViewById(R.id.settings_btn);

/*        Events.setOnClickListener(this);
        Friends.setOnClickListener(this);
        Profile.setOnClickListener(this);
        Settings.setOnClickListener(this);*/

        return rootView;
    }

    public void getButtons(View v){
        if(v instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) v;

            for (int i = 0; i <vg.getChildCount(); i++) {
                View v1 = vg.getChildAt(i);
                if (v1 instanceof Button) {
                    Button b = (Button) v1;
                    b.setOnClickListener(this);
                }
                else if(v1 instanceof ViewGroup) {
                    getButtons(v1);
                }
            }
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /*This method is for the on screen clicks by the user depending on which button is pushed
        in this case the Events, Friends, Profile or Settings buttons. Once one button has been
        clicked depending on their relationship the screen will switch to the appropriate screen.*/
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.sign_out_button) {
            mGoogleApiClient.disconnect();
            Toast.makeText(super.getActivity().getBaseContext(), "Connected: "+ mGoogleApiClient.isConnected()+"", Toast.LENGTH_LONG).show();
            Intent main = new Intent(getActivity(), MainActivity.class);
            this.startActivity(main);
        }
        if (view == Events) {
            Intent events = new Intent(getActivity(), EventsActivity.class);

            Button b = (Button) view;
            this.startActivity(events);
        } else if (view == Friends) {
            Intent friends = new Intent(getActivity(), Friends.class);

            Button b = (Button) view;
            this.startActivity(friends);
        } else if (view == Profile) {
            Intent profile = new Intent(getActivity(), UserProfile.class);

            Button b = (Button) view;
            this.startActivity(profile);
        } else if (view == Settings) {
            Intent settings = new Intent(getActivity(), Settings.class);

            Button b = (Button) view;
            this.startActivity(settings);
        }
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
