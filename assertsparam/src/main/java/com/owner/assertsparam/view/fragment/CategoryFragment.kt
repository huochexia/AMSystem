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

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
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
import com.owner.assertsparam.R
import com.owner.assertsparam.data.CategoryInfo
import com.owner.assertsparam.databinding.FragementCategoryBinding
import com.owner.assertsparam.databinding.LayoutAddThirdCategoryBinding
import com.owner.assertsparam.view.adapter.SecondCgAdapter
import com.owner.assertsparam.view.adapter.TopCgAdapter
import com.owner.assertsparam.viewmodel.CategoryFgViewModel
import com.owner.baselibrary.ext.enabled
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
    private var currentTopCategory = CategoryInfo("", "")
    //临时分类对象
    lateinit var tempCategory: CategoryInfo
    var thirdCgImage = MutableLiveData<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CategoryFgViewModel::class.java)

        //观察行为信息的变化，做出相应的响应
        viewModel.action.observe(this, Observer {
            //如果parentId为空，则是一级分类
            executeAction(it!!)
        })
        //观察刷新列表请求
        viewModel.refreshList.observe(this, Observer {
            when (it?.second) {
                0 ->topAdapter.notifyDataSetChanged()
                1 -> {
                    secondAdapter.updateList()
                    secondAdapter.notifyDataSetChanged()
                }
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
        loadTopCgList()
        mSecondCategoryRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    /**
     *对由ViewModel发生的事件进行筛分，对应处理
     */
    private fun executeAction(it: Pair<String, CategoryInfo>) {
        when (it.first) {
            CategoryFgViewModel.KEY_SELECTED_ACTION -> selectCategory(it.second)
            CategoryFgViewModel.KEY_UPDATE_ACTION -> updateCategory(it.second )
            CategoryFgViewModel.KEY_DELETE_ACTION -> deleteCategory(it.second )
            CategoryFgViewModel.KEY_ADD_ACTION -> addCategory(it.second)
            CategoryFgViewModel.KEY_ADD_THIRD_ACTION->addThirdCategory(it.second)
            CategoryFgViewModel.KEY_UPDATE_THIRD_ACTION-> updateThirdCategory(it.second)
        }
    }


    /**
     * 加载一级列表
     */
    private fun loadTopCgList() {
        mTopCategoryRv.layoutManager = LinearLayoutManager(context)
        topAdapter = TopCgAdapter(viewModel)
        mTopCategoryRv.adapter = topAdapter
    }

    /**
     * 加载二级分类列表
     */
    private fun loadSecondCgList(category: CategoryInfo) {
        secondAdapter = SecondCgAdapter(category, viewModel)
        mSecondCategoryRv.adapter = secondAdapter
        secondAdapter.notifyDataSetChanged()
    }

    /**
     * 选择分类
     */
    private fun selectCategory(category: CategoryInfo) {
        //判断是否是一级分类
        if (category.parentId == "0") {
            topAdapter.notifyDataSetChanged()
            //避免选择当前已选择一级分类时重新获取二级分类。
            if (currentTopCategory.objectId != category.objectId) {
                loadSecondCgList(category)//加载二级分类
                currentTopCategory = category//指定当前一级分类
            }
        } else {
            secondAdapter.notifyDataSetChanged()
        }
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
                arrayOf("取消", "完成"), context, AlertView.Style.Alert,
                OnItemClickListener { _, position ->
            activity?.hideSoftInput()
            when (position) {
               1 -> {
                   if (editV.text.isNullOrEmpty().not()) {
                       val name = editV.text.toString().trim()
                       val newCg = CategoryInfo("", name, parent.objectId)
                       viewModel.addCategory(newCg)
                   }
               }
            }
        })

        alertView.addExtView(extView).show()
    }

    /**
     * 增加三级分类
     */
    private fun addThirdCategory(parent: CategoryInfo) {
        tempCategory = CategoryInfo("", "")
        //刷新列表，目的是取消之前做过的长按状态
        secondAdapter.notifyDataSetChanged()
        val (editView, editV) = initDialog()
        thirdCgImage.value = parent.imageUrl
        alertView = AlertView("增加分类", null, null, null,
                arrayOf("取消", "完成"), context, AlertView.Style.Alert, OnItemClickListener { _, position ->
            activity?.hideSoftInput()
            when (position) {
                1 -> {
                    tempCategory.name = editV.text.toString()
                    tempCategory.parentId = parent.objectId
                    viewModel.addCategory(tempCategory)
                }
            }
        })
        alertView.addExtView(editView).show()

    }

    /**
     * 初始化对话框内容
     */
    private fun initDialog(): Pair<View, EditText> {
        val binding = LayoutAddThirdCategoryBinding.inflate(layoutInflater, null)
        binding.thirdvm = this
        val editView = LayoutInflater.from(context).inflate(R.layout.layout_add_third_category, null)

        val imageView = editView.find<ImageView>(R.id.mPictureIv)
        thirdCgImage.observe(this, Observer {
            if (it.isNullOrEmpty()) {
                imageView.setImageResource(R.drawable.pictures_no)
            } else {
                imageView.loadUrl(it!!)
            }
        })
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
    private fun updateCategory(category: CategoryInfo) {
        val extView = LayoutInflater.from(context).inflate(R.layout.edit_category_name, null)
        val editV = extView.findViewById<EditText>(R.id.mCgNameEt)
        editV.setText(category.name)
        alertView = AlertView("修改类别", null, null, null,
                arrayOf("取消", "完成"), context, AlertView.Style.Alert,
                OnItemClickListener { o, position ->
                    activity?.hideSoftInput()
                    when (position) {
                        1 -> {
                            category.name = editV.text.toString()
                            viewModel.updateCategory(category)
                            if (category.parentId == "0") {
                                mAddSecondCgTv.text = category.name
                                category.isLongOnClick = false
                                topAdapter.notifyDataSetChanged()
                            } else {
                                secondAdapter.notifyDataSetChanged()
                            }
                        }
                    }
                })
        alertView.addExtView(extView).show()
    }

    /**
     * 显示删除类别对话框
     */
    fun deleteCategory(category: CategoryInfo) {
        alertView = AlertView("删除类别", null, null, null,
                arrayOf("取消", "确定"), context, AlertView.Style.Alert,
                OnItemClickListener { _, position ->
            activity?.hideSoftInput()
            when (position) {
                1 -> {
                    if (category.parentId == "0") {
                        viewModel.topCgList.remove(category)
                        viewModel.isVisibleTop = false
                        topAdapter.notifyDataSetChanged()
                    } else {
                        viewModel.secondAndThirdCgList.remove(category)
                        secondAdapter.updateList()
                        secondAdapter.notifyDataSetChanged()
                    }
                    viewModel.deleteCategory(category)
                }
            }
        })
        alertView.show()
    }

    override fun takeSuccess(result: TResult?) {
        //1、利用压缩文件生成AVFile
        val file = AVFile.withAbsoluteLocalPath("${DateUtils.curTime}.png", result?.image?.compressPath)
        //2、上传AVFile对象
        file.saveInBackground(object : SaveCallback() {
            override fun done(p0: AVException?) {
                thirdCgImage.value = file.url
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
}


