package hr.markopetricevic.autodiygarage.screens.Audi

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
class AudiViewModel @Inject constructor(private val repairRepository: RepairRepository) :
    ViewModel() {

    private val _AudiList = mutableStateOf(AudiScreenState())
    val AudiList: State<AudiScreenState> = _AudiList

    fun getAudiList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repairRepository.getAudiList().collect {
                    when (it) {
                        is Resource.Success -> {
                            it.data?.let {
                                _AudiList.value =
                                    AudiScreenState(data = it, isLoading = false)
                            }
                        }

                        is Resource.Loading -> {
                            _AudiList.value = AudiScreenState(isLoading = true)
                        }

                        is Resource.Error -> {
                            _AudiList.value =
                                AudiScreenState(isLoading = false, error = it.error)
                        }
                    }
                }

            } catch (e: Exception) {
                _AudiList.value =
                    AudiScreenState(isLoading = false, error = "Failed to fetch data")
            }
        }
    }

    fun removeAudi(RepairsInfo: RepairsInfo) = viewModelScope.launch(Dispatchers.IO) {
        repairRepository.removeAudi(RepairsInfo.uid).collect {

        }
    }

}


data class AudiScreenState(
    val isLoading: Boolean = true,
    val data: List<RepairsInfo> = emptyList(),
    val error: String? = null
)