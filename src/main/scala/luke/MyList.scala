package luke

import luke.MyInts._ 
import scala.compiletime._
import scala.compiletime.ops.int._

import luke.MyInts._

sealed trait MyList[+T, S <: Int] {
  // def apply(n: Bounded[0, SIZE - 1]): T = ??? // Throw an exception

  import luke.MyList._
  // def drop(n: Bounded[0, SIZE]): MyList[0, SIZE - n.type] = ??? //Drop[T, SIZE, n.type] = ???
  
  def drop(n: Bounded[0, S]): Drop[T, S, n.type] = {
    doDrop(this, n).asInstanceOf[Drop[T, S, n.type]]
  }

  def drop2(n: Bounded[0, S]): MyList[T, S - n.type] = {
    doDrop(this, n).asInstanceOf[MyList[T, S - n.type]]
  }

}

case object MyNil extends MyList[Nothing, 0]
case class MyCons[+T, N <: Int](h: T, t: MyList[T, N]) extends MyList[T, S[N]]

object MyList {
  type Drop[+T, N <: Int, NUMTODROP <: Int] <: MyList[T, ?] = NUMTODROP match {
    case 0 => MyList[T, N]
    case S[nMinus1] => Drop[T, N -1, nMinus1]
  }

  private def doDrop[T](l: MyList[T, _], n: Int): MyList[T, _] = {
    if (n == 0) l
    else {
      l match {
        case MyNil => MyNil
        case MyCons(h, t) => doDrop(t, n - 1)
      }
    }
  }
}

object ListExample {
  val list: MyList[Int, 1] = MyCons(123, MyNil)

  val listOf2: MyList[Int, 2] = MyCons(1, MyCons(2, MyNil))

  def main(args: Array[String]): Unit = {
    println(listOf2)

    println(listOf2.drop(1))

    println(listOf2.drop(2))

    println(listOf2.drop(1).drop(1))

    // This will not compile
    // listOf2.drop(3)
  }
}