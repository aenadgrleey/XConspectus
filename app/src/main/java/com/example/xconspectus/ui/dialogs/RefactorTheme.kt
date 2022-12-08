package com.example.xconspectus.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.DialogFragment
import com.example.xconspectus.R
import com.example.xconspectus.databinding.RefactorSubjectDialogBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class RefactorTheme : BottomSheetDialogFragment() {
    private lateinit var binding: RefactorSubjectDialogBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RefactorSubjectDialogBinding.inflate(inflater)
        binding.addItem.hint = "Add item"
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        binding.addItem.text = null
        binding.addItem.setOnEditorActionListener { _, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                this.insertTheme()
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

    private fun insertTheme(){
    }
}