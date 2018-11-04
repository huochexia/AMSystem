package com.owner.assertsparam.model.repository

import com.owner.assertsparam.data.CategoryInfo
import com.owner.assertsparam.data.Manager
import com.owner.assertsparam.model.network.entites.QueryCategoryResp
import com.owner.baselibrary.model.respository.BaseRepository
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response

/**
 *
 * Created by Administrator on 2018/10/19 0019
 */
interface AssertsParamRepository:BaseRepository {
    /*
      创建分类
     */
    fun createCategory(name:String,parentId:String,imageUrl:String):Observable<CategoryInfo>
    /*
      按父类ID查找子分类
     */
    fun getCategory(parentId: String):Observable<QueryCategoryResp>

    /*
      获取管理人员列表
     */
    fun getManager(): MutableList<Manager>

    /*
     删除分类
     */
    fun deleteCategory(objectId:String):Observable<ResponseBody>
    /*
      更新分类
     */
    fun updateCategory(category:CategoryInfo):Observable<ResponseBody>

}