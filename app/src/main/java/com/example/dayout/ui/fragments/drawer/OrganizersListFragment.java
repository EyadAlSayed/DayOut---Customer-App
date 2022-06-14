package com.example.dayout.ui.fragments.drawer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dayout.R;
import com.example.dayout.adapters.recyclers.OrganizersAdapter;
import com.example.dayout.helpers.view.FN;
import com.example.dayout.models.profile.organizer.OrganizerProfileData;
import com.example.dayout.ui.activities.MainActivity;

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

    OrganizersAdapter adapter;

    List<OrganizerProfileData> mainList;
    List<OrganizerProfileData> filteredList;

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
        List<OrganizerProfileData> organizers = new ArrayList<>();
        OrganizerProfileData o1 = new OrganizerProfileData(3.2f, "Salem", "Al Ashour");
        OrganizerProfileData o2 = new OrganizerProfileData(4.1f, "Eyad", "Al Sayed");
        OrganizerProfileData o3 = new OrganizerProfileData(5.0f, "Abd Al Rahim", "Khoulani");
        OrganizerProfileData o4 = new OrganizerProfileData(0.1f, "Caesar", "Farah");
        organizers.add(o1);
        organizers.add(o2);
        organizers.add(o3);
        organizers.add(o4);
        mainList = organizers;
        adapter.refreshList(mainList);

        //if following only:

        //if all organizers:
    }

    private void filter(String organizerName){
        filteredList.clear();

        if(organizerName.isEmpty()){
            adapter.refreshList(mainList);
            return;
        }

        for(OrganizerProfileData organizer: mainList){
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