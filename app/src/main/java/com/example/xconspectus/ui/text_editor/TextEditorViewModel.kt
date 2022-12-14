package com.example.xconspectus.ui.text_editor

import android.graphics.Color
import androidx.lifecycle.ViewModel

class TextEditorViewModel : ViewModel() {

    private var _highlighted = false
    private var _highlightColor: Int = Color.BLUE
    val highlighted get() = _highlighted
    val highlightColor get() = _highlightColor

    private var _coloredText = false
    private var _textColor = Color.BLACK
    val coloredText get() = _coloredText
    val textColor get() = _coloredText

    private var _bold = false
    val bold get() = _bold

    private var _italic = false
    val italic = _italic

    private var _underline = false
    val underline get() = _underline

    private var _strikeThrough = false
    val strikeThrough get() = _strikeThrough

    private var _fontSizeMultiplier: Float = 1F
    val fontSizeMultiplier get() = _fontSizeMultiplier

    fun changeFontSizeMultiplier(newMultiplier : Float){
        _fontSizeMultiplier = newMultiplier
    }
}