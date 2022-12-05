package com.example.xconspectus.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.xconspectus.R
import com.example.xconspectus.data.repositories.SubjectsRepository
import com.example.xconspectus.data.SubjectDB
import com.example.xconspectus.databinding.RefactorSubjectDialogBinding
import com.example.xconspectus.ui.home.SubjectRefactorSharedViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class RefactorSubject : BottomSheetDialogFragment() {
    private lateinit var binding: RefactorSubjectDialogBinding
    private val viewModel : SubjectRefactorSharedViewModel by viewModels()
    var inputtedName : String = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RefactorSubjectDialogBinding.inflate(inflater)
        getSubjectToRefactor()
        binding.addItem.hint = "Add item"
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        binding.addItem.setOnEditorActionListener(){ v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                this.insertSubject()
                this.onDestroy()
                true
            } else {
                false
            }
        }
        return binding.root

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        return dialog
    }

    override fun onStart() {
        super.onStart()
    }

    private fun insertSubject(){
        val subject = SubjectDB(0, binding.addItem.text.toString())
        viewModel.subjectRefactored(subject)
    }

    private fun getSubjectToRefactor(){
        val subject = viewModel.getSubject()
        if(subject != null){
            binding.addItem.setText(subject!!.name)
        }
    }
}


