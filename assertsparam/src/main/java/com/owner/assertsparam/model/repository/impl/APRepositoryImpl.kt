package com.owner.assertsparam.model.repository.impl

import com.owner.assertsparam.data.CategoryInfo
import com.owner.assertsparam.model.network.entites.GetCategoryList
import com.owner.assertsparam.model.network.entites.CreateCgReq
import com.owner.assertsparam.model.network.entites.QueryManagerResp
import com.owner.assertsparam.model.network.service.AssertsParamService
import com.owner.assertsparam.model.repository.AssertsParamRepository
import io.reactivex.Completable
import io.reactivex.Observable
import okhttp3.ResponseBody

/**
 * Created by Administrator on 2018/10/19 0019
 */
class APRepositoryImpl : AssertsParamRepository {
    /*
        获取管理员
     */
    override fun getManager(): Observable<QueryManagerResp> {
        return AssertsParamService.getManager()
    }
    /*
       获取分类列表，得到是各种分类的超类列表
     */
    override fun getCategory(className:String,parentId: String): Observable<GetCategoryList> {
        val query="""{"parentId":"$parentId"}"""
        return AssertsParamService.getCategory(className,query)
    }
    /*
      生成分类
     */
    override fun createCategory(className:String,name: String, parentId: String, imageUrl: String): Observable<CategoryInfo> {
        return AssertsParamService.createCategory(className,CreateCgReq(name, parentId, imageUrl))
    }
    /*
       删除分类
     */
    override fun deleteCategory(className: String,objectId: String) :Completable{
        return AssertsParamService.deleteCategory(className,objectId)
    }
    /*
       更新分类
     */
    override fun updateCategory(className: String,category: CategoryInfo): Completable {
        return AssertsParamService.updateCategory(className,category.objectId,category)
    }

}