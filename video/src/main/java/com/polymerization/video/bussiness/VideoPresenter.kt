package com.polymerization.video.bussiness

import com.appcomponent.base.BaseModel
import com.appcomponent.base.refactorone.RefactorPresenter1


class VideoPresenter(mview: VideoView) : RefactorPresenter1<VideoView>(mview) {

    override fun serverResponse(data: Any) {
        mBaseView.showDataSuccess(data)
    }


    override fun requestServer(vararg param: Any) {
       mBaseModel.requestToServer(param)
    }

    override fun requestSuccess(responseJson: String) {
    }

    override fun getModel(): BaseModel {
        return VideoModel(this)
    }


}