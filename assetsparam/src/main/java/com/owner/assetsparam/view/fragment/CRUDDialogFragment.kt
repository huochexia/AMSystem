/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.owner.assetsparam.view.fragment

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.content.Intent
import android.databinding.ViewDataBinding
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
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
import com.owner.assetsparam.R
import com.owner.assetsparam.data.CategoryInfo
import com.owner.assetsparam.databinding.LayoutImageOfCategoryBinding
import com.owner.baselibrary.ext.enabled
import com.owner.baselibrary.ext.loadUrl
import com.owner.baselibrary.utils.DateUtils
import com.owner.baselibrary.utils.hideSoftInput
import com.owner.baselibrary.view.fragment.BaseFragment
import com.owner.baselibrary.viewmodel.BaseViewModel
import org.jetbrains.anko.find
import java.io.File

/**
 * 用于弹出增改删操作的对话框，初始化了TakePhoto组件，只要需要这些操作的Fragment就需要继承该类
 * Created by Liuyong on 2018-11-21.It's AMSystem
 *@description:
 */
open class CRUDDialogFragment<T : ViewDataBinding, B : BaseViewModel<*>> : BaseFragment<T, B>(),
        TakePhoto.TakeResultListener, InvokeListener {

    //临时分类对象
    lateinit var tempCategory: CategoryInfo
    var mCategoryImage = MutableLiveData<String>()

    private lateinit var mTakePhoto: TakePhoto
    private lateinit var mTempFile: File
    private lateinit var invokeParam: InvokeParam

    private lateinit var mAlertView: AlertView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTakePhoto(savedInstanceState)
    }

    /**
     * 初始化TakePhoto组件
     */
    private fun initTakePhoto(savedInstanceState: Bundle?) {
        mTakePhoto = TakePhotoInvocationHandler.of(this)
                .bind(TakePhotoImpl(this, this))
                as TakePhoto
        mTakePhoto.onCreate(savedInstanceState)
    }

    override fun takeSuccess(result: TResult?) {
        //1、利用压缩文件生成AVFile
        val file = AVFile.withAbsoluteLocalPath("${DateUtils.curTime}.png", result?.image?.compressPath)
        //2、上传AVFile对象
        file.saveInBackground(object : SaveCallback() {
            override fun done(p0: AVException?) {
                mCategoryImage.value = file.url
                tempCategory.imageUrl = file.url
            }
        })
    }

    override fun takeCancel() {

    }

    override fun takeFail(result: TResult?, msg: String?) {

    }


    override fun onSaveInstanceState(outState: Bundle) {
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
        PermissionManager.handlePermissionsResult(activity, type, invokeParam, this)
    }

    override fun invoke(invokeParam: InvokeParam): PermissionManager.TPermissionType {
        val type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.method)
        if (PermissionManager.TPermissionType.WAIT == type) {
            this.invokeParam = invokeParam
        }
        return type
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
        this.mTempFile = File(activity?.filesDir, tempFileName)
    }

    /**
     * 初始化对话框内容
     */
    private fun initDialog(hasImage: Boolean): Pair<View, EditText> {
        val binding = LayoutImageOfCategoryBinding.inflate(layoutInflater, null)
        binding.mFragment = this
        val editView = LayoutInflater.from(context).inflate(R.layout.layout_image_of_category, null)

        val imageView = editView.find<ImageView>(R.id.mPictureIv)
        imageView.visibility = if (hasImage) View.VISIBLE else View.GONE
        mCategoryImage.observe(this, Observer {
            if (it.isNullOrEmpty()) {
                imageView.setImageResource(R.drawable.pictures_no)
            } else {
                imageView.loadUrl(it!!)
            }
        })
        val getPhotoLL = editView.findViewById<LinearLayout>(R.id.mGetPhotoll)
        getPhotoLL.visibility = if (hasImage) View.VISIBLE else View.GONE
        val editV = editView.findViewById<EditText>(R.id.mThirdCgNameEt)

        val takePhoto = editView.findViewById<Button>(R.id.mPictureBtn)
        takePhoto.enabled(editV) { !editV.text.isNullOrEmpty() }

        val camera = editView.findViewById<Button>(R.id.mCameraBtn)
        camera.enabled(editV) { !editV.text.isNullOrEmpty() }

        camera.setOnClickListener {
            mTakePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(), false)
            createTempFile()
            mTakePhoto.onPickFromCapture(Uri.fromFile(mTempFile))
        }
        takePhoto.setOnClickListener {
            mTakePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(), false)
            mTakePhoto.onPickFromGallery()
        }
        return Pair(editView, editV)
    }


    /**
     * 弹出增加窗口
     * @title 标题
     * @parent 上一级
     * @hasImage 是否有图片
     * @action 确认独立行为
     */
    fun popupAddDialog(parent: CategoryInfo, hasImage: Boolean,
                       confirm: (CategoryInfo) -> Unit) {
        //因为拍照或相册操作成功后，会把imageUrl先写入tempCategory当中
        //为了防止将其他操作保存在tempCategory中的imageUrl写入这个parent当中，所以先进行清空处理
        tempCategory = CategoryInfo("", "")
        val (editView, editV) = initDialog(hasImage)
        mCategoryImage.value = parent.imageUrl
        mAlertView = AlertView("增加操作", null, null, null,
                arrayOf("取消", "完成"), context, AlertView.Style.Alert, OnItemClickListener { _, position ->
            activity?.hideSoftInput()
            when (position) {
                1 -> {
                    if (editV.text.isNullOrEmpty().not()) {
                        tempCategory.name = editV.text.toString().trim()
                        tempCategory.parentId = parent.objectId
                        //设置父类的hasChild为true
                        parent.hasChild = true
                        confirm(tempCategory)
                    }
                }
            }
        })
        mAlertView.addExtView(editView).show()
    }

    /**
     * 修改窗口
     */
    fun popupUpdateDialog(category: CategoryInfo,hasImage:Boolean, action: (CategoryInfo) -> Unit) {
        tempCategory = CategoryInfo("", "")
        val (editView, editV) = initDialog(hasImage)
        editV.setText(category.name)
        mCategoryImage.value = category.imageUrl
        mAlertView = AlertView("修改操作", null, null, null,
                arrayOf("取消", "完成"), context, AlertView.Style.Alert, OnItemClickListener { _, position ->
            activity?.hideSoftInput()
            when (position) {
                1 -> {
                    category.name = editV.text.toString().trim()
                    //要判一下图片是否发生改变,不为空说明进行了图片操作
                    if (tempCategory.imageUrl != "")
                        category.imageUrl = tempCategory.imageUrl
                    action(category)
                }
            }
        })
        mAlertView.addExtView(editView).show()
    }

    /**
     * 删除窗口
     */
    fun popupDeleteDialog(title: String, category: CategoryInfo, confirm: (CategoryInfo) -> Unit) {
        mAlertView = AlertView(title, null, null, null,
                arrayOf("取消", "确定"), context, AlertView.Style.Alert,
                OnItemClickListener { _, position ->
                    activity?.hideSoftInput()
                    when (position) {
                        1 -> {
                            confirm(category)
                        }
                    }
                })
        mAlertView.show()
    }
}