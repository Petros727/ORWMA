package hr.markopetricevic.autodiygarage.screens.addRepair

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.markopetricevic.autodiygarage.common.Resource
import hr.markopetricevic.autodiygarage.models.RepairsInfo
import hr.markopetricevic.autodiygarage.RepairRepository.RepairRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddRepairViewModel @Inject constructor(private val repairRepository: RepairRepository) :
    ViewModel() {

    private val _addRepairs = mutableStateOf(AddRepairsInfocreenState())
    val addRepairs: State<AddRepairsInfocreenState> = _addRepairs

    fun addAudi(repairInfo: RepairsInfo) = viewModelScope.launch(Dispatchers.IO) {
        _addRepairs.value =
            AddRepairsInfocreenState(isLoading = true)

        repairRepository.addAudi(repairInfo).collect {
            when (it) {
                is Resource.Error -> {
                    _addRepairs.value =
                        AddRepairsInfocreenState(isLoading = false, error = it.error)
                }

                is Resource.Success -> {

                    delay(2000)
                    it.data?.let {
                        _addRepairs.value =
                            AddRepairsInfocreenState(isLoading = false, data = true)
                    }

                }

                is Resource.Loading -> {
                    _addRepairs.value =
                        AddRepairsInfocreenState(isLoading = true)
                }
            }
        }
    }

    fun addBMW(repairInfo: RepairsInfo) = viewModelScope.launch(Dispatchers.IO) {
        _addRepairs.value =
            AddRepairsInfocreenState(isLoading = true)

        repairRepository.addBMW(repairInfo).collect {
            when (it) {
                is Resource.Error -> {
                    _addRepairs.value =
                        AddRepairsInfocreenState(isLoading = false, error = it.error)
                }

                is Resource.Success -> {

                    delay(2000)
                    it.data?.let {
                        _addRepairs.value =
                            AddRepairsInfocreenState(isLoading = false, data = true)
                    }

                }

                is Resource.Loading -> {
                    _addRepairs.value =
                        AddRepairsInfocreenState(isLoading = true)
                }
            }
        }
    }

    fun addMercedes(repairInfo: RepairsInfo) = viewModelScope.launch(Dispatchers.IO) {
        _addRepairs.value =
            AddRepairsInfocreenState(isLoading = true)

        repairRepository.addMercedes(repairInfo).collect {
            when (it) {
                is Resource.Error -> {
                    _addRepairs.value =
                        AddRepairsInfocreenState(isLoading = false, error = it.error)
                }

                is Resource.Success -> {

                    delay(2000)
                    it.data?.let {
                        _addRepairs.value =
                            AddRepairsInfocreenState(isLoading = false, data = true)
                    }

                }

                is Resource.Loading -> {
                    _addRepairs.value =
                        AddRepairsInfocreenState(isLoading = true)
                }
            }
        }
    }

    fun addMoreData() {
        _addRepairs.value = AddRepairsInfocreenState(isLoading = false, data = false)
    }

}

data class AddRepairsInfocreenState(
    val isLoading: Boolean = false,
    val data: Boolean = false,
    val error: String? = null
)