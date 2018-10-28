package com.owner.assertsparam.model.repository

import com.owner.assertsparam.model.network.entites.createCgResp
import com.owner.baselibrary.model.respository.BaseRepository
import io.reactivex.Observable

/**
 *
 * Created by Administrator on 2018/10/19 0019
 */
interface AssertsParamRepository:BaseRepository {
    /*
      创建分类
     */
    fun createCategory(name:String,parentId:String,imageUrl:String):Observable<createCgResp>
}