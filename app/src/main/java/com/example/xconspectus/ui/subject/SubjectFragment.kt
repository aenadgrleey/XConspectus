package com.example.xconspectus.ui.subject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xconspectus.R
import com.example.xconspectus.data.ThemeDB
import com.example.xconspectus.data.repositories.ThemesRepository
import com.example.xconspectus.databinding.SubjectFragmentBinding
import kotlinx.coroutines.launch


class SubjectFragment : Fragment() {
    private lateinit var binding: SubjectFragmentBinding
    private val adapterToSet = MySubjectRecyclerViewAdapter(this)

    private lateinit var viewModel: SubjectFragmentViewModel
    val sharedViewModel : ThemeRefactorSharedViewModel by activityViewModels()

    private val args: SubjectFragmentArgs by navArgs()
    private var subjectId: Int =  0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = SubjectFragmentBinding.inflate(inflater)

        subjectId = args.subjectId
        val themesRepository = ThemesRepository(requireContext().applicationContext, subjectId)
        val factory = SubjectFragmentViewModel.Factory(themesRepository)
        viewModel = ViewModelProvider(this, factory)[SubjectFragmentViewModel::class.java]
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                with(binding.list) {
                    layoutManager = LinearLayoutManager(context)
                    adapter = adapterToSet
                }
            }
        }

        setObservers()
        setListeners()

        return binding.root
    }

    private fun setObservers() {
        //observe themes to detect change in dateset
        viewModel.themes.observe(viewLifecycleOwner) {
            adapterToSet.setData(it)
        }

        //observe refactoring theme to detect changes
        //maybe should be function
        sharedViewModel.themeDB.observe(viewLifecycleOwner){
            viewModel.addTheme(it)
        }
    }

    private fun setListeners(){
        //set up "add new" button
        binding.fab.setOnClickListener{
            sharedViewModel.setNewTheme(subjectId)
            findNavController().navigate(R.id.refactorTheme)
        }
    }

    fun changeTheme(themeDB: ThemeDB){
        sharedViewModel.setThemeToRefactor(themeDB)
        findNavController().navigate(R.id.refactorTheme)
    }

}