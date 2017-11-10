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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import app.ewtc.masterung.rmutsvservice.MyServiceActivity;
import app.ewtc.masterung.rmutsvservice.R;
import app.ewtc.masterung.rmutsvservice.utility.MyConstant;
import app.ewtc.masterung.rmutsvservice.utility.UploadNewUser;

/**
 * Created by masterung on 10/11/2017 AD.
 */

public class EditFragment extends Fragment {

    private String nameString, catString, userString, passwordString;
    private EditText nameEditText, userEditText, passwordEditText;
    private RadioButton buyerRadioButton, salerRadioButton;

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

//        Save Controller
        saveController();

    }

    private void saveController() {
        ImageView imageView = getView().findViewById(R.id.imvSave);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nameString = nameEditText.getText().toString().trim();
                userString = userEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();

                if (buyerRadioButton.isChecked()) {
                    catString = "Buyer";
                }

                if (salerRadioButton.isChecked()) {
                    catString = "Saler";
                }

                editNewValue();


            }
        });
    }

    private void editNewValue() {
        try {

            MyConstant myConstant = new MyConstant();
            UploadNewUser uploadNewUser = new UploadNewUser(getActivity());
            uploadNewUser.execute(nameString, catString, userString,
                    passwordString, myConstant.getUrlEditData());

            if (Boolean.parseBoolean(uploadNewUser.get())) {
                getActivity().getSupportFragmentManager().popBackStack();
            } else {
                Toast.makeText(getActivity(), "Cannot Edit Data", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
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
        buyerRadioButton = getView().findViewById(R.id.radBuyer);
        salerRadioButton = getView().findViewById(R.id.radSaler);

        if (catString.equals("Buyer")) {
            buyerRadioButton.setChecked(true);
        } else {
            salerRadioButton.setChecked(true);
        }

    }

    private void showText() {
        nameEditText = getView().findViewById(R.id.edtName);
        userEditText = getView().findViewById(R.id.edtUser);
        passwordEditText = getView().findViewById(R.id.edtPassword);

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
