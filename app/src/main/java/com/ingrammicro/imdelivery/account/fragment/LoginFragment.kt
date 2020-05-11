package com.ingrammicro.imdelivery.account.fragment

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.ingrammicro.imdelivery.*
import com.ingrammicro.imdelivery.databinding.FragmentLoginBinding

import com.ingrammicro.imdelivery.account.viewmodel.LoginViewModel
import com.ingrammicro.imdelivery.utils.SnackbarType
import com.ingrammicro.imdelivery.utils.longSnackbar

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() =
            LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel
    private lateinit var repository: Repository
    private lateinit var fragmentLoginBinding:FragmentLoginBinding
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var handler: Handler


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentLoginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)
        viewModel  = ViewModelProvider(this).get(LoginViewModel::class.java)
        fragmentLoginBinding.viewModel = viewModel
        subscribeToChanges()
    }

    private fun subscribeToChanges() {
         val NO_CONTENT = "No content"
        val navController = findNavController()


        fragmentLoginBinding.loginbutton.setOnClickListener {
            viewModel.onLoginClick()
        }

        fragmentLoginBinding.textviewForgotPwd.setOnClickListener {
            val fm = parentFragmentManager
            var dialog =
                ForgotPasswordFragment()
            if (fm != null) {
                dialog.show(fm, "Forgot Password")
            }
        }

        viewModel.loginClick().observe(viewLifecycleOwner, Observer {
            if (it) {
                hideKeyboard()
                //viewModel.loginForm.isLoginClick = true
            }
        })
        viewModel.getDriverDetails.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    viewModel.isLoading.set(false)
                    if (it.data!!.status != NO_CONTENT) {
                        if (it.data.data.driverDetails != null) {
                            viewModel.saveDriverDetails(it.data.data.driverDetails!!)
                            navController.navigate(R.id.action_login_to_tasks_details)

                        } else {
                            showSnackbar(it.data.message)
                            /* val toast = Toast.makeText(context, "Login failed", Toast.LENGTH_LONG)
                             toast.show()*/
                        }
                    }else{
                            viewModel.checkLoginResult(it.data)
                        }
                    }

                is Resource.Error -> {
                    viewModel.isLoading.set(false)
                    it.apiError?.let { error ->
                        showSnackbar(getString(error.strId))
                    }
                }
                is Resource.Loading ->{ viewModel.isLoading.set(true)}
            }
        })

        sharedViewModel.message.observe(viewLifecycleOwner, EventObserver {
            handler.postDelayed(
                {
                    longSnackbar(fragmentLoginBinding.root, it.data!!.message, SnackbarType.YELLOW).show()

                },
                100
            )
        })
    }

    private fun showSnackbar(msg: Any) {
        when (msg) {
            is Int -> longSnackbar(fragmentLoginBinding.root, msg, SnackbarType.YELLOW).show()
            is String -> longSnackbar(fragmentLoginBinding.root, msg, SnackbarType.YELLOW).show()
        }
    }
    fun hideKeyboard() {
        val imm = requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(fragmentLoginBinding.root.windowToken, 0)
    }

}
