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
package com.owner.assertsparam.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
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
import com.owner.assertsparam.R
import com.owner.assertsparam.data.CategoryInfo
import com.owner.assertsparam.data.Footer
import com.owner.assertsparam.databinding.FragementCategoryBinding
import com.owner.assertsparam.view.adapter.SecondCgAdapter
import com.owner.assertsparam.view.adapter.TopCgAdapter
import com.owner.assertsparam.viewmodel.CategoryFgViewModel
import com.owner.baselibrary.ext.loadUrl
import com.owner.baselibrary.utils.DateUtils
import com.owner.baselibrary.utils.hideSoftInput
import com.owner.baselibrary.view.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragement_category.*
import org.jetbrains.anko.find
import java.io.File

/**
 *
 * Created by Liuyong on 2018-10-20.It's AMSystem
 *@description:
 */
class CategoryFragment : BaseFragment<FragementCategoryBinding, CategoryFgViewModel>(),
        TakePhoto.TakeResultListener, InvokeListener {


    private lateinit var topAdapter: TopCgAdapter
    private lateinit var secondAdapter: SecondCgAdapter
    private lateinit var alertView: AlertView
    private lateinit var mTakePhoto: TakePhoto
    private lateinit var mTempFile: File
    private lateinit var invokeParam: InvokeParam
    //定义总资产分类对象
    private val category=CategoryInfo("0","资产分类")
    //当前选择的一级分类
    private lateinit var currentTopCategory : CategoryInfo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CategoryFgViewModel::class.java)

        //观察状态信息的变化，做出相应的响应
        viewModel.action.observe(this, Observer {
            //如果parentId为空，则是一级分类
            executeAction(it!!)
        })
        viewModel.refreshList.observe(this, Observer {
            when (it?.second) {
                0 ->topAdapter.notifyDataSetChanged()
                1-> secondAdapter.notifyDataSetChanged()
            }
        })
        //观察是否展开显示更多三级分类状态
        viewModel.expandList.observe(this, Observer {
            secondAdapter.notifyDataSetChanged()
        })
        mTakePhoto = TakePhotoInvocationHandler.of(this)
                .bind(TakePhotoImpl(this, this))
                as TakePhoto
        mTakePhoto.onCreate(savedInstanceState)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding = DataBindingUtil.inflate<FragementCategoryBinding>(
                inflater, R.layout.fragement_category, container, false)
        binding.categoryVM = viewModel
        //绑定总资产对象
        binding.category = category
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTopCgList()
        initSecondCgList(category)
    }

    /**
     *对由ViewModel发生的事件进行筛分，对应处理
     */
    private fun executeAction(it: Pair<String, CategoryInfo>) {

        //判断是否是一级分类
        if (it.second!!.parentId=="") {
            topAdapter.notifyDataSetChanged()
            initSecondCgList(it.second)
            currentTopCategory = it.second
        } else {
            secondAdapter.notifyDataSetChanged()
        }
        when (it.first) {
            CategoryFgViewModel.KEY_UPDATE_ACTION -> updateCategory(it.second )
            CategoryFgViewModel.KEY_DELETE_ACTION -> deleteCategory(it.second )
            CategoryFgViewModel.KEY_ADD_ACTION -> addCategory(it.second)
            CategoryFgViewModel.KEY_ADD_THIRD_ACTION->addThirdCategory(it.second)
            CategoryFgViewModel.KEY_UPDATE_THIRD_ACTION-> updateThirdCategory(it.second)
        }
    }

    /**
     * 初始化一级列表
     */
    private fun initTopCgList() {
        mTopCategoryRv.layoutManager = LinearLayoutManager(context)
        topAdapter = TopCgAdapter(viewModel)
        mTopCategoryRv.adapter = topAdapter
    }

    /**
     * 初始化二级分类列表
     */
    private fun initSecondCgList(category: CategoryInfo) {
        mSecondCategoryRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        secondAdapter = SecondCgAdapter(category, viewModel)
        mSecondCategoryRv.adapter = secondAdapter
    }

    /**
     * 增加类别，在这里生成一个新子类。这里形成比在ViewModel中放便些
     * @parent:父类
     *
     */
    private fun addCategory(parent: CategoryInfo) {
        val extView = LayoutInflater.from(context).inflate(R.layout.edit_category_name, null)
        val editV = extView.findViewById<EditText>(R.id.mCgNameEt)
        alertView = AlertView("增加类别", null, null, null,
                arrayOf("取消", "完成"), context, AlertView.Style.Alert, OnItemClickListener { o, position ->
            activity?.hideSoftInput()
            when (position) {
               1 -> {
                   val name = editV.text.toString()
                   val newCg = CategoryInfo("",name,parent.objectId)
                   viewModel.addCategory(newCg)
               }

            }
        })

        alertView.addExtView(extView).show()
    }

    /**
     * 增加三级分类
     */
    private fun addThirdCategory(parent: CategoryInfo) {
        //刷新列表，目的是取消之前做过的长按状态
        secondAdapter.notifyDataSetChanged()

        alertView = AlertView("增加类别",null,null,null,
                arrayOf("取消","完成"),context,AlertView.Style.Alert, OnItemClickListener{
                 o, position ->
                    activity?.hideSoftInput()
            when (position) {
                0 ->{}
                1->{}
            }
        })
        val editView = LayoutInflater.from(context).inflate(R.layout.layout_add_third_category,null)
        val imageView = editView.find<ImageView>(R.id.mPictureIv)
        val editV = editView.find<EditText>(R.id.mThirdCgNameEt)
        val takePhoto = editView.find<Button>(R.id.mPictureBtn)
        val camera = editView.find<Button>(R.id.mCameraBtn)
        camera.setOnClickListener{
            mTakePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(), false)
            createTempFile()
            mTakePhoto.onPickFromCapture(Uri.fromFile(mTempFile))
        }
        takePhoto.setOnClickListener {
            mTakePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(), false)
            mTakePhoto.onPickFromGallery()
        }
        alertView.addExtView(editView).show()

    }
    /**
     * 修改三级分类
     */
    private fun updateThirdCategory(category: CategoryInfo) {
        //刷新列表，目的是取消之前做过的长按状态
        secondAdapter.notifyDataSetChanged()

        alertView = AlertView("修改类别",null,null,null,
                arrayOf("取消","完成"),context,AlertView.Style.Alert, OnItemClickListener{
                 o, position ->
                    activity?.hideSoftInput()
            when (position) {
                0 ->{}
                1->{}
            }
        })
        val editView = LayoutInflater.from(context).inflate(R.layout.layout_add_third_category,null)
        val imageView = editView.find<ImageView>(R.id.mPictureIv)
        imageView.loadUrl(category.imageUrl)
        val editV = editView.find<EditText>(R.id.mThirdCgNameEt)
        editV.setText(category.name)
        val takePhoto = editView.find<Button>(R.id.mPictureBtn)
        val camera = editView.find<Button>(R.id.mCameraBtn)
        camera.setOnClickListener{
            mTakePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(), false)
            createTempFile()
            mTakePhoto.onPickFromCapture(Uri.fromFile(mTempFile))
        }
        takePhoto.setOnClickListener {
            mTakePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(), false)
            mTakePhoto.onPickFromGallery()
        }
        alertView.addExtView(editView).show()

    }

    /**
     * 显示修改类别对话框
     */
    fun updateCategory(category: CategoryInfo) {
        alertView = AlertView("修改类别", null, null, null,
                arrayOf("取消", "完成"), context, AlertView.Style.Alert, OnItemClickListener { o, position ->
            activity?.hideSoftInput()
            when (position) {
                1 -> viewModel.updateCategory(category)
            }
        })
        val extView = LayoutInflater.from(context).inflate(R.layout.edit_category_name, null)
        val editV = extView.findViewById<EditText>(R.id.mCgNameEt)
        editV.setText(category.name)
        alertView.addExtView(extView).show()
    }

    /**
     * 显示删除类别对话框
     */
    fun deleteCategory(category: CategoryInfo) {
        alertView = AlertView("删除类别", null, null, null,
                arrayOf("取消", "确定"), context, AlertView.Style.Alert, OnItemClickListener { o, position ->
            activity?.hideSoftInput()
            when (position) {
                0 -> viewModel.deleteCategory(category)
            }
        })
        alertView.show()
    }

    /**
     * 增加三级类别时对话框，拍照或从相像中获取
     */
    private fun showPhotoAlert(second: CategoryInfo) {
        AlertView("选择图片", null, "取消", null, arrayOf("拍照", "相册"), context,
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

    override fun takeSuccess(result: TResult?) {
//        //1、利用压缩文件生成AVFile
//        val file = AVFile.withAbsoluteLocalPath("${DateUtils.curTime}.png",result?.image?.compressPath)
//        //2、上传AVFile对象
//        file.saveInBackground(object : SaveCallback(){
//            override fun done(p0: AVException?) {
//                //3、将返回的头像Url保存本地
//                AppPrefsUtils.putString(ProviderConstant.KEY_SP_USER_ICON,file.url.toString())
//                //4、更新用户信息，将头像信息保存至用户信息中
//                val disposable= viewModel.updateAvatar(AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN),
//                        AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ID),file.url.toString()).execute()
//                        .subscribe{
//                            if (!it.isSuccess()) {
//                                Log.d("error:",it.error)
//                            }
//                        }
//                viewModel.compositeDisposable.add(disposable)
//            }
//
//        })

    }

    override fun takeCancel() {
    }

    override fun takeFail(result: TResult?, msg: String?) {
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
    fun createTempFile() {
        val tempFileName = "${DateUtils.curTime}.png"
        if (Environment.MEDIA_MOUNTED == (Environment.getExternalStorageState())) {
            this.mTempFile = File(Environment.getExternalStorageDirectory(), tempFileName)
            return
        }
        this.mTempFile = File(activity?.filesDir, tempFileName)
    }
}


