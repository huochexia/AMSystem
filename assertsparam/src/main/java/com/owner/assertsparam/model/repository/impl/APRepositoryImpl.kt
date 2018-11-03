package com.owner.assertsparam.model.repository.impl

import com.owner.assertsparam.data.CategoryInfo
import com.owner.assertsparam.data.Manager
import com.owner.assertsparam.model.network.entites.CategoryList
import com.owner.assertsparam.model.network.entites.CreateCgReq
import com.owner.assertsparam.model.network.entites.CreateCgResp
import com.owner.assertsparam.model.network.service.AssertsParamService
import com.owner.assertsparam.model.repository.AssertsParamRepository
import io.reactivex.Observable

/**
 * Created by Administrator on 2018/10/19 0019
 */
class APRepositoryImpl : AssertsParamRepository {
    override fun getManager(): MutableList<Manager> {
        val mManagerList = mutableListOf<Manager>()
        // 根据a-z进行排序

        val user1 = Manager("张三", "客房部", phone = "18932902193")
        val user2 = Manager("李三", "客房部", phone = "18932902193")
        val user3 = Manager("王三", "客房部", phone = "18932902193")
        val user4 = Manager("刘二", "客房部", phone = "18932902193")
        val user5 = Manager("刘三", "工程", phone = "18932902193")
        val user6 = Manager("赵四", "会服部", phone = "18932902193")
        val user7 = Manager("陈小", "客房部", phone = "18932902193")
        val user8 = Manager("张三", "客房部", phone = "18932902193")
        val user9 = Manager("张三", "客房部", phone = "18932902193")
        val user10 = Manager("罗二", "客房部", phone = "18932902193")
        val user11 = Manager("孙三", "客房部", phone = "18932902193")
        val user12 = Manager("安三", "客房部", phone = "18932902193")
        val user13 = Manager("安安", "客房部", phone = "18932902193")
        val user14 = Manager("潘三", "客房部", phone = "18932902193")
        mManagerList.add(user1)
        mManagerList.add(user2)
        mManagerList.add(user3)
        mManagerList.add(user4)
        mManagerList.add(user5)
        mManagerList.add(user6)
        mManagerList.add(user7)
        mManagerList.add(user8)
        mManagerList.add(user9)
        mManagerList.add(user10)
        mManagerList.add(user11)
        mManagerList.add(user12)
        mManagerList.add(user13)
        mManagerList.add(user14)
        return mManagerList
    }

    override fun getCategory(parentId: String): Observable<CategoryList> {
        return AssertsParamService.getCategory("{\"parentId\":\"$parentId\"}")
    }

    override fun createCategory(name: String, parentId: String, imageUrl: String): Observable<CategoryInfo> {
        return AssertsParamService.createCategory(CreateCgReq(name, parentId, imageUrl))
    }

}