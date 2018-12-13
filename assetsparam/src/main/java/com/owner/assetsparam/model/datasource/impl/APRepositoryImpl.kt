package com.owner.assetsparam.model.datasource.impl

import com.owner.assetsparam.data.CategoryInfo
import com.owner.assetsparam.data.Manager
import com.owner.assetsparam.model.network.entites.GetCategoryList
import com.owner.assetsparam.model.network.entites.CreateCgReq
import com.owner.assetsparam.model.network.entites.QueryManagerResp
import com.owner.assetsparam.model.network.service.AssertsParamService
import com.owner.assetsparam.model.datasource.AssetsParamRepository
import io.reactivex.Completable
import io.reactivex.Observable

/**
 * Created by Administrator on 2018/10/19 0019
 */
class APRepositoryImpl : AssetsParamRepository {
    /*
      获取某个管理员
     */
    override fun getManager(userId: String): Observable<Manager> {
        return AssertsParamService.getManager(userId)
    }

    /*
        获取所有管理员
     */
    override fun getAllManager(): Observable<QueryManagerResp> {
        return AssertsParamService.getAllManager()
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