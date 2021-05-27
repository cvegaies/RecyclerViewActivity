package org.izv.ad.recyclerviewactivity.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.izv.ad.recyclerviewactivity.databinding.FragmentFirstBinding;

import org.izv.ad.recyclerviewactivity.R;
import org.izv.ad.recyclerviewactivity.view.recycler.StringAdapter;
import org.izv.ad.recyclerviewactivity.view.viewmodel.ApplicationViewModel;
import org.izv.ad.recyclerviewactivity.view.viewmodel.OnItemClickListener;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private ApplicationViewModel viewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(getActivity()).get(ApplicationViewModel.class);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        OnItemClickListener listener = new OnItemClickListener() {
            @Override
            public void onItemClick(View view) {
                TextView tv = (TextView) view;
                //...
            }
        };
        final StringAdapter adapter = new StringAdapter(viewModel.getWords(), listener);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        viewModel.getElements().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                adapter.notifyDataSetChanged();
            }
        });
    }

}