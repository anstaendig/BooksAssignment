package com.marcelholter.booksassignment.presentation

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

/**
 * Factory to provide [ViewModel]s based on a dagger generated map that matches classname and
 * provider.
 */
class ViewModelFactory
@Inject constructor(
    private val creators: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {
  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    var creator: Provider<out ViewModel>? = creators[modelClass]
    if (creator == null) {
      for (entry in creators.entries) {
        if (modelClass.isAssignableFrom(entry.key)) {
          creator = entry.value
          break
        }
      }
    }
    if (creator == null) {
      throw IllegalArgumentException("Unknown model class $modelClass")
    }
    try {
      return creator.get() as T
    } catch (e: ClassCastException) {
      throw ClassCastException("${creator.get()} cannot be cast!")
    }
  }
}
