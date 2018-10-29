package com.owner.assertsparam.model.repository

import com.owner.assertsparam.data.CategoryInfo
import com.owner.assertsparam.model.network.entites.CreateCgResp
import com.owner.baselibrary.model.respository.BaseRepository
import io.reactivex.Observable
import retrofit2.Response

/**
 *
 * Created by Administrator on 2018/10/19 0019
 */
interface AssertsParamRepository:BaseRepository {
    /*
      创建分类
     */
    fun createCategory(name:String,parentId:String,imageUrl:String):Observable<CreateCgResp>
    /*
      按父类ID查找子分类
     */
    fun getCategory(parentId: String):Observable<Response<List<CategoryInfo>?>>
}