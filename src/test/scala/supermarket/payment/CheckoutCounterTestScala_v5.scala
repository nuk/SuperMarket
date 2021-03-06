package supermarket.payment

import supermarket.{SupermarketItem, ShoppingCart}
import supermarket.taxes.Tax
import org.specs2.mutable._

class CheckoutCounterTestScala_v5 extends Specification with PriceConverter {

  private val riceItem = new SupermarketItem("Rice", (2,47), PlusOneTaxStub)
  private val beansItem = new SupermarketItem("Beans", (0,99), PlusOneTaxStub)
  def counter = new CheckoutCounter
  def cart = new ShoppingCart() add riceItem add riceItem add beansItem add beansItem

  "knows that an empty cart has price zero" in {
    whenCheckingOut in counter theTotalPriceFor new ShoppingCart should be equalTo((0,0))
  }

  "sums the total price of the items in the cart" in {
    whenCheckingOut in counter theTotalPriceFor cart should be equalTo((10,92))
  }

  object PlusOneTaxStub extends Tax {
    def calculate(p: Price) = (1,0)
  }
}

