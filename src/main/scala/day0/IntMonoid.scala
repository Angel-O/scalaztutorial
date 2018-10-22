package day0

object IntMonoid extends Monoid[Int] {
  def mappend(a: Int, b: Int):Int = a + b
  
  def mzero = 0
}