package com.example.preparcialayd.model

import com.example.preparcialayd.model.external.ExternalData
import com.example.preparcialayd.model.local.LocalData
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class DataRepoTest {
  private val localData : LocalData = mockk()
  private val externalData : ExternalData = mockk()
  private val dataRepo: DataRepo = DataRepoImpl(localData, externalData)

    @Test
     fun `on fetchPrice returns price from local data`() {
      val coin = "USD"
      val price = 100.0
      every { localData.getData(coin) } returns price

      val result = dataRepo.fetchPrice(coin)

      Assert.assertEquals(price, result, 0.0001)
     }

    @Test
    fun `on fetchPrice returns price from external data when local data is null`() {
      val coin = "EUR"
      val externalPrice = 200.0
      every { localData.getData(coin) } returns null
      every { externalData.getData(coin) } returns externalPrice

      val result = dataRepo.fetchPrice(coin)

      Assert.assertEquals(externalPrice, result, 0.0001)
    }

}