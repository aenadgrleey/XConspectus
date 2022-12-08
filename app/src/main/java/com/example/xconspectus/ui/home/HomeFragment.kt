package com.example.xconspectus.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xconspectus.R
import com.example.xconspectus.data.SubjectDB
import com.example.xconspectus.databinding.HomeFragmentBinding
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentBinding
    private lateinit var adapterToSet: MyHomeRecyclerViewAdapter

    private val viewModel: HomeFragmentViewModel by viewModels()
    private val sharedViewModel: SubjectRefactorSharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater)

        adapterToSet = MyHomeRecyclerViewAdapter(this, binding.root)

        this.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
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

    private fun setObservers() {

        viewModel.subjects.observe(viewLifecycleOwner) {
            adapterToSet.setData(it)
        }

        sharedViewModel.subjectDB.observe(viewLifecycleOwner) {
            viewModel.addSubject(it)
        }
    }


    private fun setListeners() {
        binding.fab.setOnClickListener {
            sharedViewModel.setNewSubject()
            findNavController().navigate(R.id.refactorSubject)
        }
    }

    fun changeSubjectRequest(subject: SubjectDB) {
        sharedViewModel.setSubjectToRefactor(subject)
        findNavController().navigate(R.id.refactorSubject)
    }

}