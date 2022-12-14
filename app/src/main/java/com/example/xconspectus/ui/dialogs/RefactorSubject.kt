package com.example.xconspectus.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.xconspectus.R
import com.example.xconspectus.databinding.RefactorSubjectDialogBinding
import com.example.xconspectus.ui.home.SubjectRefactorSharedViewModel
import com.example.xconspectus.ui.text_editor.TextEditor
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

@Suppress("DEPRECATION")
class RefactorSubject : BottomSheetDialogFragment() {
    private lateinit var binding: RefactorSubjectDialogBinding
    private val viewModel: SubjectRefactorSharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RefactorSubjectDialogBinding.inflate(inflater)
        setSubjectAttributes()
        binding.addItem.hint = "Add item"
        binding.addItem.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) this.insertSubject()
            true
        }
        binding.addItem.addTextChangedListener(TextEditor(binding.addItem))
        return binding.root

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
        dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        return dialog
    }

    private fun insertSubject() {
        val name = binding.addItem.text.toString()
        if (checkName(name)) {
            viewModel.updateSubject(name)
            dialog!!.dismiss()
        }
    }

    private fun setSubjectAttributes() {
        val subject = viewModel.subjectDB
        binding.addItem.setText(subject!!.name)
    }

    private fun checkName(name: String) = name != ""
}


