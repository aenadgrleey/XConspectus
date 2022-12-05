package com.example.xconspectus.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xconspectus.databinding.HomeFragmentBinding
import com.example.xconspectus.ui.dialogs.RefactorSubjectArgs
import com.example.xconspectus.ui.subject.MySubjectRecyclerViewAdapter
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    private lateinit var binding: HomeFragmentBinding
    private lateinit var adapterToSet : MyHomeRecyclerViewAdapter
    private val viewModel : HomeFragmentViewModel by viewModels()
    private val sharedViewModel : SubjectRefactorSharedViewModel by viewModels()
    private val args: RefactorSubjectArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater)

        adapterToSet = MyHomeRecyclerViewAdapter(binding.root)

        this.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                with(binding.list) {
                    adapterToSet.setData(viewModel.subjects.value)
                    adapter = adapterToSet
                    layoutManager = LinearLayoutManager(context)

                }
            }
        }



        setObservers()
        setListeners()


        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        if (sharedViewModel.refactored) {
            viewModel.addSubject(sharedViewModel.getSubject()!!)
            Log.v("TAG1", sharedViewModel.getSubject().toString())
        }
        Log.v("LifecycleFun", "Resumed")
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        Log.v("LifecycleFun", "Paused")

    }

    private fun setObservers() {
        viewModel.subjects.observe(viewLifecycleOwner) {
            adapterToSet.setData(it)
        }
    }

    private fun setListeners() {
        binding.fab.setOnClickListener() {
            findNavController().navigate(com.example.xconspectus.R.id.refactorSubject)
        }
    }
}