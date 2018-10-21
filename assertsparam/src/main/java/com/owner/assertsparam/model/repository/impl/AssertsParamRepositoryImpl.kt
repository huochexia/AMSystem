package com.owner.assertsparam.model.repository.impl

import com.owner.assertsparam.model.repository.AssertsParamRepository

/**
 * Created by Administrator on 2018/10/19 0019
 */
class AssertsParamRepositoryImpl  : AssertsParamRepository {

    companion object {
        lateinit var instance: AssertsParamRepositoryImpl
    }

    init {
        instance = AssertsParamRepositoryImpl()
    }


}