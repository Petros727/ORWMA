package hr.markopetricevic.autodiygarage.RepairRepository

import com.google.firebase.firestore.FirebaseFirestore
import hr.markopetricevic.autodiygarage.common.Resource
import hr.markopetricevic.autodiygarage.models.RepairsInfo
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

const val COLLECTION_Audi = "Audi"
const val COLLECTION_BMW = "BMW"
const val COLLECTION_Mercedes = "Mercedes"

class RepairRepository @Inject constructor() {

    suspend fun getAudiList(): Flow<Resource<List<RepairsInfo>>> = callbackFlow {
        val snapshotListener = FirebaseFirestore.getInstance().collection(COLLECTION_Audi)
            .addSnapshotListener { snapshot, e ->
                val snap = if (snapshot != null) {
                    Resource.Success(snapshot.toObjects(RepairsInfo::class.java))
                } else {
                    Resource.Error(e?.message ?: "Something went wrong")
                }
                trySend(snap)
            }
        awaitClose {
            snapshotListener.remove()
        }
    }

    suspend fun removeAudi(uId: String): Flow<Resource<Unit>> = flow {
        try {
            val querySnapshot =
                FirebaseFirestore.getInstance().collection(COLLECTION_Audi)
                    .whereEqualTo("uid", uId).get().await()

            for (document in querySnapshot.documents) {
                FirebaseFirestore.getInstance().collection(COLLECTION_Audi)
                    .document(document.id).delete().await()
            }

            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Failed to remove data"))
        }
    }

    suspend fun addAudi(mashroomInfo: RepairsInfo): Flow<Resource<String>> =
        callbackFlow {
            try {
                FirebaseFirestore.getInstance().collection(COLLECTION_Audi)
                    .add(mashroomInfo).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            trySend(Resource.Success("Repair Added"))
                        } else {
                            trySend(Resource.Error("Failed to add data: ${task.exception?.message}"))
                        }
                    }

                awaitClose {

                }
            } catch (e: Exception) {
                trySend(Resource.Error("Failed to add data: ${e.message}"))
            }
        }

    suspend fun getBMWList(): Flow<Resource<List<RepairsInfo>>> = callbackFlow {
        val snapshotListener = FirebaseFirestore.getInstance().collection(COLLECTION_BMW)
            .addSnapshotListener { snapshot, e ->
                val snap = if (snapshot != null) {
                    Resource.Success(snapshot.toObjects(RepairsInfo::class.java))
                } else {
                    Resource.Error(e?.message ?: "Something went wrong")
                }
                trySend(snap)
            }
        awaitClose {
            snapshotListener.remove()
        }

    }

    suspend fun removeBMW(uId: String): Flow<Resource<Unit>> = flow {
        try {
            val querySnapshot =
                FirebaseFirestore.getInstance().collection(COLLECTION_BMW)
                    .whereEqualTo("uid", uId).get().await()

            for (document in querySnapshot.documents) {
                FirebaseFirestore.getInstance().collection(COLLECTION_BMW)
                    .document(document.id).delete().await()
            }

            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Failed to remove data"))
        }
    }

    suspend fun addBMW(mashroomInfo: RepairsInfo): Flow<Resource<String>> =
        callbackFlow {
            try {
                FirebaseFirestore.getInstance().collection(COLLECTION_BMW)
                    .add(mashroomInfo).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            trySend(Resource.Success("Repair Added"))
                        } else {
                            trySend(Resource.Error("Failed to add data: ${task.exception?.message}"))
                        }
                    }

                awaitClose {

                }
            } catch (e: Exception) {
                trySend(Resource.Error("Failed to add data: ${e.message}"))
            }
        }

    suspend fun getMercedesList(): Flow<Resource<List<RepairsInfo>>> = callbackFlow {
        val snapshotListener = FirebaseFirestore.getInstance().collection(COLLECTION_Mercedes)
            .addSnapshotListener { snapshot, e ->
                val snap = if (snapshot != null) {
                    Resource.Success(snapshot.toObjects(RepairsInfo::class.java))
                } else {
                    Resource.Error(e?.message ?: "Something went wrong")
                }
                trySend(snap)
            }
        awaitClose {
            snapshotListener.remove()
        }
    }

    suspend fun removeMercedes(uId: String): Flow<Resource<Unit>> = flow {
        try {
            val querySnapshot =
                FirebaseFirestore.getInstance().collection(COLLECTION_Mercedes)
                    .whereEqualTo("uid", uId).get().await()

            for (document in querySnapshot.documents) {
                FirebaseFirestore.getInstance().collection(COLLECTION_Mercedes)
                    .document(document.id).delete().await()
            }

            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Failed to remove data"))
        }
    }

    suspend fun addMercedes(mashroomInfo: RepairsInfo): Flow<Resource<String>> =
        callbackFlow {
            try {
                FirebaseFirestore.getInstance().collection(COLLECTION_Mercedes)
                    .add(mashroomInfo).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            trySend(Resource.Success("Repair Added"))
                        } else {
                            trySend(Resource.Error("Failed to add data: ${task.exception?.message}"))
                        }
                    }

                awaitClose {

                }
            } catch (e: Exception) {
                trySend(Resource.Error("Failed to add data: ${e.message}"))
            }
        }

}