package com.hw1.devlyn.thewateringhole;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.hw1.devlyn.thewateringhole.ConnectDb.*;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentMainActivity.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentMainActivity#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentRegister extends Fragment implements  View.OnClickListener {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private boolean mIntentInProgress;

    /*Fields for the buttons to be used in this class.*/
    Button Register;
    EditText userName;
    EditText newPassword;
    EditText repeatPassword;
    EditText email;

    String userNameText;
    String newPasswordText;
    String repeatPasswordText;
    String emailText;
    private static AsyncTask<String, Void, String> dbCon;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentEvents.
     */
    public static FragmentRegister newInstance(String param1, String param2) {
        FragmentRegister fragment = new FragmentRegister();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentRegister() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        dbCon = new ConnectDb().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_register, container, false);

        getButtons(rootView);

        Register = (Button) rootView.findViewById(R.id.register_button);
        userName = (EditText) rootView.findViewById(R.id.Username);
        newPassword = (EditText) rootView.findViewById(R.id.NewPassword);
        repeatPassword = (EditText) rootView.findViewById(R.id.RepeatPassword);
        email = (EditText) rootView.findViewById(R.id.Email);


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
        if (view == Register) {
            userNameText = userName.getText().toString();
            newPasswordText = newPassword.getText().toString();
            repeatPasswordText = repeatPassword.getText().toString();
            emailText = email.getText().toString();

            if (newPasswordText.equals(repeatPasswordText)){
              MyApplicationClass.MySQLAccess dao = new MyApplicationClass.MySQLAccess();

              dao.registerUser(userNameText, newPasswordText, emailText);
              Log.d("REGISTRATION", "Sent: " + userNameText + ", " + newPasswordText + ", " + emailText);
              Toast.makeText(getActivity(),"Reg successful!", Toast.LENGTH_SHORT).show();
              Intent goToMain = new Intent(getActivity(),Login_Screen.class);
              startActivity(goToMain);

          } else {
                Toast.makeText(getActivity(), "Passwords don't match!",Toast.LENGTH_SHORT).show();
            }
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
        public void onFragmentInteraction(Uri uri);
    }

}
