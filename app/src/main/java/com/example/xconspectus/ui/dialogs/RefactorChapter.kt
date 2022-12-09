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
import com.example.xconspectus.ui.theme.ChapterRefactorSharedViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class RefactorChapter : BottomSheetDialogFragment() {
    //Using same layout as RefactorSubject
    private lateinit var binding: RefactorSubjectDialogBinding

    private val viewModel: ChapterRefactorSharedViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RefactorSubjectDialogBinding.inflate(inflater)
        binding.addItem.hint = "Add item"
        setSubjectAttributes()
        binding.addItem.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) this.insertChapter()
            true
        }
        return binding.root

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
        dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        return dialog
    }

    private fun insertChapter() {
        val name = binding.addItem.text.toString()
        if (checkName(name)) {
            viewModel.updateChapter(name)
            dialog!!.dismiss()
        }
    }

    private fun setSubjectAttributes() {
        val subject = viewModel.chapterDB
        binding.addItem.setText(subject!!.name)
    }

    private fun checkName(name: String) = name != ""
}


