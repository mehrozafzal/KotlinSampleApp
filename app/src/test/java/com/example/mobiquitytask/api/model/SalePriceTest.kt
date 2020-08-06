package com.example.mobiquitytask.api.model

import com.example.mobiquitytask.data.model.api.SalePrice
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SalePriceTest {
    private val amount: String = "100000"
    private val currency: String = "EUR 5.0"

    @Mock
    lateinit var salePrice: SalePrice

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(salePrice.amount).thenReturn(amount)
        Mockito.`when`(salePrice.currency).thenReturn(currency)
    }

    @Test
    fun testSalePriceAmount() {
        Assert.assertEquals("100000", salePrice.amount)
    }

    @Test
    fun testSalePriceCurrency() {
        Assert.assertEquals("EUR 5.0", salePrice.currency)
    }
}