package com.color.ceramicsltd.Presentation.ui.fragments.impl;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.color.ceramicsltd.Models.District;
import com.color.ceramicsltd.Models.Divisions;
import com.color.ceramicsltd.Models.MainDistrict;
import com.color.ceramicsltd.Models.MainDivisions;
import com.color.ceramicsltd.Models.MainThana;
import com.color.ceramicsltd.Models.SearchProduct;
import com.color.ceramicsltd.Models.Thana;
import com.color.ceramicsltd.Network.response.ProductSearchResponse;
import com.color.ceramicsltd.Presentation.presenters.ProductSearchPresenter;
import com.color.ceramicsltd.Presentation.ui.activities.impl.ProductDetailsActivity;
import com.color.ceramicsltd.Presentation.ui.adapters.ProductSearchAdapter;
import com.color.ceramicsltd.Presentation.ui.fragments.ProductSearchView;
import com.color.ceramicsltd.Presentation.ui.listeners.EndlessRecyclerOnScrollListener;
import com.color.ceramicsltd.Presentation.ui.listeners.SearchProductClickListener;
import com.color.ceramicsltd.R;
import com.color.ceramicsltd.Threading.MainThreadImpl;
import com.color.ceramicsltd.Utils.AppConfig;
import com.color.ceramicsltd.domain.executor.impl.ThreadExecutor;
import com.color.ceramicsltd.location.RetrofitClient;
import com.thekhaeng.recyclerviewmargin.LayoutMarginDecoration;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;

public class ProductSearchFragment extends Fragment implements ProductSearchView , SearchProductClickListener  {
    private View v;

    private List<SearchProduct> mProducts = new ArrayList<>();
    private SearchView searchView;
    private RadioGroup radioScope;
    private ProductSearchAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ProductSearchPresenter productSearchPresenter;
    private String url = AppConfig.BASE_URL+"products/search?key=&scope=product&page=1";
    private String key = "", scope = "product";
    private ProductSearchResponse mProductSearchResponse = null;
    private Spinner sp_division,sp_district,sp_thana;
    private List<Divisions> divisionList = new ArrayList<>();
    private List<District> districtList = new ArrayList<>();
    private List<Thana> thanaList = new ArrayList<>();

    private String division_position;
    private String district_position;
    private String thana_position;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_search, null);
        recyclerView = v.findViewById(R.id.product_list);
        recyclerView.addItemDecoration( new LayoutMarginDecoration( 1,  AppConfig.convertDpToPx(getContext(), 10)) );
        progressBar = v.findViewById(R.id.item_progress_bar);
        searchView = v.findViewById(R.id.search_key);
        //radioScope = v.findViewById(R.id.scope_radio);
        sp_division = v.findViewById(R.id.sp_division);
        sp_district = v.findViewById(R.id.sp_district);
        sp_thana = v.findViewById(R.id.sp_thana);

//        sp_division.setOnItemSelectedListener(this);
//        sp_district.setOnItemSelectedListener(this);
//        sp_thana.setOnItemSelectedListener(this);

        productSearchPresenter = new ProductSearchPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        //searchView.setQueryHint("I'm looking for...");
        getDivision();
        getDistrict();



        sp_division.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               loadDistrict(String.valueOf(divisionList.get(position).getId()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp_district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //thanaList.clear();
                loadThana(String.valueOf(districtList.get(position).getId()));
                System.out.println(districtList.get(position).getName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp_thana.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();     // Close keyboard on pressing IME_ACTION_SEARCH option
                key = query;
                searchProduct(key, scope);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                //Log.d("Test", "QueryTextChange: "+ newText);
                if (newText.length() == 0){
                    key = "";
                    searchProduct(key, scope);
                }
                else {
                    key = newText;
                    searchProduct(key, scope);
                }
                return true;
            }
        });
//        radioScope.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                RadioButton radiochecked = (RadioButton) v.findViewById(checkedId);
//                scope = radiochecked.getText().toString();
//                searchProduct(key, scope);
//            }
//        });

        searchProduct("", "product");
        return v;
    }
    private void searchProduct(String key, String scope){
        //Log.d("Test", scope);
        url = url.replace("key="+url.split("key=")[1].split("&")[0], "key="+key);
        url = url.replace("scope="+url.split("scope=")[1].split("&")[0], "scope="+scope.toLowerCase());

        //Log.d("Test", url);

        mProducts.removeAll(mProducts);

        GridLayoutManager horizontalLayoutManager
                = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(horizontalLayoutManager);

        adapter = new ProductSearchAdapter(getContext(), mProducts, this);

        recyclerView.addOnScrollListener(       new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                addDataToList(mProductSearchResponse);
            }
        });

        recyclerView.setAdapter(adapter);

        progressBar.setVisibility(View.VISIBLE);

        productSearchPresenter.getSearchedProducts(url);
    }
    public void addDataToList(ProductSearchResponse productSearchResponse){
        if (productSearchResponse != null && productSearchResponse.getMeta() != null && !productSearchResponse.getMeta().getCurrentPage().equals(productSearchResponse.getMeta().getLastPage())){
            progressBar.setVisibility(View.VISIBLE);
            productSearchPresenter.getSearchedProducts(productSearchResponse.getLinks().getNext());
        }
    }
    @Override
    public void onProductItemClick(SearchProduct product) {
        Intent intent = new Intent(getContext(), ProductDetailsActivity.class);
        intent.putExtra("product_name", product.getName());
        intent.putExtra("link", product.getLinks().getDetails());
        intent.putExtra("top_selling", product.getLinks().getRelated());
        startActivity(intent);
    }
    @Override
    public void setSearchedProduct(ProductSearchResponse productSearchResponse) {
        Log.d("Test", String.valueOf(productSearchResponse.getMeta().getTotal()));
        mProducts.addAll(productSearchResponse.getData());
        mProductSearchResponse = productSearchResponse;
        progressBar.setVisibility(View.GONE);
        adapter.notifyDataSetChanged();
    }

    private List<String> divName  = new ArrayList<>();
    private void getDivision() {
        Call<MainDivisions> divisionListCall = RetrofitClient.getInstance().getAllDivision().getAllDivisions();
        divisionListCall.enqueue(new Callback<MainDivisions>() {
            @Override
            public void onResponse(Call<MainDivisions> call, Response<MainDivisions> response) {
                assert response.body() != null;
                divisionList = response.body().getData();
                //MainDivisions mainDivisions = new MainDivisions(divisionsList,true,200);
                for (Divisions div : divisionList)
                    divName.add(div.getName());
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,divName);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp_division.setAdapter(dataAdapter);
            }
            @Override
            public void onFailure(Call<MainDivisions> call, Throwable t) {

            }
        });
    }
    private void getDistrict(){
        Call<MainDistrict> districtListCall = RetrofitClient.getInstance().getAllDistrict().getAllDistricts();
        districtListCall.enqueue(new Callback<MainDistrict>() {
            @Override
            public void onResponse(Call<MainDistrict> call, Response<MainDistrict> response) {
                districtList = response.body().getData();
                
            }
            @Override
            public void onFailure(Call<MainDistrict> call, Throwable t) {

            }
        });
    }
    private void getThana(){
        Call<MainThana> thanaListCall = RetrofitClient.getInstance().getAllThana().getAllThana();
        thanaListCall.enqueue(new Callback<MainThana>() {
            @Override
            public void onResponse(Call<MainThana> call, Response<MainThana> response) {
                thanaList = response.body().getData();
            }
            @Override
            public void onFailure(Call<MainThana> call, Throwable t) {

            }
        });
    }

    private List<String> disNameList = new ArrayList<>();
    private void loadDistrict(String division_id){
        disNameList.clear();
        for (District distric : districtList)
        {
            if (division_id.equals(distric.getParentId())){
                disNameList.add(distric.getName());
            }
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,disNameList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_district.setAdapter(dataAdapter);
    }
    private List<String> thana_Name_list = new ArrayList<>();
    private void loadThana(String district_id){
        getThana();
        thana_Name_list.clear();

        for (Thana thana : thanaList)
        {
            if (district_id.equals(thana.getParentId())){
                thana_Name_list.add(thana.getName());
            }
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,thana_Name_list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_thana.setAdapter(dataAdapter);
    }

}
