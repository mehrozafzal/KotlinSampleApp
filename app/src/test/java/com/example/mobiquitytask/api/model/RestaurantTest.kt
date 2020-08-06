package com.example.mobiquitytask.api.model

import com.example.mobiquitytask.data.model.api.ProductsItem
import com.example.mobiquitytask.data.model.api.RestaurantResponse
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class RestaurantTest {

    private val name: String = "Cakes and Bakes"
    private val description: String = "This is a bakery"
    private val id: String = "2"
    private val products: List<ProductsItem> = ArrayList<ProductsItem>()

    @Mock
    lateinit var restaurantResponse: RestaurantResponse

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(restaurantResponse.id).thenReturn(id)
        Mockito.`when`(restaurantResponse.description).thenReturn(description)
        Mockito.`when`(restaurantResponse.name).thenReturn(name)
        Mockito.`when`(restaurantResponse.products).thenReturn(products)
    }

    @Test
    fun testRestaurantId() {
        Assert.assertEquals("2", restaurantResponse.id)
    }

    @Test
    fun testRestaurantName() {
        Assert.assertEquals("Cakes and Bakes", restaurantResponse.name)
    }

    @Test
    fun testRestaurantDescription() {
        Assert.assertEquals("This is a bakery", restaurantResponse.description)
    }

    @Test
    fun testRestaurantProducts() {
        Assert.assertEquals(ArrayList<ProductsItem>(), restaurantResponse.products)
    }
}