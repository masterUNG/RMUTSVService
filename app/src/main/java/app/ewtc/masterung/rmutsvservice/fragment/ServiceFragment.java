package app.ewtc.masterung.rmutsvservice.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import app.ewtc.masterung.rmutsvservice.MyServiceActivity;
import app.ewtc.masterung.rmutsvservice.R;
import app.ewtc.masterung.rmutsvservice.utility.DeleteData;
import app.ewtc.masterung.rmutsvservice.utility.GetAllData;
import app.ewtc.masterung.rmutsvservice.utility.ListViewAdapter;
import app.ewtc.masterung.rmutsvservice.utility.MyConstant;

/**
 * Created by masterung on 9/11/2017 AD.
 */

public class ServiceFragment extends Fragment {

    public static ServiceFragment serviceInstance(String[] strings) {

        ServiceFragment serviceFragment = new ServiceFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray("Login", strings);
        serviceFragment.setArguments(bundle);

        return serviceFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String[] strings = getArguments().getStringArray("Login");
        Log.d("9novV1", "Login(1) on ServiceFragment ==> " + strings[1]);

//        Create Toolbar
        createToolbar(strings[1]);

//        Create ListView
        createListView();

    }

    private void createListView() {

        ListView listView = getView().findViewById(R.id.livUser);
        MyConstant myConstant = new MyConstant();

        try {

            GetAllData getAllData = new GetAllData(getActivity());
            getAllData.execute(myConstant.getUrlGetAllUser());
            String resultJSON = getAllData.get();
            Log.d("9novV1", "JSON ==> " + resultJSON);

            JSONArray jsonArray = new JSONArray(resultJSON);

            final String[] nameStrings = new String[jsonArray.length()];
            final String[] catStrings = new String[jsonArray.length()];
            final String[] userStrings = new String[jsonArray.length()];
            final String[] passwordStrings = new String[jsonArray.length()];

            for (int i = 0; i < jsonArray.length(); i += 1) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                nameStrings[i] = jsonObject.getString("Name");
                catStrings[i] = jsonObject.getString("Category");
                userStrings[i] = jsonObject.getString("User");
                passwordStrings[i] = jsonObject.getString("Password");

            }   // for

            ListViewAdapter listViewAdapter = new ListViewAdapter(getActivity(),
                    nameStrings, catStrings, userStrings, passwordStrings);
            listView.setAdapter(listViewAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                    confirmDialog(nameStrings[i], catStrings[i],
                            userStrings[i], passwordStrings[i]);
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void confirmDialog(final String nameString,
                               final String catString,
                               final String userString,
                               final String passwordString) {

        Log.d("11novV1", "Name[confirmDialog] ==> " + nameString);
        Log.d("11novV1", "Cat[confirmDialog] ==> " + catString);
        Log.d("11novV1", "User[confirmDialog] ==> " + userString);
        Log.d("11novV1", "Password[confirmDialog] ==> " + passwordString);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_action_alert);
        builder.setTitle("You Choose " + nameString);
        builder.setMessage("What do you want ?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteDataWhere(nameString);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentServiceFragment, EditFragment.editInstance(
                                nameString,
                                catString,
                                userString,
                                passwordString))
                        .addToBackStack(null).commit();

                dialog.dismiss();
            }
        });


        builder.show();

    }

    private void deleteDataWhere(String nameString) {

        try {

            MyConstant myConstant = new MyConstant();
            DeleteData deleteData = new DeleteData(getActivity());
            deleteData.execute(nameString, myConstant.getUrlDeleteData());

            if (Boolean.parseBoolean(deleteData.get())) {
                Toast.makeText(getActivity(), "Delete Success", Toast.LENGTH_SHORT).show();
                createListView();
            } else {
                Toast.makeText(getActivity(), "Delete Error", Toast.LENGTH_SHORT).show();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void createToolbar(String strTitle) {
        Toolbar toolbar = getView().findViewById(R.id.toolbarService);
        ((MyServiceActivity) getActivity()).setSupportActionBar(toolbar);
        ((MyServiceActivity) getActivity()).getSupportActionBar().setTitle(strTitle);
        ((MyServiceActivity) getActivity()).getSupportActionBar().setSubtitle("Who Loged");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_service, container, false);

        return view;
    }
}   // Main Class
