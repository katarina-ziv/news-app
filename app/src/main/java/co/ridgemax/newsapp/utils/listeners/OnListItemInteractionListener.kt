package co.ridgemax.newsapp.utils.listeners

import co.ridgemax.newsapp.utils.enums.AppAction

interface OnListItemInteractionListener<T> {
    fun onListItemInteraction(position: Int, item: T, appAction: AppAction)
}