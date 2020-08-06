package com.example.mobiquitytask.api.model

import com.example.mobiquitytask.data.model.api.ProductsItem
import com.example.mobiquitytask.data.model.api.SalePrice
import com.google.gson.annotations.SerializedName
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ProductItemTest {

    private val amount: String = "2.0"
    private val currency: String = "EUR"

    private val name: String = "Bread"
    private val description: String = "This is a bread"
    private val id: String = "1"
    private val categoryId: String = "36802"
    private val url: String = "/Bread.jpg"

    @Mock
    lateinit var salePrice: SalePrice
    @Mock
    lateinit var productsItem: ProductsItem

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(productsItem.id).thenReturn(id)
        Mockito.`when`(productsItem.categoryId).thenReturn(categoryId)
        Mockito.`when`(productsItem.name).thenReturn(name)
        Mockito.`when`(productsItem.description).thenReturn(description)
        Mockito.`when`(productsItem.url).thenReturn(url)
        Mockito.`when`(productsItem.salePrice).thenReturn(salePrice)

        Mockito.`when`(salePrice.amount).thenReturn(amount)
        Mockito.`when`(salePrice.currency).thenReturn(currency)
    }

    @Test
    fun testSalePriceAmount() {
        Assert.assertEquals("2.0", salePrice.amount)
    }

    @Test
    fun testSalePriceCurrency() {
        Assert.assertEquals("EUR", salePrice.currency)
    }

    @Test
    fun testProductName() {
        Assert.assertEquals("Bread", productsItem.name)
    }

    @Test
    fun testProductDescription() {
        Assert.assertEquals("This is a bread", productsItem.description)
    }

    @Test
    fun testProductId() {
        Assert.assertEquals("1", productsItem.id)
    }

    @Test
    fun testProductCategoryId() {
        Assert.assertEquals("36802", productsItem.categoryId)
    }

    @Test
    fun testProductUrl() {
        Assert.assertEquals("/Bread.jpg", productsItem.url)
    }

    @Test
    fun testProductSalePrice() {
        Assert.assertEquals(salePrice, productsItem.salePrice)
    }

 /*   @After
    @Throws(Exception::class)
    fun tearDown() {
        salePrice = null
        productsItem = null
    }*/
}