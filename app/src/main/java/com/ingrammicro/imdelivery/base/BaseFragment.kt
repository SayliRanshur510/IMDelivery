package com.ingrammicro.imdelivery.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ingrammicro.imdelivery.R

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    var viewCreated = false

    lateinit var navController: NavController
    lateinit var activity: AppCompatActivity
    private lateinit var dataBinding: T
    private lateinit var createdView: View
    private var actionBar: ActionBar? = null
    private lateinit var callback: OnBackPressedCallback

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (!viewCreated) {
            val activity = getActivity() as AppCompatActivity
            dataBinding = DataBindingUtil.inflate(inflater, layoutRes(), container, false)
            navController = Navigation.findNavController(activity, R.id.nav_host_fragment)
            createdView = dataBinding.root
            viewCreated = true
        }

        return createdView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as AppCompatActivity
    }
    @LayoutRes
    protected abstract fun layoutRes(): Int

    protected fun getDataBinding(): T {
        return dataBinding
    }

    
}