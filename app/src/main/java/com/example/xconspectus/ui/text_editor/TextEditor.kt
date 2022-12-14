package com.example.xconspectus.ui.text_editor

import android.graphics.Color
import android.graphics.Typeface
import android.text.Editable
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextWatcher
import android.text.style.StyleSpan
import android.widget.EditText

class TextEditor(val editor: EditText) : TextWatcher {
    private var _ignore = true
    private var editingString: SpannableStringBuilder = SpannableStringBuilder("")
    private var editingStringStart = 0




    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        if (before > count) {
            editingStringStart = -1
            return
        }
        if (_ignore) {
            editingString = SpannableStringBuilder(s.drop(s.length - count))
            editingStringStart = start
            editingString.setSpan(
                Spanned.SPAN_COMPOSING,
                before,
                count,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
    }

    override fun afterTextChanged(s: Editable?) {
        if (editingStringStart == -1) return
        if (_ignore) {
            _ignore = false
            var spans = editingString.getSpans<Any>(0, editingString.length - 1, Any::class.java)
            for (span in spans) {
                if (span == Spanned.SPAN_COMPOSING) {
                    editingString.setSpan(
                        StyleSpan(Typeface.BOLD),
                        editingString.getSpanStart(span),
                        editingString.getSpanEnd(span),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE

                    )
                }
            }
            editingString.removeSpan(Spanned.SPAN_COMPOSING)

            s!!.replace(editingStringStart,
                editingStringStart + editingString.length,
                editingString)
            _ignore = true
        }
    }

    private fun transFormText(textToEdit: Spannable){

    }



}