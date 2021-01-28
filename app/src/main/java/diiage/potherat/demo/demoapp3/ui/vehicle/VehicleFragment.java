package diiage.potherat.demo.demoapp3.ui.vehicle;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import diiage.potherat.demo.demoapp3.R;
import diiage.potherat.demo.demoapp3.databinding.FragmentVehicleBinding;
import diiage.potherat.demo.demoapp3.model.sw.Vehicle;

@AndroidEntryPoint
public class VehicleFragment extends Fragment {

    @Inject
    public VehicleViewModel vehicleViewModel;
    private FragmentVehicleBinding binding;

    private EditText inputId;
    private TextView textName;
    private TextView textModel;
    private Button buttonSearch;

    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentVehicleBinding.inflate(inflater,container,false);

        ready();

        View root = binding.getRoot();

        textName = root.findViewById(R.id.textName);
        textModel = root.findViewById(R.id.textModel);
        inputId = root.findViewById(R.id.inputId);
        buttonSearch = root.findViewById(R.id.buttonSearch);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!inputId.getText().toString().equals("") && Integer.parseInt(inputId.getText().toString()) > 0 ) {
                    vehicleViewModel.loadVehicle(Integer.parseInt(inputId.getText().toString()));
                    vehicleViewModel.getVehicle().observe(getViewLifecycleOwner(), new Observer<Vehicle>() {
                        @Override
                        public void onChanged(Vehicle vehicle) {
                            if ( vehicle == null ) {
                                textName.setText("Ce véhicule n'existe pas !");
                                textModel.setText("");
                            } else {
                                textName.setText("Nom : " + vehicle.name);
                                textModel.setText("Modèle : " + vehicle.model);
                            }
                        }
                    });
                }
            }
        });

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void ready(){
        if (binding != null && vehicleViewModel != null){
            binding.setLifecycleOwner(this);
            binding.setVehicleViewmodel(vehicleViewModel);
        }
    }
}