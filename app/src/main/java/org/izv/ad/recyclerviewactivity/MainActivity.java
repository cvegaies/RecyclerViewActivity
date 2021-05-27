package org.izv.ad.recyclerviewactivity;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.izv.ad.recyclerviewactivity.databinding.ActivityMainBinding;
import org.izv.ad.recyclerviewactivity.view.viewmodel.ApplicationViewModel;

import android.view.Menu;
import android.view.MenuItem;


//https://developer.android.com/guide/topics/ui/layout/recyclerview?hl=es-419
//https://developer.android.com/guide/topics/ui/layout/recyclerview-custom?hl=es-419
//https://antonioleiva.com/recyclerview-listener/
//https://stackoverflow.com/questions/49969278/recyclerview-item-click-listener-the-right-way
//https://github.com/android/views-widgets-samples/tree/main/RecyclerView
//https://developer.android.com/guide/topics/providers/content-provider-basics?hl=es-419

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ApplicationViewModel viewModel = new ViewModelProvider(this).get(ApplicationViewModel.class);
        //ApplicationViewModel viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ApplicationViewModel.class);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.addWord();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}