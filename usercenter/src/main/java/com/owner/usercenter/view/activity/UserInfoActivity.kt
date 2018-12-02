package com.owner.usercenter.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import com.alibaba.android.arouter.facade.annotation.Route
import com.avos.avoscloud.AVException
import com.avos.avoscloud.AVFile
import com.avos.avoscloud.SaveCallback
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.app.TakePhotoImpl
import com.jph.takephoto.compress.CompressConfig
import com.jph.takephoto.model.InvokeParam
import com.jph.takephoto.model.TContextWrap
import com.jph.takephoto.model.TResult
import com.jph.takephoto.permission.InvokeListener
import com.jph.takephoto.permission.PermissionManager
import com.jph.takephoto.permission.TakePhotoInvocationHandler
import com.owner.baselibrary.common.BaseConstant
import com.owner.baselibrary.ext.loadWithGlide
import com.owner.baselibrary.utils.AppPrefsUtils
import com.owner.baselibrary.utils.DateUtils
import com.owner.baselibrary.utils.NetWorkUtils
import com.owner.baselibrary.view.activity.BaseActivity
import com.owner.provideslib.common.ProviderConstant
import com.owner.provideslib.common.isLogined
import com.owner.provideslib.exception.ExceptionMsg
import com.owner.provideslib.router.RouterPath
import com.owner.usercenter.R
import com.owner.usercenter.common.UserConstant
import com.owner.usercenter.databinding.ActivityUserInfoBinding
import com.owner.usercenter.viewmodel.UserInfoViewModel
import kotlinx.android.synthetic.main.activity_user_info.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.io.File

/**
 * 对头像图片文件的上传使用的是LeanCloud提供的SDK[AVFile],对用户信息的更新即文件地址保存采用
 * 的是RestApi
 */
@Route(path = RouterPath.UserCenter.PATH_USER_INFO)
class UserInfoActivity : BaseActivity<ActivityUserInfoBinding, UserInfoViewModel>(),
        TakePhoto.TakeResultListener, InvokeListener {

    private lateinit var mTakePhoto: TakePhoto
    private lateinit var mTempFile: File
    private lateinit var invokeParam: InvokeParam
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        mTakePhoto = TakePhotoInvocationHandler.of(this)
                .bind(TakePhotoImpl(this, this))
                as TakePhoto
        mTakePhoto.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UserInfoViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_info)
        binding.infovm = viewModel
        initView()
    }

    private fun initView() {
        if (!NetWorkUtils.isNetWorkAvailable(this)) {
            toast(ExceptionMsg.getError(UserConstant.NET_NO))
        }
        mUserAvatarIv.setOnClickListener {
            //如果是登录状态，则允许修改头像
            if (isLogined()) {
                showAlertView()
            } else {
                showLoginAlert()
            }

        }
        viewModel.avatar.observe(this, Observer {
            if (!isLogined()) {
                mUserAvatarIv.setImageResource(R.drawable.icon_default_user)
            } else {
                val username = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NAME)
                mUserAvatarIv.loadWithGlide(it,username.first())
            }
        })
    }

    /**
     * 显示是否登录对话框
     */
    private fun showLoginAlert() {
        AlertView("您尚未登录，是否登录？", null, null, arrayOf("确定", "取消"), null, this,
                AlertView.Style.Alert, OnItemClickListener { _, position ->
            when (position) {
                0 -> startActivity<LoginActivity>()
            }

        }).show()
    }

    /**
     * 修改头像
     */
    private fun showAlertView() {
        AlertView("选择图片", null, "取消", null, arrayOf("拍照", "相册"), this,
                AlertView.Style.ActionSheet, OnItemClickListener { _, position ->
            mTakePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(), false)
            when (position) {
                0 -> {
                    createTempFile()
                    mTakePhoto.onPickFromCapture(Uri.fromFile(mTempFile))
                }
                1 -> mTakePhoto.onPickFromGallery()
            }
        }).show()
    }

    private fun updateAvatar(token: String, userId: String, avatarUrl: String) {
        viewModel.updateAvatar(token, userId, avatarUrl)
                .compose(bindToLifecycle())
                .subscribe({
                    if (!it.isSuccess()) {
                        toast(it.error.toString())
                    }
                }, {
                    toast(it.message.toString())
                })
    }
    /**
     * TakeResultListener的方法，获取TakePhoto相关方法的结果
     */
    override fun takeSuccess(result: TResult?) {
        //1、利用压缩文件生成AVFile
        val file = AVFile.withAbsoluteLocalPath("${DateUtils.curTime}.png",result?.image?.compressPath)
        //2、上传AVFile对象
        file.saveInBackground(object :SaveCallback(){
            override fun done(p0: AVException?) {
                //3、将返回的头像Url保存本地
                AppPrefsUtils.putString(ProviderConstant.KEY_SP_USER_ICON,file.url.toString())
                //4、更新用户信息，将头像信息保存至用户信息中
                updateAvatar(AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN),
                        AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ID), file.url.toString())

            }

        })

    }

    override fun takeCancel() {

    }

    override fun takeFail(result: TResult?, msg: String?) {

    }

    override fun onSaveInstanceState(outState: Bundle?) {
        //TakePhoto的要求
        mTakePhoto.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //TakePhoto的要求
        mTakePhoto.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    /**
     * TakePhoto对权限设置针对6.0 和7.0版动态权限的获取
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this)
    }

    /**
     * InvokeListener的方法
     */
    override fun invoke(invokeParam: InvokeParam): PermissionManager.TPermissionType {
        val type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.method)
        if (PermissionManager.TPermissionType.WAIT == type) {
            this.invokeParam = invokeParam
        }
        return type
    }


    /**
     * 获取TakePhoto
     */
    fun getTakePhoto(): TakePhoto {
        return mTakePhoto
    }

    /**
     * 为照像创建临时文件
     */
    private fun createTempFile() {
        val tempFileName = "${DateUtils.curTime}.png"
        if (Environment.MEDIA_MOUNTED == (Environment.getExternalStorageState())) {
            this.mTempFile = File(Environment.getExternalStorageDirectory(), tempFileName)
            return
        }
        this.mTempFile = File(filesDir, tempFileName)
    }


}
