package com.owner.assertsparam.model.repository.impl

import com.owner.assertsparam.data.CategoryInfo
import com.owner.assertsparam.model.network.entites.CreateCgReq
import com.owner.assertsparam.model.network.entites.CreateCgResp
import com.owner.assertsparam.model.network.entites.GetCategoryList
import com.owner.assertsparam.model.network.service.AssertsParamService
import com.owner.assertsparam.model.repository.AssertsParamRepository
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.adapter.rxjava2.Result

/**
 * Created by Administrator on 2018/10/19 0019
 */
class APRepositoryImpl  : AssertsParamRepository {
    override fun getCategory(parentId: String): Observable<GetCategoryList> {
        return AssertsParamService.getCategory(parentId)
    }

    override fun createCategory(name: String, parentId: String, imageUrl: String):Observable<CreateCgResp> {
        return AssertsParamService.createCategory(CreateCgReq(name,parentId,imageUrl))
    }

}