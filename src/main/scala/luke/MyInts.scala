package luke

import scala.compiletime._
import scala.compiletime.ops.int._

object MyInts {

  // data Number = 0 | S[Number]

  type Plus[A <: Int, B <: Int] <: Int = A match {
    case 0 => B
    case S[aMinus1] => Plus[aMinus1, S[B]]
  }

  type Bounded[MIN <: Int, MAX <: Int] <: Int = MAX match {
    case MIN => MIN
    case ? => MAX | Bounded[MIN, MAX - 1]
  }

  val a: 1 = 1
  val b: 1 + 2 = 3

  val x: 3 = 3

  val foo: Bounded[0, 1] = 1

}