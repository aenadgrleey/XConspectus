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

    private lateinit var _binding: HomeFragmentBinding
    val binding: HomeFragmentBinding get() =  _binding
    private lateinit var adapterToSet: MyHomeRecyclerViewAdapter

    private val viewModel: HomeFragmentViewModel by viewModels()
    private val sharedViewModel: SubjectRefactorSharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater)

        adapterToSet = MyHomeRecyclerViewAdapter(this)

        this.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                with(_binding.list) {
                    adapterToSet.setData(viewModel.subjects.value)
                    adapter = adapterToSet
                    layoutManager = LinearLayoutManager(context)

                }
            }
        }

        setObservers()
        setListeners()

        return _binding.root
    }

    private fun setObservers() {
        //observe subjects to detect change in database
        viewModel.subjects.observe(viewLifecycleOwner) {
            adapterToSet.setData(it)
        }
        //observe refactoring subject to detect changes
        //maybe should be function
        sharedViewModel.subjectDB.observe(viewLifecycleOwner) {
            viewModel.addSubject(it)
        }
    }


    private fun setListeners() {
        //set up "add new" button
        _binding.fab.setOnClickListener {
            sharedViewModel.setNewSubject()
            findNavController().navigate(R.id.refactorSubject)
        }
    }

    fun changeSubject(subject: SubjectDB) {
        sharedViewModel.setSubjectToRefactor(subject)
        findNavController().navigate(R.id.refactorSubject)
    }

}