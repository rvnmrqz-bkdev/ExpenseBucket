package com.arvinmarquez.expensebucket.presentation.cash_flow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.arvinmarquez.expensebucket.R
import com.arvinmarquez.expensebucket.databinding.CashflowDialogBinding
import com.arvinmarquez.expensebucket.domain.CashFlow

class CashFlowDialog(
    val title: String,
    var cashFlow: CashFlow?
) : DialogFragment() {

    private lateinit var binder: CashflowDialogBinding

    override fun getTheme(): Int {
        return R.style.DefaultDialogTheme
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binder = CashflowDialogBinding.inflate(inflater, container, false)
        return binder.root
    }


}