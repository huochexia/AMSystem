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
import com.alibaba.android.arouter.launcher.ARouter
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
import com.orhanobut.logger.Logger
import com.owner.assertsparam.Interface.QueryAssertsInfo
import com.owner.assertsparam.R
import com.owner.assertsparam.data.CategoryInfo
import com.owner.assertsparam.databinding.FragementCategoryBinding
import com.owner.assertsparam.databinding.LayoutAddThirdCategoryBinding
import com.owner.assertsparam.view.adapter.SecondCgAdapter
import com.owner.assertsparam.view.adapter.TopCgAdapter
import com.owner.assertsparam.viewmodel.ArgumentViewModel
import com.owner.assertsparam.viewmodel.CategoryFgViewModel
import com.owner.assertsparam.viewmodel.CategoryViewModelFactory
import com.owner.baselibrary.ext.enabled
import com.owner.baselibrary.ext.loadUrl
import com.owner.baselibrary.utils.DateUtils
import com.owner.baselibrary.utils.hideSoftInput
import com.owner.baselibrary.view.fragment.BaseFragment
import com.owner.provideslib.router.RouterPath
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

    private lateinit var sharedViewModel: ArgumentViewModel//用于保存每个参数的选择结果
    private var tableName: String = ""
    private var isEdited: Boolean = true//当前界面是否用于编辑
    private var isQuery: Boolean = false // 当前界面是否用于查询

    lateinit var queryInterface: QueryAssertsInfo

    private lateinit var topAdapter: TopCgAdapter
    private lateinit var secondAdapter: SecondCgAdapter
    private lateinit var alertView: AlertView
    private lateinit var mTakePhoto: TakePhoto
    private lateinit var mTempFile: File
    private lateinit var invokeParam: InvokeParam
    //定义总分类对象
    private val category = CategoryInfo("0", "")
    //当前选择的一级分类
    private var currentTopCategory = CategoryInfo("", "")
    //临时分类对象
    lateinit var tempCategory: CategoryInfo
    var thirdCgImage = MutableLiveData<String>()


    /*
      获得外部传入的分类名称
     */
    companion object {
        fun newInstance(tableName: String, isEdited: Boolean, isQuery: Boolean) = CategoryFragment().apply {
            val bundle = Bundle()
            bundle.putString("tableName", tableName)
            bundle.putBoolean("isEdited", isEdited)
            bundle.putBoolean("isQuery", isQuery)
            arguments = bundle
            return this
        }
        const val SELECT_ITEM_REQUEST_CODE =100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = arguments!!
        tableName=bundle.getString("tableName")!!
        isEdited = bundle.getBoolean("isEdited")
        isQuery = bundle.getBoolean("isQuery")

        sharedViewModel = ViewModelProviders.of(activity!!).get(ArgumentViewModel::class.java)

        initViewModel()

        initTakePhoto(savedInstanceState)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding = DataBindingUtil.inflate<FragementCategoryBinding>(
                inflater, R.layout.fragement_category, container, false)
        binding.categoryVM = viewModel
        //绑定总资产对象
        binding.category = category
        binding.mHeaderBar.getTitleView().text = viewModel.categoryName
        if (!isEdited && !isQuery) { //选择状态
            initRightView(binding)
        }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadTopCgList()
        mSecondCategoryRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    /**
     * 初始化HeadBar的右标题
     */
    private fun initRightView(binding: FragementCategoryBinding) {
        binding.mHeaderBar.getRightView().text = "完成"
        binding.mHeaderBar.getRightView().visibility = View.VISIBLE
        binding.mHeaderBar.getRightView().setOnClickListener {
          Logger.d(sharedViewModel.selectedArgumentMap.toString())
        }
    }

    /**
     * 初始化ViewModel
     */
    private fun initViewModel() {
        // 通过工厂方法将分类名称传入ViewModel中
        viewModel = ViewModelProviders.of(this, CategoryViewModelFactory(tableName, isEdited, isQuery))
                .get(CategoryFgViewModel::class.java)

        //观察行为信息的变化，做出相应的响应
        viewModel.action.observe(this, Observer {
            //如果parentId为空，则是一级分类
            executeAction(it!!)
        })
        //观察刷新列表请求
        viewModel.refreshList.observe(this, Observer {
            when (it?.second) {
                0 -> topAdapter.notifyDataSetChanged()
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
        //得到所选择的分类,返回值
        viewModel.getCategoryInfo.observe(this, Observer {
            sharedViewModel.selectedArgumentMap[it!!.first] = it.second
        })
        //观察是否要查询
        viewModel.gotoQueryAsserts.observe(this, Observer {
            queryInterface.queryAssert(it!!.first, it.second)
        })
        //启动四级分类明细
        viewModel.selectedFourthCg.observe(this, Observer {
            ARouter.getInstance().build(RouterPath.AssertsParam.PATH_ASSERTSPARAM_FOUR)
                    .withString("tableName", tableName)
                    .withBoolean("isEdited", isEdited)
                    .withBoolean("isQuery", isQuery)
                    .withParcelable("thirdCg", it)
                    .navigation(activity, SELECT_ITEM_REQUEST_CODE)
        })
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

    /**
     *对由ViewModel发生的事件进行筛分，对应处理
     */
    private fun executeAction(it: Pair<String, CategoryInfo>) {
        when (it.first) {
            CategoryFgViewModel.KEY_SELECTED_ACTION -> selectCategory(it.second)
            CategoryFgViewModel.KEY_UPDATE_ACTION -> updateCategory(it.second)
            CategoryFgViewModel.KEY_DELETE_ACTION -> deleteCategory(it.second)
            CategoryFgViewModel.KEY_ADD_ACTION -> addCategory(it.second)
            CategoryFgViewModel.KEY_ADD_THIRD_ACTION -> addThirdCategory(it.second)
            CategoryFgViewModel.KEY_UPDATE_THIRD_ACTION -> updateThirdCategory(it.second)
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
     * 点击分类，显示列表
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
                                //设置父类的hasChild为true
                                parent.hasChild = true
                                viewModel.restoreState(parent)
                                viewModel.updateCategory(parent)
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
        //因为拍照或相册操作成功后，会把imageUrl先写入tempCategory当中
        //为了防止将其他操作保存在tempCategory中的imageUrl写入这个category当中，所以先进行清空处理
        tempCategory = CategoryInfo("", "")
        //刷新列表，目的是取消之前做过的长按状态
        secondAdapter.notifyDataSetChanged()
        val (editView, editV) = initDialog()
        editV.setText(category.name)
        thirdCgImage.value = category.imageUrl
        alertView = AlertView("修改类别", null, null, null,
                arrayOf("取消", "完成"), context, AlertView.Style.Alert, OnItemClickListener { o, position ->
            activity?.hideSoftInput()
            when (position) {
                1 -> {
                    category.name = editV.text.toString()
                    viewModel.restoreState(category)
                    //要判一下图片是否发生改变,不为空说明进行了图片操作
                    if (tempCategory.imageUrl != "")
                        category.imageUrl = tempCategory.imageUrl
                    viewModel.updateCategory(category)
                    secondAdapter.notifyDataSetChanged()
                }
            }
        })
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
                                category.isSelected = false
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
    private fun deleteCategory(category: CategoryInfo) {
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


