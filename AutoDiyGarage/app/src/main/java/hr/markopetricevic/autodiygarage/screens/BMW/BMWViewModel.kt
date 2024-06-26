package hr.markopetricevic.autodiygarage.screens.BMW

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.markopetricevic.autodiygarage.common.Resource
import hr.markopetricevic.autodiygarage.models.RepairsInfo
import hr.markopetricevic.autodiygarage.RepairRepository.RepairRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BMWViewModel @Inject constructor(private val repairRepository: RepairRepository) :
    ViewModel() {

    private val _repairList = mutableStateOf(BMWScreenState())
    val repairList: State<BMWScreenState> = _repairList

    fun getBMWList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repairRepository.getBMWList().collect { it ->
                    when (it) {
                        is Resource.Success -> {
                            it.data?.let {
                                _repairList.value =
                                    BMWScreenState(data = it, isLoading = false)
                            }
                        }

                        is Resource.Loading -> {
                            _repairList.value = BMWScreenState(isLoading = true)
                        }

                        is Resource.Error -> {
                            _repairList.value =
                                BMWScreenState(isLoading = false, error = it.error)
                        }
                    }
                }

            } catch (e: Exception) {
                _repairList.value =
                    BMWScreenState(isLoading = false, error = "Failed to fetch data")
            }
        }
    }

    fun removeFromLrepairList(repairInfo: RepairsInfo) =
        viewModelScope.launch(Dispatchers.IO) {
            repairRepository.removeBMW(repairInfo.uid).collect {

            }
        }

}

data class BMWScreenState(
    val isLoading: Boolean = true,
    val data: List<RepairsInfo> = emptyList(),
    val error: String? = null
)