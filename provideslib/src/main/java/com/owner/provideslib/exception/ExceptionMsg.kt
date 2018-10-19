package com.owner.provideslib.exception

/**
 * Created by Administrator on 2018/10/12 0012
 */
object ExceptionMsg {
    fun getError(code: Int): String {
        when (code) {
            1 -> return "服务器内部错误或者参数错误"
            100 -> return "网络故障"
            101 -> return "对象不存在，或者密码不正确"
            102 -> return "无效查询"
            103 -> return "非法的 Class 名称"
            104 ->return "缺少 objectId或者 objectId 非法"
            105 ->return "无效的列名"
            106 ->return "无效的 Pointer 格式"
            107 ->return "无效的 JSON 对象，解析 JSON 数据失败"
            108 ->return "此 API 仅供内部使用"
            109 ->return "共享的 Class 无权限执行此操作，请检查 Class 共享的权限设置"
            111 ->return "想要存储的值不匹配列的类型"
            112 ->return "推送订阅的频道无效"
            115 ->return "Class 中的某个字段设定成必须，保存的对象缺少该字段"
            116 ->return "iOS 推送存储的 deviceToken 无效"
            117 ->return "更新的 Key 是只读属性"
            119 ->return "该操作无法从客户端发起"
            120 ->return "查询结果无法从缓存中找到"
            121 ->return "JSON 对象中 key 的名称不能包含 \$ 和 . 符号"
            122 ->return "无效的文件名称"
            123 ->return "ACL 格式错误"
            124 ->return " 请求超时"
            125 ->return "电子邮箱地址无效"
            126 ->return "无效的用户 Id，可能用户不存在"
            127 ->return "手机号码无效"
            137 ->return "无效的 Relation 数据"
            139 ->return "违反 class 中的唯一性索引约束"
            140 ->return "角色名称非法"
            141 ->return "云引擎调用超时"
            142 ->return "云引擎校验错误"
            153 ->return "文件删除错误"
            200 ->return "网络请求不成功"
            201 ->return "没有提供密码，或者密码为空"
            202 ->return "用户名已经被占用"
            203 ->return "电子邮箱地址已经被占用"
            204 ->return "没有提供电子邮箱地址"
            205 ->return "找不到电子邮箱地址对应的用户"
            206 ->return "没有提供 session，无法修改用户信息"
            207 ->return "只能通过注册创建用户，不允许第三方登录"
            208 ->return "第三方帐号已经绑定到一个用户，不可绑定到其他用户"
            209 ->return "用户Id不匹配"
            210 ->return "用户名和密码不匹配"
            211 ->return "找不到用户"
            212 ->return " 请提供正确手机号码"
            213 ->return "手机号码对应的用户不存在"
            214 ->return "手机号码已经被注册"
            215 ->return "未验证的手机号码"
            218-> return "无效的密码，不允许空白密码"
            219 ->return "登录失败次数超过限制，请稍候再试，或者通过忘记密码重设密码"
            250 ->return "连接的第三方账户没有返回用户唯一标示 id"
            251 ->return "无效的账户连接"
            252 ->return "无效的微信授权信息"
            603 -> return "无效的短信验证码"
            503 -> return "应用被临时禁用或者进入只读状态"
            1001-> return "两次密码不一致"
            1002-> return "网络不可用"
            else -> return "未知错误"
        }
    }
}