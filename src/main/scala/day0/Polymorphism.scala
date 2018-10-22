package day0

object Polymorphism extends App {
  def head[A](xs: List[A]): A = xs(0)

  def plus[A: Plus](a1: A, a2: A): A = implicitly[Plus[A]].plus(a1, a2)
  
  def sum(xs: List[Int]): Int = xs.foldLeft(0) { _ + _ }
  
  def sum2(xs: List[Int]): Int = xs.foldLeft(IntMonoid.mzero)(IntMonoid.mappend)
  
  def sum3(xs: List[Int])(implicit m: Monoid[Int]): Int = xs.foldLeft(m.mzero)(m.mappend)
  
  val result3 = sum3(List(1,2,3))(IntMonoid)
  
  def sum4[A](xs: List[A])(implicit m: Monoid[A]): A = xs.foldLeft(m.mzero)(m.mappend)
  
  var result4 = sum4(List(1,2,3))
  
  // A: Monoid is a "context bound"
  def sum5[A: Monoid](xs: List[A]): A = {
    val m = implicitly[Monoid[A]]
    xs.foldLeft(m.mzero)(m.mappend)
  }
  
  val result5 = sum5(List(1,2,3))
  
  require(result5 > 0)
  
  assert(result5 > 0)
  
  assume(result5 > 0)
  
  def plus[A: Monoid](a: A, b: A): A = implicitly[Monoid[A]].mappend(a, b)
  
  import MethodInjection._
  
  3 |+| 4
  
  "hello" |+| "world"
  
  trait FoldLeft[F[_]]{
    def foldLeft[A,B](xs: F[A], initial: B, f: (B, A) => B): B
  }
  
  object FoldLeft{
    implicit object FoldLeftList extends FoldLeft[List] {
      def foldLeft[A, B](xs: List[A], initial: B, f: (B, A) => B): B = xs.foldLeft(initial)(f)
    }
  }
  
//  trait FoldLeftList[A] {
//    def foldLeft(m: Monoid[A], xs: List[A]) = xs.foldLeft(m.mzero)(m.mappend)
//  }
  
  import FoldLeft._
  
  def sum6[A: Monoid](xs: List[A]): A = {
    val m = implicitly[Monoid[A]]
    FoldLeftList.foldLeft(xs, m.mzero, m.mappend)
  }
  
  def sum7[M[_], A](xs: M[A])(implicit m: Monoid[A], fl: FoldLeft[M]) = {   
    fl.foldLeft(xs, m.mzero, m.mappend)
  }
  
  sum7(List("hello", "world"))
  
   List(1,2,3) |+| List(4,5,6)
   
   List("hello", "world") |+| List("yay", "!!")
   
   List("hello", "world", 1, 56, null) |+| List("yay", "!!")
   
   //Vector("Hello") |+| Vector("World")
 
}