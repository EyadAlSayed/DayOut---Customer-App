package com.example.dayout.ui.fragments.drawer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dayout.R;
import com.example.dayout.adapters.recyclers.OrganizersAdapter;
import com.example.dayout.helpers.view.FN;
import com.example.dayout.models.profile.ProfileData;
import com.example.dayout.models.profile.organizer.OrganizersModel;
import com.example.dayout.ui.activities.MainActivity;
import com.example.dayout.ui.dialogs.ErrorDialog;
import com.example.dayout.ui.dialogs.LoadingDialog;
import com.example.dayout.viewModels.UserViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("NonConstantResourceId")
public class OrganizersListFragment extends Fragment {

    View view;

    @BindView(R.id.organizers_back_button)
    ImageButton backButton;

    @BindView(R.id.organizers_search_field)
    EditText searchField;

    @BindView(R.id.organizers_recycler)
    RecyclerView organizersRecycler;

    LoadingDialog loadingDialog;

    OrganizersAdapter adapter;

    List<ProfileData> mainList;
    List<ProfileData> filteredList;

    boolean followingOnly;

    public OrganizersListFragment(boolean followingOnly){
        this.followingOnly = followingOnly;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_organizers_list, container, false);
        ButterKnife.bind(this, view);
        initView();
        getDataFromAPI();
        return view;
    }

    @Override
    public void onStart() {
        ((MainActivity) requireActivity()).hideBottomBar();
        super.onStart();
    }

    private void initView() {
        loadingDialog = new LoadingDialog(requireContext());
        mainList = new ArrayList<>();
        filteredList = new ArrayList<>();
        initRecycler();
        backButton.setOnClickListener(onBackClicked);
        searchField.addTextChangedListener(textWatcher);
    }

    private void initRecycler(){
        organizersRecycler.setHasFixedSize(true);
        organizersRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new OrganizersAdapter(new ArrayList<>(), requireContext());
        organizersRecycler.setAdapter(adapter);
    }

    private void getDataFromAPI(){
        loadingDialog.show();
        //if following only:

        //if all organizers:
        UserViewModel.getINSTANCE().getAllOrganizers();
        UserViewModel.getINSTANCE().organizersMutableLiveData.observe(requireActivity(), organizersObserver);
    }

    private final Observer<Pair<OrganizersModel, String>> organizersObserver = new Observer<Pair<OrganizersModel, String>>() {
        @Override
        public void onChanged(Pair<OrganizersModel, String> organizersModelStringPair) {
            loadingDialog.dismiss();
            if(organizersModelStringPair != null){
                if(organizersModelStringPair.first != null){
                    mainList = organizersModelStringPair.first.data;
                    adapter.refreshList(organizersModelStringPair.first.data);
                } else
                    new ErrorDialog(requireContext(), organizersModelStringPair.second).show();
            } else
                new ErrorDialog(requireContext(), "Error Connection").show();
        }
    };

    private void filter(String organizerName){
        filteredList.clear();

        if(organizerName.isEmpty()){
            adapter.refreshList(mainList);
            return;
        }

        for(ProfileData organizer: mainList){
            String name = organizer.first_name + " " + organizer.last_name;
            if(name.toLowerCase().contains(organizerName)){
                filteredList.add(organizer);
            }
        }

        adapter.refreshList(filteredList);
    }

    private final View.OnClickListener onBackClicked = v -> FN.popStack(requireActivity());

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            filter(s.toString().toLowerCase());
        }
    };
}