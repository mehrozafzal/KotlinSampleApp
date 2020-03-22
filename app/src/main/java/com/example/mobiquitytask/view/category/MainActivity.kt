package com.example.mobiquitytask.view.category

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils.loadLayoutAnimation
import android.view.animation.LayoutAnimationController
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobiquitytask.BR
import com.example.mobiquitytask.R
import com.example.mobiquitytask.data.model.api.ProductsItem
import com.example.mobiquitytask.data.model.api.RestaurantResponse
import com.example.mobiquitytask.databinding.ActivityMainBinding
import com.example.mobiquitytask.view.ViewModelProviderFactory
import com.example.mobiquitytask.view.base.BaseActivity
import com.example.mobiquitytask.view.category.productDetail.DetailFragment
import com.google.android.material.animation.AnimationUtils
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(),
    MainHelper, HasSupportFragmentInjector {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var mainViewModel: MainViewModel
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var categorySpinnerAdapter: CategorySpinnerAdapter
    private val resId: Int = R.anim.layout_animation
    private lateinit var animation: LayoutAnimationController

    companion object {
        const val PRODUCT_KEY = "productKey";
        fun getIntent(activity: AppCompatActivity): Intent {
            return Intent(activity, MainActivity::class.java)
        }
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModel(): MainViewModel {
        return ViewModelProvider(this, factory).get(MainViewModel::class.java)
            .apply { mainViewModel = this; setHelper(this@MainActivity) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = viewDataBinding
        bindViews()
    }

    private fun bindViews() {
        animation = loadLayoutAnimation(this, resId)
        mainViewModel.fetchCategoryList()
        observeCategoryList()
    }

    private fun observeCategoryList() {
        mainViewModel.restaurantLiveData.observe(this, androidx.lifecycle.Observer {
            categorySpinnerAdapter = CategorySpinnerAdapter(this, R.layout.item_cateogy_spinner, it)
            activityMainBinding.activityMainContent.activityMainSpinner.adapter =
                categorySpinnerAdapter
        })

        activityMainBinding.activityMainContent.activityMainSpinner.onItemSelectedListener =
            object : OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    val restaurantResponse: RestaurantResponse =
                        parent.getItemAtPosition(position) as RestaurantResponse
                    onCategoryItemSelected(restaurantResponse)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    private fun populateRestaurantCategoryList(productList: List<ProductsItem>) {
        activityMainBinding.activityMainContent.activityMainRecyclerView.layoutManager =
            linearLayoutManager
        activityMainBinding.activityMainContent.activityMainRecyclerView.layoutAnimation = animation
        activityMainBinding.activityMainContent.activityMainRecyclerView.adapter =
            ProductAdapter(
                this,
                productList,
                this
            )
    }

    fun onCategoryItemSelected(response: RestaurantResponse) {
        response.products?.let { populateRestaurantCategoryList(it) }
    }

    override fun onCategoryItemSelected(productsItem: ProductsItem) {
        val bundle = Bundle()
        bundle.putParcelable(PRODUCT_KEY, productsItem)
        val detailFragment = DetailFragment.newInstance(bundle)
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(DetailFragment.TAG)
            .setCustomAnimations(
                R.anim.slide_in_up,
                0,
                0,
                R.anim.slide_out_down
            )
            .replace(R.id.activityMain_container, detailFragment, DetailFragment.TAG)
            .commit()
    }

    override fun showToast(messageStringRes: Int) {
        super.showToast(getString(messageStringRes))
    }

    override fun showToast(message: String?) {
        super.showToast(message)
    }

    override fun handleError(throwable: Throwable?) {
        super.showToast(throwable?.message)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0)
            supportFragmentManager.popBackStackImmediate()
        else
            super.onBackPressed()
    }
}
