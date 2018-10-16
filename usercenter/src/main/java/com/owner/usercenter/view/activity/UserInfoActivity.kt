package com.owner.usercenter.view.activity

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
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
import com.orhanobut.logger.Logger
import com.owner.baselibrary.ext.execute
import com.owner.baselibrary.utils.DateUtils
import com.owner.baselibrary.utils.NetWorkUtils
import com.owner.baselibrary.view.activity.BaseActivity
import com.owner.provideslib.exception.ExceptionMsg
import com.owner.provideslib.router.RouterPath
import com.owner.usercenter.R
import com.owner.usercenter.common.UserConstant
import com.owner.usercenter.databinding.ActivityUserInfoBinding
import com.owner.usercenter.viewmodel.UserInfoViewModel
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_user_info.*
import org.jetbrains.anko.toast
import java.io.File


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
            showAlertView()
        }
    }

    /**
     * 显示选择图片对话框
     */
    private fun showAlertView() {
        AlertView("选择图片", "", "取消", null, arrayOf("拍照", "相册"), this,
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
     * TakeResultListener的方法，获取TakePhoto相关方法的结果
     */
    override fun takeSuccess(result: TResult?) {
        Logger.d("TakePhoto", result?.image?.originalPath)
        Log.d("TakePhoto_comp", result?.image?.compressPath)
        viewModel.uploadAvatar(File(result?.image?.compressPath)).execute()
                .subscribeBy {
                    if (it.isSuccessful) {
                        val avatar = it.body()
                        Log.d("avatar:", avatar?.url)
                    } else {

                    }
                }
    }

    override fun takeCancel() {

    }

    override fun takeFail(result: TResult?, msg: String?) {

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
    fun createTempFile() {
        val tempFileName = "${DateUtils.curTime}.png"
        if (Environment.MEDIA_MOUNTED == (Environment.getExternalStorageState())) {
            this.mTempFile = File(Environment.getExternalStorageDirectory(), tempFileName)
            return
        }
        this.mTempFile = File(filesDir, tempFileName)
    }


}
