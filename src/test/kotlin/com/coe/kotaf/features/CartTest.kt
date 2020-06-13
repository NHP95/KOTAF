package com.coe.kotaf.features

import com.coe.kotaf.base.BaseGUI
import com.coe.kotaf.enum.ProductType
import com.coe.kotaf.pages.HomePage
import com.coe.kotaf.pages.ProductDetailPage
import com.coe.kotaf.pages.ProductPage
import com.coe.kotaf.pages.steps
import org.junit.jupiter.api.Test

class CartTest : BaseGUI() {
    @Test
    fun `Add a single item to cart successfully`() {
        steps<HomePage> {
            openPage()
            mainMenu.goToProductPage(ProductType.ACCESSORIES)
        }
        steps<ProductPage> {
            selectARandomProduct()
        }
        steps<ProductDetailPage> {
            val product = addProductToCart()
            shouldDisplayInCart(product)
        }
    }
}