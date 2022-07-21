package com.arvinmarquez.expensebucket.presentation.categories.update

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arvinmarquez.expensebucket.core.utils.Resource
import com.arvinmarquez.expensebucket.features.category.domain.Category
import com.arvinmarquez.expensebucket.features.category.domain.use_case.AddCategoryUseCase
import com.arvinmarquez.expensebucket.features.category.domain.use_case.DeleteCategoryUseCase
import com.arvinmarquez.expensebucket.features.category.domain.use_case.UpdateCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateCategoryViewModel @Inject constructor(
    private val updateCategoryUseCase: UpdateCategoryUseCase,
    private val deleteCategoryUseCase: DeleteCategoryUseCase
) : ViewModel() {

    private var _category: Category? = null

    private var _state = MutableLiveData<Resource<Category>>()
    var savingState = _state as LiveData<Resource<Category>>

    private var _delete = MutableLiveData<Resource<Category>>()
    var deletionState = _delete as LiveData<Resource<Category>>

    fun setOpenedCategory(category: Category) {
        _category = _category ?: category
    }

    fun update() {
        viewModelScope.launch(Dispatchers.IO) {
            _category?.let {
                updateCategoryUseCase.update(it).onEach { result ->
                    when (result) {
                        is Resource.Loading -> {
                            _state.postValue(Resource.Loading())
                        }
                        is Resource.Success -> {
                            result.data?.let { it -> _state.postValue(Resource.Success(it)) }
                        }
                        is Resource.Error -> {
                            _state.postValue(
                                Resource.Error(
                                    result.message ?: "Unexpected error occurred"
                                )
                            )
                        }
                    }
                }.launchIn(this)
            }
        }
    }

    fun delete(){
        viewModelScope.launch(Dispatchers.IO) {
            _category?.let {
                deleteCategoryUseCase.delete(it).onEach { result ->
                    when (result) {
                        is Resource.Loading -> {
                            _delete.postValue(Resource.Loading())
                        }
                        is Resource.Success -> {
                            result.data?.let { it -> _delete.postValue(Resource.Success(it)) }
                        }
                        is Resource.Error -> {
                            _delete.postValue(
                                Resource.Error(
                                    result.message ?: "Unexpected error occurred"
                                )
                            )
                        }
                    }
                }.launchIn(this)
            }
        }
    }

    fun getDescription(): String = _category?.description ?: ""

    fun isExpense(): Boolean = _category?.isExpense ?: true

    fun setDescription(description: String) {
        _category?.description = description
    }

    fun setIsExpense(isExpense: Boolean) {
        _category?.isExpense = isExpense
    }

}