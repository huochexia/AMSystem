package com.owner.usercenter.model.network.entities

/**
 * 修改头像
 * Created by Administrator on 2018/10/17 0017
 */
data class UpdateAvatarReq(val avatar: String)

data class UpdateAvatarResp(
        val code: Int = 0,
        val error: String?,
        val updateAt: String?) {
    fun isSuccess(): Boolean = code == 0 && updateAt.isNullOrEmpty().not()
}