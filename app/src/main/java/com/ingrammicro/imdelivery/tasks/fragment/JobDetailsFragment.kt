package com.ingrammicro.imdelivery.tasks.fragment

import android.graphics.Bitmap
import android.graphics.Color
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Base64
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ingrammicro.imdelivery.EventObserver

import com.ingrammicro.imdelivery.R
import com.ingrammicro.imdelivery.Resource
import com.ingrammicro.imdelivery.SharedViewModel
import com.ingrammicro.imdelivery.databinding.ContentCustomerSignatureBinding
import com.ingrammicro.imdelivery.databinding.FragmentJobDetailsBinding
import com.ingrammicro.imdelivery.tasks.viewmodel.JobDetailsViewModel
import java.io.ByteArrayOutputStream

class JobDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = JobDetailsFragment()
    }

    private lateinit var viewModel: JobDetailsViewModel
    private lateinit var jobDetailsBinding:FragmentJobDetailsBinding
    private lateinit var sharedViewModel: SharedViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_job_details, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        jobDetailsBinding = FragmentJobDetailsBinding.bind(view)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)
        viewModel = ViewModelProvider(this).get(JobDetailsViewModel::class.java)
        jobDetailsBinding.viewModel = viewModel
        //callJobDetailsApi()
        signatureToByte()
        subscribeToChanges()
        addSpinnerAdapter()

    }

    fun subscribeToChanges(){
//        if (viewModel.dataLoad.get()){
//            sharedViewModel.selectedTask.observe(viewLifecycleOwner, Observer { item ->
//                item?.let {
//                    viewModel.jobDetail(item.tripSheetNo, item.orderNo)
//                }
//            })
//        }

        jobDetailsBinding.toolbarMain.textToolbarTitle.text = "Job Details"

        viewModel.getJobDetails.observe(viewLifecycleOwner,EventObserver{

            when(it){
                is Resource.Success->{
                    viewModel.isLoading.set(false)
                  //  viewModel.populateData(it.data!!.tripData.tripDetails)
                    populateUI()

                }
                is Resource.Error->{
                    viewModel.isLoading.set(false)

                }
                is Resource.Loading->{
                    viewModel.isLoading.set(true)

                }
            }
        })


    }
    fun populateUI(){
        jobDetailsBinding.customerInfo.textInvoiceNo.setText(viewModel.invoiceNo)
        jobDetailsBinding.customerInfo.textContactNo.setText(viewModel.custContactNo)
        jobDetailsBinding.customerInfo.textEmail.setText(viewModel.custEmailId)
        jobDetailsBinding.customerInfo.deliveryAddress.setText(viewModel.deliveryAddress)
        jobDetailsBinding.customerInfo.textName.setText(viewModel.custName)

    }
    fun callJobDetailsApi(){
        sharedViewModel.selectedTask.observe(viewLifecycleOwner, Observer {
            it?.let { task ->
                //viewModel.jobDetail(task.tripSheetNo, task.orderNo)
            }
        })
    }

    fun signatureToByte(){
//        contentCustomerSignatureBinding.signatureView.(Color.BLACK)
//        contentCustomerSignatureBinding.signatureView.setWidth(200.0)

        jobDetailsBinding.includeCustomerSign.clearButton.setOnClickListener {
            jobDetailsBinding.includeCustomerSign.signatureView.clear()
        }


//        jobDetailsBinding.includeCustomerSign.doneButton.setOnClickListener {
//            val imageBitmap: Bitmap? = jobDetailsBinding.includeCustomerSign.signatureView.signatureBitmap
//            if (imageBitmap != null) {
//                val imageFinal: ByteArray = bitmapToByteArray(imageBitmap)
//                //val imageBoundingBox: ByteArray = bitmapToByteArray(contentCustomerSignatureBinding.signatureView.getSignatureBitmap(true)!!)
//                val stringImage = byteArrayToBase64(imageFinal)
//
////                val intent = Intent(this, ImageActivity::class.java)
////                intent.putExtra("imageFinal", imageFinal)
////                intent.putExtra("imageBoundingBox", imageBoundingBox)
////                startActivity(intent)
//            }
//        }
    }

    private fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }

    private fun byteArrayToBase64(byteArray: ByteArray?): String {
        if (byteArray == null) return ""
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }

    fun addSpinnerAdapter(){
        val remarks = arrayOf("Customer not available", "Refused by customer", "Partially delivered", "Delivered")

        if (jobDetailsBinding.jobStatus.actionBarSpinner != null) {
            val arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, remarks)
            jobDetailsBinding.jobStatus.actionBarSpinner.adapter = arrayAdapter

            jobDetailsBinding.jobStatus.actionBarSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    jobDetailsBinding.jobStatus.actionBarSpinner.setSelection(position)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    jobDetailsBinding.jobStatus.actionBarSpinner.setSelection(R.string.hint_remarks)
                }
            }
        }

    }

    }

