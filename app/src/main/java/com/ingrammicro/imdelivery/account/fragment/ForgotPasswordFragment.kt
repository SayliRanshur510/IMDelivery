package com.ingrammicro.imdelivery.account.fragment

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ingrammicro.imdelivery.Event

import com.ingrammicro.imdelivery.R
import com.ingrammicro.imdelivery.Resource
import com.ingrammicro.imdelivery.SharedViewModel
import com.ingrammicro.imdelivery.databinding.FragmentForgotPasswordBinding
import com.ingrammicro.imdelivery.account.viewmodel.ForgotPasswordViewModel
import com.ingrammicro.imdelivery.utils.SnackbarType
import com.ingrammicro.imdelivery.utils.longSnackbar

class ForgotPasswordFragment : DialogFragment() {


    companion object {
        fun newInstance() =
            ForgotPasswordFragment()
    }

    private lateinit var viewModel: ForgotPasswordViewModel
    private lateinit var fragmentForgotPasswordBinding: FragmentForgotPasswordBinding
    private lateinit var sharedViewModel: SharedViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.window?.setBackgroundDrawableResource(R.color.transparent_color)
        dialog!!.setCancelable(false)
        return inflater.inflate(R.layout.fragment_forgot_password, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentForgotPasswordBinding = FragmentForgotPasswordBinding.bind(view)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sharedViewModel = activity.run { ViewModelProvider.AndroidViewModelFactory.getInstance(Application()).create(
            SharedViewModel::class.java) }
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(Application()).create(
            ForgotPasswordViewModel::class.java)
        fragmentForgotPasswordBinding.viewModel = viewModel
        subscribeToChanges()
    }
    fun subscribeToChanges(){
        val NO_CONTENT = "No content"

        fragmentForgotPasswordBinding.closeIcon.setOnClickListener {
            dismiss()
        }

        fragmentForgotPasswordBinding.submitButton.setOnClickListener {
            viewModel.onSubmitClick()
        }

        viewModel.getNewPassword.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    viewModel.isLoading.set(false)
                    if (it.data!!.status != NO_CONTENT) {

                            dismiss()
                            sharedViewModel.message.value = Event(it)
                            /* val toast =
                                 Toast.makeText(context, "Login Successful", Toast.LENGTH_LONG)
                             toast.show()*/
                        } else{
                        dismiss()
                        sharedViewModel.message.value = Event(it)
                    }
                }

                is Resource.Error -> {
                    viewModel.isLoading.set(false)
                    it.apiError?.let { error ->
                        dismiss()
                        sharedViewModel.message.value = Event(it)
                    }
                }
                is Resource.Loading ->{ viewModel.isLoading.set(true)}
            }
        })
    }
    private fun showSnackbar(msg: Any) {
        when (msg) {
            is Int -> longSnackbar(fragmentForgotPasswordBinding.root, msg, SnackbarType.YELLOW).show()
            is String -> longSnackbar(fragmentForgotPasswordBinding.root, msg, SnackbarType.YELLOW).show()
        }
    }

}
