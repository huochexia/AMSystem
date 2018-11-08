package com.owner.assertsparam.model.repository.impl

import com.owner.assertsparam.data.CategoryInfo
import com.owner.assertsparam.data.Manager
import com.owner.assertsparam.model.network.entites.QueryCategoryResp
import com.owner.assertsparam.model.network.entites.CreateCgReq
import com.owner.assertsparam.model.network.entites.QueryManagerResp
import com.owner.assertsparam.model.network.service.AssertsParamService
import com.owner.assertsparam.model.repository.AssertsParamRepository
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response

/**
 * Created by Administrator on 2018/10/19 0019
 */
class APRepositoryImpl : AssertsParamRepository {

    override fun getManager(): Observable<QueryManagerResp> {
        return AssertsParamService.getManager()
    }

    override fun getCategory(parentId: String): Observable<QueryCategoryResp> {
        val query="""{"parentId":"$parentId"}"""
        return AssertsParamService.getCategory(query)
    }

    override fun createCategory(name: String, parentId: String, imageUrl: String): Observable<CategoryInfo> {
        return AssertsParamService.createCategory(CreateCgReq(name, parentId, imageUrl))
    }

    override fun deleteCategory(objectId: String) :Observable<ResponseBody>{
        return AssertsParamService.deleteCategory(objectId)
    }
    override fun updateCategory(category: CategoryInfo): Observable<ResponseBody> {
        return AssertsParamService.updateCategory(category.objectId,category)
    }

}