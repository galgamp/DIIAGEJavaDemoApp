package diiage.potherat.demo.demoapp3.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import diiage.potherat.demo.demoapp3.R;
import diiage.potherat.demo.demoapp3.common.EventObserver;
import diiage.potherat.demo.demoapp3.databinding.FragmentGalleryBinding;
import diiage.potherat.demo.demoapp3.databinding.FragmentHomeBinding;
import diiage.potherat.demo.demoapp3.databinding.FragmentQuoteEditBinding;
import diiage.potherat.demo.demoapp3.ui.edit.QuoteEditFragmentDirections;
import diiage.potherat.demo.demoapp3.ui.edit.QuoteEditViewModel;
import diiage.potherat.demo.demoapp3.ui.gallery.GalleryViewModel;

@AndroidEntryPoint
public class HomeFragment extends Fragment {

    @Inject
    HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        ready();

        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeViewModel.loadNumberOfQuotes();
        homeViewModel.loadNumberDistinctOfSources();
        homeViewModel.loadLastQuote();
    }

    private void ready(){
        if(binding != null && homeViewModel != null)
        {
            binding.setLifecycleOwner(this);
            binding.setHomeViewModel(homeViewModel);
        }
    }
}