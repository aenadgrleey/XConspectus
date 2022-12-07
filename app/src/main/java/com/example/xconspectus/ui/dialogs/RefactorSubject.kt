package com.example.xconspectus.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.xconspectus.R
import com.example.xconspectus.data.SubjectDB
import com.example.xconspectus.databinding.RefactorSubjectDialogBinding
import com.example.xconspectus.ui.MainActivity
import com.example.xconspectus.ui.home.SubjectRefactorSharedViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar

class RefactorSubject : BottomSheetDialogFragment() {
    private lateinit var binding: RefactorSubjectDialogBinding
    private val viewModel: SubjectRefactorSharedViewModel by activityViewModels()
    private var subject: SubjectDB? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RefactorSubjectDialogBinding.inflate(inflater)
        getSubjectToRefactor()
        binding.addItem.hint = "Add item"
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        binding.addItem.setOnEditorActionListener() { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                this.insertSubject()
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

    private fun insertSubject() {
        with(binding.addItem.text.toString()) {
            if(this != null || this != "") {
                viewModel.refactorSubjectName(this)
                viewModel.subjectRefactored()
                dialog!!.dismiss()
            }
        }
    }

    private fun getSubjectToRefactor() {
        subject = viewModel.subjectDB
        if (subject != null) {
            binding.addItem.setText(subject!!.name)
        }
    }
}


