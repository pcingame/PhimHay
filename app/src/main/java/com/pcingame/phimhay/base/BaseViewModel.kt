package com.pcingame.phimhay.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pcingame.phimhay.common.DebugLog
import com.pcingame.phimhay.common.error.handleException
import com.pcingame.phimhay.common.utils.SingleLiveData
import kotlinx.coroutines.*
import org.koin.core.component.KoinComponent

abstract class BaseViewModel : ViewModel(), KoinComponent {


    val mLoading = SingleLiveData<Boolean>()
    val mError = SingleLiveData<Throwable>()

    private val debugLog: DebugLog by lazy { DebugLog() }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        debugLog.e(throwable.stackTraceToString())
        mError.value = handleException(throwable)
        mLoading.value = false
    }

    protected val scope = viewModelScope.plus(exceptionHandler)

    /**
     * @param scope coroutine scope to execute task
     * @param onError the callback run when had error
     * @param onExecute the action to execute task
     * */
    protected fun <T> executeTask(
        scope: CoroutineScope = this.scope,
        onError: ((Exception) -> Unit)? = null,
        onExecute: suspend () -> T,
    ) {
        scope.launch(Dispatchers.Main) {
            try {
                onExecute()
            } catch (e: Exception) {
                onError?.invoke(e) ?: throw e
            }
        }
    }
}
