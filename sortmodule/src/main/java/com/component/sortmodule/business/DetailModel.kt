package com.component.sortmodule.business

import com.appcomponent.base.refactorone.AbsRefactorBaseModel
import com.appcomponent.utils.ResponseTransformer
import com.appcomponent.utils.RxJavaUtils
import com.appcomponent.utils.RxLoading
import com.appcomponent.utils.StackManager
import com.component.sortmodule.SortService
import com.component.sortmodule.model.ArticleResponseBo
import com.polymerization.core.okhttp.NetUtils
import com.safframework.log.L
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer

class DetailModel(sortPresenter: DetailPresenter) : AbsRefactorBaseModel<DetailPresenter>(sortPresenter) {

    override fun requestToServer() {

    }

    override fun requestToServer(args: Any) {
        var params = args as Array<*>

        //调用其他
        if (params.size == 1) {
            addFavorite(params[0].toString())
            return
        }
        NetUtils.creatRequest(SortService::class.java).getDetailById(params[0].toString(), params[1].toString())
                .compose(RxJavaUtils.observableToMain())
                .compose(ResponseTransformer.handleResult())
                .subscribe(Consumer<ArticleResponseBo> {
                    basePresenter.serverResponse(it)
                }, Consumer<Throwable> {
                    L.d(it.message)
                }, Action {
                    L.d("网络请求完毕")
                }, Consumer<Disposable> {
                    addDisposable(it)
                })
    }

    fun addFavorite(para: String) {
        NetUtils.creatRequest(SortService::class.java).addFavorite(para)
                .compose(RxJavaUtils.observableToMain())
                .compose(ResponseTransformer.handleResult())
                .compose(RxLoading.applyProgressBar(StackManager.currentActivity()))
                .subscribe(Consumer {
                    basePresenter.serverResponse(it)
                }, Consumer<Throwable> {
                    L.d(it.message)
                }, Action {
                    L.d("网络请求完毕")
                }, Consumer<Disposable> {
                    addDisposable(it)
                })
    }
}