package app.ewtc.masterung.rmutsvservice.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;

import app.ewtc.masterung.rmutsvservice.MyServiceActivity;
import app.ewtc.masterung.rmutsvservice.R;

/**
 * Created by masterung on 10/11/2017 AD.
 */

public class EditFragment extends Fragment {

    private String nameString, catString, userString, passwordString;

    public static EditFragment editInstance(String nameString,
                                            String catString,
                                            String userString,
                                            String passwordString) {

        EditFragment editFragment = new EditFragment();
        Bundle bundle = new Bundle();
        bundle.putString("Name", nameString);
        bundle.putString("Cat", catString);
        bundle.putString("User", userString);
        bundle.putString("Password", passwordString);
        editFragment.setArguments(bundle);
        return editFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Get Value From Argument
        nameString = getArguments().getString("Name");
        catString = getArguments().getString("Cat");
        userString = getArguments().getString("User");
        passwordString = getArguments().getString("Password");

//        Check Log
        checkLog();

//        Show Text
        showText();

//        Show Click
        showClick();

//        Create Toolbar
        createToolbar();

    }

    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarEdit);
        ((MyServiceActivity) getActivity()).setSupportActionBar(toolbar);
        ((MyServiceActivity) getActivity()).getSupportActionBar().setTitle("Edit Data");
        ((MyServiceActivity) getActivity()).getSupportActionBar().setSubtitle(nameString);

        ((MyServiceActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MyServiceActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    private void showClick() {
        RadioButton buyerRadioButton = getView().findViewById(R.id.radBuyer);
        RadioButton salerRadioButton = getView().findViewById(R.id.radSaler);

        if (catString.equals("Buyer")) {
            buyerRadioButton.setChecked(true);
        } else {
            salerRadioButton.setChecked(true);
        }

    }

    private void showText() {
        EditText nameEditText = getView().findViewById(R.id.edtName);
        EditText userEditText = getView().findViewById(R.id.edtUser);
        EditText passwordEditText = getView().findViewById(R.id.edtPassword);

        nameEditText.setText(nameString);
        userEditText.setText(userString);
        passwordEditText.setText(passwordString);
    }

    private void checkLog() {
        Log.d("11novV1", "Name ==> " + nameString);
        Log.d("11novV1", "Cat ==> " + catString);
        Log.d("11novV1", "User ==> " + userString);
        Log.d("11novV1", "Password ==> " + passwordString);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        return view;
    }
}
