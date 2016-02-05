package uk.gov.homeoffice.presentation.futures

import scala.concurrent.{Promise, Future}
import org.specs2.concurrent.ExecutionEnv
import org.specs2.mutable.Specification

/**
  * Every promise object corresponds to exactly one future object.
  * To obtain the future associated with a promise, call the future method on the promise.
  * A promise and a future represent two aspects of a single-assignment variable:
  * the promise allows you to assign a value to the future object, whereas the future allows you to read that value.
  */
class Futures8Spec(implicit ev: ExecutionEnv) extends Specification {
  "Promise" should {
    "work with its future" in {
      class Logic {
        def callback = true
      }

      /* The following pattern is used a lot with the RTP Rabbit library where we would like to know if a callback was triggered without mocking */
      val promise = Promise[Boolean]()

      val logic = new Logic {
        override def callback = {
          val result = super.callback
          promise success true
          result
        }
      }

      Future {
        logic.callback
      }

      promise.future must beTrue.await
    }
  }
}