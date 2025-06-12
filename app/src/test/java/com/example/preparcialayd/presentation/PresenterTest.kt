package com.example.preparcialayd.presentation

import com.example.preparcialayd.model.DataRepo
import io.mockk.mockk
import io.mockk.every
import org.junit.Test
import kotlin.math.roundToInt

class PresenterTest {
 private val repository: DataRepo = mockk()
 private val presenter: Presenter = PresenterImpl(repository)

 @Test
 fun `fetch price should return correct price`() {
     val expectedPrice = 100.0
     every { repository.fetchPrice("USD") } returns expectedPrice
     presenter.fetchPrice("USD")

     presenter.viewObserver.subscribe { result ->
         assert(result.first == "USD")
         assert(result.second == expectedPrice.roundToInt())
     }
 }

}