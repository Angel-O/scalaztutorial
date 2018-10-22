package day0

trait Plus[A] {
  def plus(a1: A, a2: A): A
}

object Plus{
//  implicit class IntPlus(a: Int) extends Plus[Int]{
//    def plus(b: Int) = a + b
//  }
}