package org.koin.sample.androidx.mvp

import android.os.Bundle
import kotlinx.android.synthetic.main.mvp_activity.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.koin.androidx.scope.ScopeActivity
import org.koin.core.parameter.parametersOf
import org.koin.sample.android.R
import org.koin.sample.androidx.components.ID
import org.koin.sample.androidx.components.mvp.FactoryPresenter
import org.koin.sample.androidx.components.mvp.ScopedPresenter
import org.koin.sample.androidx.mvvm.MVVMActivity
import org.koin.sample.androidx.utils.navigateTo

class MVPActivity : ScopeActivity(contentLayoutId = R.layout.mvp_activity) {

    // Inject presenter as Factory
    val factoryPresenter: FactoryPresenter by inject { parametersOf(ID) }

    // Inject presenter from MVPActivity's scope
    val scopedPresenter: ScopedPresenter by inject { parametersOf(ID) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        assertEquals(factoryPresenter.id, scopedPresenter.id)
        assertEquals(factoryPresenter.service, scopedPresenter.service)

        assertNotEquals(get<FactoryPresenter> { parametersOf(ID) }, factoryPresenter)
        assertEquals(get<ScopedPresenter>(), scopedPresenter)

        title = "Android MVP"

        mvp_button.setOnClickListener {
            navigateTo<MVVMActivity>(isRoot = true)
        }
    }
}