package uk.gov.homeoffice.presentation.futures

import java.util.concurrent.TimeUnit
import scala.concurrent.duration._
import scala.concurrent.Future
import org.specs2.concurrent.ExecutionEnv
import org.specs2.mutable.Specification

/**
  * Futures can be transformed and composed via standard monadic methods flatMap and map.
  */
class Futures3Spec(implicit ev: ExecutionEnv) extends Specification {
  "Futures composition" should {
    "run in parallel" in {
      val xCalc: Future[Int] = Future {
        TimeUnit.SECONDS.sleep(2)
        2
      }

      val yCalc: Future[Int] = Future {
        TimeUnit.SECONDS.sleep(4)
        4
      }

      val zCalc: Future[Int] = Future {
        TimeUnit.SECONDS.sleep(6)
        6
      }

      val outcome = for {
        x <- xCalc
        y <- yCalc
        z <- zCalc
      } yield x + y + z

      outcome must beEqualTo(12).awaitFor(7 seconds)
    }
  }
}
