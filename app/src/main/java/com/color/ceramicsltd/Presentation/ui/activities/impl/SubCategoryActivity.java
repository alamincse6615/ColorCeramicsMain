package com.color.ceramicsltd.Presentation.ui.activities.impl;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.color.ceramicsltd.Models.Category;
import com.color.ceramicsltd.Models.SubCategory;
import com.color.ceramicsltd.Presentation.presenters.SubSubCategoryPresenter;
import com.color.ceramicsltd.Presentation.ui.activities.SubCategoryView;
import com.color.ceramicsltd.Presentation.ui.adapters.SubCategoryAdapter;
import com.color.ceramicsltd.Presentation.ui.listeners.SubCategoryClickListener;
import com.color.ceramicsltd.R;
import com.color.ceramicsltd.Threading.MainThreadImpl;
import com.color.ceramicsltd.Utils.AppConfig;
import com.color.ceramicsltd.domain.executor.impl.ThreadExecutor;
import com.thekhaeng.recyclerviewmargin.LayoutMarginDecoration;

import java.util.List;

public class SubCategoryActivity extends BaseActivity implements SubCategoryView, SubCategoryClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_sub_category);

        Category category = (Category) getIntent().getSerializableExtra("category");

        initializeActionBar();
        setTitle(category.getName());

        SubSubCategoryPresenter subSubCategoryPresenter = new SubSubCategoryPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        subSubCategoryPresenter.getSubSubCategories(category.getLinks().getSubCategories());
    }

    @Override
    public void setSubCategories(List<SubCategory> subCategories) {
        RecyclerView recyclerView = findViewById(R.id.subcategory_list);
        LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration( new LayoutMarginDecoration( 1,  AppConfig.convertDpToPx(getApplicationContext(), 10)) );
        SubCategoryAdapter adapter = new SubCategoryAdapter(this, subCategories, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onSubCategoryItemClick(SubCategory subCategory) {
        Intent intent = new Intent(this, ProductListingActivity.class);
        intent.putExtra("title", subCategory.getName());
        intent.putExtra("url", subCategory.getLinks().getProducts());
        startActivity(intent);
    }
}
