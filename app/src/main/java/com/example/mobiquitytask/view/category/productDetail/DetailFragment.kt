package com.example.mobiquitytask.view.category.productDetail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.mobiquitytask.BR
import com.example.mobiquitytask.R
import com.example.mobiquitytask.data.model.api.ProductsItem
import com.example.mobiquitytask.data.remote.ApiEndPoints
import com.example.mobiquitytask.databinding.FragmentProductDetailBinding
import com.example.mobiquitytask.utils.BindingUtils.setImageUrl
import com.example.mobiquitytask.view.ViewModelProviderFactory
import com.example.mobiquitytask.view.base.BaseFragment
import com.example.mobiquitytask.view.category.MainActivity
import com.example.mobiquitytask.view.category.MainViewModel
import javax.inject.Inject

class DetailFragment :
    BaseFragment<FragmentProductDetailBinding, DetailViewModel>(),
    DetailHelper {
    @Inject
    lateinit var factory: ViewModelProviderFactory
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var fragmentProductDetailBinding: FragmentProductDetailBinding

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_product_detail
    }

    override fun getViewModel(): DetailViewModel {
        return ViewModelProvider(this, factory).get(DetailViewModel::class.java)
            .apply { detailViewModel = this; setHelper(this@DetailFragment) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailViewModel.setHelper(this)
    }

    private fun bindViews() {
        arguments?.let {
            val productsItem: ProductsItem? = it.getParcelable(MainActivity.PRODUCT_KEY)
            productsItem?.let { product ->
                setImageUrl(
                    fragmentProductDetailBinding.fragmentDetailImage,
                    ApiEndPoints.BASE_IMAGE_URL.plus(product.url),
                    R.drawable.placeholder_image
                )
                fragmentProductDetailBinding.fragmentDetailName.text = product.name
                product.salePrice?.let { salePrice ->
                    fragmentProductDetailBinding.fragmentDetailPrice.text =
                        String.format("%s %s", salePrice.amount, salePrice.currency)
                }
            }
        }
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        fragmentProductDetailBinding = viewDataBinding
        bindViews()
    }

    override fun onCloseBtnClicked() {
        activity?.onBackPressed()
    }


    override fun showToast(message: String?) {
        super.showToast(message)
    }

    override fun showToast(messageStringRes: Int) {
        super.showToast(getString(messageStringRes))
    }

    override fun handleError(throwable: Throwable?) {
        showToast(throwable?.message)
    }

    companion object {
        val TAG = DetailFragment::class.java.simpleName
        fun newInstance(args: Bundle?): DetailFragment {
            val fragment = DetailFragment()
            fragment.arguments = args
            return fragment
        }
    }
}