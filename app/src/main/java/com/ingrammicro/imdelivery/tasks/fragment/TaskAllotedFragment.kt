package com.ingrammicro.imdelivery.tasks.fragment

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ingrammicro.imdelivery.EventObserver
import com.ingrammicro.imdelivery.R
import com.ingrammicro.imdelivery.Resource
import com.ingrammicro.imdelivery.SharedViewModel
import com.ingrammicro.imdelivery.databinding.FragmentTaskAllotedBinding
import com.ingrammicro.imdelivery.tasks.adapter.TaskListAdapter
import com.ingrammicro.imdelivery.tasks.model.Task
import com.ingrammicro.imdelivery.tasks.viewmodel.TaskAllotedViewModel
import com.ingrammicro.imdelivery.utils.SnackbarType
import com.ingrammicro.imdelivery.utils.longSnackbar
import com.paginate.Paginate
import kotlinx.android.synthetic.main.fragment_task_alloted.*

internal const val PARAM_PENDING="pending"
internal const val PARAM_COMPLETED="completed"
internal const val PARAM_ALL="all"
internal const val NO_CONTENT = "No content"
private const val THRESHOLD = 0
private const val GRID_SPAN = 2

class TaskAllotedFragment : Fragment() {

    companion object {
        fun newInstance() =
            TaskAllotedFragment()
    }

    private lateinit var viewModel: TaskAllotedViewModel
    private lateinit var taskListAdapter:TaskListAdapter
    private lateinit var taskAllotedBinding: FragmentTaskAllotedBinding
    private var address:String?=null
    private var taskSize:Int = 0
    private lateinit var taskList:ArrayList<Task>
    private var position:Int?=0
    private lateinit var sharedViewModel: SharedViewModel
    private var paginate: Paginate? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_task_alloted, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        taskAllotedBinding = FragmentTaskAllotedBinding.bind(view)

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)
        viewModel = ViewModelProvider(this).get(TaskAllotedViewModel::class.java)
        taskAllotedBinding.viewModel = viewModel
        setupAdapter()
        subscribeToChanges()

    }


    fun subscribeToChanges(){
        taskAllotedBinding.toolbarMain.textToolbarTitle.text = "Active Jobs"
        taskAllotedBinding.filterMenu.setOnClickListener {

            val popupMenu: PopupMenu = PopupMenu(requireContext(),taskAllotedBinding.filterMenu)
            popupMenu.menuInflater.inflate(R.menu.filter_menu,popupMenu.menu)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when(item.itemId) {
                    R.id.action_pending -> {
                        taskListAdapter.clear()
                        viewModel.getTaskAllotedList(PARAM_PENDING)
                        taskAllotedBinding.toolbarMain.textToolbarTitle.text = "Active Jobs"


                    }
                    R.id.action_completed ->{
                        taskListAdapter.clear()
                        viewModel.getTaskAllotedList(PARAM_COMPLETED)
                        taskAllotedBinding.toolbarMain.textToolbarTitle.text = "Completed Jobs"


                    }
                    R.id.action_all ->{
                        taskListAdapter.clear()
                        viewModel.getTaskAllotedList(PARAM_ALL)
                        taskAllotedBinding.toolbarMain.textToolbarTitle.text = "All Jobs"


                    }
                }
                true
            })
            popupMenu.show()
        }



        viewModel.getJobDetails.observe(viewLifecycleOwner, EventObserver {
            when(it){
                is Resource.Success->{
                    if (it.data!!.status!= NO_CONTENT) {
                        viewModel.isLoading.set(false)
                        taskSize = it.data.taskData.task.size
                        taskList = it.data.taskData.task
                        viewModel.totalRecords = it.data.totalCount
                        viewModel.totalPages = viewModel.totalRecords!! /10
                        address = it.data.taskData.task.get(0).deliveryAdd
                        taskListAdapter.addItems(it.data.taskData.task)

                        if (viewModel.page ==0 && viewModel.totalPages >1){
                            setupPagination()
                        }
                    }else
                    {
                        viewModel.checkTaskDetails(it.data)
                    }

                }
                is Resource.Error->{
                    viewModel.isLoading.set(false)
                    it.apiError?.let { error ->
                        showSnackbar(getString(error.strId))
                    }

                }
                is Resource.Loading->{
                    viewModel.isLoading.set(true)


                }
            }
        })



        viewModel.getLocationCoordinates.observe(viewLifecycleOwner, EventObserver {
            for (i in taskSize..1 ) {
                address = taskList.get(i).deliveryAdd
            }
            val gmmIntentUri: Uri =
                Uri.parse("geo:0,0?q="+address)
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        })

        viewModel.getContact.observe(viewLifecycleOwner,EventObserver{
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:9908334197")
            startActivity(intent)
        })

    }
    private fun setupAdapter() {

        val navController = findNavController()

        taskListAdapter = TaskListAdapter(viewModel,position!!) { item ->
            sharedViewModel.selectedTask.value = item
            navController.navigate(R.id.action_task_to_job_details)
        }

        with(taskAllotedBinding.taskRecyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = taskListAdapter
        }
    }
    private fun showSnackbar(msg: Any) {
        when (msg) {
            is Int -> longSnackbar(taskAllotedBinding.root, msg, SnackbarType.YELLOW).show()
            is String -> longSnackbar(taskAllotedBinding.root, msg, SnackbarType.YELLOW).show()
        }
    }

    private fun setupPagination() {
        paginate = Paginate.with(taskAllotedBinding.taskRecyclerView, mPagingCallback)
            .setLoadingTriggerThreshold(THRESHOLD)
            .addLoadingListItem(true)
            .setLoadingListItemCreator(null)
            .setLoadingListItemSpanSizeLookup {
                GRID_SPAN
            }
            .build()
    }
    val mPagingCallback = object : Paginate.Callbacks {
        override fun onLoadMore() {
            viewModel.loading = true
            viewModel.loadMore()
        }

        override fun isLoading(): Boolean {
            return viewModel.loading
        }

        override fun hasLoadedAllItems(): Boolean {
            return viewModel.hasLoadedAllData()
        }
    }

}


