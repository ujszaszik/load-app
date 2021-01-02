package com.udacity.loadapp.fetcher.fancybutton.states

sealed class ButtonState {

    object Initial : ButtonState()

    object Loading : ButtonState()

    object Finished : ButtonState()

    object ShowIcon : ButtonState()

}