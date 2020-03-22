package com.example.mobiquitytask.view.category.productDetail

import com.example.mobiquitytask.data.manager.app.IAppDataManager
import com.example.mobiquitytask.utils.rx.SchedulerProvider
import com.example.mobiquitytask.view.base.BaseViewModel

class DetailViewModel(
    iAppDataManager: IAppDataManager,
    mSchedulerProvider: SchedulerProvider
) : BaseViewModel<DetailHelper?>(iAppDataManager, mSchedulerProvider) {
    fun onDetailCloseBtnClicked() {
        helper?.onCloseBtnClicked()
    }
}