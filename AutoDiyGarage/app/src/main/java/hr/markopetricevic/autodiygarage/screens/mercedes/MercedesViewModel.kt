package hr.markopetricevic.autodiygarage.screens.mercedes


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
class MercedesViewModel @Inject constructor(private val repairRepository: RepairRepository) :
    ViewModel() {

    private val _mercedesList = mutableStateOf(MercedesScreenState())
    val mercedesList: State<MercedesScreenState> = _mercedesList

    fun getMercedesList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repairRepository.getMercedesList().collect {
                    when (it) {
                        is Resource.Success -> {
                            it.data?.let {
                                _mercedesList.value =
                                    MercedesScreenState(data = it, isLoading = false)
                            }
                        }

                        is Resource.Loading -> {
                            _mercedesList.value = MercedesScreenState(isLoading = true)
                        }

                        is Resource.Error -> {
                            _mercedesList.value =
                                MercedesScreenState(isLoading = false, error = it.error)
                        }
                    }
                }

            } catch (e: Exception) {
                _mercedesList.value =
                    MercedesScreenState(isLoading = false, error = "Failed to fetch data")
            }
        }
    }

    fun removeMercedes(RepairsInfo: RepairsInfo) = viewModelScope.launch(Dispatchers.IO) {
        repairRepository.removeMercedes(RepairsInfo.uid).collect {

        }
    }

}


data class MercedesScreenState(
    val isLoading: Boolean = true,
    val data: List<RepairsInfo> = emptyList(),
    val error: String? = null
)