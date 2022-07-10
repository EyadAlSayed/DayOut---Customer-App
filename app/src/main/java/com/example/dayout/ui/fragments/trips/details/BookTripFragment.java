package com.example.dayout.ui.fragments.trips.details;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.dayout.R;
import com.example.dayout.helpers.view.FN;
import com.example.dayout.helpers.view.NoteMessage;
import com.example.dayout.models.trip.BookTripModel;
import com.example.dayout.models.trip.SinglePassengerModel;
import com.example.dayout.ui.dialogs.notify.ErrorDialog;
import com.example.dayout.ui.dialogs.notify.SuccessDialog;
import com.example.dayout.viewModels.TripViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;

@SuppressLint("NonConstantResourceId")
public class BookTripFragment extends Fragment {

    View view;

    @BindView(R.id.book_trip_submit_button)
    Button bookTripSubmitButton;

    @BindView(R.id.booking_back_arrow)
    ImageButton bookingBackArrow;

    @BindView(R.id.book_trip_passengers_layout)
    LinearLayout bookTripPassengersLayout;

    @BindView(R.id.book_trip_add_passenger_button)
    Button bookTripAddPassengerButton;

    int tripId;

    ArrayList<SinglePassengerModel> passengers;

    public BookTripFragment(int tripId){
        this.tripId = tripId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_book_trip, container, false);
        ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    private void initViews() {
        passengers = new ArrayList<>();
        bookTripSubmitButton.setOnClickListener(onSubmitClicked);
        bookTripAddPassengerButton.setOnClickListener(onAddClicked);
        bookingBackArrow.setOnClickListener(onBackClicked);
    }

    private void removeView(View v){
        bookTripPassengersLayout.removeView(v);
    }

    private boolean validBooking(){
        if (bookTripPassengersLayout.getChildCount() < 1){
            NoteMessage.showSnackBar(requireActivity(), getResources().getString(R.string.at_least_one_passenger));
            return false;
        }
        return true;
    }

    private BookTripModel getModel(){
        BookTripModel model = new BookTripModel();

        model.trip_id = tripId;
        model.passengers = passengers;

        return model;
    }

    private void bookTrip(){
        TripViewModel.getINSTANCE().bookTrip(getModel());
        TripViewModel.getINSTANCE().bookTripMutableLiveData.observe(requireActivity(), bookTripObserver);
    }

    private final Observer<Pair<ResponseBody, String>> bookTripObserver = new Observer<Pair<ResponseBody, String>>() {
        @Override
        public void onChanged(Pair<ResponseBody, String> booleanStringPair) {
            if(booleanStringPair != null){
                if(booleanStringPair.first != null){
                    new SuccessDialog(requireContext(), getResources().getString(R.string.trip_booked)).show();
                    FN.popStack(requireActivity());
                } else
                    new ErrorDialog(requireContext(), booleanStringPair.second).show();
            } else
                new ErrorDialog(requireContext(), getResources().getString(R.string.error_connection)).show();
        }
    };

    private final View.OnClickListener onBackClicked = v -> {FN.popStack(requireActivity());};

    private final View.OnClickListener onAddClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final View passengersView = getLayoutInflater().inflate(R.layout.book_trip_single_passenger, null, false);

            ImageButton deleteOptionButton = (ImageButton) passengersView.findViewById(R.id.single_passenger_delete_icon);

            deleteOptionButton.setOnClickListener(v1 -> removeView(passengersView));

            bookTripPassengersLayout.addView(passengersView);
        }
    };

    private final View.OnClickListener onSubmitClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            passengers.clear();
            if(validBooking()){
                for(int i = 0; i < bookTripPassengersLayout.getChildCount(); i++){

                    View passengerView = bookTripPassengersLayout.getChildAt(i);
                    EditText passengerName = (EditText)passengerView.findViewById(R.id.single_passenger_name);

                    passengers.add(new SinglePassengerModel(passengerName.getText().toString()));
                }
                bookTrip();
            }
        }
    };
}
