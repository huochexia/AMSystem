package com.owner.assertsparam.model.repository.impl

import com.owner.assertsparam.model.network.entites.createCgReq
import com.owner.assertsparam.model.network.entites.createCgResp
import com.owner.assertsparam.model.network.service.AssetsParamService
import com.owner.assertsparam.model.repository.AssertsParamRepository
import io.reactivex.Observable

/**
 * Created by Administrator on 2018/10/19 0019
 */
class APRepositoryImpl  : AssertsParamRepository {

    override fun createCategory(name: String, parentId: String, imageUrl: String):Observable<createCgResp> {
        return AssetsParamService.createCategory(createCgReq(name,parentId,imageUrl))
    }

}