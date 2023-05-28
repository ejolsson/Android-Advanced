package ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.androidadvanced.data.Repository
import com.example.androidadvanced.ui.home.HeroViewModel
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule

class SuperHeroViewModelTest {

    @get:Rule // L5, 1.04.00
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // UUT o SUT
    private lateinit var viewModel: HeroViewModel

    // Mocks
    private lateinit var repository: Repository

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        repository = mockk() // L5, 0.36.45
        viewModel = HeroViewModel(repository)
    }
}