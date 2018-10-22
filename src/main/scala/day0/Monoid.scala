package day0

trait Monoid[A] {
  def mappend(a: A, b: A): A

  def mzero: A
}

trait HigherKindedMonoid[HK[_]] extends Monoid[HK[_]] {
  def mappend[A](a: HK[A], b: HK[A]): HK[A]
  def mzero[A]: HK[A]
}

//class HKMonoid[A] extends HigherKindedMonoid[List, A]

object Monoid {
  implicit val IntMonoid: Monoid[Int] = new Monoid[Int] {
    def mappend(a: Int, b: Int): Int = a + b
    def mzero: Int = 0
  }

  implicit val StringMonoid: Monoid[String] = new Monoid[String] {
    def mappend(a: String, b: String): String = a + b
    def mzero: String = ""
  }

//  implicit val ListMonoid: HigherKindedMonoid[List] = new HigherKindedMonoid[List] {
//    def mappend[A](a: List[A], b: List[A]): List[A] = a ++ b
//    def mzero[A]: List[A] = Nil
//  }
  
  implicit val ListMonoid = new HigherKindedMonoid[List] {
    def mappend[A](a: List[A], b: List[A]): List[A] = a ++ b
    def mzero[A]: List[A] = Nil
  }
  
//  implicit val VectorMonoid = new HigherKindedMonoid[Vector, _] {
//    def mappend[A](a: Vector[A], b: Vector[A]): Vector[A] = a ++ b
//    def mzero[A]: Vector[A] = Vector.empty
//  }

  //  implicit val multiMonoid: Monoid[Int] = new Monoid[Int] {
  //    def mappend(a: Int, b: Int): Int = a * b
  //    def mzero: Int = 1
  //  }
}