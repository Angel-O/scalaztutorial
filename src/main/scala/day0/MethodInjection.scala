package day0

object MethodInjection {
  trait MonoidOp[A] {
    val F: Monoid[A]
    val value: A
    def |+|(a2: A) = F.mappend(value, a2)
  }
  
  trait HKMonoidOp[HK[_], A] {
    val F: HigherKindedMonoid[HK]
    val value: HK[A]
    def |+|(a2: HK[A]) = F.mappend(value, a2)
  }

  implicit def toMonoidOp[A: Monoid](a: A): MonoidOp[A] = new MonoidOp[A] {
    val F = implicitly[Monoid[A]]
    val value = a
  }
  
  implicit def toHKMonoidOp[A, HK[_]: HigherKindedMonoid](a: HK[A]): HKMonoidOp[HK, A] = new HKMonoidOp[HK, A] {
    val F = implicitly[HigherKindedMonoid[HK]]
    val value = a
  }
}