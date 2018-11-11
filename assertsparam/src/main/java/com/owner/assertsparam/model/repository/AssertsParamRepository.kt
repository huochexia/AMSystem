package com.owner.assertsparam.model.repository

import com.owner.assertsparam.data.CategoryInfo
import com.owner.assertsparam.data.Manager
import com.owner.assertsparam.model.network.entites.GetCategoryList
import com.owner.assertsparam.model.network.entites.QueryManagerResp
import com.owner.baselibrary.model.respository.BaseRepository
import io.reactivex.Completable
import io.reactivex.Observable
import okhttp3.ResponseBody

/**
 *
 * Created by Administrator on 2018/10/19 0019
 */
interface AssertsParamRepository : BaseRepository {
    /*
      创建分类
     */
    fun createCategory(className:String,name: String, parentId: String, imageUrl: String): Observable<CategoryInfo>

    /*
      按父类ID查找子分类
     */
    fun getCategory(className: String,parentId: String): Observable<GetCategoryList>


    /*
     删除分类
     */
    fun deleteCategory(className: String,objectId: String): Completable

    /*
      更新分类
     */
    fun updateCategory(className: String,category:CategoryInfo): Completable
    /*
         获取管理人员列表
        */
    fun getAllManager(): Observable<QueryManagerResp>
    /*
        获取某个管理人员
     */
    fun getManager(userId:String):Observable<Manager>
}