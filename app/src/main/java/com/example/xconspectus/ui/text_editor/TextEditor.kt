package com.example.xconspectus.ui.text_editor

import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan

class TextEditor : TextWatcher {
    private var _ignore = false
    private var editingString: SpannableStringBuilder = SpannableStringBuilder("")
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        TODO("Not yet implemented")
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        editingString = SpannableStringBuilder(s)
        editingString.setSpan(
            Spanned.SPAN_COMPOSING,
            start,
            start + count,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

    }

    override fun afterTextChanged(s: Editable?) {
        var spans = editingString.getSpans<Any>(0, editingString.length, Any::class.java)
        for (span in spans) {
            if (span == Spanned.SPAN_COMPOSING) {
                editingString.setSpan(
                    ForegroundColorSpan(android.graphics.Color.RED),
                    editingString.getSpanStart(span),
                    editingString.getSpanEnd(span),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }

    }

    private fun textTransformation(text: Editable?) {

    }
}